package com.desafio.apigestionusuarios.service.mantenimiento;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.desafio.apigestionusuarios.entity.Users;
import com.desafio.apigestionusuarios.repository.UsersRepository;
import com.desafio.apigestionusuarios.service.exception.ServiceException;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UsersRepository userRepository;

	@Override
	public List<Users> findLike(Users user) throws ServiceException {		
		try {
			if (Objects.isNull(user)) {
				return userRepository.findAll();
			}else {
				
				return userRepository.findAll();
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}	

	@Override
	public Optional<Users> findById(Users user) throws ServiceException {
		try {
			return userRepository.findById(user.getId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Users insert(Users user) throws ServiceException {
		try {
			return userRepository.save(user);
		} catch (Exception e) {	
			log.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Users update(Users user) throws ServiceException {
		try {			
			return userRepository.save(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Users delete(Users user) throws ServiceException {
		try {			
			
			return userRepository.save(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

}
