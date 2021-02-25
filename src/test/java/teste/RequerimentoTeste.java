
package teste;

import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import model.Requerimento;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
    
    @Test
    public void t02_AlterarRequerimento(){
        requerimento = requerimentoService.consultarPorId(2L);
        requerimento.setEmail("anaclair@gmail.com");
        requerimentoService.alterar(requerimento);
        requerimento = requerimentoService.consultarPorId(2L);
        assertEquals("anaclair@gmail.com",requerimento.getEmail());
    }
    
    @Test
    public void t03_deletarRequerimento(){
        requerimento = requerimentoService.consultarPorId(3L);
        requerimentoService.deletar(requerimento);
        requerimento = requerimentoService.consultarPorId(3L);
        assertNull(requerimento);
    }
    
    @Test
    public void t04_listarTodosRequerimentos(){
        List<Requerimento> requerimentos = requerimentoService.listarTodos();
        assertEquals(3, requerimentos.size());
    }
    
    @Test
    public void t05_buscarPorMatricula(){
        assertNotNull(requerimentoService.buscarPorMatricula("XBY334"));
    }
    
    
}
