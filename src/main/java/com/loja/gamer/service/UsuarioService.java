package com.loja.gamer.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.loja.gamer.model.Usuario;
import com.loja.gamer.model.UsuarioLogin;
import com.loja.gamer.repository.UsuarioRepository;

@Service
public class UsuarioService {

	 @Autowired
	    private UsuarioRepository usuarioRepository;
	private Object senhaDigita;
	private Object senhaBanco;
	private Object enconder;


	    public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
	        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
	            return Optional.empty();
	        usuario.setSenha(criptografarSenha(usuario.getSenha()));
	        return Optional.of(usuarioRepository.save(usuario));
	    }


	    public Optional<Usuario> atualizarUsuario(Usuario usuario) {
	        if(usuarioRepository.findById(usuario.getId()).isPresent()){

	            Optional<Usuario> buscausuario = usuarioRepository.findByUsuario(usuario.getUsuario());

	            if (buscausuario.isPresent() && buscausuario.get().getId() != usuario.getId())
	                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"usuario ja existe",null);

	            usuario.setSenha(criptografarSenha(usuario.getSenha()));
	            return Optional.ofNullable(usuarioRepository.save(usuario));

	        }
	        return Optional.empty();
	    }

	    public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
	        Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
	        if (usuario.isPresent()) {
	            if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
	                usuarioLogin.get().setId(usuario.get().getId());
	                usuarioLogin.get().setNome(usuario.get().getNome());
	                usuarioLogin.get().setFoto(usuario.get().getFoto());
	                usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
	                usuarioLogin.get().setSenha(usuario.get().getSenha());
	                return usuarioLogin;
	            }
	        }
	        return Optional.empty();
	    }

	    private boolean compararSenhas(String senha, String senha2) {
	    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder.matches(senhaDigita, senhaBanco);
		}


		private String gerarBasicToken(String usuario, String senha) {
	    	String token = usuario + ":" + senha;
	    	byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
			return null;
		}


		private String criptografarSenha(String senha) {
	    	BCryptPasswordEncoder encoder = new BCryPasswordEncoder();
	    	return encoder.encoder(senha);
	   
	}
	}