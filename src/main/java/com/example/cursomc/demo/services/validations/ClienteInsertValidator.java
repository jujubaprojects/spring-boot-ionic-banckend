package com.example.cursomc.demo.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.cursomc.demo.domain.Cliente;
import com.example.cursomc.demo.domain.enums.TipoCliente;
import com.example.cursomc.demo.dto.ClienteNewDTO;
import com.example.cursomc.demo.repositories.ClienteRepository;
import com.example.cursomc.demo.resources.exceptions.FieldMessage;
import com.example.cursomc.demo.services.validations.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> 
{
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) 
	{

	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) 
	{
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOuCnpj()))
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		if (objDTO.getTipo().equals(TipoCliente.PESSOAJURIDCA.getCod()) && !BR.isValidCPF(objDTO.getCpfOuCnpj()))
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		
		Cliente aux = repo.findByEmail(objDTO.getEmail());
		if (aux != null)
			list.add(new FieldMessage("email", "Email já existente"));
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
