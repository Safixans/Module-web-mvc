package uz.pdp.springFrameworkCore.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.pdp.springFrameworkCore.exceptions.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/book")
public class BookController {

    private final AtomicInteger counter = new AtomicInteger(1);
    private final List<Book> books = new ArrayList<>() {{
        add(new Book(counter.getAndIncrement(), "Reactive Spring", "Josh Long"));
        add(new Book(counter.getAndIncrement(), "Spring Security with easy way", "John Adam"));
    }};


    @GetMapping("/login/page")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login/page")
    public String login(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error_message", "Bad Credentials");

        return "redirect:/book/login/page";
    }

    @GetMapping("")
    public String books(Model model) {
        model.addAttribute("books", books);
        return "book";
    }

    @GetMapping("/add")
    public String createPage(Model model) {
        model.addAttribute("dto", new BookCreateDTO());
        return "create";
    }

    @PostMapping("/add")
    public String create(@Valid @ModelAttribute("dto") BookCreateDTO dto, BindingResult errors) {
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
        if (!dto.getTitle().equals(dto.getAuthor())) {
            errors.rejectValue("title", "", "Title must match to author");
            errors.rejectValue("author", "", "Author must match to Title");
            return "create";
        }

        // if username already exist
        /*if (true){
            errors.rejectValue("username","","Username already taken");
        }*/
        books.add(new Book(counter.getAndIncrement(), dto.getTitle(), dto.getAuthor()));

        return "redirect:/book";
    }

    @GetMapping("/delete/{id}")
    public String deletePage(Model model, @PathVariable Integer id) {
        Book book = books.stream().filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book Not Found with id : '%s'".formatted(id), "/book"));
        model.addAttribute("book", book);
        return "delete";
    }
  /*  @ExceptionHandler({BookNotFoundException.class})
    public ModelAndView error_404(HttpServletRequest httpServletRequest,BookNotFoundException e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404");
        modelAndView.addObject("message",e.getMessage());
        modelAndView.addObject("path",httpServletRequest.getRequestURI());
        modelAndView.addObject("back_path",e.getPath());
        return modelAndView;
    }*/
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Book {
    private Integer id;
    private String title;
    private String author;

}


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class BookCreateDTO {

    @NotBlank(message = "title.notnull")
    private String title;
    @NotBlank(message = "author.notnull")
    private String author;

}