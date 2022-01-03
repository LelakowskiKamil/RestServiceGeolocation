package com.lelakowski.RestServiceGeolocation.web.controller;

import com.lelakowski.RestServiceGeolocation.RestServiceGeolocationApplicationTests;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RemoveGeolocationForDeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GeolocationService geolocationService;

    @MockBean
    private GeolocationRepository geolocationRepository;

    String endpoint = "/geolocations/remove/11";


    @DisplayName("- should return status 404 when it does not find a geolocation with the given deviceId")
    @Test
    void test1() throws Exception {
        Mockito.when(geolocationRepository.findGeolocationsByDeviceId(anyLong())).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(endpoint)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @DisplayName("- should return status 200 when it removes the existing geolocations")
    @Test
    void test2() throws Exception {
        Geolocation geolocation1 = Mockito.mock(Geolocation.class);
        Geolocation geolocation2 = Mockito.mock(Geolocation.class);

        Mockito.when(geolocationRepository.findGeolocationsByDeviceId(anyLong())).thenReturn(Arrays.asList(geolocation1,geolocation2));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(endpoint)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}