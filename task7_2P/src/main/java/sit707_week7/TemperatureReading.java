package sit707_week7;

public class TemperatureReading {
    
    // Customer object
    private Customer customer;
    
    // Reading time string format hh:mm:ss
    private String readingTime = "00:00:00";  // Default value
    
    // Body temperature
    private double bodyTemperature;
    
    // Constructor that takes a single double for body temperature
    public TemperatureReading(double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }
    
    // Getters for the fields
    public Customer getCustomer() {
        return customer;
    }
    
    public String getReadingTime() {
        return readingTime;
    }
    
    public double getBodyTemperature() {
        return bodyTemperature;
    }
    
    // Setters for the fields
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }
    
    public void setBodyTemperature(double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }
}
