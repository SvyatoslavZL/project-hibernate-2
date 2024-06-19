package com.javarush.kovalinsky.projecthibernate2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(schema = "movie", name = "address")
public class Address {
    @Id
    @Column(name = "address_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Setter
    @Column(name = "address")
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "district")
    private String district;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
