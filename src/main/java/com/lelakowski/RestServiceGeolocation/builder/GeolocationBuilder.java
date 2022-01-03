package com.lelakowski.RestServiceGeolocation.builder;

import com.lelakowski.RestServiceGeolocation.domain.model.Geolocation;
import com.lelakowski.RestServiceGeolocation.request.GeolocationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeolocationBuilder {

    public Geolocation from(GeolocationRequest request) {
        return Geolocation.builder()
                .deviceId(request.getDeviceId())
                .latitiude(request.getLatitiude())
                .longitude(request.getLongitude())
                .build();
    }

}
