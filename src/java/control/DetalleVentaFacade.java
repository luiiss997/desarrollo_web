/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.DetalleVenta;

/**
 *
 * @author Luis
 */
@Stateless
public class DetalleVentaFacade extends AbstractFacade<DetalleVenta> {

    @PersistenceContext(unitName = "desarrollo_webPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleVentaFacade() {
        super(DetalleVenta.class);
    }
    
    public List<DetalleVenta> listaActivos(){
        Query consulta = em.createNamedQuery("DetalleVenta.findActive",DetalleVenta.class);
        List<DetalleVenta> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
    
    public List<DetalleVenta> listaEliminados(){
        Query consulta = em.createNamedQuery("DetalleVenta.findDelated",DetalleVenta.class);
        List<DetalleVenta> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
    
}
