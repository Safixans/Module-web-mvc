package uz.pdp.springFrameworkCore.domains;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Uploads {
    private Integer id;
    private String originalName;
    private String generatedName;
    private Long size;
    private String mimeType;


}
