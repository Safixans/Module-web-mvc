package uz.pdp.springFrameworkCore.config.security;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.pdp.springFrameworkCore.domains.AuthUser;
import uz.pdp.springFrameworkCore.daos.AuthUserDao;

import java.util.Collections;
import java.util.Set;

@Component
@ComponentScan("uz.pdp.springFrameworkCore")
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthUserDao authUserDao;

    public CustomUserDetailsService(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser=authUserDao.findByUsername(username);
        String role="ROLE_"+authUser.getRole();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
        Set<GrantedAuthority> authorities= (Set<GrantedAuthority>) Collections.singletonList(grantedAuthority);
        return new CustomUserDetails(authUser,authorities);
    }
}
