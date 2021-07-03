package control;

import modelo.Compras;
import control.util.JsfUtil;
import control.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

@Named("comprasController")
@SessionScoped
public class ComprasController implements Serializable {

    @EJB
    private control.ComprasFacade ejbFacade;
    private List<Compras> items = null;
    private List<Compras> items2 = null;
    private Compras selected;
    private final Date today = new Date();
    private String mensaje = "";
    private boolean bnd = false;

    public ComprasController() {
    }

    public List<Compras> getItems2() {
        if (items2 == null) {
            // items = getFacade().findAll();
            items2 = ejbFacade.listaEliminados();
        }
        return items2;
    }

    public void setItems2(List<Compras> items2) {
        this.items2 = items2;
    }

    public Compras getSelected() {
        return selected;
    }

    public Date getToday() {
        return today;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setSelected(Compras selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ComprasFacade getFacade() {
        return ejbFacade;
    }

    public Compras prepareCreate() {
        selected = new Compras();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if (bnd) {
            selected.setStatus(1);
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ComprasCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos Incorrectos, Intentelo de nuevo", null));
        }
    }

    public void update() {
        if (bnd) {
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ComprasUpdated"));
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null;
        }
    }

    public void destroy() {
        selected.setStatus(0);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ComprasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null;
        }
    }

    public void restaurar() {
        selected.setStatus(1);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ComprasUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null;
        }
    }

    public List<Compras> getItems() {
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

    public Compras getCompras(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Compras> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Compras> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Compras.class)
    public static class ComprasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ComprasController controller = (ComprasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "comprasController");
            return controller.getCompras(getKey(value));
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
            if (object instanceof Compras) {
                Compras o = (Compras) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Compras.class.getName()});
                return null;
            }
        }

    }

    public void validarCompra(AjaxBehaviorEvent event) {
        if (selected.getSubtotal() >= 1 && selected.getTotal() >= 1) {
            if (selected.getSubtotal() < selected.getTotal()) {
                bnd = true;
                mensaje = "";
            } else {
                bnd = false;
                mensaje = "El subtotal no puede ser mayor o igual al total";
            }
        } else {
            bnd = false;
            mensaje = "Los precios no pueden ser menor o igual a 0";
        }
    }

}
