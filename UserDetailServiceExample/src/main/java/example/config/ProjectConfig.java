package example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * Здесь по умолчанию происходит basic auth
 */
@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        // Здесь мы используем UserDetailsManager,
        // чтобы использовать .createUser
        UserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        UserDetails userDetails = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        userDetailsService.createUser(userDetails);
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // деприкейтид, не значит, что нельзя использовать
        // но крайне не рекомендуется, однако в учебных целях можно
        return NoOpPasswordEncoder.getInstance();
    }
}
