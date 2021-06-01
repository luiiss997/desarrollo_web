package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Envios;
import modelo.Estados;
import modelo.Municipios;
import modelo.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-31T21:31:53")
@StaticMetamodel(Direcciones.class)
public class Direcciones_ { 

    public static volatile SingularAttribute<Direcciones, Integer> codigoPostal;
    public static volatile SingularAttribute<Direcciones, String> noInterior;
    public static volatile SingularAttribute<Direcciones, String> calle;
    public static volatile SingularAttribute<Direcciones, Usuarios> idUsuario;
    public static volatile SingularAttribute<Direcciones, Municipios> idMunicipio;
    public static volatile CollectionAttribute<Direcciones, Envios> enviosCollection;
    public static volatile SingularAttribute<Direcciones, String> colonia;
    public static volatile SingularAttribute<Direcciones, String> noExterior;
    public static volatile SingularAttribute<Direcciones, String> calle2;
    public static volatile SingularAttribute<Direcciones, Estados> idEstado;
    public static volatile SingularAttribute<Direcciones, String> calle1;
    public static volatile SingularAttribute<Direcciones, Integer> id;
    public static volatile SingularAttribute<Direcciones, Integer> telefono;
    public static volatile SingularAttribute<Direcciones, Integer> status;

}