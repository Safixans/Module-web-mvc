package uz.pdp.springFrameworkCore.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("uz.pdp.springFrameworkCore")
public class GlobalExceptionHandler {
    @ExceptionHandler({TodoNotFoundException.class})
    public ModelAndView error_404(HttpServletRequest httpServletRequest, TodoNotFoundException e){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error/404");
            modelAndView.addObject("message",e.getMessage());
            modelAndView.addObject("path",httpServletRequest.getRequestURI());
            modelAndView.addObject("back_path",e.getPath());
            return modelAndView;
    }
}
