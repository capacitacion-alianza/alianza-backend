
package co.com.alianza.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.com.alianza.entities.SucursalEntity;
import co.com.alianza.models.Sucursal;

@Mapper(componentModel = "spring")
public interface SucursalMapper {
	
	Sucursal toSucursal(SucursalEntity sucursalEntity);
	
	SucursalEntity toSucursal(Sucursal sucursal);
	
	List<Sucursal> toSucursalList(List<SucursalEntity> sucursalEntity);
	
	List<SucursalEntity> toSucursalEntityList(List<Sucursal> sucursal);

}
