
package service;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import model.Requerimento;
import static model.Requerimento.REQUERIMENTO_POR_MATRICULA;

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
        requerimento = entityManager.merge(requerimento);
        entityManager.remove(requerimento);
    }
    
    public List<Requerimento> listarTodos(){
        return super.getEntidades("todosRequerimentosPorData");
    }
    
    public List<Requerimento> listarTodosVencidos(){
//        Requerimento e = this.criar();
//        
//        List<Requerimento> requerimentos;
//        String query ="Select * From TB_REQUERIMENTO where DATEADD(CL_DATAINCLUSAO, 15, DAY) < CURRENT_DATE ";
//        
//       requerimentos =  entityManager.createNativeQuery(query)
//                .getResultList();               
//       
//       return requerimentos;
       return super.getEntidades("todosRequerimentoVencidos");
    }
           
    public List<Requerimento> buscarPorMatricula(String matricula){
        return super.getEntidades(REQUERIMENTO_POR_MATRICULA, new Object[]{matricula});
    }
    
    public Requerimento criar(){
        return new Requerimento();
    }
    
    
}
