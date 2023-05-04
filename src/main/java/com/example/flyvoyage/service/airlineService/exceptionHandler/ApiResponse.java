package com.example.flyvoyage.service.airlineService.exceptionHandler;

import lombok.*;

import java.time.ZonedDateTime;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
public class ApiResponse {
    private ZonedDateTime timeStamp;
    private int statusCode;
    private String path;
    private  Object data;
    private Boolean isSuccessful;
}
