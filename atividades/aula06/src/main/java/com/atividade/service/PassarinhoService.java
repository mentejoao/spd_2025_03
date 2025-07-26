package com.atividade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade.model.Passarinho;
import com.atividade.repository.PassarinhoRepository;

@Service
public class PassarinhoService {

    @Autowired
    private PassarinhoRepository passarinhoRepository;

    // Create new Passarinho
    public Passarinho save(Passarinho passarinho) {
        passarinho.setId(null);
        return passarinhoRepository.save(passarinho);
    }

    // Retrieve all Passarinhos
    public List<Passarinho> findAll() {
        return passarinhoRepository.findAll();
    }

    // Retrieve by ID
    public Optional<Passarinho> findById(String id) {
        return passarinhoRepository.findById(id);
    }

    // Update existing Passarinho
    public Passarinho update(Passarinho passarinho) {
        if (passarinho.getId() == null) {
            throw new IllegalArgumentException("Passarinho ID must not be null for update");
        }
        return passarinhoRepository.save(passarinho);
    }

    // Delete by ID
    public void deleteById(String id) {
        passarinhoRepository.deleteById(id);
    }
}
