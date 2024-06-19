package com.javarush.kovalinsky.projecthibernate2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(schema = "movie", name = "film_text")
public class FilmText {
    @Id
    @Column(name = "film_id")
    private Short id;

    @OneToOne
    @JoinColumn(name = "film_id")
    @MapsId
    private Film film;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    @Type(type = "text")
    private String description;
}
