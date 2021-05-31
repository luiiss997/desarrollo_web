package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Direcciones;
import modelo.Municipios;
import modelo.Paises;
import modelo.Proveedores;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-30T22:24:19")
@StaticMetamodel(Estados.class)
public class Estados_ { 

    public static volatile CollectionAttribute<Estados, Direcciones> direccionesCollection;
    public static volatile SingularAttribute<Estados, Paises> idPais;
    public static volatile CollectionAttribute<Estados, Proveedores> proveedoresCollection;
    public static volatile SingularAttribute<Estados, Integer> id;
    public static volatile SingularAttribute<Estados, String> nombre;
    public static volatile CollectionAttribute<Estados, Municipios> municipiosCollection;
    public static volatile SingularAttribute<Estados, Integer> status;

}