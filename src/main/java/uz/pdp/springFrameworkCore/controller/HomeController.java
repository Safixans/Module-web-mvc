package uz.pdp.springFrameworkCore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MessageSource messageSource;


    @GetMapping("/home2")
    public String homePage(@CookieValue String language,
                           @RequestParam(required = false) String lang){
        lang= Objects.requireNonNullElse(lang,language);
        String message=messageSource.getMessage("welcome3", new Object[]{"Safixon"}, Locale.forLanguageTag(lang));
        System.out.println("messageSource = " + message);
        return "home2";
    }
}
