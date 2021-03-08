
package teste;

import java.util.Date;
import java.util.List;
import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import model.Requerimento;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
        requerimento.setTurno("Noite");
        requerimento.setCurso("Administração");
        requerimento.setPeriodoSerie("Concluiu");
        requerimento.setProblema("Diploma");
        requerimento.setDataInclusao(dataInclusao);
        requerimento.setObservacoes("Necessito do diploma para ser promovida");
        requerimento.setStatus("Em Aberto");  
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
    
    @Test
    public void t06_atualizarInvalido(){
        requerimento = requerimentoService.consultarPorId(2L);
        requerimento.setEmail("anaClair#gmail.com");
        requerimento.setNome(null);
        
        try{
            requerimentoService.alterar(requerimento);
            assertTrue(false);
        }catch(EJBException ex){
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(startsWith("Não é um endereço de e-mail"),
                                startsWith("Não pode ser branco")));
                               
            }
        
       }
    }
         
}
