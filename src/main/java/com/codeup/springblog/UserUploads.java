package com.codeup.springblog;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import java.nio.file.Paths;
import java.io.*;

@Controller
public class UserUploads {
    @Value("${file-upload-path}")
    private String uploadPath;

    @GetMapping("/fileupload")
    public String showUploadFileForm() {
        return "uploading/index";
    }

    @PostMapping("/fileupload")
    public String saveFile(
            @RequestParam(name = "file") MultipartFile uploadedFile,
            Model model
    ) {
        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();

        File destinationFile = new File(filepath);
        try {
            uploadedFile.transferTo(destinationFile);
            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }
        return "index";
    }
}
