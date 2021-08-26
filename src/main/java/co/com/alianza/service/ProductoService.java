package co.com.alianza.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.com.alianza.entities.ProductoEntity;
import co.com.alianza.models.Producto;

public interface ProductoService extends CrudService<Producto> {
	Page<ProductoEntity> findAll(Pageable p);
}
