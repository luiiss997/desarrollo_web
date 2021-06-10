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
import modelo.Modelo;

/**
 *
 * @author Luis
 */
@Stateless
public class ModeloFacade extends AbstractFacade<Modelo> {

    @PersistenceContext(unitName = "desarrollo_webPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModeloFacade() {
        super(Modelo.class);
    }
    
    public List<Modelo> listaActivos(){
        Query consulta = em.createNamedQuery("Modelo.findActive",Modelo.class);
        List<Modelo> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
    
     public List<Modelo> listaEliminados(){
        Query consulta = em.createNamedQuery("Modelo.findDelated",Modelo.class);
        List<Modelo> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
     
     public List<Modelo> busquedaMarcas(int idMarca, int idcar){
        Query consulta = em.createNamedQuery("Modelo.findByMarcayCategoria",Modelo.class)
                .setParameter("idmarca", idMarca)
                .setParameter("idcat", idcar);
        List<Modelo> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
}
