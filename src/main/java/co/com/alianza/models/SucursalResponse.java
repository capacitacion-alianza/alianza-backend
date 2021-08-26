package co.com.alianza.models;

import java.time.LocalDateTime;

public class SucursalResponse {
	
	private Integer codigoSucursal;
	
	private String nombre;
	
	private LocalDateTime createdAt;

	public SucursalResponse() {
		super();
	}

	public SucursalResponse(Integer codigoSucursal, String nombre) {
		super();
		this.codigoSucursal = codigoSucursal;
		this.nombre = nombre;
	}

	public Integer getCodigoSucursal() {
		return codigoSucursal;
	}

	public void setCodigoSucursal(Integer codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	

}
