package co.com.alianza.models;

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
public class ProductoResponse {
	
	private Long codigoProducto;
	private String nombreProducto;
	private Double precio;

}
