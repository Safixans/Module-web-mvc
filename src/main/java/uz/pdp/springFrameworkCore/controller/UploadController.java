package uz.pdp.springFrameworkCore.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.springFrameworkCore.daos.UploadsDao;
import uz.pdp.springFrameworkCore.domains.Uploads;
import uz.pdp.springFrameworkCore.dto.BookCreateDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Controller
public class UploadController {
private final  Path rootPath=Path.of("/Users/safixonabdusattorov/Documents/Upload/");
    private final UploadsDao uploadsDao;

    public UploadController(UploadsDao uploadsDao) {
        this.uploadsDao = uploadsDao;
    }

    /*@GetMapping("/upload")
    @ResponseBody
    public String a(
            @RequestParam(name = "param1",required = false) String o,
            @RequestParam(name = "param2",required = false) String o2) {
        return "Server recieved : " + o+"::"+o2;
    }


    @GetMapping("/download/{fileName:.+}")
    @ResponseBody
    public String download(@PathVariable(name = "fileName") String fileName){
        return "FIle with name: "+ fileName+"is downloaded successfully";
    }*/


    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(/*@RequestParam("file")MultipartFile file,*/
            @ModelAttribute BookCreateDto dto) throws IOException {
//        String originalFilename = dto.getFile().getOriginalFilename();
        for (MultipartFile file : dto.getFiles()) {
            System.out.println("dto = " + dto);
            String newName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
            Path path = rootPath.resolve(newName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("file uploaded " + file.getOriginalFilename());
        }
        return "redirect:/upload";
    }
    @PostMapping("/upload2")
    public String upload2File(
            @RequestParam("file") MultipartFile file) throws IOException {
        Uploads uploads= Uploads.builder()
                .originalName(file.getOriginalFilename())
                .generatedName(UUID.randomUUID()+"."+StringUtils.getFilenameExtension(file.getOriginalFilename()))
                .size(file.getSize())
                .mimeType(file.getContentType())
                .build();
        uploadsDao.save(uploads);

            Files.copy(file.getInputStream(), rootPath.resolve(uploads.getGeneratedName()), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("file uploaded " + file.getOriginalFilename());

        return "redirect:/upload";
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadPage(@PathVariable String filename) {

        FileSystemResource fileSystemResource = new FileSystemResource(rootPath.resolve(filename));

        Uploads uploads=uploadsDao.findByGeneratedName(filename);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(uploads.getMimeType()))
                .contentLength(uploads.getSize())
                .header("Content-Disposition","attachment; filename = "+uploads.getOriginalName())
                .body(fileSystemResource);

    }

}
