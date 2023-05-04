package com.example.flyvoyage.service.airlineService;

import com.example.flyvoyage.data.dto.request.FlightScheduleRequest;
import com.example.flyvoyage.data.dto.response.FlightScheduleResponse;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class FlightScheduleServiceImpl implements FlightScheduleService{

    private static final String accessToken = System.getenv("TRAVEL_KEY");
    private static final String rapidApiKey = System.getenv("RAPID_KEY");
    private String from = "https://travelpayouts-travelpayouts-flight-data-v1.p.rapidapi.com/v2/prices/nearest-places-matrix?";
    private String to = "&flexibility=0&currency=USD&show_to_affiliates=true&limit=10&distance=100";
    private static final String rapidApiHost = "travelpayouts-travelpayouts-flight-data-v1.p.rapidapi.com";


    public FlightScheduleResponse getFlightSchedule(FlightScheduleRequest searchRequest) throws IOException {
        String destinations = searchRequest.destination();
        String origins = searchRequest.origin();
        String format =  "origin="+origins+"&destination="+destinations;
        String fullFormURL = from + format + to;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(fullFormURL)
                .get()
                .addHeader("X-Access-Token", accessToken)
                .addHeader("X-RapidAPI-Key", rapidApiKey)
                .addHeader("X-RapidAPI-Host", rapidApiHost)
                .build();

        try (ResponseBody response = client.newCall(request).execute().body()) {
            Gson gson = new Gson();
            return gson.fromJson(response.string(), FlightScheduleResponse.class);
        }

    }


}