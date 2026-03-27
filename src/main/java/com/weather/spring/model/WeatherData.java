package com.weather.spring.model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class WeatherData {
    // Dati attuali
    private String city;
    private double temperature;
    private double feelsLike;
    private int humidity;
    private String weatherDescription;
    private double windSpeed;
    
    // Previsioni giornaliere
    private List<DailyForecast> dailyForecasts;
    
    public WeatherData() {
        this.dailyForecasts = new ArrayList<>();
    }
    
    public WeatherData(String city, double temperature, double feelsLike, 
                       int humidity, String weatherDescription, double windSpeed) {
        this.city = city;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.weatherDescription = weatherDescription;
        this.windSpeed = windSpeed;
        this.dailyForecasts = new ArrayList<>();
    }
    
    // Getter e Setter esistenti
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    
    public double getFeelsLike() { return feelsLike; }
    public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }
    
    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }
    
    public String getWeatherDescription() { return weatherDescription; }
    public void setWeatherDescription(String weatherDescription) { this.weatherDescription = weatherDescription; }
    
    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }
    
    public List<DailyForecast> getDailyForecasts() { return dailyForecasts; }
    public void setDailyForecasts(List<DailyForecast> dailyForecasts) { this.dailyForecasts = dailyForecasts; }
    
    public void addDailyForecast(DailyForecast forecast) {
        this.dailyForecasts.add(forecast);
    }
    
    /**
     * Classe interna per le previsioni giornaliere
     */
    public static class DailyForecast {
        private LocalDate date;
        private double maxTemperature;
        private double minTemperature;
        private String weatherDescription;
        private int humidity;
        private double windSpeed;
        private double precipitationProbability;
        
        public DailyForecast() {}
        
        public DailyForecast(LocalDate date, double maxTemperature, double minTemperature, 
                            String weatherDescription, int humidity, double windSpeed, 
                            double precipitationProbability) {
            this.date = date;
            this.maxTemperature = maxTemperature;
            this.minTemperature = minTemperature;
            this.weatherDescription = weatherDescription;
            this.humidity = humidity;
            this.windSpeed = windSpeed;
            this.precipitationProbability = precipitationProbability;
        }
        
        // Getter e Setter
        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }
        
        public double getMaxTemperature() { return maxTemperature; }
        public void setMaxTemperature(double maxTemperature) { this.maxTemperature = maxTemperature; }
        
        public double getMinTemperature() { return minTemperature; }
        public void setMinTemperature(double minTemperature) { this.minTemperature = minTemperature; }
        
        public String getWeatherDescription() { return weatherDescription; }
        public void setWeatherDescription(String weatherDescription) { this.weatherDescription = weatherDescription; }
        
        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }
        
        public double getWindSpeed() { return windSpeed; }
        public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }
        
        public double getPrecipitationProbability() { return precipitationProbability; }
        public void setPrecipitationProbability(double precipitationProbability) { 
            this.precipitationProbability = precipitationProbability; 
        }
        
        public String getFormattedDate() {
            return date.toString();
        }
        
        public String getDayOfWeek() {
            String[] days = {"Domenica", "Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato"};
            return days[date.getDayOfWeek().getValue() % 7];
        }
    }
}