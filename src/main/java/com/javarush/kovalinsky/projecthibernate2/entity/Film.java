package com.javarush.kovalinsky.projecthibernate2.entity;

import com.javarush.kovalinsky.projecthibernate2.util.RatingConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = {"categories", "actors"})
@Entity
@Table(schema = "movie", name = "film")
public class Film {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    private Short year;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration")
    private Byte rentalDuration;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    @Column(name = "rating", columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Column(name = "special_features",
            columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    private Collection<Actor> actors;

    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private Collection<Category> categories;

    public Set<Feature> getSpecialFeatures() {
        if (Objects.isNull(specialFeatures) || specialFeatures.isEmpty()) {
            return null;
        }
        Set<Feature> result = new HashSet<>();
        String[] features = specialFeatures.split(",");
        for (String feature : features) {
            result.add(Feature.getFeatureByValue(feature));
        }
        result.remove(null);
        return result;
    }

    public void setSpecialFeatures(Set<Feature> specialFeatures) {
        if (Objects.isNull(specialFeatures)) {
            this.specialFeatures = null;
        } else {
            this.specialFeatures = specialFeatures.stream().map(Feature::getValue).collect(Collectors.joining(","));
        }
    }
}
