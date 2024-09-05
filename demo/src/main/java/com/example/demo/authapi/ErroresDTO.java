package com.example.demo.authapi;

public class ErroresDTO {
	private String mensaje="";

	public ErroresDTO(String mensaje)
	{
		this.mensaje=mensaje;
	}
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	 

}
