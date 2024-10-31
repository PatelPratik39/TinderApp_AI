package com.ai.tinder_ai_backend;

import com.ai.tinder_ai_backend.conversations.ChatMessages;
import com.ai.tinder_ai_backend.conversations.Conversation;
import com.ai.tinder_ai_backend.conversations.ConversationRepository;
import com.ai.tinder_ai_backend.profiles.Gender;
import com.ai.tinder_ai_backend.profiles.Profile;
import com.ai.tinder_ai_backend.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ConversationRepository conversationRepository;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);
//		System.err.println("Tinder App is Up and Running");
	}

//	this run method run when Application starts that is Hard coded
	public void run(String... args) throws Exception {
		System.out.println("My Tinder_AI_App is up and Running");
		Profile profile = new Profile(
				"1",
				"Chaman",
				"Lal",
				39,
				Gender.MALE,
				"Indian",
				"Software Enginner",
				"foo.jpg",
				"INTP"
		);
		profileRepository.save(profile);
		profileRepository.findAll().forEach(System.out::println);

		Conversation conversation = new Conversation(
				"1",
				profile.id(),
				List.of(
						new ChatMessages("Hello", profile.id(), LocalDateTime.now())
				)
		);
		conversationRepository.save(conversation);   //saving the conversation to repository
		conversationRepository.findAll().forEach(System.out::println); //findAll conversation based on profileId
	}

}
