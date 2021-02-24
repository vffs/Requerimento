
package bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import model.Requerimento;
import service.RequerimentoService;

/**
 *
 * @author valeria
 */
@RequestScoped
@Named
public class RequerimentoBean implements Serializable {
    private RequerimentoService requerimentoService;
    
    @PostConstruct
    public void iniciarCampos(){
        requerimentoService.criar();
    }
    
    public void inserir(Requerimento requerimento){
        requerimentoService.salvar(requerimento);
    }
    
    public void alterar(Requerimento requerimento){
        requerimentoService.alterar(requerimento);
    }
    
    public void deletar(Requerimento requerimento){
        requerimentoService.deletar(requerimento);
    }
    
    public List<Requerimento> listarTodos(){
       return requerimentoService.listarTodos();
    }
}
