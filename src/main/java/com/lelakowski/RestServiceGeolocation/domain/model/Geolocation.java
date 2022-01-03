package com.lelakowski.RestServiceGeolocation.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Geolocation")
@ToString
public class Geolocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "deviceId", nullable = false)
    Long deviceId;
    @Column(name = "latitiude", nullable = false)
    Long latitiude;
    @Column(name = "longitude", nullable = false)
    Long longitude;


}
