package com.example.cursomc.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursomc.demo.domain.Categoria;
import com.example.cursomc.demo.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource 
{
	/*
	 * @RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar()
	{
		Categoria c1 = new Categoria(1, "Informática");
		Categoria c2 = new Categoria(2, "Escritório");
		
		List<Categoria> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		
		return list;
	}*/
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{pId}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer pId)
	{
		Categoria obj = service.buscar(pId);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
