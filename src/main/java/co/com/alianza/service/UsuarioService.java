package co.com.alianza.service;

import java.util.List;

import co.com.alianza.models.Usuario;
import co.com.alianza.models.dto.MiClaseDTO;

public interface UsuarioService extends CrudService<Usuario>{

	List<Usuario> paginate(int pageNumber, int pageSize);

	MiClaseDTO getAllVentaById(Integer idVenta);

	Boolean validateUsername(String userName);


}
