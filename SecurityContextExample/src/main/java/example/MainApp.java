package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MainApp {
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    /**
     * Допустим мы хотим получить объект класса Authentication
     * Это можно сделать следующим способом
     * 1. испоьзуя SecurityContextHolder получить SecurityContext
     * 2. из контекста получить Authentication путем вызова геттера
     * @return
     */
    @GetMapping("/hello")
    public String hello(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return "Hello, " + authentication.getName();
    }

    /**
     * Однако, спринг достаточно умен, чтобы просто заинджектить
     * экземпляр класса Authentication
     * @param auth
     * @return
     */
    @GetMapping("/smart")
    public String smartInject(Authentication auth){
        return "Hello, " + auth.getName();
    }
}
