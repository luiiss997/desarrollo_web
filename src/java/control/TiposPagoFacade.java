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
import modelo.TiposPago;

/**
 *
 * @author Luis
 */
@Stateless
public class TiposPagoFacade extends AbstractFacade<TiposPago> {

    @PersistenceContext(unitName = "desarrollo_webPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiposPagoFacade() {
        super(TiposPago.class);
    }
    
    public List<TiposPago> listaActivos(){
        Query consulta = em.createNamedQuery("TiposPago.findActive",TiposPago.class);
        List<TiposPago> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
    
     public List<TiposPago> listaEliminados(){
        Query consulta = em.createNamedQuery("TiposPago.findDelated",TiposPago.class);
        List<TiposPago> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
    
}
