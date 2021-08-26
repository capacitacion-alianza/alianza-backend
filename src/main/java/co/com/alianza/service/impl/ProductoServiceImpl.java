package co.com.alianza.service.impl;

import co.com.alianza.entities.ProductoEntity;
import co.com.alianza.exceptions.ResourceNotFoundException;
import co.com.alianza.mapper.ProductoMapper;
import co.com.alianza.models.Producto;
import co.com.alianza.repository.ProductoRepository;
import co.com.alianza.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

	private ProductoRepository productoRepository;
	private ProductoMapper productoMapper;

	public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper) {
		super();
		this.productoRepository = productoRepository;
		this.productoMapper = productoMapper;
	}

	@Override
	public Producto insert(Producto productoRequest) {
		ProductoEntity productoAGuardar = productoMapper.toProductoEntity(productoRequest);
	
		ProductoEntity productoEntityGuardado = productoRepository.save(productoAGuardar);
		
//		return Producto.builder()
//				.codigoProducto(productoEntityGuardado.getCodigoProducto())
//				.nombreProducto(productoEntityGuardado.getNombreProducto())
//				.precio(productoEntityGuardado.getPrecio())
//				.build();
		return productoMapper.toProducto(productoEntityGuardado);
	}

	@Override
	public Producto update(Long productoId, Producto productoRequest) {
		
//		ProductoEntity productoEntity = productoRepository
//				.findById(productoId)
//				.orElseThrow(() -> new ResourceNotFoundException("PRODUCTO CON ID " + productoId + "NO ENCONTRADO"));
//		
//		productoEntity.setNombreProducto(productoRequest.getNombreProducto());
//		productoEntity.setPrecio(productoRequest.getPrecio());
//		productoRepository.save(productoEntity);
//		
//		Producto producto = new Producto();
//		producto.setCodigoProducto(productoEntity.getCodigoProducto());
//		producto.setNombreProducto(productoEntity.getNombreProducto());
//		producto.setPrecio(productoEntity.getPrecio());
//		
//		return producto;
		
		ProductoEntity productoEntity = productoRepository
		.findById(productoId)
		.orElseThrow(() -> new ResourceNotFoundException("PRODUCTO CON ID " + productoId + "NO ENCONTRADO"));
		
		productoEntity = productoMapper.toProductoEntity(productoRequest);
		
		productoRepository.save(productoEntity);
		
		return productoMapper.toProducto(productoEntity); 
		
	}

	@Override
	public Producto findById(Long productoId) {
//		ProductoEntity productoEntity = productoRepository
//				.findById(productoId)
//				.orElseThrow(() -> new ResourceNotFoundException("PRODUCTO CON ID " + productoId + " NO ENCONTRADO"));
//		
//		
//		return Producto
//				.builder()
//				.codigoProducto(productoEntity.getCodigoProducto())
//				.nombreProducto(productoEntity.getNombreProducto())
//				.precio(productoEntity.getPrecio())
//				.build();
		
		ProductoEntity productoEntity = productoRepository
		.findById(productoId)
		.orElseThrow(() -> new ResourceNotFoundException("PRODUCTO CON ID " + productoId + " NO ENCONTRADO"));
		
		return productoMapper.toProducto(productoEntity); 
	}

	@Override
	public List<Producto> findAll() {
		// FORMA 1
//		List<ProductoEntity> productoEntityList = productoRepository.findAll();
//		
//		return productoEntityList
//				.stream()
//				.map(productoEntity -> Producto
//						.builder()
//						.codigoProducto(productoEntity.getCodigoProducto())
//						.nombreProducto(productoEntity.getNombreProducto())
//						.precio(productoEntity.getPrecio())
//						.build())
//				.collect(Collectors.toList());
		
		// FORMA 2
//		List<ProductoEntity> productoEntityList = productoRepository.findAll();
//		return productoEntityList
//		.stream()
//		.map(productoEntity -> productoMapper.toProducto(productoEntity))
//		.collect(Collectors.toList());
		
		//FORMA 3
//		List<ProductoEntity> productoEntityList = productoRepository.findAll();
//		return productoMapper.toProductoList(productoEntityList);
		
		//FORMA 4
		return productoMapper.toProductoList(productoRepository.findAll());
	}

	@Override
	public void delete(Long id) {
		ProductoEntity productoEntity = productoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PRODUCTO NO ENCONTRADO"));
		productoRepository.deleteById(id);
	}

	@Override
	public Page<ProductoEntity> findAll(Pageable p) {
		// TODO Auto-generated method stub
		return productoRepository.findAll(p);
	}
	

}
