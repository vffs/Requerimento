
package teste;

import java.util.Date;
import javax.naming.NamingException;
import model.Requerimento;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import service.RequerimentoService;

/**
 *
 * @author valeria
 */
public class RequerimentoTeste extends Teste{
    RequerimentoService requerimentoService;
    Requerimento requerimento;
    Date dataInclusao;
    
    @Before
    public void setup() throws NamingException{
        requerimentoService = (RequerimentoService) container.getContext().lookup("java:global/classes/RequerimentoService!service.RequerimentoService");
    }
    
    @After
    public void teardown(){
        requerimentoService = null;
        requerimento = null;
    }
    
    @Test
    public void t01_persistirRequerimento(){
        requerimento = requerimentoService.criar();
        dataInclusao = new Date();
        
        requerimento.setNome("Letícia katarina Silva");
        requerimento.setMatricula("XYZ444");
        requerimento.setEmail("leticia@gmail.com");
        requerimento.setTurno("noite");
        requerimento.setCurso("Administração");
        requerimento.setPeriodoSerie("Concluiu");
        requerimento.setProblema("Solicitar Diploma");
        requerimento.setDataInclusao(dataInclusao);
        requerimento.setObservacoes("Necessito do diploma para ser promovida");
        requerimentoService.salvar(requerimento);
        assertNotNull(requerimento.getIdRequerimento());
    }
    
    
    
}
