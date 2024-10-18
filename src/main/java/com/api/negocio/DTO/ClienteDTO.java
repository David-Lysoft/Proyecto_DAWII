package com.api.negocio.DTO;


import lombok.Data;

@Data
public class ClienteDTO {
	private int id_cliente;
	private String nombre;
	private String apellido;
	private String correo;
	private int telefono;
	private String distrito;
	private String producto;
	
}
