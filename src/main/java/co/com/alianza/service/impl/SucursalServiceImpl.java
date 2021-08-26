package co.com.alianza.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import co.com.alianza.entities.ProductoEntity;
import co.com.alianza.entities.SucursalEntity;
import co.com.alianza.exceptions.ResourceNotFoundException;
import co.com.alianza.exceptions.UserConflictException;
import co.com.alianza.models.Producto;
import co.com.alianza.models.Sucursal;
import co.com.alianza.models.SucursalRequest;
import co.com.alianza.models.SucursalResponse;
import co.com.alianza.repository.SucursalRepository;
import co.com.alianza.service.SucursalService;

@Service
public class SucursalServiceImpl implements SucursalService {

	@Autowired
	private SucursalRepository sucursalRepository;

	@Override
	public Sucursal insert(Sucursal sucursalRequest) {

		SucursalEntity sucursalAGuardar = new SucursalEntity();
		sucursalAGuardar.setNombre(sucursalRequest.getNombre());

		SucursalEntity sucursalGuardado = sucursalRepository.save(sucursalAGuardar);

		return Sucursal.builder().codigoSucursal(sucursalGuardado.getCodigoSucursal())
				.nombre(sucursalGuardado.getNombre()).createdAt(sucursalGuardado.getCreatedAt())
				.updatedAt(sucursalGuardado.getUpdatedAt()).build();
	}

	@Override
	public Sucursal update(Long id, Sucursal sucursalRequest) {

		Sucursal sucursalResponse = new Sucursal();

//		Optional<SucursalEntity> findById = sucursalRepository.findById(id);
//		if(findById.isPresent()) {
//			SucursalEntity sucursalEntity = findById.get();
//			sucursalEntity.setNombre(sucursalRequest.getNombre());
//			sucursalEntity.setUpdatedAt(LocalDateTime.now());
//
//			sucursalRepository.save(sucursalEntity);
//			
//			sucursalResponse.setCodigoSucursal(sucursalEntity.getCodigoSucursal());
//			sucursalResponse.setNombre(sucursalEntity.getNombre());
//			sucursalResponse.setCreatedAt(sucursalEntity.getCreatedAt());
//			sucursalResponse.setUpdatedAt(sucursalEntity.getUpdatedAt());
//		} else {
//			throw new ResourceNotFoundException("SUCURSAL CON ID " + id + "NO ENCONTRADA");
//		}

		SucursalEntity sucursalEntity = sucursalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SUCURSAL CON ID " + id + "NO ENCONTRADA"));

		sucursalEntity.setNombre(sucursalRequest.getNombre());
		sucursalRepository.save(sucursalEntity);

		sucursalResponse.setCodigoSucursal(sucursalEntity.getCodigoSucursal());
		sucursalResponse.setNombre(sucursalEntity.getNombre());
		sucursalResponse.setCreatedAt(sucursalEntity.getCreatedAt());
		sucursalResponse.setUpdatedAt(sucursalEntity.getUpdatedAt());

		return sucursalResponse;
	}

	@Override
	public Sucursal findById(Long id) {
		SucursalEntity sucursalEntity = sucursalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("SUCURSAL CON ID " + id + "NO ENCONTRADA"));

		return Sucursal.builder().codigoSucursal(sucursalEntity.getCodigoSucursal()).nombre(sucursalEntity.getNombre())
				.createdAt(sucursalEntity.getCreatedAt()).updatedAt(sucursalEntity.getUpdatedAt()).build();
	}

	@Override
	public List<Sucursal> findAll() {
		List<Sucursal> sucursales = new ArrayList<>();

		List<SucursalEntity> findAll = sucursalRepository.findAll();

//		for(int i = 0; i < findAll.size(); i++) {
//			SucursalEntity entity = findAll.get(i);
//			
//			Sucursal sucursal = new Sucursal();
//			sucursal.setCodigoSucursal(entity.getCodigoSucursal());
//			sucursal.setNombre(entity.getNombre());
//			sucursal.setCreatedAt(entity.getCreatedAt());
//			sucursal.setUpdatedAt(entity.getUpdatedAt());
//			
//			sucursales.add(sucursal);
//		}
//		

//		sucursales = findAll.stream()
//				.map(entity -> 
//					Sucursal.builder()
//						.codigoSucursal(entity.getCodigoSucursal())
//						.nombre(entity.getNombre())
//						.createdAt(entity.getCreatedAt())
//						.updatedAt(entity.getUpdatedAt())
//						.build()
//				).collect(Collectors.toList());

		sucursales = findAll.stream().map(entity -> {
			Sucursal sucursal = new Sucursal();
			sucursal.setCodigoSucursal(entity.getCodigoSucursal());
			sucursal.setNombre(entity.getNombre());
			sucursal.setCreatedAt(entity.getCreatedAt());
			sucursal.setUpdatedAt(entity.getUpdatedAt());
			return sucursal;
		}).collect(Collectors.toList());

		return sucursales;
	}

	@Override
	public void delete(Long id) {
		SucursalEntity sucursalEntity = sucursalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("SUCURSAL CON ID " + id + "NO ENCONTRADA"));
		
		sucursalRepository.deleteById(id);
	}

//	@Override
//	public SucursalResponse guardar(SucursalRequest sucursalRequest) {
//		SucursalResponse sucursalResponse = new SucursalResponse();
//		
//		String nombreSucursalAGuardar = sucursalRequest.getNombre();
//		
//		SucursalEntity sucursalEntity = new SucursalEntity();
//		sucursalEntity.setNombre(nombreSucursalAGuardar);
//		sucursalEntity.setCreatedAt(LocalDateTime.now());
//		
//		SucursalEntity sucursalGuardada = sucursalRepository.save(sucursalEntity); // INSERT INTO sucursal (nombre) VALUES ('sucursal 1');
//		
//		sucursalResponse.setCodigoSucursal(sucursalGuardada.getCodigoSucursal());
//		sucursalResponse.setNombre(sucursalGuardada.getNombre());
//		
//		return sucursalResponse;
//	}
//
//	@Override
//	public SucursalResponse buscarPorId(Integer sucursalId) {
//		
//		SucursalResponse sucursalResponse = null;
//		
//		Optional<SucursalEntity> sucursalEncontrada =  sucursalRepository.findById(sucursalId);
//		
//		if (sucursalEncontrada.isPresent()) {
//			SucursalEntity entity = sucursalEncontrada.get();
//			
//			sucursalResponse = new SucursalResponse();
//			sucursalResponse.setCodigoSucursal(entity.getCodigoSucursal());
//			sucursalResponse.setNombre(entity.getNombre());
//			sucursalResponse.setCreatedAt(entity.getCreatedAt());
//		}
//		
//		
//		return sucursalResponse;
//	}
//	
//	@Override
//	public String eliminar(Integer sucursalId) {
//		
//		Optional<SucursalEntity> findById = sucursalRepository.findById(sucursalId);
//		if(findById.isPresent()) {
//			return "NO EXISTE";
//		}
//	
//		sucursalRepository.deleteById(sucursalId);
//		return "ELIMINADO";
//	}
//	
//	@Override
//	public SucursalResponse actualizar(Integer sucursalId, SucursalRequest request) {
//		Optional<SucursalEntity> findById = sucursalRepository.findById(sucursalId);
//		
//		SucursalEntity sucursalEntity = findById.get();
//		sucursalEntity.setNombre(request.getNombre());
//		
//		sucursalRepository.save(sucursalEntity);
//		
//		SucursalResponse response = new SucursalResponse();
//		response.setCodigoSucursal(sucursalEntity.getCodigoSucursal());
//		response.setNombre(sucursalEntity.getNombre());
//		
//		return response;
//	}
//	
//	@Override
//	public SucursalResponse buscarPorNombre(String nombre) {
//
//		Optional<SucursalEntity> findByNombre = sucursalRepository.findByNombre(nombre);
//		SucursalEntity sucursalEntity = findByNombre.get();
//		
//		SucursalResponse response = new SucursalResponse();
//		response.setNombre(sucursalEntity.getNombre());
//		response.setCodigoSucursal(sucursalEntity.getCodigoSucursal());
//		
//		return response;
//	}

}
