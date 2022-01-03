package com.lelakowski.RestServiceGeolocation.web.controller;

import com.google.gson.Gson;
import com.lelakowski.RestServiceGeolocation.domain.model.Geolocation;
import com.lelakowski.RestServiceGeolocation.request.GeolocationRequest;
import com.lelakowski.RestServiceGeolocation.service.GeolocationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/geolocations")
@RequiredArgsConstructor
@Slf4j
public class AddGeolocationController {

    private final GeolocationService geolocationService;
    private final Gson gson;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new geolocation")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "deviceId", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "latitiude", value = "Latitiude", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "longitude", value = "Longitude", dataType = "Long", paramType = "query")
    })
    ResponseEntity<Geolocation> addNewGeolocation(@RequestBody @Valid GeolocationRequest request) {
        log.info("Add new geolocation. Post request: " + gson.toJson(request));
        geolocationService.saveGeolocation(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
