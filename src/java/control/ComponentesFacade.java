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
import modelo.Componentes;

/**
 *
 * @author Luis
 */
@Stateless
public class ComponentesFacade extends AbstractFacade<Componentes> {

    @PersistenceContext(unitName = "desarrollo_webPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComponentesFacade() {
        super(Componentes.class);
    }
    
    public List<Componentes> listaActivos(){
        Query consulta = em.createNamedQuery("Componentes.findActive",Componentes.class);
        List<Componentes> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
    
     public List<Componentes> listaEliminados(){
        Query consulta = em.createNamedQuery("Componentes.findDelated",Componentes.class);
        List<Componentes> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
    
}
