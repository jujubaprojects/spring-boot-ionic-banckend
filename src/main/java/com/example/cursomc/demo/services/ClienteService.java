package com.example.cursomc.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc.demo.domain.Cliente;
import com.example.cursomc.demo.repositories.ClienteRepository;
import com.example.cursomc.demo.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService 
{
	@Autowired
	private ClienteRepository repo;
		
	public Cliente buscar(Integer id)
	{
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
}
