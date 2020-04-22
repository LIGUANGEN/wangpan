package li.gen.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LoginUserDetailService {
    UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException;
}
