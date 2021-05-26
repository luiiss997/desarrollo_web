/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Usuarios;

/**
 *
 * @author Luis
 */
@Named(value = "login")
@SessionScoped
public class login implements Serializable {

    @EJB
    private UsuariosFacade ejbFacade;
    private HttpServletRequest httpServletRequest;

    private String username;
    private String password;
    private modelo.Usuarios usuario_auth;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuarios getUsuario_auth() {
        return usuario_auth;
    }

    public void setUsuario_auth(Usuarios usuario_auth) {
        this.usuario_auth = usuario_auth;
    }

    public login() {
        httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public void Acceso() throws IOException {
        httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        usuario_auth = ejbFacade.Acceder(username, password);
        if (usuario_auth != null) {//poner todas la variables de usuario
            httpServletRequest.getSession().setAttribute("usuario", usuario_auth);
            httpServletRequest.getSession().setAttribute("username", usuario_auth.getNombre());
            httpServletRequest.getSession().setAttribute("correo", usuario_auth.getEmail());
            httpServletRequest.getSession().setAttribute("nivel_usu", usuario_auth.getIdTipoUsu());
            httpServletRequest.getSession().setAttribute("nombre_completo", usuario_auth.getNombre()+" "+ usuario_auth.getApPat() +" "+ usuario_auth.getApMat());
            switch (usuario_auth.getIdTipoUsu().getNivel()) {
                case 1://Administrador
                    
                    break;
                case 2: //Supervisor
                    FacesContext.getCurrentInstance().getExternalContext().redirect("supervisor/home.xhtml");
                    break;
                case 3://Venta online
                    
                    break;
                case 4://Cliente
                    
                    break;
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario o contraseña incorrectos", null));
        }
    }
    
    public void cerrarSesion(){
        try{
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/desarrollo_web/faces/views/home.xhtml");
        }catch(Exception e){
            
        }
    }
    
    public void verificaUsuario(int lvl)throws IOException{
        httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Usuarios user= (Usuarios) httpServletRequest.getSession().getAttribute("usuario");
        if(user!=null){
            if(user.getIdTipoUsu().getNivel() == lvl){
                
            }
        }
    }

}
