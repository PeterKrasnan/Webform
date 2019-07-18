package com.peterkrasnan.example.webform.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum RequestType {

    CONTRACT_ADJUSTMENT("Contract Adjustment"),
    DAMAGE_CASE("Damage Case"),
    COMPLAINT("Complaint");

    private String value;

    RequestType(String value) {
        this.value = value;
    }

    @JsonValue
    final String value() {
        return this.value;
    }

    @JsonCreator
    public static RequestType forValue(String value) {
        return Arrays.stream(values())
                .filter(requestType -> requestType.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Unknown enum type: " + value + ". Allowed values are " + Arrays.toString(values())));
    }

    public static List<String> getAllRequestsToString() {
        return Arrays.stream(values()).map(RequestType::toString).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return value;
    }
}
