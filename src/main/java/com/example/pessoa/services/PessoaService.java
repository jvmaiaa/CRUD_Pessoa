package com.example.pessoa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pessoa.entities.Pessoa;
import com.example.pessoa.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	public List<Pessoa> findAll() {
		return repository.findAll();
	}
	
	public Pessoa findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Pessoa insert(Pessoa obj) {
		return repository.save(obj);
	}
} 
