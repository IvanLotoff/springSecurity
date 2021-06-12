package example;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Component  добавляем в бин контейнер, либо альтернативно
// через
// protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authenticationProvider);
//        }
public class CustomAuthenticationProvider implements AuthenticationProvider {
    /**
     * здесь мы пишем логику аутонтефикации. Стоит отметить,
     * что данный метод может возвращать null, в таком случае,
     * мы будем вызывать следующих провайдеров по цепочке.
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // логично, что из передаваемого объекто должно быть
        // возможно достать логин и пароль
        String name = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        if("ivan".equals(name) && "123".equals(password))
            return new UsernamePasswordAuthenticationToken(name, password, Arrays.asList());
        else
            throw new AuthenticationCredentialsNotFoundException("Error");
    }

    /**
     * этот метод пока принимаем на веру
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(aClass);
    }
}
