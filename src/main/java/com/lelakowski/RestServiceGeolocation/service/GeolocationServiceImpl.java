package com.lelakowski.RestServiceGeolocation.service;

import com.lelakowski.RestServiceGeolocation.builder.GeolocationBuilder;
import com.lelakowski.RestServiceGeolocation.domain.model.Geolocation;
import com.lelakowski.RestServiceGeolocation.domain.repository.GeolocationRepository;
import com.lelakowski.RestServiceGeolocation.request.GeolocationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class GeolocationServiceImpl implements GeolocationService {

    private final GeolocationRepository geolocationRepository;
    private final GeolocationBuilder geolocationBuilder;

    @Override
    public Long saveGeolocation(GeolocationRequest request) {
        Geolocation geolocationToSave = geolocationBuilder.from(request);
        Geolocation geolocation = geolocationRepository.save(geolocationToSave);
        return geolocation.getId();
    }

    @Override
    public List<Geolocation> getGeolocationsForDevice(Long deviceId) {
        return geolocationRepository.findGeolocationsByDeviceId(deviceId);
    }

    @Override
    public List<Geolocation> getGeolocations() {
        return geolocationRepository.findAll();
    }

    @Override
    @Transactional
    public long removeGeolocationsForDevice(Long deviceId) {
        List<Geolocation> geolocationsForDevice = geolocationRepository.findGeolocationsByDeviceId(deviceId);
        if (geolocationsForDevice.isEmpty()) {
            log.debug("Geolocations not found for the device: " + deviceId);
            return 0L;
        } else {
            long geolocationsCount = geolocationsForDevice.size();
            geolocationRepository.deleteAll(geolocationsForDevice);
            return geolocationsCount;
        }
    }
}
