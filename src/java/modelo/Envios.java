/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "envios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Envios.findAll", query = "SELECT e FROM Envios e")
    , @NamedQuery(name = "Envios.findById", query = "SELECT e FROM Envios e WHERE e.id = :id")
    , @NamedQuery(name = "Envios.findActive", query = "SELECT e FROM Envios e WHERE e.status = 1")
    , @NamedQuery(name = "Envios.findDelated", query = "SELECT e FROM Envios e WHERE e.status = 0")
    , @NamedQuery(name = "Envios.findBySeguroEnvio", query = "SELECT e FROM Envios e WHERE e.seguroEnvio = :seguroEnvio")
    , @NamedQuery(name = "Envios.findByFechaEnvio", query = "SELECT e FROM Envios e WHERE e.fechaEnvio = :fechaEnvio")
    , @NamedQuery(name = "Envios.findByFechaEntrega", query = "SELECT e FROM Envios e WHERE e.fechaEntrega = :fechaEntrega")
    , @NamedQuery(name = "Envios.findByGuiaSeguimiento", query = "SELECT e FROM Envios e WHERE e.guiaSeguimiento = :guiaSeguimiento")
    , @NamedQuery(name = "Envios.findByStatus", query = "SELECT e FROM Envios e WHERE e.status = :status")})
public class Envios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seguro_envio")
    private int seguroEnvio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_envio")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "guia_seguimiento")
    private String guiaSeguimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "id_venta", referencedColumnName = "id")
    @ManyToOne
    private Ventas idVenta;
    @JoinColumn(name = "id_direccion", referencedColumnName = "id")
    @ManyToOne
    private Direcciones idDireccion;
    @JoinColumn(name = "id_paqueteria", referencedColumnName = "id")
    @ManyToOne
    private Paqueteria idPaqueteria;

    public Envios() {
    }

    public Envios(Integer id) {
        this.id = id;
    }

    public Envios(Integer id, int seguroEnvio, Date fechaEnvio, Date fechaEntrega, String guiaSeguimiento, int status) {
        this.id = id;
        this.seguroEnvio = seguroEnvio;
        this.fechaEnvio = fechaEnvio;
        this.fechaEntrega = fechaEntrega;
        this.guiaSeguimiento = guiaSeguimiento;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSeguroEnvio() {
        return seguroEnvio;
    }

    public void setSeguroEnvio(int seguroEnvio) {
        this.seguroEnvio = seguroEnvio;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getGuiaSeguimiento() {
        return guiaSeguimiento;
    }

    public void setGuiaSeguimiento(String guiaSeguimiento) {
        this.guiaSeguimiento = guiaSeguimiento;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Ventas getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Ventas idVenta) {
        this.idVenta = idVenta;
    }

    public Direcciones getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Direcciones idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Paqueteria getIdPaqueteria() {
        return idPaqueteria;
    }

    public void setIdPaqueteria(Paqueteria idPaqueteria) {
        this.idPaqueteria = idPaqueteria;
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
        if (!(object instanceof Envios)) {
            return false;
        }
        Envios other = (Envios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Envios[ id=" + id + " ]";
    }
    
}
