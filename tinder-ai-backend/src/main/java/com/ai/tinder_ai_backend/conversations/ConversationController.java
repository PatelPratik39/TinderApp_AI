package com.ai.tinder_ai_backend.conversations;

import com.ai.tinder_ai_backend.profiles.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
public class ConversationController {

    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;

    public ConversationController(ConversationRepository conversationRepository, ProfileRepository profileRepository) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
    }

    @PostMapping("/conversations")
    public Conversation createConversation(@RequestBody CreateConversationRequest request) {
        profileRepository.findById(request.profileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Unable to find conversation Id : "+ request.profileId));

        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                request.profileId(),
                new ArrayList<>()
        );
        conversationRepository.save(conversation);
        return conversation;
    }

//    @CrossOrigin(origins = "*")
    @GetMapping("/conversations/{conversationId}")
    public Conversation getConversationById(@PathVariable String conversationId) {
       return conversationRepository.findById(conversationId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Unable to find conversation Id : "+ conversationId));
    }

    @PostMapping("/conversations/{conversationId}")
    public Conversation addMessageToConversation(@PathVariable String conversationId, @RequestBody ChatMessages chatMessages) {
        Conversation conversation = conversationRepository.findById(conversationId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Unable to find conversation Id : "+ conversationId));
        profileRepository.findById(chatMessages.authorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Unable to find conversation Id : "+ chatMessages.authorId()));
//        TODO --------
        ChatMessages messageWithTime = new ChatMessages(chatMessages.messageText(),chatMessages.authorId(), LocalDateTime.now());
        conversation.messages().add(chatMessages);
        conversationRepository.save(conversation);

        return conversation;
    }


    public record CreateConversationRequest(
            String profileId
    ){ }
}
