package com.example.cursomc.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursomc.demo.domain.Pedido;
import com.example.cursomc.demo.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource 
{
	/*
	 * @RequestMapping(method=RequestMethod.GET)
	public List<Pedido> listar()
	{
		Pedido c1 = new Pedido(1, "Informática");
		Pedido c2 = new Pedido(2, "Escritório");
		
		List<Pedido> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		
		return list;
	}*/
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id)
	{
		Pedido obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
