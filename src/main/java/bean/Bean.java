
package bean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Valéria
 * @param <T> Tipo da classe.
 */
public abstract class Bean<T> {

    protected T entidade;

    @PostConstruct
    public void init() {
        iniciarCampos();
    }

    protected abstract void iniciarCampos();

    public T getEntidade() {
        return entidade;
    }

    public void setEntidade(T entidade) {
        this.entidade = entidade;
    }
    
    protected void adicionarMessagem(String mensagem) {
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensagem));
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem) {
        FacesMessage message = new FacesMessage(severity, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
