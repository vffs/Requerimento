
package model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author valeria
 */
@Entity
@Table(name = "tb_usuario")
@NamedQueries({
    @NamedQuery(
           name="todosUsuarios" ,
           query="Select u From Usuario u" 
    ),
    @NamedQuery(
            name="usuarioPorEmailSenha",
            query="Select u From Usuario u Where u.email=?1 and u.senha=?2"
    )     
})
public class Usuario implements Serializable{
    public static final String USUARIO_POR_EMAIL_SENHA = "usuarioPorEmailSenha";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @Column(name = "cl_nome")
    @NotBlank(message = "Não pode ser branco")
    private String nome;
    
    @Column(name = "cl_senha")
    @NotBlank(message = "Não pode ser branco")
    @Size(min = 6, message="Senha deve ter no mínimo 6 caracteres") 
    private String senha;
    
    @Column(name="cl_email")
    @NotBlank(message = "Não pode ser branco")
    @Email(message="Não é um endereço de e-mail")
    private String email;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.idUsuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        return true;
    }
    
    
}
