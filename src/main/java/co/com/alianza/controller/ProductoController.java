package co.com.alianza.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.multipart.MultipartFile;

import co.com.alianza.entities.ProductoEntity;
import co.com.alianza.models.Producto;
import co.com.alianza.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	

	@Autowired
	private ProductoService productoService;

	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public @ResponseBody Producto insert(@RequestBody Producto productoRequest) {
		Producto producto = productoService.insert(productoRequest);

		return producto;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "/{productoId}")
	public @ResponseBody Producto update(
			@PathVariable(name = "productoId") Long productoId,
			@RequestBody Producto productoRequest
			) {
		Producto producto = productoService.update(productoId, productoRequest);

		return producto;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/{productoId}")
	public @ResponseBody Producto findById(@PathVariable(name = "productoId") Long productoId) {
		Producto producto = productoService.findById(productoId);

		return producto;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/listar-productos")
	public ResponseEntity<List<Producto>> findAll() {
		List<Producto> sucursales = productoService.findAll();

		return ResponseEntity.ok(sucursales);
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{productoId}")
	public @ResponseBody String delete(@PathVariable(name = "productoId") Long productoId) {
		productoService.delete(productoId);

		return "PRODUCTO ELIMINADA";
	}
	
	@GetMapping(value="/listar-productos/page/{page}/{size}")
	public ResponseEntity<?> findAll(@PathVariable Integer page, @PathVariable Integer size){
		Page<ProductoEntity> data = null;
		Map<String, Object> response = new HashMap<>();
		try {
			Pageable pageable = PageRequest.of(page, size);
			data = productoService.findAll(pageable);
			return new ResponseEntity<Page<ProductoEntity>>(data, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/uploadFile")
	public ResponseEntity<?> uploadFile(@RequestParam("id") Long idProduct, @RequestParam("file") MultipartFile file){
		Map<String, Object> response = new HashMap<>();
		try {
			Producto productoUpdate = productoService.findById(idProduct);
			if (!file.isEmpty()) {
				String fileName = UUID.randomUUID().toString().concat("_").concat(file.getOriginalFilename());
				Path pathFile = Paths.get("C:/uploads").resolve(fileName).toAbsolutePath();
				if(productoUpdate.getImagePath() != null && productoUpdate.getImagePath().length()> 0) {
					Path imageBefore = Paths.get("C:/uploads").resolve(productoUpdate.getImagePath()).toAbsolutePath();
					if (Files.exists(imageBefore)){
						Files.delete(imageBefore);
					}
				}
				
				Files.copy(file.getInputStream(), pathFile);
				System.err.println(file.getBytes().toString());
				productoUpdate.setImagePath(fileName);
				productoService.update(idProduct, productoUpdate);
				response.put("producto", productoUpdate);
				response.put("mensaje", "Se ha subido Correctamente el Archivo");
			}			
		}
		catch (IOException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al subir el archivo");
			response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping(value="viewFile/{file:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String file){
		Path filepath = Paths.get("C:/uploads").resolve(file).toAbsolutePath();
		Resource resource = null;
		try {
			resource = new UrlResource(filepath.toUri());
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"".concat(resource.getFilename()));
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
}
