package com.example.cursomc.demo.services;

import org.springframework.mail.SimpleMailMessage;

import com.example.cursomc.demo.domain.Pedido;

public interface EmailService 
{
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
