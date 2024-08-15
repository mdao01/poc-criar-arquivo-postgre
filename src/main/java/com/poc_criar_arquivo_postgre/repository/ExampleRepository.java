package com.poc_criar_arquivo_postgre.repository;

import com.poc_criar_arquivo_postgre.model.ExempleData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<ExempleData, Long> {
}
