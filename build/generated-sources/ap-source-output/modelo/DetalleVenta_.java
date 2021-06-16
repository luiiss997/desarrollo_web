package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Componentes;
import modelo.Ventas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-15T20:48:17")
@StaticMetamodel(DetalleVenta.class)
public class DetalleVenta_ { 

    public static volatile SingularAttribute<DetalleVenta, Double> precioCompra;
    public static volatile SingularAttribute<DetalleVenta, Componentes> idComponente;
    public static volatile SingularAttribute<DetalleVenta, Integer> id;
    public static volatile SingularAttribute<DetalleVenta, Integer> cantidad;
    public static volatile SingularAttribute<DetalleVenta, Double> precioVenta;
    public static volatile SingularAttribute<DetalleVenta, Integer> status;
    public static volatile SingularAttribute<DetalleVenta, Ventas> idVenta;

}