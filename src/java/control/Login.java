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
public class Login implements Serializable {

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

    public Login() {
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
            httpServletRequest.getSession().setAttribute("nombre_completo", usuario_auth.getNombre() + " " + usuario_auth.getApPat() + " " + usuario_auth.getApMat());
            switch (usuario_auth.getIdTipoUsu().getNivel()) {
                case 1://Administrador
                case 2: //Supervisor                  
                case 3://Venta online
                    FacesContext.getCurrentInstance().getExternalContext().redirect("empresa/home.xhtml");
                    break;
                case 4://Cliente
                    FacesContext.getCurrentInstance().getExternalContext().redirect("cliente/home.xhtml");
                    break;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrectos", null));
        }
    }

    public void cerrarSesion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/desarrollo_web/faces/views/home.xhtml");
        } catch (Exception e) {

        }
    }

    public void verifySesionAndLevels(int niveles) throws IOException {
        httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Usuarios user = (Usuarios) httpServletRequest.getSession().getAttribute("usuario");
        if (user != null) {
            int nivel = user.getIdTipoUsu().getNivel();
            if (nivel != 1) {
                switch (niveles) {
                    case 1:
                        if (nivel == 4) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/desarrollo_web/faces/views/cliente/sin_privilegios.xhtml");
                        } else {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/desarrollo_web/faces/views/empresa/sin_privilegios.xhtml");
                        }
                        break;
                    case 123:
                        if (nivel == 4) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/desarrollo_web/faces/views/cliente/sin_privilegios.xhtml");
                        }
                        break;
                    case 12:
                        if (nivel == 4) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/desarrollo_web/faces/views/cliente/sin_privilegios.xhtml");
                        } else {
                            if (nivel == 3) {
                                FacesContext.getCurrentInstance().getExternalContext().redirect("/desarrollo_web/faces/views/empresa/sin_privilegios.xhtml");
                            }
                        }
                        break;
                    case 13:
                        if (nivel == 4) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/desarrollo_web/faces/views/cliente/sin_privilegios.xhtml");
                        } else {
                            if (nivel == 2) {
                                FacesContext.getCurrentInstance().getExternalContext().redirect("/desarrollo_web/faces/views/empresa/sin_privilegios.xhtml");
                            }
                        }
                        break;
                }
            }
        } else {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/desarrollo_web/faces/views/login.xhtml");
        }
    }

    public void verificaCliente() throws IOException {
        httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Usuarios user = (Usuarios) httpServletRequest.getSession().getAttribute("usuario");
        if (user != null) {
            if (user.getIdTipoUsu().getNivel() != 4) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/desarrollo_web/faces/views/empresa/sin_privilegios.xhtml");
            }
        } else {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/desarrollo_web/faces/views/login.xhtml");
        }
    }

}
