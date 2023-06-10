package com.logiceacards;

import com.logiceacards.entities.Role;
import com.logiceacards.entities.User;
import com.logiceacards.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class LogiceaCardManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogiceaCardManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> {
            userService.createUser(User.builder()
                    .role(Role.ADMIN).createdOn(new Date()).userId(1L)
                    .fullName("Logicea user 1").username("logiceaAdmin").password("logicea123").build());
            userService.createUser(User.builder().userId(2L)
                    .role(Role.USER).createdOn(new Date())
                    .fullName("Logicea user 1").username("logiceaUser").password("logicea123").build());
        };
    }

}
