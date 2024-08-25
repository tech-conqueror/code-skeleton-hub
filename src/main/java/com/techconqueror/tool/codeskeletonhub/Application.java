package com.techconqueror.tool.codeskeletonhub;

import com.techconqueror.tool.codeskeletonhub.entity.UserEntity;
import com.techconqueror.tool.codeskeletonhub.java.Algorithm;
import com.techconqueror.tool.codeskeletonhub.repository.UserEntityRepository;
import com.techconqueror.tool.codeskeletonhub.service.ChatCompletionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

//@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);

        System.out.println(Algorithm.isBeanArray2(new int[]{3, 4, 5, 7}));
    }

    @Bean
    public CommandLineRunner init(final UserEntityRepository userEntityRepository, final ChatCompletionService chatCompletionService) {
        return args -> {
            IntStream.rangeClosed(1, 3)
                    .mapToObj(i -> new UserEntity("name" + i, "email" + i + "@example.com"))
                    .forEach(userEntityRepository::save);
        };
    }
}
