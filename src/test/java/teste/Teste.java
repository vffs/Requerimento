
package teste;

import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author valeria
 */
public class Teste {
    
    protected static EJBContainer container;
    protected final Logger logger = Logger.getGlobal();
    
    @BeforeClass
    public static void setUpClass(){
        container = EJBContainer.createEJBContainer();
        
    }
    
    @AfterClass
    public static void tearDownClass(){
        container.close();
    }
    
}
