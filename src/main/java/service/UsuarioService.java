
package service;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import model.Usuario;
import static model.Usuario.USUARIO_POR_EMAIL_SENHA;
/**
 *
 * @author valeria
 */
@Stateless
public class UsuarioService extends Service<Usuario>{
    
    @PostConstruct
    public void init(){
        super.setClasse(Usuario.class);
    }
    
    public void salvar(Usuario usuario){
        entityManager.persist(usuario);
    }
    
    public void alterar(Usuario usuario){
        entityManager.merge(usuario);
        entityManager.flush();
    }
    
    public void deletar(Usuario usuario){
        usuario = entityManager.merge(usuario);
        entityManager.remove(usuario);
    }
    
    public List<Usuario> buscarTodos(){
        return super.getEntidades("todosUsuarios");
    }
    
    public List<Usuario> buscarUsuarioPorLoginSenha(String email, String senha) {
        return super.getEntidades(USUARIO_POR_EMAIL_SENHA, new Object[]{email, senha});
    }
    
    public Usuario criar(){
        return new Usuario();
    }
}
