package co.com.alianza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.alianza.models.Usuario;
import co.com.alianza.models.dto.MiClaseDTO;
import co.com.alianza.models.Usuario;
import co.com.alianza.service.UsuarioService;
import co.com.alianza.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public @ResponseBody Usuario insert(@RequestBody Usuario usuarioRequest) {
		Usuario usuario = usuarioService.insert(usuarioRequest);
		
		return usuario;
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "/{usuarioId}")
	public @ResponseBody Usuario update(
			@PathVariable(name = "usuarioId") Long usuarioId,
			@RequestBody Usuario usuarioRequest
			) {
		Usuario usuario = usuarioService.update(usuarioId, usuarioRequest);

		return usuario;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/{usuarioId}")
	public @ResponseBody Usuario findById(@PathVariable(name = "usuarioId") Long usuarioId) {
		Usuario producto = usuarioService.findById(usuarioId);

		return producto;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> usuarios = usuarioService.findAll();

		return ResponseEntity.ok(usuarios);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "/paginate")
	public @ResponseBody List<Usuario> paginate(
			@RequestParam(name = "pageNumber") int pageNumber,
			@RequestParam(name = "pageSize") int pageSize
			) {
		return usuarioService.paginate(pageNumber, pageSize);
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{usuarioId}")
	public @ResponseBody String delete(@PathVariable(name = "usuarioId") Long usuarioId) {
		usuarioService.delete(usuarioId);

		return "PRODUCTO ELIMINADA";
	}
	
	
	@GetMapping(value = "/detalle-venta/{idVenta}")
	public @ResponseBody MiClaseDTO getAllVentaById(@PathVariable(name = "idVenta") Integer idVenta) {
		return usuarioService.getAllVentaById(idVenta);
	}
	
	@GetMapping(value = "/validate-username/{userName}")
	public @ResponseBody Boolean validateUsername(@PathVariable String userName) {
		return usuarioService.validateUsername(userName);
	}

}
