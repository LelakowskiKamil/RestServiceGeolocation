package com.lelakowski.RestServiceGeolocation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@ToString
public class GeolocationRequest {

    @NotNull
    Long deviceId;
    @NotNull
    Long latitiude;
    @NotNull
    Long longitude;

}
