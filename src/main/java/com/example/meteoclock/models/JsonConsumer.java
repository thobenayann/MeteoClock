package com.example.meteoclock.models;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.Locale;

public class JsonConsumer {

    private String urlApi = "https://api.openweathermap.org/data/2.5/weather";
    private String appId = "82b97d58a63264fde1d719885d474532";
    private String imageUrl = "http://openweathermap.org/img/w/";

    private String units = "metric";
    private String lang = "fr";

    private String ville;
    private String temps;
    private String temperature;
    private String icon;

    private String error;

    public void sendRequest(String search) throws UnirestException {
        search = search.toLowerCase(Locale.ROOT);

        HttpResponse<JsonNode> response =
                Unirest.get(urlApi)
                        .header("accept", "application/json")
                        .queryString("q", search)
                        .queryString("appid", appId)
                        .queryString("units", units)
                        .queryString("lang", lang)
                        .asJson();

        if (response.getStatus() != 200) {
            throw new UnirestException("Une erreur est survenue");
        } else {
            System.out.println(response.getBody());
            JSONObject obj = response.getBody().getObject();
            ville = obj.getString("name");
            JSONObject tmp= obj.getJSONArray("weather").getJSONObject(0);
            temps = tmp.getString("description");
            icon = tmp.getString("icon");
            temperature = Double.toString(obj.getJSONObject("main").getDouble("temp"));
        }
    }

    public String getVille() {
        return ville;
    }

    public String getTemps() {
        return temps;
    }

    public String getTemperature() {
        return temperature+ " °C";
    }

    public String getIcon() {
        return imageUrl + icon + ".png";
    }

    public String getError() {
        return error;
    }
}
