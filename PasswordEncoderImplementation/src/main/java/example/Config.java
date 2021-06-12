package example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class Config {
    @Bean
    UserDetailsService service(PasswordEncoder encoder){
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        UserDetails lotoff = User.withUsername("lotoff")
                .password(encoder.encode("123"))
                .authorities("read")
                .build();
        userDetailsManager.createUser(lotoff);
        return userDetailsManager;
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        //return new PlainTextPasswordEncoder();
        //return new BCryptPasswordEncoder(4);
        //return new StandardPasswordEncoder("secret");
        return new Pbkdf2PasswordEncoder("secret");
    }
}
