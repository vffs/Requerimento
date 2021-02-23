
package service;

import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;
import javax.validation.constraints.NotNull;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.TypedQuery;

/**
 *
 * @author valeria
 * @param <T>
 */
public class Service<T> {
    
    @PersistenceContext(name = "requerimento", type = TRANSACTION)
    protected EntityManager entityManager;
    protected Class<T> classe;

    @TransactionAttribute(NOT_SUPPORTED)
    protected void setClasse(@NotNull Class<T> classe){
        this.classe = classe;
    }
    
    @TransactionAttribute(SUPPORTS)
    public T consultarPorId(@NotNull Long id){
      return  this.entityManager.find(classe, id);
    }
    
    @TransactionAttribute(SUPPORTS)
    protected List<T> getEntidades(String nameQuery){
        TypedQuery<T> query = this.entityManager.createNamedQuery(nameQuery, classe);
        return query.getResultList();
    }
    
    @TransactionAttribute(SUPPORTS)
    protected List<T> getEntidades(String nameQuery, Object[] parametros){
        TypedQuery<T> query = this.entityManager.createNamedQuery(nameQuery, classe);
        
        int i = 0;
        for(Object parametro: parametros){
            query.setParameter(i++, parametro);
        }
        return query.getResultList();
    }
    
    @TransactionAttribute(SUPPORTS)
    protected T getEntidade(String nameQuery, Object[] parametros){
        TypedQuery<T> query = this.entityManager.createNamedQuery(nameQuery, classe);
        
        int i= 0;
        for(Object parametro: parametros){
            query.setParameter(i++, parametro);
        }
        return query.getSingleResult();
    }
}
