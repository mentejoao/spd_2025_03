package com.atividade.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "passarinhos")
public class Passarinho {

    public Passarinho(String species, BigDecimal weight, Date migration) {
        this.species = species;
        this.weight = weight;
        this.migration = migration;
    }

    @Id
    private String id;

    private String species;
    private BigDecimal weight;
    private Date migration;

    @CreatedDate
    private Instant timestamp;
}
