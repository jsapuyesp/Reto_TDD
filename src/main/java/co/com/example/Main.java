package co.com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
        SpringApplication.run(Main.class, args);
    }
}