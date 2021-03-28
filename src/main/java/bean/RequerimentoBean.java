package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Requerimento;
import service.RequerimentoService;

/**
 *
 * @author valeria
 */
@SessionScoped
@Named()
public class RequerimentoBean extends Bean<Requerimento> implements Serializable {

    private Requerimento cadastro;
    private Requerimento selecionar;
    private List<Requerimento> requerimentos;

    @Inject
    private RequerimentoService requerimentoService;

    @Override
    public void iniciarCampos() {
        setEntidade(requerimentoService.criar());
        cadastro = requerimentoService.criar();
        requerimentos = new ArrayList<>();
    }

    public String inserir() {
        Date dataInclusao = new Date();
        this.cadastro.setDataInclusao(dataInclusao);
        this.cadastro.setStatus("Em Aberto");
        this.cadastro.setResultado("Aguardando");
        requerimentos.add(this.cadastro);
        requerimentoService.salvar(this.cadastro);
        cadastro = new Requerimento();
        adicionarMessagem("O requerimento foi inserido com sucesso!");
        return "consultarRequerimento.xhtml";
    }

    public void alterar() {
        Requerimento[] req = new Requerimento[this.requerimentos.size()];
        for (int i = 0; i < requerimentos.size(); i++) {
            req[i] = requerimentos.get(i);
        }

        for (int i = 0; i < requerimentos.size(); i++) {
            if (req[i].equals(this.selecionar)) {
                req[i] = this.selecionar;
            }
        }
        for (int i = 0; i < requerimentos.size(); i++) {
            this.requerimentos = new ArrayList<>();
            this.requerimentos.add(req[i]);
        }

        requerimentoService.alterar(this.selecionar);

    }

    public String update() {
        this.alterar();
        adicionarMessagem("O requerimento foi alterado com sucesso!");
        return "consultarRequerimento.xhtml";
    }

    public String resultadoRequerimento() {
        this.alterar();
        adicionarMessagem("Resultado inserido com sucesso");
        return "indexLogado.xhtml";
    }

    public void deletar(Requerimento requerimento) {
        requerimentoService.deletar(requerimento);
    }

    public List<Requerimento> listarTodos() {
        return requerimentoService.listarTodos();
    }
    
    public List<Requerimento> listarTodosVencidos() {
        return requerimentoService.listarTodosVencidos();
    }

    public List<Requerimento> buscarRequerimento(String matricula) {
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
