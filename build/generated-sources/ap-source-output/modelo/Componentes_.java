package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Categorias;
import modelo.DetalleCompra;
import modelo.DetalleVenta;
import modelo.FotosComponentes;
import modelo.Marca;
import modelo.Modelo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-31T20:16:29")
@StaticMetamodel(Componentes.class)
public class Componentes_ { 

    public static volatile SingularAttribute<Componentes, String> descripcion;
    public static volatile SingularAttribute<Componentes, String> tipo;
    public static volatile CollectionAttribute<Componentes, DetalleCompra> detalleCompraCollection;
    public static volatile SingularAttribute<Componentes, String> resolucion;
    public static volatile SingularAttribute<Componentes, String> certificacion;
    public static volatile SingularAttribute<Componentes, String> fForma;
    public static volatile SingularAttribute<Componentes, String> nombre;
    public static volatile SingularAttribute<Componentes, Integer> existencia;
    public static volatile SingularAttribute<Componentes, String> potencia;
    public static volatile SingularAttribute<Componentes, String> size;
    public static volatile CollectionAttribute<Componentes, FotosComponentes> fotosComponentesCollection;
    public static volatile SingularAttribute<Componentes, Double> precioCompra;
    public static volatile SingularAttribute<Componentes, Integer> id;
    public static volatile SingularAttribute<Componentes, Modelo> idModelo;
    public static volatile SingularAttribute<Componentes, String> socket;
    public static volatile SingularAttribute<Componentes, Integer> stock;
    public static volatile SingularAttribute<Componentes, Categorias> idCategoria;
    public static volatile SingularAttribute<Componentes, Marca> idMarca;
    public static volatile SingularAttribute<Componentes, Double> precioVenta;
    public static volatile SingularAttribute<Componentes, String> velocidad;
    public static volatile SingularAttribute<Componentes, String> vRam;
    public static volatile SingularAttribute<Componentes, String> capacidad;
    public static volatile SingularAttribute<Componentes, Integer> status;
    public static volatile CollectionAttribute<Componentes, DetalleVenta> detalleVentaCollection;

}