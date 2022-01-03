package com.lelakowski.RestServiceGeolocation.web.controller;

import com.google.gson.Gson;
import com.lelakowski.RestServiceGeolocation.RestServiceGeolocationApplication;
import com.lelakowski.RestServiceGeolocation.domain.model.Geolocation;
import com.lelakowski.RestServiceGeolocation.domain.repository.GeolocationRepository;
import com.lelakowski.RestServiceGeolocation.request.GeolocationRequest;
import com.lelakowski.RestServiceGeolocation.service.GeolocationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = RestServiceGeolocationApplication.class)
class AddGeolocationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @Autowired
    private GeolocationService geolocationService;

    @MockBean
    private GeolocationRepository geolocationRepository;

    String endpoint = "/geolocations/add";


    @DisplayName("- should return status 201 when it request is correct")
    @Test
    void test1() throws Exception {
        GeolocationRequest request = new GeolocationRequest(123L, 12345L, 23456L);
        Geolocation geolocation = new Geolocation(1L, 123L, 12345L, 23456L);
        String json = gson.toJson(request);

        Mockito.when(geolocationRepository.save(any(Geolocation.class))).thenReturn(geolocation);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @DisplayName("- should return status 404 when it removes the existing geolocations")
    @Test
    void test2() throws Exception {
        GeolocationRequest request = new GeolocationRequest(123L, null, 23456L);
        String json = gson.toJson(request);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}