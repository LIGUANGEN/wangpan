package li.gen.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public class MyUserDetails implements UserDetails  {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> collection;
    private boolean enable;

    public MyUserDetails(String username,String password,Collection<? extends GrantedAuthority> collection){
       this.collection = collection;
       this.username = username;
       this.password = password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return collection;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
