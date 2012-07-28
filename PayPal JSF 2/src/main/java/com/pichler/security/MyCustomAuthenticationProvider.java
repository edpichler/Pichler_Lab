package com.pichler.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class MyCustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication arg0)
			throws AuthenticationException {
		String password = arg0.getCredentials().toString();
		String user = arg0.getPrincipal().toString();
		if (user.equals("1")) {
			throw new BadCredentialsException("Bah tche! Um não!"); 
		} else {
			SimpleGrantedAuthority role = new SimpleGrantedAuthority("user");
			List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
			roles.add(role);
			return new UsernamePasswordAuthenticationToken(user, password, roles);
			
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
		
	}

}
