package edu.cms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cms.entity.User;
import edu.cms.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.ServletException;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
UserService userService;
	
	@PostMapping("/login")
	public String validate(@RequestBody User user)throws ServletException
	{
		String jwtToken="";
		
		if(user.getUserName()==null || user.getPassword()==null)
		{
			throw new ServletException("Please Fill the userName and password!!");
			
		}
		String userName=user.getUserName();
		String password=user.getPassword();
		
		user=userService.login(userName, password);
		
		if(user==null)
		{
			throw new ServletException("User details not Found!!");
		}
		
		jwtToken=Jwts.builder().setSubject(userName).claim("role","user").
		setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"secretkey" ).compact();
		return jwtToken;
	}
}
