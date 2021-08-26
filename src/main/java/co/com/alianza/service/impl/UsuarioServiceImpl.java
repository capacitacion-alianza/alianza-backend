package co.com.alianza.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.alianza.entities.SucursalEntity;
import co.com.alianza.entities.UserEntity;
import co.com.alianza.exceptions.ResourceNotFoundException;
import co.com.alianza.models.Sucursal;
import co.com.alianza.models.Usuario;
import co.com.alianza.models.dto.MiClaseDTO;
import co.com.alianza.repository.SucursalRepository;
import co.com.alianza.repository.UserRepository;
import co.com.alianza.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private UserRepository usuarioRepository;
	private SucursalRepository sucursalRepository;

	public UsuarioServiceImpl(UserRepository usuarioRepository, SucursalRepository sucursalRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
		this.sucursalRepository = sucursalRepository;
	}

	@Override
	public Usuario insert(Usuario usuarioRequest) {
		
		UserEntity usuarioEntity = new UserEntity();
		usuarioEntity.setFirstName(usuarioRequest.getFirstName());
		usuarioEntity.setLastName(usuarioRequest.getLastName());
		usuarioEntity.setClave(usuarioRequest.getClave());
		usuarioEntity.setUsername(usuarioRequest.getUsername());
		
		
		Sucursal sucursal = usuarioRequest.getSucursal();
		Long sucursalId = sucursal.getCodigoSucursal();
		SucursalEntity sucursalEntity = sucursalRepository
				.findById(sucursalId)
				.orElseThrow(() -> new ResourceNotFoundException("SUCURSAL CON ID " + sucursalId + "NO ENCONTRADA"));
				
		usuarioEntity.setSucursalEntity(sucursalEntity);
		
		UserEntity usuarioEntitySaved = usuarioRepository.save(usuarioEntity);
		
		sucursal.setNombre(sucursalEntity.getNombre());
		sucursal.setCreatedAt(sucursalEntity.getCreatedAt());
		
		return Usuario
				.builder()
				.usuarioId(usuarioEntitySaved.getUsuarioId())
				.firstName(usuarioEntitySaved.getFirstName())
				.lastName(usuarioEntitySaved.getLastName())
				.clave(usuarioEntitySaved.getClave())
				.username(usuarioEntitySaved.getUsername())
				.sucursal(sucursal)
				.build();
	}

	@Override
	public Usuario update(Long usuarioId, Usuario usuarioRequest) {
		
		UserEntity usuarioEntity = usuarioRepository
				.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("USUARIO NO ENCONTRADO"));
		
		usuarioEntity.setClave(usuarioRequest.getClave());
		usuarioEntity.setFirstName(usuarioRequest.getFirstName());
		usuarioEntity.setLastName(usuarioRequest.getLastName());
		usuarioEntity.setUsername(usuarioRequest.getUsername());
		
		Sucursal sucursalRequest = usuarioRequest.getSucursal();
		Long sucursalId = sucursalRequest.getCodigoSucursal();
		
		SucursalEntity sucursalEntity = sucursalRepository
				.findById(sucursalId)
				.orElseThrow(() -> new ResourceNotFoundException("SUCURSAL NO EXISTE"));
		
		usuarioEntity.setSucursalEntity(sucursalEntity);
		
		usuarioRepository.save(usuarioEntity);
		
		return Usuario
				.builder()
				.usuarioId(usuarioEntity.getUsuarioId())
				.firstName(usuarioEntity.getFirstName())
				.lastName(usuarioEntity.getLastName())
				.clave(usuarioEntity.getClave())
				.username(usuarioEntity.getUsername())
				.build();
	}

	@Override
	public Usuario findById(Long usuarioId) {
		
		UserEntity usuarioEntity = usuarioRepository
				.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("USUARIO NO ENCONTRADO"));
		
		SucursalEntity sucursalEntity = usuarioEntity.getSucursalEntity();
		
		Sucursal sucursal = Sucursal
				.builder()
				.codigoSucursal(sucursalEntity.getCodigoSucursal())
				.nombre(sucursalEntity.getNombre())
				.build();
		
		return Usuario
				.builder()
				.usuarioId(usuarioEntity.getUsuarioId())
				.firstName(usuarioEntity.getFirstName())
				.lastName(usuarioEntity.getLastName())
				.clave(usuarioEntity.getClave())
				.username(usuarioEntity.getUsername())
				.sucursal(sucursal)
				.build();
	}

	@Override
	public List<Usuario> findAll() {
		List<Usuario> usuarios = new ArrayList<>();
		
		List<UserEntity> usuarioEntityList =  usuarioRepository.findAll();
		
		usuarios = usuarioEntityList
		.stream()
		.map(usuarioEntity -> 
			Usuario
			.builder()
			.clave(usuarioEntity.getClave())
			.firstName(usuarioEntity.getFirstName())
			.lastName(usuarioEntity.getLastName())
			.username(usuarioEntity.getUsername())
			.sucursal(
					Sucursal
					.builder()
					.codigoSucursal(usuarioEntity.getSucursalEntity().getCodigoSucursal())
					.nombre(usuarioEntity.getSucursalEntity().getNombre())
					.build()
			)
			.build()
		)
		.collect(Collectors.toList());
		
		return usuarios;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> paginate(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<UserEntity> usuarioEntityPage = usuarioRepository.findAll(pageable);
		List<UserEntity> usuarioEntityList = usuarioEntityPage.getContent();
		
		return usuarioEntityList
				.stream()
				.map(usuarioEntity -> 
					Usuario
					.builder()
					.clave(usuarioEntity.getClave())
					.firstName(usuarioEntity.getFirstName())
					.lastName(usuarioEntity.getLastName())
					.username(usuarioEntity.getUsername())
					.sucursal(
							Sucursal
							.builder()
							.codigoSucursal(usuarioEntity.getSucursalEntity().getCodigoSucursal())
							.nombre(usuarioEntity.getSucursalEntity().getNombre())
							.build()
					)
					.build()
				)
				.collect(Collectors.toList());
	}

	@Override
	public MiClaseDTO getAllVentaById(Integer idVenta) {
		MiClaseDTO response = new MiClaseDTO();
		
//		Venta venta = ventasRepository.findById(idVenta);
//		Lis<Producto> listaProductos = productoRepository.findById();
//		Usuario usuasrio = usuarioRepository.findAll();
//		
//		response.setProducto(listaProductos);
		
		return response;
	}
	
	@Override
	public Boolean validateUsername(String userName) {
		
		List<UserEntity> usuariosList = this.usuarioRepository.findByUsername(userName);

		boolean anyMatch = usuariosList.stream().anyMatch(u ->  Objects.equals(u.getUsername(), userName));
		
		return anyMatch;
	}

}
