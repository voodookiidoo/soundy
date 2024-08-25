package com.soundy;

import org.springframework.boot.SpringApplication;

public class TestSoundyApplication {

	public static void main(String[] args) {
		SpringApplication.from(SoundyApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
