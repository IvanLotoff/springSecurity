package example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        // ключ - префикс, который передается в запросе {noop}fewihgviwegvojij
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("befwefpt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        // bcrypt - дефолтный кодировщик паролей
        return new DelegatingPasswordEncoder("befwefpt", encoders);
    }
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails lotoff = User.withUsername("lotoff")
                .password("1234")
                .authorities("read")
                .passwordEncoder(encoder::encode)
                .build();
        manager.createUser(lotoff);
        System.out.println(lotoff.getPassword());
        return manager;
    }
}
