package com.api.negocio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.negocio.DTO.ClienteDTO;
import com.api.negocio.entity.Cliente;
import com.api.negocio.service.ClienteService;
import com.api.negocio.utils.MensajeResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Cliente")
public class ClienteController {

	@Autowired
	private ClienteService serviceCli;

	@Autowired
	private ModelMapper mapper;
	
	//listarCliente
	@GetMapping("/listarCliente")
	public ResponseEntity<?> listarCliente() throws Exception{
		List<Cliente> lista = serviceCli.listarCliente();
		if(lista.size() == 0) {
			return new ResponseEntity<>(MensajeResponse.builder().mnesaje("No hay registros").
					object(null).build(),HttpStatus.OK);
		}else {
			List<ClienteDTO> lista2 = lista.stream().map(
					m->mapper.map(m, ClienteDTO.class)
					).collect(Collectors.toList());
			
			return new ResponseEntity<>(MensajeResponse.builder().mnesaje("Si hay registros").
					object(lista2).build(),HttpStatus.OK);
		}
	}
	
	
	//Registrar
	@PostMapping("/registrarCliente")
	public ResponseEntity<?> registrarCliente(@Valid @RequestBody ClienteDTO bean) throws Exception {
		try {
			
			Cliente p = null;
			p = mapper.map(bean, Cliente.class);
			p = serviceCli.registrarCliente(p);
			ClienteDTO pro = mapper.map(p, ClienteDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder().mnesaje("Registrado correctamente").
					object(pro).build(),HttpStatus.CREATED);
			
			
		} catch (DataAccessException e) {
			return new ResponseEntity<>(MensajeResponse.builder().mnesaje(e.getMessage()).
					object(null).build(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Actualizar
	@PostMapping("/actualizarCliente")
	public ResponseEntity<?> actualizarCliente(@Valid @RequestBody ClienteDTO bean) throws Exception {
	    try {
	        Cliente clienteActualizado = mapper.map(bean, Cliente.class);
	        clienteActualizado = serviceCli.actualizarCliente(clienteActualizado);
	        ClienteDTO clienteDTO = mapper.map(clienteActualizado, ClienteDTO.class);
	        return new ResponseEntity<>(MensajeResponse.builder().mnesaje("Actualizado correctamente").
	                object(clienteDTO).build(), HttpStatus.OK);
	    } catch (DataAccessException e) {
	        return new ResponseEntity<>(MensajeResponse.builder().mnesaje(e.getMessage()).
	                object(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	//Eliminar
	@DeleteMapping("/eliminarCliente/{codigo}")
	public ResponseEntity<MensajeResponse> eliminarCliente(@PathVariable int codigo) {
	    try {
	        // Validar que el código no sea negativo o cero
	        if (codigo <= 0) {
	            return new ResponseEntity<>(MensajeResponse.builder()
	                    .mnesaje("El código debe ser un número positivo.")
	                    .object(null)
	                    .build(), HttpStatus.BAD_REQUEST);
	        }

	        // Llamada al servicio para eliminar el cliente
	        serviceCli.EliminarCliente(codigo);

	        return new ResponseEntity<>(MensajeResponse.builder()
	                .mnesaje("Cliente eliminado correctamente.")
	                .object(null)
	                .build(), HttpStatus.NO_CONTENT);
	    } catch (DataAccessException e) {
	        return new ResponseEntity<>(MensajeResponse.builder()
	                .mnesaje("Error al eliminar el cliente: " + e.getMessage())
	                .object(null)
	                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
	    } catch (Exception e) {
	        return new ResponseEntity<>(MensajeResponse.builder()
	                .mnesaje("Error inesperado: " + e.getMessage())
	                .object(null)
	                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	
	//listar por distrito
	@GetMapping("/listarPorDistrito/{distrito}")
	public ResponseEntity<?> listarPorDistrito(@PathVariable String distrito) throws Exception {
	    List<Cliente> lista = serviceCli.ConsultaPorDistrito(distrito);
	    if(lista.isEmpty()) {
	        return new ResponseEntity<>(MensajeResponse.builder().mnesaje("No hay registros en este distrito").
	                object(null).build(), HttpStatus.OK);
	    } else {
	        List<ClienteDTO> listaDTO = lista.stream()
	                .map(m -> mapper.map(m, ClienteDTO.class))
	                .collect(Collectors.toList());
	        return new ResponseEntity<>(MensajeResponse.builder().mnesaje("Clientes encontrados").
	                object(listaDTO).build(), HttpStatus.OK);
	    }
	}

	
	//listar por apellido
	@GetMapping("/listarPorApellido/{apellido}")
	public ResponseEntity<?> listarPorApellido(@PathVariable String apellido) throws Exception {
	    List<Cliente> lista = serviceCli.ConsultaPorApellido(apellido);
	    if(lista.isEmpty()) {
	        return new ResponseEntity<>(MensajeResponse.builder().mnesaje("No hay registros con ese apellido").
	                object(null).build(), HttpStatus.OK);
	    } else {
	        List<ClienteDTO> listaDTO = lista.stream()
	                .map(m -> mapper.map(m, ClienteDTO.class))
	                .collect(Collectors.toList());
	        return new ResponseEntity<>(MensajeResponse.builder().mnesaje("Clientes encontrados").
	                object(listaDTO).build(), HttpStatus.OK);
	    }
	}

	
}
