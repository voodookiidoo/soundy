package com.soundy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Soundy api", version = "2.0", description = "api для нашего русского спотифаюшки"))
@EnableWebMvc
public class SoundyApplication {
    public static void main(String... args) {
        SpringApplication.run(SoundyApplication.class, args);
    }

}
