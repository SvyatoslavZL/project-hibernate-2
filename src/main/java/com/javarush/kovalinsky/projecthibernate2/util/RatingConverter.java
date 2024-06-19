package com.javarush.kovalinsky.projecthibernate2.util;

import com.javarush.kovalinsky.projecthibernate2.entity.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        return attribute.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        for (Rating rating : Rating.values()) {
            if (dbData.equals(rating.getValue())) {
                return rating;
            }
        }
        return null;
    }
}
