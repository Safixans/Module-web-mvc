package uz.pdp.springFrameworkCore.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.pdp.springFrameworkCore.config.security.CustomUserDetails;
import uz.pdp.springFrameworkCore.config.security.SessionUser;
import uz.pdp.springFrameworkCore.domains.AuthUser;

@Controller
@ResponseBody
public class AuthenticationUserInfoController {


    private final SessionUser sessionUser;

    public AuthenticationUserInfoController(SessionUser sessionUser) {
        this.sessionUser = sessionUser;
    }

    @GetMapping("/create/blog")
    public String createBlog(@AuthenticationPrincipal CustomUserDetails userDetails){
        AuthUser authUser = userDetails.getAuthUser();
        System.out.println(authUser);
//        AuthUser authUser = sessionUser.getUser();
        System.out.println(authUser.getId());
        return "Authenticated User info successfully";
    }
}
