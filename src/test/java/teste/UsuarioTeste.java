package teste;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import model.Usuario;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import service.UsuarioService;
import util.Criptografia;

/**
 *
 * @author valeria
 */
public class UsuarioTeste extends Teste {

    UsuarioService usuarioService;
    Usuario usuario;

    @Before
    public void setup() throws NamingException {
        usuarioService = (UsuarioService) container.getContext().lookup("java:global/classes/UsuarioService!service.UsuarioService");
    }

    @After
    public void tearDown() {
        usuarioService = null;
    }

    @Test
    public void t01_inserirUsuario() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        usuario = usuarioService.criar();

        usuario.setNome("Clarissa Farias");
        usuario.setEmail("clarissafs@instituicao.com.br");
        usuario.setSenha(Criptografia.criptografar("123456"));
        usuarioService.salvar(usuario);
        assertNotNull(usuario.getIdUsuario());
    }

    @Test
    public void t02_alterarUsuario() {
        usuario = usuarioService.consultarPorId(1L);
        usuario.setNome("João Apressadinho");
        usuarioService.alterar(usuario);
        usuario = usuarioService.consultarPorId(1L);

        assertEquals("João Apressadinho", usuario.getNome());
    }

    @Test
    public void t03_deletarUsuario() {
        usuario = usuarioService.consultarPorId(3L);
        usuarioService.deletar(usuario);
        usuario = usuarioService.consultarPorId(3L);
        assertNull(usuario);
    }

    @Test
    public void t04_buscarUsuarioPorEmailSenha() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        List<Usuario> usuarios = usuarioService.buscarUsuarioPorLoginSenha("joanars@instituicao.com.br", Criptografia.criptografar("123456"));
        assertEquals("joanars@instituicao.com.br", usuarios.get(0).getEmail());
        assertEquals(1, usuarios.size());
    }

    
}
