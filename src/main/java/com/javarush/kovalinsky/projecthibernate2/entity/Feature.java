package com.javarush.kovalinsky.projecthibernate2.entity;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum Feature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETEDSCENES("Deleted Scenes"),
    BEHINDTHESCENES("Behind the Scenes");

    private final String value;

    Feature(String value) {
        this.value = value;
    }

    public static Feature getFeatureByValue(String value) {
        if (Objects.isNull(value) || value.isEmpty()) {
            return null;
        }

        Feature[] features = Feature.values();
        for (Feature feature : features) {
            if (feature.value.equals(value)) {
                return feature;
            }
        }
        return null;
    }
}
