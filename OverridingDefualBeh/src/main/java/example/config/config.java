package example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Нужно расширять WebSecurityConfigurerAdapter
 */
@Configuration
public class config extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic(); // устанавливаем вид Auth
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated(); // Все запросы нуждаются в авторизации
//
        http.authorizeRequests()
                .anyRequest()
                .permitAll(); // пропускаем все реквесты

    }
}
