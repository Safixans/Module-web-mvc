package uz.pdp.springFrameworkCore.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Getter

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "BOOK NOT FOUND WITH GIVEN ID !")
public class BookNotFoundException extends RuntimeException{
    private final String path;
    public BookNotFoundException(String message, String path) {
        super(message);
        this.path = path;
    }
}
