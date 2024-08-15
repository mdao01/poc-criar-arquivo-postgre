package com.poc_criar_arquivo_postgre.controller;

import com.poc_criar_arquivo_postgre.model.JsonExample;
import com.poc_criar_arquivo_postgre.service.ExempleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/application")
public class CreateFileController {

    @Autowired
    private ExempleService service;

    @PostMapping("/id")
    public void creatFile(@RequestBody JsonExample jsonExample) {
        System.out.println("Bateu champanha");
        try {
            service.writeEntityToFile(jsonExample.getId());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error writing to file");
        }
    }
}
