/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "paqueteria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paqueteria.findAll", query = "SELECT p FROM Paqueteria p")
    , @NamedQuery(name = "Paqueteria.findById", query = "SELECT p FROM Paqueteria p WHERE p.id = :id")
    , @NamedQuery(name = "Paqueteria.findActive", query = "SELECT p FROM Paqueteria p WHERE p.status = 1")
    , @NamedQuery(name = "Paqueteria.findDelated", query = "SELECT p FROM Paqueteria p WHERE p.status = 0")
    , @NamedQuery(name = "Paqueteria.findByNombre", query = "SELECT p FROM Paqueteria p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Paqueteria.findByPrecioEnvio", query = "SELECT p FROM Paqueteria p WHERE p.precioEnvio = :precioEnvio")
    , @NamedQuery(name = "Paqueteria.findByStatus", query = "SELECT p FROM Paqueteria p WHERE p.status = :status")})
public class Paqueteria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_envio")
    private int precioEnvio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(mappedBy = "idPaqueteria")
    private Collection<Envios> enviosCollection;

    public Paqueteria() {
    }

    public Paqueteria(Integer id) {
        this.id = id;
    }

    public Paqueteria(Integer id, String nombre, int precioEnvio, int status) {
        this.id = id;
        this.nombre = nombre;
        this.precioEnvio = precioEnvio;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioEnvio() {
        return precioEnvio;
    }

    public void setPrecioEnvio(int precioEnvio) {
        this.precioEnvio = precioEnvio;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Envios> getEnviosCollection() {
        return enviosCollection;
    }

    public void setEnviosCollection(Collection<Envios> enviosCollection) {
        this.enviosCollection = enviosCollection;
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
        if (!(object instanceof Paqueteria)) {
            return false;
        }
        Paqueteria other = (Paqueteria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
