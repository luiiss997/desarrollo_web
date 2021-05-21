package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Categorias;
import modelo.Componentes;
import modelo.Modelo;
import modelo.Proveedores;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-19T20:25:43")
@StaticMetamodel(Marca.class)
public class Marca_ { 

    public static volatile CollectionAttribute<Marca, Modelo> modeloCollection;
    public static volatile CollectionAttribute<Marca, Componentes> componentesCollection;
    public static volatile SingularAttribute<Marca, Proveedores> idProveedor;
    public static volatile SingularAttribute<Marca, Integer> id;
    public static volatile SingularAttribute<Marca, Categorias> idCategoria;
    public static volatile SingularAttribute<Marca, String> nombre;
    public static volatile SingularAttribute<Marca, Integer> status;

}