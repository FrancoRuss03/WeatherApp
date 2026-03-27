package com.weather.spring.service;

import com.weather.spring.model.WeatherData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class OpenMeteoService implements WeatherService {
    
    private static final Logger logger = LoggerFactory.getLogger(OpenMeteoService.class);
    
    @Override
    @Cacheable(value = "weatherCache", key = "#city.toLowerCase()", unless = "#result == null")
    public WeatherData getWeatherByCity(String city) throws Exception {
        logger.info("🌐 Cache MISS - Chiamata API per: {}", city);
        
        // Step 1: Geocoding per ottenere coordinate
        String geoUrl = String.format(
            "https://geocoding-api.open-meteo.com/v1/search?name=%s&count=1&language=it&format=json",
            URLEncoder.encode(city, "UTF-8")
        );
        
        String geoResponse = callApi(geoUrl);
        JSONObject geoJson = new JSONObject(geoResponse);
        
        if (!geoJson.has("results")) {
            throw new Exception("Città non trovata: " + city);
        }
        
        JSONArray results = geoJson.getJSONArray("results");
        if (results.isEmpty()) {
            throw new Exception("Nessun risultato per: " + city);
        }
        
        JSONObject location = results.getJSONObject(0);
        double latitude = location.getDouble("latitude");
        double longitude = location.getDouble("longitude");
        String cityName = location.getString("name");
        
        logger.info("📍 Coordinate trovate: {}, {}", latitude, longitude);
        
        // Step 2: Chiamata API meteo con previsioni giornaliere
        // Usiamo i parametri corretti per le previsioni giornaliere
        String weatherUrl = String.format(Locale.US,
            "https://api.open-meteo.com/v1/forecast?" +
            "latitude=%.6f&longitude=%.6f&" +
            "current_weather=true&" +
            "hourly=temperature_2m,relative_humidity_2m,apparent_temperature,windspeed_10m&" +
            "daily=weathercode,temperature_2m_max,temperature_2m_min,precipitation_probability_max&" +
            "timezone=auto&forecast_days=5",
            latitude, longitude
        );
        
        logger.info("🌐 URL chiamata meteo: {}", weatherUrl);
        String weatherResponse = callApi(weatherUrl);
        JSONObject weatherJson = new JSONObject(weatherResponse);
        
        // Dati attuali
        JSONObject currentWeather = weatherJson.getJSONObject("current_weather");
        double temperature = currentWeather.getDouble("temperature");
        double windSpeed = currentWeather.getDouble("windspeed");
        
        // Dati orari per umidità e temperatura percepita
        JSONObject hourly = weatherJson.getJSONObject("hourly");
        JSONArray humidityArray = hourly.getJSONArray("relative_humidity_2m");
        JSONArray feelsLikeArray = hourly.getJSONArray("apparent_temperature");
        
        int humidity = humidityArray.getInt(0);
        double feelsLike = feelsLikeArray.getDouble(0);
        
        String description = getWeatherDescription(temperature);
        
        WeatherData weatherData = new WeatherData(cityName, temperature, feelsLike, 
                                                   humidity, description, windSpeed);
        
        // Step 3: Previsioni giornaliere (5 giorni)
        if (weatherJson.has("daily")) {
            JSONObject daily = weatherJson.getJSONObject("daily");
            
            JSONArray timeArray = daily.getJSONArray("time");
            JSONArray maxTempArray = daily.getJSONArray("temperature_2m_max");
            JSONArray minTempArray = daily.getJSONArray("temperature_2m_min");
            JSONArray weatherCodeArray = daily.getJSONArray("weathercode");
            JSONArray precipProbArray = daily.getJSONArray("precipitation_probability_max");
            
            logger.info("📅 Numero di giorni di previsione: {}", timeArray.length());
            
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            
            for (int i = 0; i < timeArray.length() && i < 5; i++) {
                LocalDate date = LocalDate.parse(timeArray.getString(i), formatter);
                double maxTemp = maxTempArray.getDouble(i);
                double minTemp = minTempArray.getDouble(i);
                int weatherCode = weatherCodeArray.getInt(i);
                double precipProb = precipProbArray.getDouble(i);
                
                String weatherDesc = getWeatherDescriptionFromCode(weatherCode, maxTemp);
                
                WeatherData.DailyForecast forecast = new WeatherData.DailyForecast(
                    date, maxTemp, minTemp, weatherDesc, 
                    -1, // Umidità giornaliera non disponibile
                    0,  // Velocità vento non disponibile
                    precipProb
                );
                
                weatherData.addDailyForecast(forecast);
                
                logger.info("📅 Previsione giorno {}: {}/{}°C, {}",
                    i+1, minTemp, maxTemp, weatherDesc);
            }
        } else {
            logger.warn("⚠️ Nessun dato di previsione giornaliera ricevuto dall'API");
        }
        
        logger.info("✅ Dati meteo e previsioni recuperati per {}: {}°C", cityName, temperature);
        logger.info("📊 Numero previsioni: {}", weatherData.getDailyForecasts().size());
        
        return weatherData;
    }
    
    /**
     * Converte il codice meteo Open-Meteo in descrizione testuale
     */
    private String getWeatherDescriptionFromCode(int weatherCode, double temperature) {
        // Open-Meteo Weather Codes (WMO)
        switch (weatherCode) {
            case 0:
                return "Cielo sereno ☀️";
            case 1:
                return "Principalmente sereno 🌤️";
            case 2:
                return "Parzialmente nuvoloso ⛅";
            case 3:
                return "Nuvoloso ☁️";
            case 45:
            case 48:
                return "Nebbia 🌫️";
            case 51:
            case 53:
            case 55:
                return "Pioviggine 🌦️";
            case 56:
            case 57:
                return "Pioviggine gelata 🌨️";
            case 61:
            case 63:
            case 65:
                return "Pioggia 🌧️";
            case 66:
            case 67:
                return "Pioggia gelata 🧊";
            case 71:
            case 73:
            case 75:
                return "Neve ❄️";
            case 77:
                return "Neve granulosa 🌨️";
            case 80:
            case 81:
            case 82:
                return "Rovesci di pioggia ⛈️";
            case 85:
            case 86:
                return "Rovesci di neve 🌨️";
            case 95:
                return "Temporale ⚡";
            case 96:
            case 99:
                return "Temporale con grandine 🌩️";
            default:
                return getWeatherDescription(temperature);
        }
    }
    
    private String getWeatherDescription(double temperature) {
        if (temperature <= -10) return "Freddo polare ❄️";
        if (temperature <= 0) return "Gelido 🧊";
        if (temperature <= 5) return "Molto freddo 🧣";
        if (temperature <= 10) return "Freddo 🍂";
        if (temperature <= 15) return "Fresco 🌬️";
        if (temperature <= 20) return "Leggermente fresco 🌿";
        if (temperature <= 25) return "Mite 🌸";
        if (temperature <= 30) return "Caldo ☀️";
        if (temperature <= 35) return "Molto caldo 🔥";
        return "Torrido 🌋";
    }
    
    private String callApi(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            // Leggi l'errore per debug
            StringBuilder errorResponse = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    errorResponse.append(line);
                }
            } catch (Exception e) {
                // Ignora
            }
            throw new Exception("Errore API: " + responseCode + " - " + errorResponse.toString());
        }
        
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        
        conn.disconnect();
        return response.toString();
    }
    
    public String getWeatherIconUrl(double temperature) {
        if (temperature < 0) {
            return "https://cdn-icons-png.flaticon.com/512/3233/3233945.png";
        } else if (temperature < 10) {
            return "https://cdn-icons-png.flaticon.com/512/3233/3233943.png";
        } else if (temperature < 20) {
            return "https://cdn-icons-png.flaticon.com/512/3233/3233932.png";
        } else if (temperature < 30) {
            return "https://cdn-icons-png.flaticon.com/512/3233/3233936.png";
        } else {
            return "https://cdn-icons-png.flaticon.com/512/3233/3233937.png";
        }
    }
    
    /**
     * Metodo per pulire la cache manualmente
     */
    @org.springframework.cache.annotation.CacheEvict(value = "weatherCache", allEntries = true)
    public void clearAllCache() {
        logger.info("🗑️ Cache completamente svuotata manualmente");
    }
}