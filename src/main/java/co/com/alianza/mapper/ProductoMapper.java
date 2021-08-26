package co.com.alianza.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.com.alianza.entities.ProductoEntity;
import co.com.alianza.models.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

	Producto toProducto(ProductoEntity producto);
	
	ProductoEntity toProductoEntity(Producto producto);
	
	List<Producto> toProductoList(List<ProductoEntity> productoList);
	
	List<ProductoEntity> toProductoEntityList(List<Producto> producto);

}
