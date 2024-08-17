package com.poc_criar_arquivo_postgre.controller;

import com.poc_criar_arquivo_postgre.service.ExempleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping
public class CreateFileController {

    @Autowired
    private ExempleService service;

    @PostMapping("/generate-file")
    public void creatFile(@RequestParam String filePath) {
        try {
            service.writeEntityToFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error writing to file");
        }
    }
}
