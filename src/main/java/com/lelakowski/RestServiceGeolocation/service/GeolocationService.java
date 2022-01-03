package com.lelakowski.RestServiceGeolocation.service;

import com.lelakowski.RestServiceGeolocation.domain.model.Geolocation;
import com.lelakowski.RestServiceGeolocation.request.GeolocationRequest;

import java.util.List;

public interface GeolocationService {

    Long saveGeolocation(GeolocationRequest request);

    List<Geolocation> getGeolocationsForDevice(Long deviceId);

    List<Geolocation> getGeolocations();

    long removeGeolocationsForDevice(Long deviceId);

}
