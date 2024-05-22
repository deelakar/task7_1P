package sit707_week7;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


public class BodyTemperatureMonitorTest {

	@Test
	public void testStudentIdentity() {
		String studentId = "S223693522";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Deelaka Rathnayake";
		Assert.assertNotNull("Student name is null", studentName);
	}
	
	@Test
    public void testReadTemperatureNegative() {
        TemperatureSensor temperatureSensor = Mockito.mock(TemperatureSensor.class);
        Mockito.when(temperatureSensor.readTemperatureValue()).thenReturn(-5.0);

        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(temperatureSensor, null, null);

        double temperature = monitor.readTemperature();
        System.out.println("Negative temperature reading: " + temperature);
        Assert.assertEquals(-5.0, temperature, 0);
    }

    @Test
    public void testReadTemperatureZero() {
        TemperatureSensor temperatureSensor = Mockito.mock(TemperatureSensor.class);
        Mockito.when(temperatureSensor.readTemperatureValue()).thenReturn(0.0);

        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(temperatureSensor, null, null);

        double temperature = monitor.readTemperature();
        System.out.println("Zero temperature reading: " + temperature);
        Assert.assertEquals(0.0, temperature, 0);
    }

    @Test
    public void testReadTemperatureNormal() {
        TemperatureSensor temperatureSensor = Mockito.mock(TemperatureSensor.class);
        Mockito.when(temperatureSensor.readTemperatureValue()).thenReturn(36.5);

        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(temperatureSensor, null, null);

        double temperature = monitor.readTemperature();
        System.out.println("Normal temperature reading: " + temperature);
        Assert.assertEquals(36.5, temperature, 0);
    }

    @Test
    public void testReadTemperatureAbnormallyHigh() {
        TemperatureSensor temperatureSensor = Mockito.mock(TemperatureSensor.class);
        Mockito.when(temperatureSensor.readTemperatureValue()).thenReturn(42.0);

        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(temperatureSensor, null, null);

        double temperature = monitor.readTemperature();
        System.out.println("Abnormally high temperature reading: " + temperature);
        Assert.assertEquals(42.0, temperature, 0);
    }

    @Test
    public void testReportTemperatureReadingToCloud() {
        CloudService cloudService = Mockito.mock(CloudService.class);
        TemperatureReading reading = new TemperatureReading(36.5);  // Corrected

        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(null, cloudService, null);
        monitor.reportTemperatureReadingToCloud(reading);

        // Verify if the mocked cloud service received the correct temperature reading
        Mockito.verify(cloudService).sendTemperatureToCloud(reading);
    }

    @Test
    public void testInquireBodyStatusNormalNotification() {
        CloudService cloudService = Mockito.mock(CloudService.class);
        NotificationSender notificationSender = Mockito.mock(NotificationSender.class);

        Mockito.when(cloudService.queryCustomerBodyStatus(Mockito.any(Customer.class)))
            .thenReturn("NORMAL");

        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(null, cloudService, notificationSender);
        monitor.inquireBodyStatus();

        Mockito.verify(notificationSender).sendEmailNotification(Mockito.any(Customer.class), Mockito.eq("Thumbs Up!"));
    }

    @Test
    public void testInquireBodyStatusAbnormalNotification() {
        CloudService cloudService = Mockito.mock(CloudService.class);
        NotificationSender notificationSender = Mockito.mock(NotificationSender.class);

        Mockito.when(cloudService.queryCustomerBodyStatus(Mockito.any(Customer.class)))
            .thenReturn("ABNORMAL");

        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(null, cloudService, notificationSender);
        monitor.inquireBodyStatus();

        Mockito.verify(notificationSender).sendEmailNotification(Mockito.any(FamilyDoctor.class), Mockito.eq("Emergency!"));
    }
    
    @Test
    public void testGetFixedCustomer() {
        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(
            Mockito.mock(TemperatureSensor.class),
            Mockito.mock(CloudService.class),
            Mockito.mock(NotificationSender.class)
        );

        Customer customer = monitor.getFixedCustomer();
        Assert.assertNotNull("Fixed customer should not be null", customer);
    }

    @Test
    public void testGetFamilyDoctor() {
        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(
            Mockito.mock(TemperatureSensor.class),
            Mockito.mock(CloudService.class),
            Mockito.mock(NotificationSender.class)
        );

        FamilyDoctor doctor = monitor.getFamilyDoctor();
        Assert.assertNotNull("Family doctor should not be null", doctor);
    }
    
    @Test
    public void testSetAndGetTemperatureSensor() {
        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(
            Mockito.mock(TemperatureSensor.class),
            Mockito.mock(CloudService.class),
            Mockito.mock(NotificationSender.class)
        );

        TemperatureSensor newSensor = Mockito.mock(TemperatureSensor.class);
        monitor.setTemperatureSensor(newSensor);
        Assert.assertEquals("Temperature sensor should be the same as set", newSensor, monitor.getTemperatureSensor());
    }

    @Test
    public void testSetAndGetCloudService() {
        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(
            Mockito.mock(TemperatureSensor.class),
            Mockito.mock(CloudService.class),
            Mockito.mock(NotificationSender.class)
        );

        CloudService newService = Mockito.mock(CloudService.class);
        monitor.setCloudService(newService);
        Assert.assertEquals("Cloud service should be the same as set", newService, monitor.getCloudService());
    }

    @Test
    public void testSetAndGetNotificationSender() {
        BodyTemperatureMonitor monitor = new BodyTemperatureMonitor(
            Mockito.mock(TemperatureSensor.class),
            Mockito.mock(CloudService.class),
            Mockito.mock(NotificationSender.class)
        );

        NotificationSender newSender = Mockito.mock(NotificationSender.class);
        monitor.setNotificationSender(newSender);
        Assert.assertEquals("Notification sender should be the same as set", newSender, monitor.getNotificationSender());
    }

}
