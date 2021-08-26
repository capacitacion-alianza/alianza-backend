package co.com.alianza.models;

import co.com.alianza.entities.SucursalEntity;
import co.com.alianza.entities.UserEntity;
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
public class Usuario {

	private Long usuarioId;

	private String firstName;

	private String lastName;

	private String username;

	private String clave;
	
	private Sucursal sucursal;

}
