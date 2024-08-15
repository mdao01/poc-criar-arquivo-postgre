package com.poc_criar_arquivo_postgre.service;

import com.poc_criar_arquivo_postgre.model.ExempleData;
import com.poc_criar_arquivo_postgre.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExempleService {

    @Autowired
    private ExampleRepository exempleDataRepository;

    public String generateFile(Long id) throws IOException {
        // Busca a entidade pelo ID
        ExempleData entity = exempleDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));

        // Obtém os nomes das colunas da entidade
        List<String> columnNames = entity.getClass().getDeclaredFields()
                .stream()
                .map(field -> field.getName())
                .collect(Collectors.toList());

        // Concatena os nomes das colunas com "|"
        String header = String.join("|", columnNames);

        // Obtém os valores dos campos da entidade
        List<String> columnValues = columnNames.stream()
                .map(fieldName -> {
                    try {
                        var field = entity.getClass().getDeclaredField(fieldName);
                        field.setAccessible(true);
                        Object value = field.get(entity);
                        return value != null ? value.toString() : "";
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        // Concatena os valores dos campos com "|"
        String data = String.join("|", columnValues);

        // Cria e escreve no arquivo
        String filePath = "output.txt";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(header + "\n");
            writer.write(data + "\n");
        }

        return filePath;
    }
}

