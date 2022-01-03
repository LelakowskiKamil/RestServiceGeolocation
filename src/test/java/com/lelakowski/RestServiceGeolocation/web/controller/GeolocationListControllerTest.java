package com.lelakowski.RestServiceGeolocation.web.controller;

import com.google.gson.Gson;
import com.lelakowski.RestServiceGeolocation.RestServiceGeolocationApplication;
import com.lelakowski.RestServiceGeolocation.domain.model.Geolocation;
import com.lelakowski.RestServiceGeolocation.domain.repository.GeolocationRepository;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class GeolocationListControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @Autowired
    private GeolocationService geolocationService;

    @MockBean
    private GeolocationRepository geolocationRepository;

    String baseEndpoint = "/geolocations";


    @DisplayName("- should return status 200 when it request is correct")
    @Test
    void test1() throws Exception {
        Geolocation geolocation1 = new Geolocation(1L, 123L, 123L, 123L);

        Mockito.when(geolocationRepository.findAll()).thenReturn(List.of(geolocation1));
        mockMvc.perform(MockMvcRequestBuilders
                        .get(baseEndpoint+"/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("- should return status 200 when you request locations for a given device")
    @Test
    void test2() throws Exception {
        Geolocation geolocation1 = new Geolocation(1L, 123L, 123L, 123L);
        Geolocation geolocation2 = new Geolocation(2L, 123L, 234L, 234L);
        List<Geolocation> expectedValues = Arrays.asList(geolocation1, geolocation2);
        Mockito.when(geolocationRepository.findGeolocationsByDeviceId(123L)).thenReturn(expectedValues);

        mockMvc.perform(MockMvcRequestBuilders.get(baseEndpoint + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}