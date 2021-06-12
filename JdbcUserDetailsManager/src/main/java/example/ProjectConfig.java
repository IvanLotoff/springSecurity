package example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class ProjectConfig {
    /**
     * Здесь отлично рабоате дефолтный jdbcUserDetailsManager
     * поскольку таблицы называются users и authorities.
     * Ниже показано, как изменить определнные query в зависимости от нужды
     * @param dataSource
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
//        String usersByUsernameQuery = "select username, password, enabled " +
//                "from ivanlotoff_test.users where username =?";
//        String authsByUserQuery = "select username, authority " +
//                "from ivanlotoff_test.authorities where username = ?";
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
