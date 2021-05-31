/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import modelo.Componentes;

/**
 *
 * @author Luis
 */
@Named(value = "ajax")
@SessionScoped
public class Ajax implements Serializable {
    private modelo.Componentes idProducto;
    private int cantidad;
    private String mensaje;

    /**
     * Creates a new instance of ajax
     */
    public Ajax() {
    }

    public Componentes getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Componentes idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }   
    
}
