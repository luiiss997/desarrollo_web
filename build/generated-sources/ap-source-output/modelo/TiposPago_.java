package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Ventas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-19T20:25:43")
@StaticMetamodel(TiposPago.class)
public class TiposPago_ { 

    public static volatile CollectionAttribute<TiposPago, Ventas> ventasCollection;
    public static volatile SingularAttribute<TiposPago, Integer> id;
    public static volatile SingularAttribute<TiposPago, String> nombre;
    public static volatile SingularAttribute<TiposPago, Integer> status;

}