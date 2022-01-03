package com.lelakowski.RestServiceGeolocation.web.controller;

import com.lelakowski.RestServiceGeolocation.domain.model.Geolocation;
import com.lelakowski.RestServiceGeolocation.service.GeolocationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geolocations")
@RequiredArgsConstructor
@Slf4j
public class RemoveGeolocationForDeviceController {

    private final GeolocationService geolocationService;

    @DeleteMapping(path = "/remove/{deviceId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Remove geolocations with given deviceId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "deviceId", dataType = "Long", paramType = "path")
    })
    ResponseEntity<Geolocation> addNewGeolocation(@PathVariable Long deviceId) {
        log.info("Attempting to delete all locations for the device: " + deviceId);
        long result = geolocationService.removeGeolocationsForDevice(deviceId);
        log.info(result + " records have been deleted");
        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
