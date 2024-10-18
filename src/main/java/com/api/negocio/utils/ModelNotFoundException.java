package com.api.negocio.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//esta funcion de va a disparar cuando el runtime cuando el status sea no econtrado
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends RuntimeException{
	public ModelNotFoundException(String mensaje){
		super(mensaje);
	}
}
