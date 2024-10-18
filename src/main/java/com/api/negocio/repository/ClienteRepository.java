package com.api.negocio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.negocio.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	//consultas
	//consulta por distrito
	@Query("select m from Cliente m where m.distrito = :distrito")
	public List<Cliente> ConsultaPorDistrito(String distrito);
	
	//consulta por distrito
	@Query("select m from Cliente m where m.apellido = :apellido")
	public List<Cliente> ConsultaPorApellido(String apellido);
}
