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
import modelo.Estados;

/**
 *
 * @author Luis
 */
@Stateless
public class EstadosFacade extends AbstractFacade<Estados> {

    @PersistenceContext(unitName = "desarrollo_webPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadosFacade() {
        super(Estados.class);
    }
    
    public List<Estados> listaActivos(){
        Query consulta = em.createNamedQuery("Estados.findActive",Estados.class);
        List<Estados> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
    
    public List<Estados> listaEliminados(){
        Query consulta = em.createNamedQuery("Estados.findDelated",Estados.class);
        List<Estados> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
    
}
