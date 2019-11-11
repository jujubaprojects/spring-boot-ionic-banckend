package com.example.cursomc.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.cursomc.demo.domain.Categoria;
import com.example.cursomc.demo.domain.Produto;
import com.example.cursomc.demo.repositories.CategoriaRepository;
import com.example.cursomc.demo.repositories.ProdutoRepository;
import com.example.cursomc.demo.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService 
{
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
		
	public Produto find(Integer id)
	{
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search (String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String directionAscDesc)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directionAscDesc), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	
}
