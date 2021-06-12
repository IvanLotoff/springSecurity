package example.elsee;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Кастомная имплементация [InMemoryUserDetailsService]
 */
public class InMemoryUserDetailsService implements UserDetailsService {
    private final List<UserDetails> users;

    public InMemoryUserDetailsService(List<UserDetails> users) {
        this.users = users;
    }

    /**
     * Мы подгружаем пользователя по имени
     * @param s имя пользователя
     * @return подгруженный пользователь
     * @throws UsernameNotFoundException если пользователь не найден, бросаем это исключение
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return users.stream()
                .filter(user->user.getUsername().equals(s))
                .findFirst()
                .orElseThrow(()-> new UsernameNotFoundException("Not found"));
    }
}
