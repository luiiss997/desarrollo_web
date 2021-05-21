/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "fotos_componentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FotosComponentes.findAll", query = "SELECT f FROM FotosComponentes f")
    , @NamedQuery(name = "FotosComponentes.findById", query = "SELECT f FROM FotosComponentes f WHERE f.id = :id")
    , @NamedQuery(name = "FotosComponentes.findActive", query = "SELECT f FROM FotosComponentes f WHERE f.status = 1")
    , @NamedQuery(name = "FotosComponentes.findDelated", query = "SELECT f FROM FotosComponentes f WHERE f.status = 0")
    , @NamedQuery(name = "FotosComponentes.findByRuta", query = "SELECT f FROM FotosComponentes f WHERE f.ruta = :ruta")
    , @NamedQuery(name = "FotosComponentes.findByStatus", query = "SELECT f FROM FotosComponentes f WHERE f.status = :status")})
public class FotosComponentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "id_componente", referencedColumnName = "id")
    @ManyToOne
    private Componentes idComponente;

    public FotosComponentes() {
    }

    public FotosComponentes(Integer id) {
        this.id = id;
    }

    public FotosComponentes(Integer id, String ruta, int status) {
        this.id = id;
        this.ruta = ruta;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Componentes getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Componentes idComponente) {
        this.idComponente = idComponente;
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
        if (!(object instanceof FotosComponentes)) {
            return false;
        }
        FotosComponentes other = (FotosComponentes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.FotosComponentes[ id=" + id + " ]";
    }
    
}
