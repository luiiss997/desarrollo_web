package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Direcciones;
import modelo.Paqueteria;
import modelo.Ventas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-02T10:25:46")
@StaticMetamodel(Envios.class)
public class Envios_ { 

    public static volatile SingularAttribute<Envios, Direcciones> idDireccion;
    public static volatile SingularAttribute<Envios, Date> fechaEnvio;
    public static volatile SingularAttribute<Envios, Date> fechaEntrega;
    public static volatile SingularAttribute<Envios, Paqueteria> idPaqueteria;
    public static volatile SingularAttribute<Envios, String> guiaSeguimiento;
    public static volatile SingularAttribute<Envios, Integer> id;
    public static volatile SingularAttribute<Envios, Integer> seguroEnvio;
    public static volatile SingularAttribute<Envios, Integer> status;
    public static volatile SingularAttribute<Envios, Ventas> idVenta;

}