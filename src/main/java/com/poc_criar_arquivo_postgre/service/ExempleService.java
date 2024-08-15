package com.poc_criar_arquivo_postgre.service;

import com.poc_criar_arquivo_postgre.model.ExempleData;
import com.poc_criar_arquivo_postgre.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

@Service
public class ExempleService {

    @Autowired
    private ExampleRepository repository;

    public void writeEntityToFile(Long id) throws IOException {
        Optional<ExempleData> entityOptional = repository.findById(id);

        System.out.println(id);
        System.out.println(entityOptional);

        if (entityOptional.isPresent()) {
            ExempleData data = entityOptional.get();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                String line = String.join("|",
                        data.getColumn01(),
                        data.getColumn02(),
                        data.getColumn03(),
                        data.getColumn04(),
                        data.getColumn05(),
                        data.getColumn06(),
                        data.getColumn07(),
                        data.getColumn08(),
                        data.getColumn09(),
                        data.getColumn10()
                );
                writer.write(line);
            }
        } else {
            throw new RuntimeException("Entity not found with ID: " + id);
        }
    }
}
