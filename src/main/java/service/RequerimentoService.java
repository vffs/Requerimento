
package service;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import model.Requerimento;

/**
 *
 * @author valeria
 */
@Stateless
public class RequerimentoService extends Service<Requerimento>{
    
    @PostConstruct
    public void init(){
        super.setClasse(Requerimento.class);
    }
    
    public void salvar(Requerimento requerimento){
        entityManager.persist(requerimento);
    }
    
    public void alterar(Requerimento requerimento){
        entityManager.merge(requerimento);
        entityManager.flush();
    }
    
    public void deletar(Requerimento requerimento){
        entityManager.remove(requerimento);
    }
    
    public List<Requerimento> listarTodos(){
        return super.getEntidades("todosRequerimentos");
    }
    
    public Requerimento criar(){
        return new Requerimento();
    }
    
    
}
