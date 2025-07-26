package com.atividade.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atividade.model.Passarinho;

@Repository
public interface PassarinhoRepository extends MongoRepository<Passarinho, String> {
}
