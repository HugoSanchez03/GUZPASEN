package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guzpasen.models.Usuario;
import guzpasen.repositories.UsuarioRepository;
import guzpasen.services.UsuarioServiceImpl;

class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setNick("usuarioTest");
        usuario.setClave("password123");
        // Rellena otros campos si existen
    }

    @Test
    void testCreateUsuario() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario creado = usuarioService.createUsuario(usuario);

        assertNotNull(creado);
        assertEquals("usuarioTest", creado.getNick());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testFindByNick_Existing() {
        when(usuarioRepository.findByNick("usuarioTest")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.findByNick("usuarioTest");

        assertTrue(resultado.isPresent());
        assertEquals("usuarioTest", resultado.get().getNick());
        verify(usuarioRepository).findByNick("usuarioTest");
    }

    @Test
    void testFindByNick_NotExisting() {
        when(usuarioRepository.findByNick("noExiste")).thenReturn(Optional.empty());

        Optional<Usuario> resultado = usuarioService.findByNick("noExiste");

        assertFalse(resultado.isPresent());
        verify(usuarioRepository).findByNick("noExiste");
    }
}
