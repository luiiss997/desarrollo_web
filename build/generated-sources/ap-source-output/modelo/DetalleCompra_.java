package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Componentes;
import modelo.Compras;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-19T20:25:42")
@StaticMetamodel(DetalleCompra.class)
public class DetalleCompra_ { 

    public static volatile SingularAttribute<DetalleCompra, Double> precioCompra;
    public static volatile SingularAttribute<DetalleCompra, Compras> idCompra;
    public static volatile SingularAttribute<DetalleCompra, Componentes> idComponente;
    public static volatile SingularAttribute<DetalleCompra, Integer> id;
    public static volatile SingularAttribute<DetalleCompra, Integer> cantidad;
    public static volatile SingularAttribute<DetalleCompra, Double> precioVenta;
    public static volatile SingularAttribute<DetalleCompra, Integer> status;

}