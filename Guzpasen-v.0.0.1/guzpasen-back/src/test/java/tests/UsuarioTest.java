package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import guzpasen.models.Usuario;

class UsuarioTest {

    @Test
    void testConstructorAndGetters() {
        Usuario usuario = new Usuario(
                1L,
                "nick123",
                "María",
                "López",
                "maria@example.com",
                "claveSegura",
                Usuario.Rol.PROFESOR,
                true
        );

        assertEquals(1L, usuario.getIdUsuario());
        assertEquals("nick123", usuario.getNick());
        assertEquals("María", usuario.getNombre());
        assertEquals("López", usuario.getApellidos());
        assertEquals("maria@example.com", usuario.getEmail());
        assertEquals("claveSegura", usuario.getClave());
        assertEquals(Usuario.Rol.PROFESOR, usuario.getRol());
        assertTrue(usuario.getUsuarioMovil());
    }

    @Test
    void testSetters() {
        Usuario usuario = new Usuario();

        usuario.setIdUsuario(2L);
        usuario.setNick("profe2025");
        usuario.setNombre("Carlos");
        usuario.setApellidos("Martínez");
        usuario.setEmail("carlos@example.com");
        usuario.setClave("otraClave123");
        usuario.setRol(Usuario.Rol.ADMIN);
        usuario.setUsuarioMovil(false);

        assertEquals(2L, usuario.getIdUsuario());
        assertEquals("profe2025", usuario.getNick());
        assertEquals("Carlos", usuario.getNombre());
        assertEquals("Martínez", usuario.getApellidos());
        assertEquals("carlos@example.com", usuario.getEmail());
        assertEquals("otraClave123", usuario.getClave());
        assertEquals(Usuario.Rol.ADMIN, usuario.getRol());
        assertFalse(usuario.getUsuarioMovil());
    }

    @Test
    void testEqualsAndHashCode() {
        Usuario u1 = new Usuario();
        u1.setIdUsuario(3L);

        Usuario u2 = new Usuario();
        u2.setIdUsuario(3L);

        assertEquals(u1, u2);
        assertEquals(u1.hashCode(), u2.hashCode());
    }

    @Test
    void testToStringContainsFields() {
        Usuario usuario = new Usuario();
        usuario.setNick("nickTest");
        usuario.setNombre("Ana");
        usuario.setRol(Usuario.Rol.GESTOR_INCIDENCIAS);

        String str = usuario.toString();

        assertTrue(str.contains("nickTest"));
        assertTrue(str.contains("Ana"));
        assertTrue(str.contains("GESTOR_INCIDENCIAS"));
    }

    @Test
    void testRolEnum() {
        assertEquals("ADMIN", Usuario.Rol.ADMIN.name());
        assertEquals("PROFESOR", Usuario.Rol.PROFESOR.name());
        assertEquals("GESTOR_INCIDENCIAS", Usuario.Rol.GESTOR_INCIDENCIAS.name());
    }
}
