
package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author valeria
 */
public class Criptografia {
           
    public static String criptografar(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte hash[] = md.digest(senha.getBytes("UTF-8"));
        
        StringBuilder texto = new StringBuilder();
        for(byte b : hash){
            texto.append(String.format("%02X",0xFF & b));
        }
        return texto.toString();
    }
 
    
}
