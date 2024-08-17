package com.poc_criar_arquivo_postgre.service;

import com.poc_criar_arquivo_postgre.model.ExempleData;
import com.poc_criar_arquivo_postgre.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExempleService {

    @Autowired
    private ExampleRepository repository;

    public void writeEntityToFile(String filePath) throws IOException {

        List<ExempleData> dataList = repository.findAll();

        List<String> lines = dataList.stream()
                .map(data -> String.join("|",
                data.getColumn01(),
                data.getColumn02(),
                data.getColumn03(),
                data.getColumn04(),
                data.getColumn05(),
                data.getColumn06(),
                data.getColumn07(),
                data.getColumn08(),
                data.getColumn09(),
                data.getColumn10()))
                .collect(Collectors.toList());

        if (!dataList.isEmpty()) {

            //Passando path de teste, más pode mudar para o path que recebe na requisição 'filePath'
            String pathTeste = "output.txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathTeste))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } else{
            throw new RuntimeException("Data not found in Data Base ");
        }
    }
}
