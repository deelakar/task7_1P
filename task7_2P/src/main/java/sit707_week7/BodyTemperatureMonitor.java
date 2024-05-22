package sit707_week7;

public class BodyTemperatureMonitor {
    // Dependencies
    private TemperatureSensor temperatureSensor;
    private NotificationSender notificationSender;
    private CloudService cloudService;

    // Assume a fixed user where this mobile application is running.
    private Customer fixedCustomer = new Customer();
    
    // Assume a pre-configured family doctor instance.
    private FamilyDoctor familyDoctor = new FamilyDoctor();

    // Constructor accepting dependencies
    public BodyTemperatureMonitor(TemperatureSensor temperatureSensor, CloudService cloudService, NotificationSender notificationSender) {
        this.temperatureSensor = temperatureSensor;
        this.cloudService = cloudService;
        this.notificationSender = notificationSender;
    }

    // Get the temperature reading from the sensor
    public double readTemperature() {
        if (temperatureSensor == null) {
            throw new IllegalArgumentException("TemperatureSensor not set");
        }
        return temperatureSensor.readTemperatureValue();
    }

    // Report temperature reading to the cloud service
    public void reportTemperatureReadingToCloud(TemperatureReading temperatureReading) {
        if (cloudService == null) {
            throw new IllegalArgumentException("CloudService not set");
        }
        cloudService.sendTemperatureToCloud(temperatureReading);
    }

    // Inquire body status and send notification accordingly
    public void inquireBodyStatus() {
        if (cloudService == null || notificationSender == null) {
            throw new IllegalArgumentException("Dependencies not set");
        }

        String status = cloudService.queryCustomerBodyStatus(fixedCustomer);
        if (status.equalsIgnoreCase("NORMAL")) {
            notificationSender.sendEmailNotification(fixedCustomer, "Thumbs Up!");
        } else if (status.equalsIgnoreCase("ABNORMAL")) {
            notificationSender.sendEmailNotification(familyDoctor, "Emergency!");
        } else {
            throw new IllegalArgumentException("Unknown body status");
        }
    }

    // Getters for fixed customer and family doctor
    public Customer getFixedCustomer() {
        return fixedCustomer;
    }

    public FamilyDoctor getFamilyDoctor() {
        return familyDoctor;
    }

    // Setters for dependencies (used in testing)
    public void setTemperatureSensor(TemperatureSensor sensor) {
        this.temperatureSensor = sensor;
    }

    public void setCloudService(CloudService service) {
        this.cloudService = service;
    }

    public void setNotificationSender(NotificationSender sender) {
        this.notificationSender = sender;
    }

    // New getters for testing
    public TemperatureSensor getTemperatureSensor() {
        return temperatureSensor;
    }

    public CloudService getCloudService() {
        return cloudService;
    }

    public NotificationSender getNotificationSender() {
        return notificationSender;
    }
}
