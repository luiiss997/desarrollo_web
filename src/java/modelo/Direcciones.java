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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "direcciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Direcciones.findAll", query = "SELECT d FROM Direcciones d")
    , @NamedQuery(name = "Direcciones.findById", query = "SELECT d FROM Direcciones d WHERE d.id = :id")
    , @NamedQuery(name = "Direcciones.findActive", query = "SELECT d FROM Direcciones d WHERE d.status = 1")
    , @NamedQuery(name = "Direcciones.findDelated", query = "SELECT d FROM Direcciones d WHERE d.status = 0")
    , @NamedQuery(name = "Direcciones.findByCodigoPostal", query = "SELECT d FROM Direcciones d WHERE d.codigoPostal = :codigoPostal")
    , @NamedQuery(name = "Direcciones.findByColonia", query = "SELECT d FROM Direcciones d WHERE d.colonia = :colonia")
    , @NamedQuery(name = "Direcciones.findByCalle", query = "SELECT d FROM Direcciones d WHERE d.calle = :calle")
    , @NamedQuery(name = "Direcciones.findByNoExterior", query = "SELECT d FROM Direcciones d WHERE d.noExterior = :noExterior")
    , @NamedQuery(name = "Direcciones.findByNoInterior", query = "SELECT d FROM Direcciones d WHERE d.noInterior = :noInterior")
    , @NamedQuery(name = "Direcciones.findByCalle1", query = "SELECT d FROM Direcciones d WHERE d.calle1 = :calle1")
    , @NamedQuery(name = "Direcciones.findByCalle2", query = "SELECT d FROM Direcciones d WHERE d.calle2 = :calle2")
    , @NamedQuery(name = "Direcciones.findByTelefono", query = "SELECT d FROM Direcciones d WHERE d.telefono = :telefono")
    , @NamedQuery(name = "Direcciones.findByStatus", query = "SELECT d FROM Direcciones d WHERE d.status = :status")
    , @NamedQuery(name = "Direcciones.findByIdUser", query = "SELECT d FROM Direcciones d WHERE d.idUsuario.id = :idusuario")})
public class Direcciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_postal")
    private int codigoPostal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "colonia")
    private String colonia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "calle")
    private String calle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "no_exterior")
    private String noExterior;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "no_interior")
    private String noInterior;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "calle1")
    private String calle1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "calle2")
    private String calle2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefono")
    private int telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Estados idEstado;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Municipios idMunicipio;
    @OneToMany(mappedBy = "idDireccion")
    private Collection<Envios> enviosCollection;

    public Direcciones() {
    }

    public Direcciones(Integer id) {
        this.id = id;
    }

    public Direcciones(Integer id, int codigoPostal, String colonia, String calle, String noExterior, String noInterior, String calle1, String calle2, int telefono, int status) {
        this.id = id;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.noExterior = noExterior;
        this.noInterior = noInterior;
        this.calle1 = calle1;
        this.calle2 = calle2;
        this.telefono = telefono;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNoExterior() {
        return noExterior;
    }

    public void setNoExterior(String noExterior) {
        this.noExterior = noExterior;
    }

    public String getNoInterior() {
        return noInterior;
    }

    public void setNoInterior(String noInterior) {
        this.noInterior = noInterior;
    }

    public String getCalle1() {
        return calle1;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Municipios getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipios idMunicipio) {
        this.idMunicipio = idMunicipio;
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
        if (!(object instanceof Direcciones)) {
            return false;
        }
        Direcciones other = (Direcciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID de la direccion: " + id;
    }

}
