package uz.pdp.springFrameworkCore.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserRegisterDto {
    @NotBlank(message = "username.notnull")
    private String username;
    @NotBlank(message = "password.nonidentical")
    private String password;
    @NotBlank(message = "password.nonidentical")
    private String confirmationPassword;

}
