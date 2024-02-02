package uz.pdp.springFrameworkCore.controller;


import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.springFrameworkCore.daos.AuthUserDao;
import uz.pdp.springFrameworkCore.domains.AuthUser;
import uz.pdp.springFrameworkCore.dto.UserRegisterDto;


@Controller
public class AuthUserController {
    private final PasswordEncoder passwordEncoder;
    private final AuthUserDao authUserDao;

    public AuthUserController(PasswordEncoder passwordEncoder, AuthUserDao authUserDao) {
        this.passwordEncoder = passwordEncoder;
        this.authUserDao = authUserDao;
    }

    @GetMapping("/login")
    public ModelAndView loginPage(@RequestParam(required = false) String error){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        modelAndView.addObject("error message: ",error);
        return modelAndView;
    }


    @GetMapping("/register")
    public String register(@ModelAttribute UserRegisterDto dto){
        AuthUser user=AuthUser.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        Long id = authUserDao.save(user);
        return "register";
    }

    @PostMapping("/register")
    public String create(@Valid @ModelAttribute("dto") UserRegisterDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
//            System.out.println(errors);
            return "create";
        }

        /*
         * username,
         * email
         * password
         * confirmation password
         *
         * */
        String username = authUserDao.findByUsername(dto.getUsername()).getUsername();
        // searching from a DB to identify similar username registered
        if (!dto.getPassword().equals(dto.getConfirmationPassword())) {
            errors.rejectValue("title", "", "Title must match to author");
            errors.rejectValue("author", "", "Author must match to Title");
            return "register";
        } else if (username == null){
            errors.rejectValue("username","","Username Cannot be null");
            return "register";
        }
        // if username already exist

//        books.add(new Book(counter.getAndIncrement(), dto.getTitle(), dto.getAuthor()));

        return "redirect:/home";
    }


    @GetMapping("/logout")
    public String logoutPage(){

        return "/logout";
    }
    @GetMapping("/todolist")
    public String listOfTodo(){

        return "todolist";
    }

}
