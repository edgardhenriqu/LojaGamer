package com.loja.gamer.security;

import java.util.List;

import com.loja.gamer.model.Usuario;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private List<GrandtedAuthority> authorities;
	
	public UserDetailsImp(Usuario usuario) {
		this.userName = usuario.getUsuario();
		this.password = usuario.getSenha();
		
	}
	
	public UserDetailsImpl() {}
	
	
}


