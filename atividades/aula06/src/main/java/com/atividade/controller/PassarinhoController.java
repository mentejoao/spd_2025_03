package com.atividade.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.model.Passarinho;
import com.atividade.service.PassarinhoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/passarinhos")
public class PassarinhoController {

    @Autowired
    private PassarinhoService passarinhoService;

    @PostMapping
    public Passarinho postPassarinho(@RequestBody Passarinho passarinho) {
        return passarinhoService.save(passarinho);
    }

    @GetMapping
    public List<Passarinho> getAllPassarinhos() {
        return passarinhoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Passarinho> getPassarinhoById(@PathVariable String id) {
        return passarinhoService.findById(id);
    }

    @PutMapping("/{id}")
    public Passarinho putPassarinho(@PathVariable String id, @RequestBody Passarinho passarinho) {
        passarinho.setId(id);
        return passarinhoService.update(passarinho);
    }

    @DeleteMapping("/{id}")
    public void deletePassarinho(@PathVariable String id) {
        passarinhoService.deleteById(id);
    }
}
