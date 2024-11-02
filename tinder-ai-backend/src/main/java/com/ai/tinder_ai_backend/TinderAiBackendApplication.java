package com.ai.tinder_ai_backend;

import com.ai.tinder_ai_backend.conversations.ChatMessages;
import com.ai.tinder_ai_backend.conversations.Conversation;
import com.ai.tinder_ai_backend.conversations.ConversationRepository;
import com.ai.tinder_ai_backend.profiles.Gender;
import com.ai.tinder_ai_backend.profiles.Profile;
import com.ai.tinder_ai_backend.profiles.ProfileRepository;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
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

//	Open AI Chat client Injected
	@Autowired
	private OpenAiChatModel chatModel;

    public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);
	}

//	this run method run when Application starts that is Hard coded
	public void run(String... args) throws Exception {
		System.err.println("My Tinder_AI_App is up and Running");

		Prompt prompt = new Prompt("Who is Elon Musk");
		ChatResponse response = chatModel.call(prompt);
		System.out.println(response.getResult().getOutput());

//		String response = chatModel.call("Who is Narendra Modi");
//		System.out.println(response);


		profileRepository.deleteAll();
		conversationRepository.deleteAll();

		Profile profile = new Profile(
				"1",
				"Chaman",
				"Lal",
				39,
				Gender.MALE,
				"Indian",
				"Software Engineer",
				"foo.jpg",
				"INTP"
		);
		profileRepository.save(profile);
		 profile = new Profile(
				"2",
				"Mangoo",
				"Moti",
				44,
				Gender.FEMALE,
				"Nepali",
				"QA",
				"laml.jpg",
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
