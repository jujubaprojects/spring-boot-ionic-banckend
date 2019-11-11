package com.example.cursomc.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursomc.demo.domain.Categoria;
import com.example.cursomc.demo.domain.Produto;
import com.example.cursomc.demo.dto.CategoriaDTO;
import com.example.cursomc.demo.dto.ProdutoDTO;
import com.example.cursomc.demo.resources.utils.URL;
import com.example.cursomc.demo.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource 
{
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id)
	{
		Produto obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome, 
			@RequestParam(value = "categorias", defaultValue = "") String categorias, 
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "directionAscDesc", defaultValue = "ASC") String directionAscDesc)
	{
		
		String nomeDecoded = URL.decoParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		
		Page<Produto> lista = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, directionAscDesc);		
		Page<ProdutoDTO> listaDTO = lista.map(obj -> new ProdutoDTO(obj));
		
		return ResponseEntity.ok().body(listaDTO);
	}
}
