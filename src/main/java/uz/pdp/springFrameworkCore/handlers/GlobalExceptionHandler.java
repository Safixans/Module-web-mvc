package uz.pdp.springFrameworkCore.handlers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.springFrameworkCore.exceptions.BookNotFoundException;

@ControllerAdvice("uz.pdp.springFrameworkCore")
public class GlobalExceptionHandler {
    @ExceptionHandler({BookNotFoundException.class})
    public ModelAndView error_404(HttpServletRequest httpServletRequest, BookNotFoundException e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404");
        modelAndView.addObject("message",e.getMessage());
        modelAndView.addObject("path",httpServletRequest.getRequestURI());
        modelAndView.addObject("back_path",e.getPath());
        return modelAndView;
    }
}
