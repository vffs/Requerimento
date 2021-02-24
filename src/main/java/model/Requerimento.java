
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author valeria
 */

@Entity
@Table(name="tb_rerquerimento")
@NamedQueries({
    @NamedQuery(
            name="todosRequerimentos",
            query="Select r From Requerimento r")
})
public class Requerimento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cl_idRequetimento")
    private Long idRequerimento;
    @Column(name = "cl_matricula")
    private String matricula;
    @Column(name="cl_nome")
    private String nome;
    @Column(name="cl_email")
    private String email;
    @Column(name="cl_curso")
    private String curso;
    @Column(name="cl_periodo_serie")
    private String periodoSerie;
    @Column(name="cl_turno")
    private String turno;
    @Column(name="cl_problema")
    private String problema;
    @Column(name="cl_documento")
    private String documento;
    @Column(name="cl_observacoes")
    private String observacoes;
    @Column(name="cl_dataInclusao")
    @Temporal(TemporalType.DATE)
    private Date dataInclusao;

    public Long getIdRequerimento() {
        return idRequerimento;
    }

    public void setIdRequerimento(Long idRequerimento) {
        this.idRequerimento = idRequerimento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPeriodoSerie() {
        return periodoSerie;
    }

    public void setPeriodoSerie(String periodoSerie) {
        this.periodoSerie = periodoSerie;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
        
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.idRequerimento);
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
        final Requerimento other = (Requerimento) obj;
        if (!Objects.equals(this.idRequerimento, other.idRequerimento)) {
            return false;
        }
        return true;
    }
    
    
    
}
