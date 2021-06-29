package control;

import modelo.Usuarios;
import control.util.JsfUtil;
import control.util.JsfUtil.PersistAction;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;

@Named("usuariosController")
@SessionScoped
public class UsuariosController implements Serializable {

    @EJB
    private control.UsuariosFacade ejbFacade;
    private List<Usuarios> items = null;
    private List<Usuarios> items2 = null;
    private Usuarios selected;
    private String password2;

    private String mensaje, mensaje2;
    private boolean bnd = false, bnd2 = false;

    public UsuariosController() {
    }
    
    public List<Usuarios> getItems2() {
        if (items2 == null) {
            // items = getFacade().findAll();
            items2 = ejbFacade.listaEliminados();
        }
        return items2;
    }

    public void setItems2(List<Usuarios> items2) {
        this.items2 = items2;
    }

    public Usuarios getSelected() {
        return selected;
    }

    public void setSelected(Usuarios selected) {
        this.selected = selected;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje2() {
        return mensaje2;
    }

    public void setMensaje2(String mensaje2) {
        this.mensaje2 = mensaje2;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuariosFacade getFacade() {
        return ejbFacade;
    }

    public void registrar() throws IOException {
        if (bnd && bnd2) {
            selected.setStatus(1);

            String pw = selected.getPassword();
            String pwe = DigestUtils.sha1Hex(pw);
            selected.setPassword(pwe);

            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosCreated"));

            HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            httpServletRequest.getSession().setAttribute("usuario", selected);
            httpServletRequest.getSession().setAttribute("username", selected.getNombre());
            httpServletRequest.getSession().setAttribute("correo", selected.getEmail());
            httpServletRequest.getSession().setAttribute("nivel_usu", selected.getIdTipoUsu().getNivel());
            httpServletRequest.getSession().setAttribute("nombre_completo", selected.getNombre() + " " + selected.getApPat() + " " + selected.getApMat());
            switch (selected.getIdTipoUsu().getNivel()) {
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifica los campos de registro", null));
        }
    }

    public void verificaremail2(AjaxBehaviorEvent event) {
        System.out.println("Email: " + selected.getEmail());
        if (veryfyEmail(selected.getEmail())) {
            mensaje = "";
            bnd = true;
        } else {
            mensaje = "Este no es un correo válido";
            bnd = false;
        }
    }

    public void verificarEmail(AjaxBehaviorEvent event) {
        System.out.println("Email: " + selected.getEmail());
        if (veryfyEmail(selected.getEmail())) {
            Usuarios correo = ejbFacade.buscarEmail(selected.getEmail());

            if (correo != null) {
                mensaje = "Este correo ya está registrado";
                bnd = false;
            } else {
                mensaje = "";
                bnd = true;
            }
        } else {
            mensaje = "Este no es un correo válido";
            bnd = false;
        }
    }

    public void verificarPassword(AjaxBehaviorEvent event) {
        System.out.println("Pass 1: " + selected.getPassword());
        System.out.println("Pass 2: " + password2);
        if (selected.getPassword().equals(password2)) {
            mensaje2 = "";
            bnd2 = true;
        } else {
            mensaje2 = "Las contraseñas no coiciden";
            bnd2 = false;
        }
    }

    public Usuarios prepareCreate() {
        mensaje = "";
        selected = new Usuarios();
        initializeEmbeddableKey();
        return selected;
    }

    public void prepareCreate2() {
        mensaje = "";
        mensaje2 = "";
        initializeEmbeddableKey();
        selected = new Usuarios();
        selected.setPassword("");
    }

    public void create() {
        if (bnd) {
            selected.setStatus(1);

            String pwe = DigestUtils.sha1Hex(selected.getPassword());
            selected.setPassword(pwe);

            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null));
        }
    }

    public void update() {     
        if (bnd) {
            String pwe = DigestUtils.sha1Hex(selected.getPassword());
            selected.setPassword(pwe);

            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosUpdated"));
            items = null;
            items2 = null;
        } else {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null));
        }       
    }
    
    public void actualizar() {     
        if (veryfyEmail(selected.getEmail())) {          
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosUpdated"));
            items = null;
            items2 = null;
        } else {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El correo no es valido", null));
        }       
    }

    public void destroy() {
        selected.setStatus(0);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null;
        }
    }

    public void restaurar() {
        selected.setStatus(1);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null;
        }
    }

    public List<Usuarios> getItems() {
        if (items == null) {
            items = getFacade().listaActivos();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Usuarios getUsuarios(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usuarios> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuarios> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Usuarios.class)
    public static class UsuariosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuariosController controller = (UsuariosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuariosController");
            return controller.getUsuarios(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usuarios) {
                Usuarios o = (Usuarios) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuarios.class.getName()});
                return null;
            }
        }
    }
    
    public boolean veryfyEmail(String correo){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(correo);
        return mather.find();      
    }

}
