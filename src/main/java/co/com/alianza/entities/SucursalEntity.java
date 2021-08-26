package co.com.alianza.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "sucursal")
@Entity
public class SucursalEntity {
	
	@Id
	@Column(name = "codigo_sucursal")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoSucursal;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	

	public SucursalEntity() {
		super();
	}

	public SucursalEntity(Long codigoSucursal, String nombre) {
		super();
		this.codigoSucursal = codigoSucursal;
		this.nombre = nombre;
	}

	public Long getCodigoSucursal() {
		return codigoSucursal;
	}

	public void setCodigoSucursal(Long codigoSucursal) {
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	

}
