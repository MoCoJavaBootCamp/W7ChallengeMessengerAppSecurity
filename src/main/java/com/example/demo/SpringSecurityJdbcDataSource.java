package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityJdbcDataSource {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJdbcDataSource.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 MessageRepository messageRepository) throws Exception {
        return (String[] args) -> {
            User user = new User("bart", "bart@domain.com",
                    "bart", "Bart", "Simpson", true);
            Role userRole = new Role("bart", "ROLE_USER");
            Message message = new Message();
            Message message2 = new Message();
            message.setMessage("Hello world!");
            message.setUser(user);
            message2.setMessage("What are you doing?");
            message2.setUser(user);
            Set<Message> messageSet = new HashSet<>();
            messageSet.add(message);
            messageSet.add(message2);
            user.setMessages(messageSet);

            userRepository.save(user);
            roleRepository.save(userRole);

            User admin = new User("super", "super@domain.com",
                    "super", "Super", "Man", true);
            Role adminRole = new Role("super", "ROLE_ADMIN");
            Message message3 = new Message();
            message3.setMessage("My name is SUPER and I approve this message");
            message3.setUser(admin);
            Set<Message> messageSet2 = new HashSet<>();
            messageSet2.add(message3);
            admin.setMessages(messageSet2);


            messageRepository.save(message);
            messageRepository.save(message2);
            userRepository.save(admin);
            roleRepository.save(adminRole);

            messageRepository.save(message3);

        };
    }
}
