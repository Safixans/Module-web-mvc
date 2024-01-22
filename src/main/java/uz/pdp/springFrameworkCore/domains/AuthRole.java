package uz.pdp.springFrameworkCore.domains;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class AuthRole {
    private Long id;
    private String name;
    private String code;
    private List<AuthPermission> permissions;
}
