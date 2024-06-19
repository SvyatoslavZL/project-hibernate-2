package com.javarush.kovalinsky.projecthibernate2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = "picture")
@Entity
@Table(schema = "movie", name = "staff")
public class Staff {
    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "picture", columnDefinition = "BLOB")
    @Lob
    private byte[] picture;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "active", columnDefinition = "BIT default 1")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isActive;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
