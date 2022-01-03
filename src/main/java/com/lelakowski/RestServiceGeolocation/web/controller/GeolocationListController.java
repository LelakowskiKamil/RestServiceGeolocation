package com.lelakowski.RestServiceGeolocation.web.controller;

import com.lelakowski.RestServiceGeolocation.domain.model.Geolocation;
import com.lelakowski.RestServiceGeolocation.service.GeolocationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/geolocations")
@RequiredArgsConstructor
@Slf4j
public class GeolocationListController {

    private final GeolocationService geolocationService;

    @GetMapping("/{deviceId}")
    @ApiOperation(value = "Get list of geolocations for given deviceId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "deviceId", dataType = "Long", paramType = "path")
    })
    public ResponseEntity<List> showGeolocationsForDevice(@PathVariable Long deviceId) {
        List<Geolocation> geolocationsForDevice = geolocationService.getGeolocationsForDevice(deviceId);
        log.info("Geolocations for device: " + deviceId + ". " + geolocationsForDevice.toString());
        return new ResponseEntity<>(geolocationsForDevice, HttpStatus.OK);
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all geolocations")
    public ResponseEntity<List> showAllGeolocations() {
        return new ResponseEntity<>(geolocationService.getGeolocations(), HttpStatus.OK);
    }
}
