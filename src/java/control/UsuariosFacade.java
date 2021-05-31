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
import modelo.Usuarios;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Luis
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "desarrollo_webPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
     public List<Usuarios> listaActivos(){
        Query consulta = em.createNamedQuery("Usuarios.findActive",Usuarios.class);
        List<Usuarios> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
    
     public List<Usuarios> listaEliminados(){
        Query consulta = em.createNamedQuery("Usuarios.findDelated",Usuarios.class);
        List<Usuarios> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista;
        }else{
            return null;
        }
    }
     //abi01@gmail.com 123456789
     public Usuarios Acceder(String user, String pw){
         String pwe = DigestUtils.sha1Hex(pw);
         Query query = em.createNamedQuery("Usuarios.Acceder", Usuarios.class)
                 .setParameter("email", user)
                 .setParameter("pass", pwe);
         List<Usuarios> list = query.getResultList();
         if(!list.isEmpty()){
             return list.get(0);
         }else{
             return null;
         }
     }
     
     public Usuarios buscarEmail(String email){
         Query query = em.createNamedQuery("Usuarios.findByEmail", Usuarios.class)
                 .setParameter("email", email);
         List<Usuarios> list = query.getResultList();
         if(!list.isEmpty()){
             return list.get(0);
         }else{
             return null;
         }
     }
     
}
