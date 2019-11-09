package com.example.cursomc.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc.demo.domain.Categoria;
import com.example.cursomc.demo.repositories.CategoriaRepository;

@Service
public class CategoriaService 
{
	@Autowired
	private CategoriaRepository repo;
		
	public Categoria buscar(Integer pId)
	{
		Optional<Categoria> obj = repo.findById(pId);
		return obj.orElse(null);
	}
	
}
