package com.desafio.apigestionusuarios.service.generic;

import java.util.List;
import java.util.Optional;

import com.desafio.apigestionusuarios.service.exception.ServiceException;

public interface GenericService <T>{

	List <T> findLike(T t) throws ServiceException;
	
	Optional<T> findById(T t) throws ServiceException;

	T insert(T t) throws ServiceException;

	T update(T t) throws ServiceException;

	T delete(T t) throws ServiceException;

}
