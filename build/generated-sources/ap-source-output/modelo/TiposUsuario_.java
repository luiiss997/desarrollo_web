package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-02T10:25:46")
@StaticMetamodel(TiposUsuario.class)
public class TiposUsuario_ { 

    public static volatile CollectionAttribute<TiposUsuario, Usuarios> usuariosCollection;
    public static volatile SingularAttribute<TiposUsuario, Integer> id;
    public static volatile SingularAttribute<TiposUsuario, String> nombre;
    public static volatile SingularAttribute<TiposUsuario, Integer> nivel;
    public static volatile SingularAttribute<TiposUsuario, Integer> status;

}