
package service;

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
    
    public Requerimento criar(){
        return new Requerimento();
    }
    
    
}
