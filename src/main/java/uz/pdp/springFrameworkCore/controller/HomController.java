package uz.pdp.springFrameworkCore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomController {
    @GetMapping("/home")
    public String homePage(){
        return "admin.html";
    }
}
