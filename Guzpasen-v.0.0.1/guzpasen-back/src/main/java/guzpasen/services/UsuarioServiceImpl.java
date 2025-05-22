package guzpasen.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guzpasen.models.Usuario;
import guzpasen.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario createUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Override
	public Optional<Usuario> findByNick(String nick) {
		return usuarioRepository.findByNick(nick);
	}

}
