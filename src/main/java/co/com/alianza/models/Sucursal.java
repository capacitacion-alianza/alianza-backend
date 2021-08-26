package co.com.alianza.models;

import java.time.LocalDateTime;

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
public class Sucursal {
	
	private Long codigoSucursal;
	private String nombre;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
