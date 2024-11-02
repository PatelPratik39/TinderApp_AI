package com.ai.tinder_ai_backend.matches;

import com.ai.tinder_ai_backend.profiles.Profile;

public record Match(String id, Profile profile, String conversationId) {

}
