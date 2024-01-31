package uz.pdp.springFrameworkCore.session;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.springFrameworkCore.config.security.CustomUserDetails;
import uz.pdp.springFrameworkCore.domains.AuthUser;


@Component
public class SessionUserIs {
    public AuthUser getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return principal.getAuthUser();
    }

    public Long getId() {
        return getUser().getId();
    }
}
