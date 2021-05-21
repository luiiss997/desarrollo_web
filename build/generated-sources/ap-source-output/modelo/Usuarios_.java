package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Compras;
import modelo.Direcciones;
import modelo.TiposUsuario;
import modelo.Ventas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-19T20:25:43")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile CollectionAttribute<Usuarios, Ventas> ventasCollection1;
    public static volatile SingularAttribute<Usuarios, String> password;
    public static volatile CollectionAttribute<Usuarios, Compras> comprasCollection;
    public static volatile CollectionAttribute<Usuarios, Direcciones> direccionesCollection;
    public static volatile SingularAttribute<Usuarios, TiposUsuario> idTipoUsu;
    public static volatile CollectionAttribute<Usuarios, Ventas> ventasCollection;
    public static volatile SingularAttribute<Usuarios, Integer> id;
    public static volatile SingularAttribute<Usuarios, String> apMat;
    public static volatile SingularAttribute<Usuarios, String> nombre;
    public static volatile SingularAttribute<Usuarios, String> email;
    public static volatile SingularAttribute<Usuarios, String> apPat;
    public static volatile SingularAttribute<Usuarios, Integer> status;

}