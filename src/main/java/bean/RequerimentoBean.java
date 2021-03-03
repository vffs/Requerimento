
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.Requerimento;
import service.RequerimentoService;

/**
 *
 * @author valeria
 */
@RequestScoped
@Named()
public class RequerimentoBean extends Bean<Requerimento> implements Serializable {
    private Requerimento cadastro;
    private Requerimento selecionar;
    private List<Requerimento> requerimentos;
    
    @Inject
    private RequerimentoService requerimentoService;
    
    
    @Override
    public void iniciarCampos(){
       setEntidade(requerimentoService.criar());
       cadastro = requerimentoService.criar();
       
    }
    
    public String inserir(){
        Date dataInclusao =  new Date() ;
        this.cadastro.setDataInclusao(dataInclusao);
        requerimentoService.salvar(this.cadastro);
        cadastro = new Requerimento();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O requerimento foi inserido com sucesso!"));
        return "consultarRequerimento.xhtml"; 
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
    
    public List<Requerimento> buscarRequerimento(String matricula){
        requerimentos = requerimentoService.buscarPorMatricula(matricula);
        return requerimentos;
    }

    public Requerimento getCadastro() {
        return cadastro;
    }

    public void setCadastro(Requerimento cadastro) {
        this.cadastro = cadastro;
    }

    public Requerimento getSelecionar() {
        return selecionar;
    }

    public void setSelecionar(Requerimento selecionar) {
        this.selecionar = selecionar;
    }

    public List<Requerimento> getRequerimentos() {
        return requerimentos;
    }

    public void setRequerimentos(List<Requerimento> requerimentos) {
        this.requerimentos = requerimentos;
    }
    
    
    
    
}
