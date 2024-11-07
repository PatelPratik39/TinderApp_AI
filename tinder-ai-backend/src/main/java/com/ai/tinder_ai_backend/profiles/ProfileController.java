package com.ai.tinder_ai_backend.profiles;

import com.ai.tinder_ai_backend.conversations.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private final ProfileRepository profileRepository;

    @Autowired
    private final ConversationRepository conversationRepository;

    public ProfileController(ProfileRepository profileRepository, ConversationRepository conversationRepository) {
        this.profileRepository = profileRepository;
        this.conversationRepository = conversationRepository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/profiles/random")
    public Profile getRandomProfile(){
        return profileRepository.getRandomProfile();
    }
}
