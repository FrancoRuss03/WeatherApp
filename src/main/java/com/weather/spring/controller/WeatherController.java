package com.weather.spring.controller;

import com.weather.spring.model.WeatherData;
import com.weather.spring.service.WeatherService;
import com.weather.spring.service.OpenMeteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;
    
    @Autowired
    private OpenMeteoService openMeteoService;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("success", false);
        model.addAttribute("bgClass", "bg-default");
        return "weather";
    }
    
    @PostMapping("/weather")
    public String getWeather(@RequestParam("city") String city, Model model) {
        try {
            WeatherData weather = weatherService.getWeatherByCity(city);
            
            model.addAttribute("weather", weather);
            model.addAttribute("success", true);
            model.addAttribute("error", null);
            
            // Aggiungi la classe dello sfondo in base alla temperatura
            String bgClass = getBackgroundClass(weather.getTemperature());
            model.addAttribute("bgClass", bgClass);
            
            // Aggiungi la classe dell'icona
            String iconClass = getIconClass(weather.getTemperature());
            model.addAttribute("iconClass", iconClass);
            
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("success", false);
            model.addAttribute("error", e.getMessage());
            model.addAttribute("bgClass", "bg-default");
            model.addAttribute("iconClass", "icon-mild");
        }
        return "weather";
    }
    
    private String getBackgroundClass(double temperature) {
        if (temperature <= -15) return "bg-polar";
        if (temperature <= -10) return "bg-arctic";
        if (temperature <= -5) return "bg-mountain-snow";
        if (temperature <= 0) return "bg-winter";
        if (temperature <= 5) return "bg-melting";
        if (temperature <= 10) return "bg-spring-early";
        if (temperature <= 13) return "bg-apple-blossom";
        if (temperature <= 16) return "bg-flowers";
        if (temperature <= 19) return "bg-hills";
        if (temperature <= 22) return "bg-forest-lake";
        if (temperature <= 25) return "bg-sunflowers";
        if (temperature <= 28) return "bg-beach";
        if (temperature <= 32) return "bg-mediterranean";
        if (temperature <= 35) return "bg-desert";
        return "bg-volcano";
    }
    
    private String getIconClass(double temperature) {
        if (temperature < 0) return "icon-cold";
        if (temperature < 10) return "icon-cool";
        if (temperature < 20) return "icon-mild";
        if (temperature < 30) return "icon-warm";
        return "icon-hot";
    }
}