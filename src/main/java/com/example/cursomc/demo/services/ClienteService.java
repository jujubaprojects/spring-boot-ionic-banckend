package com.example.cursomc.demo.services;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.cursomc.demo.domain.Categoria;
import com.example.cursomc.demo.domain.Cliente;
import com.example.cursomc.demo.dto.ClienteDTO;
import com.example.cursomc.demo.repositories.ClienteRepository;
import com.example.cursomc.demo.services.exceptions.DataIntegrityException;
import com.example.cursomc.demo.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService 
{
	@Autowired
	private ClienteRepository repo;
		
	public Cliente find(Integer id)
	{
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll ()
	{
		return repo.findAll();
	}
	
	//public Cliente insert(Cliente obj)
	//{
	//	obj.setId(null);
	//	return repo.save(obj);
	//}
	
	public Cliente update(Cliente obj)
	{
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Cliente newOjb, Cliente obj)
	{
		newOjb.setNome(obj.getNome());
		newOjb.setEmail(obj.getEmail());
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
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos/endereços!");
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String directionAscDesc)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directionAscDesc), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO)
	{
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
}
