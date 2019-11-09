package com.example.cursomc.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cursomc.demo.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>
{

}
