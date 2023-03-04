package com.poc.academia.api.files.controllers;

import com.poc.academia.api.aws.services.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@AllArgsConstructor
public class FileController {

    private final S3Service s3Service;

    @PostMapping
    public Object createObject(@RequestParam("file") MultipartFile file) {
        return s3Service.putObject(file);
    }
}
