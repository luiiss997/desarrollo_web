package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.DetalleCompra;
import modelo.Proveedores;
import modelo.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-31T21:31:53")
@StaticMetamodel(Compras.class)
public class Compras_ { 

    public static volatile SingularAttribute<Compras, Date> fecha;
    public static volatile SingularAttribute<Compras, Double> total;
    public static volatile CollectionAttribute<Compras, DetalleCompra> detalleCompraCollection;
    public static volatile SingularAttribute<Compras, Double> iva;
    public static volatile SingularAttribute<Compras, Double> subtotal;
    public static volatile SingularAttribute<Compras, Proveedores> idProveedor;
    public static volatile SingularAttribute<Compras, Usuarios> idUsuario;
    public static volatile SingularAttribute<Compras, Integer> id;
    public static volatile SingularAttribute<Compras, Integer> status;

}