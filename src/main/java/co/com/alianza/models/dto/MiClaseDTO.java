package co.com.alianza.models.dto;

import java.time.LocalDateTime;
import java.util.List;

import co.com.alianza.models.Producto;
import co.com.alianza.models.Usuario;
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
public class MiClaseDTO {
	
	private int idVenta;
	
	private List<Producto> producto;
	
	private LocalDateTime fechaFactura;
	
	private Usuario usuario;
	
	private Object cajero;
	
	

}
