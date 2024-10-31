package com.ai.tinder_ai_backend.profiles;

public record Profile(
        String id,
        String firstName,
        String lastName,
        int age,
        Gender gender,
        String ethnicity,
        String bio,
        String imageUrl,
        String myersBriggsPersonalityType
) {

}
