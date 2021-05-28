package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Componentes;
import modelo.Marca;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-27T21:03:36")
@StaticMetamodel(Categorias.class)
public class Categorias_ { 

    public static volatile SingularAttribute<Categorias, String> descripcion;
    public static volatile CollectionAttribute<Categorias, Componentes> componentesCollection;
    public static volatile CollectionAttribute<Categorias, Marca> marcaCollection;
    public static volatile SingularAttribute<Categorias, Integer> id;
    public static volatile SingularAttribute<Categorias, String> nombre;
    public static volatile SingularAttribute<Categorias, Integer> status;

}