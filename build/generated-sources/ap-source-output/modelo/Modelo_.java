package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Categorias;
import modelo.Componentes;
import modelo.Marca;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-31T21:31:53")
@StaticMetamodel(Modelo.class)
public class Modelo_ { 

    public static volatile CollectionAttribute<Modelo, Componentes> componentesCollection;
    public static volatile SingularAttribute<Modelo, Integer> id;
    public static volatile SingularAttribute<Modelo, Marca> idMarca;
    public static volatile SingularAttribute<Modelo, Categorias> idCategoria;
    public static volatile SingularAttribute<Modelo, String> nombre;
    public static volatile SingularAttribute<Modelo, Integer> status;

}