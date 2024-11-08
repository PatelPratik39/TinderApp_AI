package com.ai.tinder_ai_backend.matches;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MatchRepository extends MongoRepository<Match, String> {

    // Custom query method to find a match by profileId
    Optional<Match> findByProfileId(String profileId);

    // Custom method to delete a match by profileId
    void deleteByProfileId(String profileId);
}
