package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Envios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-30T22:24:19")
@StaticMetamodel(Paqueteria.class)
public class Paqueteria_ { 

    public static volatile SingularAttribute<Paqueteria, Integer> precioEnvio;
    public static volatile CollectionAttribute<Paqueteria, Envios> enviosCollection;
    public static volatile SingularAttribute<Paqueteria, Integer> id;
    public static volatile SingularAttribute<Paqueteria, String> nombre;
    public static volatile SingularAttribute<Paqueteria, Integer> status;

}