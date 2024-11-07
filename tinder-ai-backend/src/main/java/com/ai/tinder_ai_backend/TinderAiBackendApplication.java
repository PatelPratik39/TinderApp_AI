package com.ai.tinder_ai_backend;

import com.ai.tinder_ai_backend.profiles.ProfileCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileCreationService profileCreationService;

    public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);
		System.err.println("Tinder AI App is up and Running");
	}

	public void run(String... args) {
		profileCreationService.createProfiles(0);
		profileCreationService.saveProfilesToDB();
	}

}
