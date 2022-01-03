package com.lelakowski.RestServiceGeolocation.domain.repository;

import com.lelakowski.RestServiceGeolocation.domain.model.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeolocationRepository extends JpaRepository<Geolocation, Long> {

    @Query(value = "SELECT g FROM Geolocation g WHERE g.deviceId = ?1")
    List<Geolocation> findGeolocationsByDeviceId(Long id);

    @Query(value = "SELECT g FROM Geolocation g WHERE g.id = ?1")
    Geolocation getOne(Long id);

}