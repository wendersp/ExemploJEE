
package model.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author wender
 */
@Entity
@Table(name = "estado", schema = "exemplojee")
@NamedQueries({
    @NamedQuery(name = "Estado.findByNome", 
            query = "SELECT e FROM Estado e WHERE e.nome LIKE :nome "
                    + "ORDER BY e.nome"),
    @NamedQuery(name = "Estado.findBySigla", 
            query = "SELECT e FROM Estado e WHERE e.sigla LIKE :sigla "
                    + "ORDER BY e.nome")
        
})
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @Column(name = "sigla", length = 2, nullable = false, unique = true)
    private String sigla;
    
    @Column(name = "data_cadastro", insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @Column(name = "data_alteracao", insertable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    
    public Date getDataCadastro() {
        return dataCadastro;
    }

    @PrePersist
    private void setDataCadastro() {
        this.dataCadastro = new Date();
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    @PreUpdate
    private void setDataAlteracao() {
        this.dataAlteracao = new Date();
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.id == null && other.id != null) || (this.id != null 
                && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entidades.Estado[ id=" + id + " ]";
    }
    
}
