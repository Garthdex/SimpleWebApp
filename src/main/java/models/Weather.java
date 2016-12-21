package models;

public class Weather {
    private int weatherId;
    private String city;
    private String temperature;

    public Weather() {
    }

    public Weather(int weatherId, String city, String temperature) {
        this.weatherId = weatherId;
        this.city = city;
        this.temperature = temperature;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
