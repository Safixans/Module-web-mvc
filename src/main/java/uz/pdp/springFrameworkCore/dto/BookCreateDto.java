package uz.pdp.springFrameworkCore.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookCreateDto {
    private String title;
    private String overview;
    private MultipartFile[] files;
}
