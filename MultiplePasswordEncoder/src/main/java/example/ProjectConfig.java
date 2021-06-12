package example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * Этот класс пока лишь для ознакомления. 
 * К сожаления, его тестирование пока невозможно.
 * Предлагаю протестировать его в SimpleAuthenticationProvider
 */
@Configuration
public class ProjectConfig {
    /**
     * Для удобства, спринг секюрити предоставляет
     * [DelegatingPasswrodEncoder] который содержит
     * мапу на все стандартные шифровщики паролей. То есть,
     * за нас прописали руками encoders.put(***, ***) для всех
     * стандартных кодировщиков паролей. Чтобы получить данный класс,
     * нам нужно использовать фабрику PasswordEncodersFactories
     * PasswordEncoderFactories.createDelegatingPasswordEncoder()
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        // ключ - префикс, который передается в запросе {noop}fewihgviwegvojij
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        // bcrypt - дефолтный кодировщик паролей
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }
}
