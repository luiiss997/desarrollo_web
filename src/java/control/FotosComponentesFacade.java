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
import modelo.FotosComponentes;

/**
 *
 * @author Luis
 */
@Stateless
public class FotosComponentesFacade extends AbstractFacade<FotosComponentes> {

    @PersistenceContext(unitName = "desarrollo_webPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FotosComponentesFacade() {
        super(FotosComponentes.class);
    }
    
    public List<FotosComponentes> listaActivos(){
        Query consulta = em.createNamedQuery("FotosComponentes.findActive",FotosComponentes.class);
        List<FotosComponentes> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
    
    public List<FotosComponentes> listaEliminados(){
        Query consulta = em.createNamedQuery("FotosComponentes.findDelated",FotosComponentes.class);
        List<FotosComponentes> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
    
}
