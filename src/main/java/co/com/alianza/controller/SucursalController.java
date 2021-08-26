package co.com.alianza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.com.alianza.models.Producto;
import co.com.alianza.models.Sucursal;
import co.com.alianza.models.SucursalRequest;
import co.com.alianza.models.SucursalResponse;
import co.com.alianza.service.SucursalService;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

	@Autowired
	private SucursalService sucursalService;

//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public @ResponseBody SucursalResponse guardar(@RequestBody SucursalRequest sucursalRequest) {		
//		SucursalResponse response = sucursalService.guardar(sucursalRequest); 
//		
//		return response;
//	}
//	
//	@GetMapping("/{sucursalId}")
//	@ResponseStatus(HttpStatus.OK)
//	public @ResponseBody SucursalResponse buscarPorId(@PathVariable(name = "sucursalId") Integer sucursalId) {
//		
//		SucursalResponse sucursalResponse = sucursalService.buscarPorId(sucursalId);
//		
//		return sucursalResponse;
//	}
//	
//	@DeleteMapping("/{sucursalId}")
//	public ResponseEntity<String> eliminar(@PathVariable(name = "sucursalId") Integer sucursalId) {
//		return ResponseEntity.ok(sucursalService.eliminar(sucursalId));
//	}
//	
//	@PutMapping("/{sucursalId}")
//	public SucursalResponse actualizar(
//			@PathVariable Integer sucursalId,
//			@RequestBody SucursalRequest request
//			) {
//		SucursalResponse sucursalResponse = sucursalService.actualizar(sucursalId, request);
//		
//		return sucursalResponse;
//	}
//	
//	@GetMapping("/buscar/{nombre}")
//	@ResponseStatus(HttpStatus.OK)
//	public @ResponseBody SucursalResponse buscarPorNombre(@PathVariable(name = "nombre") String nombre) {
//		
//		SucursalResponse sucursalResponse = sucursalService.buscarPorNombre(nombre);
//		
//		return sucursalResponse;
//	}

	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public @ResponseBody Sucursal insert(@RequestBody Sucursal sucursalRequest) {
		Sucursal sucursal = sucursalService.insert(sucursalRequest);

		return sucursal;
	}

	// http://localhost:1234/sucursales/1 -> {sucursal => }
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "/{sucursalId}")
	public @ResponseBody Sucursal update(@PathVariable(name = "sucursalId") Long sucursalId,
			@RequestBody Sucursal sucursalRequest) {
		Sucursal sucursal = sucursalService.update(sucursalId, sucursalRequest);

		return sucursal;
	}

	// http://localhost:1234/sucursales/1
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/{sucursalId}")
	public @ResponseBody Sucursal findById(@PathVariable(name = "sucursalId") Long sucursalId) {
		Sucursal sucursal = sucursalService.findById(sucursalId);

		return sucursal;
	}

	// http://localhost:1234/sucursales
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping
	public ResponseEntity<List<Sucursal>> findAll() {
		List<Sucursal> sucursales = sucursalService.findAll();

		return ResponseEntity.ok(sucursales);
	}
	
	// http://localhost:1234/sucursales/1
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{sucursalId}")
	public @ResponseBody String delete(@PathVariable(name = "sucursalId") Long sucursalId) {
		sucursalService.delete(sucursalId);

		return "SUCURSAL ELIMINADA";
	}
	
	

}
