package com.poc_criar_arquivo_postgre.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbteste01")
public class ExempleData {

    @Id
    private Long id;
    private String column01;
    private String column02;
    private String column03;
    private String column04;
    private String column05;
    private String column06;
    private String column07;
    private String column08;
    private String column09;
    private String column10;
}
