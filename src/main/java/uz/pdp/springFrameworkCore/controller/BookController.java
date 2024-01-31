package uz.pdp.springFrameworkCore.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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

    @GetMapping("")
    public String books(Model model) {

        model.addAttribute("books", books);
        return "book";
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

