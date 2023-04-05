package edu.cms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cms.entity.User;
import edu.cms.repository.UserRepository;
import edu.cms.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User login(String username, String password) {
		
		return userRepository.findByUserNameAndPassword(username, password);
	}

}
