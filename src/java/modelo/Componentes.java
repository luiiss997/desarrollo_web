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
@Table(name = "componentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Componentes.findAll", query = "SELECT c FROM Componentes c")
    , @NamedQuery(name = "Componentes.findById", query = "SELECT c FROM Componentes c WHERE c.id = :id")
    , @NamedQuery(name = "Componentes.findActive", query = "SELECT c FROM Componentes c WHERE c.status = 1")
    , @NamedQuery(name = "Componentes.findDelated", query = "SELECT c FROM Componentes c WHERE c.status = 0")
    , @NamedQuery(name = "Componentes.findByNombre", query = "SELECT c FROM Componentes c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Componentes.findByDescripcion", query = "SELECT c FROM Componentes c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Componentes.findByTipo", query = "SELECT c FROM Componentes c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "Componentes.findByCapacidad", query = "SELECT c FROM Componentes c WHERE c.capacidad = :capacidad")
    , @NamedQuery(name = "Componentes.findByPotencia", query = "SELECT c FROM Componentes c WHERE c.potencia = :potencia")
    , @NamedQuery(name = "Componentes.findByCertificacion", query = "SELECT c FROM Componentes c WHERE c.certificacion = :certificacion")
    , @NamedQuery(name = "Componentes.findByFForma", query = "SELECT c FROM Componentes c WHERE c.fForma = :fForma")
    , @NamedQuery(name = "Componentes.findBySize", query = "SELECT c FROM Componentes c WHERE c.size = :size")
    , @NamedQuery(name = "Componentes.findByResolucion", query = "SELECT c FROM Componentes c WHERE c.resolucion = :resolucion")
    , @NamedQuery(name = "Componentes.findByVelocidad", query = "SELECT c FROM Componentes c WHERE c.velocidad = :velocidad")
    , @NamedQuery(name = "Componentes.findBySocket", query = "SELECT c FROM Componentes c WHERE c.socket = :socket")
    , @NamedQuery(name = "Componentes.findByVRam", query = "SELECT c FROM Componentes c WHERE c.vRam = :vRam")
    , @NamedQuery(name = "Componentes.findByExistencia", query = "SELECT c FROM Componentes c WHERE c.existencia = :existencia")
    , @NamedQuery(name = "Componentes.findByPrecioCompra", query = "SELECT c FROM Componentes c WHERE c.precioCompra = :precioCompra")
    , @NamedQuery(name = "Componentes.findByPrecioVenta", query = "SELECT c FROM Componentes c WHERE c.precioVenta = :precioVenta")
    , @NamedQuery(name = "Componentes.findByStock", query = "SELECT c FROM Componentes c WHERE c.stock = :stock")
    , @NamedQuery(name = "Componentes.findByStatus", query = "SELECT c FROM Componentes c WHERE c.status = :status")})
public class Componentes implements Serializable {

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
    @Size(min = 1, max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 80)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 10)
    @Column(name = "capacidad")
    private String capacidad;
    @Size(max = 80)
    @Column(name = "potencia")
    private String potencia;
    @Size(max = 80)
    @Column(name = "certificacion")
    private String certificacion;
    @Size(max = 80)
    @Column(name = "f_forma")
    private String fForma;
    @Size(max = 80)
    @Column(name = "size")
    private String size;
    @Size(max = 80)
    @Column(name = "resolucion")
    private String resolucion;
    @Size(max = 80)
    @Column(name = "velocidad")
    private String velocidad;
    @Size(max = 80)
    @Column(name = "socket")
    private String socket;
    @Size(max = 80)
    @Column(name = "v_ram")
    private String vRam;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existencia")
    private int existencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_compra")
    private double precioCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_venta")
    private double precioVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock")
    private int stock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    @ManyToOne
    private Categorias idCategoria;
    @JoinColumn(name = "id_marca", referencedColumnName = "id")
    @ManyToOne
    private Marca idMarca;
    @JoinColumn(name = "id_modelo", referencedColumnName = "id")
    @ManyToOne
    private Modelo idModelo;
    @OneToMany(mappedBy = "idComponente")
    private Collection<DetalleVenta> detalleVentaCollection;
    @OneToMany(mappedBy = "idComponente")
    private Collection<FotosComponentes> fotosComponentesCollection;
    @OneToMany(mappedBy = "idComponente")
    private Collection<DetalleCompra> detalleCompraCollection;

    public Componentes() {
    }

    public Componentes(Integer id) {
        this.id = id;
    }

    public Componentes(Integer id, String nombre, String descripcion, int existencia, double precioCompra, double precioVenta, int stock, int status) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.existencia = existencia;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(String certificacion) {
        this.certificacion = certificacion;
    }

    public String getFForma() {
        return fForma;
    }

    public void setFForma(String fForma) {
        this.fForma = fForma;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getVRam() {
        return vRam;
    }

    public void setVRam(String vRam) {
        this.vRam = vRam;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Categorias getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categorias idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }

    public Modelo getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelo idModelo) {
        this.idModelo = idModelo;
    }

    @XmlTransient
    public Collection<DetalleVenta> getDetalleVentaCollection() {
        return detalleVentaCollection;
    }

    public void setDetalleVentaCollection(Collection<DetalleVenta> detalleVentaCollection) {
        this.detalleVentaCollection = detalleVentaCollection;
    }

    @XmlTransient
    public Collection<FotosComponentes> getFotosComponentesCollection() {
        return fotosComponentesCollection;
    }

    public void setFotosComponentesCollection(Collection<FotosComponentes> fotosComponentesCollection) {
        this.fotosComponentesCollection = fotosComponentesCollection;
    }

    @XmlTransient
    public Collection<DetalleCompra> getDetalleCompraCollection() {
        return detalleCompraCollection;
    }

    public void setDetalleCompraCollection(Collection<DetalleCompra> detalleCompraCollection) {
        this.detalleCompraCollection = detalleCompraCollection;
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
        if (!(object instanceof Componentes)) {
            return false;
        }
        Componentes other = (Componentes) object;
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
