package uz.pdp.springFrameworkCore.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Todo not Found with given id")
public class TodoNotFoundException extends RuntimeException {
    private final String path;

    public TodoNotFoundException(String message, String path) {
        super(message);
        this.path = path;
    }
}
