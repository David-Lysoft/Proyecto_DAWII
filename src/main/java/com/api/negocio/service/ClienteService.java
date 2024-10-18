package com.api.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.negocio.entity.Cliente;
import com.api.negocio.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repositoryCli;
	
	//listar
	public List<Cliente> listarCliente() throws Exception{
		return repositoryCli.findAll();
	}
	
	//registrar
	public Cliente registrarCliente(Cliente bean) throws Exception{
		return repositoryCli.save(bean);
	}
	
	//actualizar
	public Cliente actualizarCliente(Cliente bean) throws Exception{
		return repositoryCli.save(bean);
	}
	
	//eliminar
	public void EliminarCliente(int codigo) throws Exception{
		repositoryCli.deleteById(codigo);
	}
	
	//consultas
	//encontrar por distrito
	public List<Cliente> ConsultaPorDistrito(String distrito) throws Exception{
		return repositoryCli.ConsultaPorDistrito(distrito);
	}
	
	//
	public List<Cliente> ConsultaPorApellido (String apellido) throws Exception{
		return repositoryCli.ConsultaPorApellido(apellido);
	}
	
	
	
	
}
