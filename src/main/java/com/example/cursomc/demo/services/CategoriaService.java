package com.example.cursomc.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.cursomc.demo.domain.Categoria;
import com.example.cursomc.demo.dto.CategoriaDTO;
import com.example.cursomc.demo.repositories.CategoriaRepository;
import com.example.cursomc.demo.services.exceptions.DataIntegrityException;
import com.example.cursomc.demo.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService 
{
	@Autowired
	private CategoriaRepository repo;
	
	public List<Categoria> findAll ()
	{
		return repo.findAll();
	}

	public Categoria find (Integer id)
	{
	Optional<Categoria> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj)
	{
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj)
	{
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id)
	{
		find(id);
		
		try
		{
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e)
		{
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String directionAscDesc)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directionAscDesc), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO)
	{
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
}
