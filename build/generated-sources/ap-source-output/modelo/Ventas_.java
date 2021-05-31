package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.DetalleVenta;
import modelo.Envios;
import modelo.TiposPago;
import modelo.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-30T22:24:19")
@StaticMetamodel(Ventas.class)
public class Ventas_ { 

    public static volatile SingularAttribute<Ventas, Date> fecha;
    public static volatile SingularAttribute<Ventas, Double> total;
    public static volatile SingularAttribute<Ventas, Usuarios> idCliente;
    public static volatile SingularAttribute<Ventas, TiposPago> idTipoPago;
    public static volatile SingularAttribute<Ventas, Double> iva;
    public static volatile SingularAttribute<Ventas, Double> subtotal;
    public static volatile SingularAttribute<Ventas, Usuarios> idUsuario;
    public static volatile CollectionAttribute<Ventas, Envios> enviosCollection;
    public static volatile SingularAttribute<Ventas, Integer> id;
    public static volatile SingularAttribute<Ventas, Integer> status;
    public static volatile CollectionAttribute<Ventas, DetalleVenta> detalleVentaCollection;

}