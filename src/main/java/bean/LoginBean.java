package bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.Usuario;
import service.UsuarioService;
import util.Criptografia;

/**
 *
 * @author valeria
 */
@SessionScoped
@Named()
public class LoginBean extends Bean<Usuario> implements Serializable {

    private Usuario logado;

    @Inject
    private UsuarioService usuarioService;

    @Override
    protected void iniciarCampos() {
        setEntidade(usuarioService.criar());
        logado = usuarioService.criar();
    }

    public String logar(String email, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String senhaCripto = Criptografia.criptografar(senha);
        try {
            Usuario auxiliar = usuarioService.buscarUsuarioPorLoginSenha(email, senhaCripto).get(0);
            this.logado = auxiliar;

            if (this.logado == null) {
                adicionarMessagem("As informações de login estão incorretas");
                FacesContext.getCurrentInstance().validationFailed();
                return "";
            }
            return "faces/indexLogado.xhtml?faces-redirect=true";

        } catch (IndexOutOfBoundsException in) {
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao logar",
                    "As informações de login estão incorretas"));
            return "";
        }
    }
    
    public String logout(){
        this.logado = null;
        return "faces/index.xhtml";
    }

    public Usuario getLogado() {
        return logado;
    }

    public void setLogado(Usuario logado) {
        this.logado = logado;
    }

}
