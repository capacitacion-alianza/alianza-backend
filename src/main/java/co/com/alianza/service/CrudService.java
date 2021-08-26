package co.com.alianza.service;

import java.util.List;

public interface CrudService<T> {
	
	T insert(T t);
	
	T update(Long id, T t);
	
	T findById(Long id);
	
	List<T> findAll();
	
	void delete(Long id);

}
