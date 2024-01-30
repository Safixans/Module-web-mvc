package uz.pdp.springFrameworkCore.domains;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class AuthUser {
    private Long id;
    private String username;
    private String password;
    private String role;
    private AuthRole[] roles;



}
