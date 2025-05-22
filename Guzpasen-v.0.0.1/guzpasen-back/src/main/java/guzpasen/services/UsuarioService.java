package guzpasen.services;

import java.util.Optional;

import guzpasen.models.Usuario;

public interface UsuarioService {
	
	public Usuario createUsuario(Usuario usuario);

	Optional<Usuario> findByNick(String nick);
	
}
