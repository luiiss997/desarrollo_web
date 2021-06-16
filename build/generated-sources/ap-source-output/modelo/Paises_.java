package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Estados;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-15T20:48:17")
@StaticMetamodel(Paises.class)
public class Paises_ { 

    public static volatile SingularAttribute<Paises, String> clave;
    public static volatile CollectionAttribute<Paises, Estados> estadosCollection;
    public static volatile SingularAttribute<Paises, Integer> id;
    public static volatile SingularAttribute<Paises, String> nombre;
    public static volatile SingularAttribute<Paises, Integer> status;

}