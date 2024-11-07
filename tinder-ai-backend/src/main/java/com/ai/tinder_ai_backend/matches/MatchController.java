package com.ai.tinder_ai_backend.matches;

import com.ai.tinder_ai_backend.conversations.Conversation;

import com.ai.tinder_ai_backend.conversations.ConversationRepository;
import com.ai.tinder_ai_backend.profiles.Profile;
import com.ai.tinder_ai_backend.profiles.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class MatchController {

    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;
    private final MatchRepository matchRepository;

    public MatchController(ConversationRepository conversationRepository, ProfileRepository profileRepository, MatchRepository matchRepository) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
        this.matchRepository = matchRepository;
    }

    public record  CreateMatchRequest(String profileId) {}


    @PostMapping("/matches")
    public Match createMatch(@RequestBody CreateMatchRequest request) {
       Profile profile =  profileRepository.findById(request.profileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Unable to find conversation Id : "+ request.profileId));

       //TODO :make sure not existing conversation present

        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                request.profileId(),
                new ArrayList<>()
        );
        conversationRepository.save(conversation);
        Match match =  new Match(
                UUID.randomUUID().toString(),
                profile,
                conversation.id());
        matchRepository.save(match);
        return match;
    }

    @GetMapping("/matches")
    public List<Match> getAllMatches(){
        return matchRepository.findAll();
    }
}
