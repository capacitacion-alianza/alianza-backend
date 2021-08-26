package co.com.alianza.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "producto")
@Entity
public class ProductoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoProducto;
	
	@Column(name = "nombre")
	private String nombreProducto;
	
	@Column(name = "precio")
	private Double precio;
	
	@Column(name = "imagePath")
	private String imagePath;
	
	
	
	
}
