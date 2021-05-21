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
import modelo.TiposUsuario;

/**
 *
 * @author Luis
 */
@Stateless
public class TiposUsuarioFacade extends AbstractFacade<TiposUsuario> {

    @PersistenceContext(unitName = "desarrollo_webPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiposUsuarioFacade() {
        super(TiposUsuario.class);
    }
    
    public List<TiposUsuario> listaActivos(){
        Query consulta = em.createNamedQuery("TiposUsuario.findActive",TiposUsuario.class);
        List<TiposUsuario> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
    
     public List<TiposUsuario> listaEliminados(){
        Query consulta = em.createNamedQuery("TiposUsuario.findDelated",TiposUsuario.class);
        List<TiposUsuario> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
}
