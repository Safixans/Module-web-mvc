package uz.pdp.springFrameworkCore.domains;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class AuthPermission {
    private Long id;
    private String name;
    private String code;

}
