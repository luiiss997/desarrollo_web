package control;

import modelo.DetalleVenta;
import control.util.JsfUtil;
import control.util.JsfUtil.PersistAction;

import java.io.Serializable;
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

@Named("detalleVentaController")
@SessionScoped
public class DetalleVentaController implements Serializable {

    @EJB
    private control.DetalleVentaFacade ejbFacade;
    private List<DetalleVenta> items = null;
    private List<DetalleVenta> items2 = null;
    private DetalleVenta selected;
    private String mensaje = "";
    private boolean bnd = false, bnd2 = false;

    public DetalleVentaController() {
    }

    public List<DetalleVenta> getItems2() {
        if (items2 == null) {
            // items = getFacade().findAll();
            items2 = ejbFacade.listaEliminados();
        }
        return items2;
    }

    public void setItems2(List<DetalleVenta> items2) {
        this.items2 = items2;
    }

    public DetalleVenta getSelected() {
        return selected;
    }

    public void setSelected(DetalleVenta selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public String getMensaje() {
        return mensaje;
    }

    private DetalleVentaFacade getFacade() {
        return ejbFacade;
    }

    public DetalleVenta prepareCreate() {
        selected = new DetalleVenta();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if (bnd && bnd2) {
            selected.setStatus(1);
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DetalleVentaCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos Incorrectos, Intentelo de nuevo", null));
        }
    }

    public void update() {
        if (bnd && bnd2) {
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetalleVentaUpdated"));
            items = null;
            items2 = null;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos Incorrectos, Intentelo de nuevo", null));
        }
    }

    public void destroy() {
        selected.setStatus(0);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetalleVentaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null;
        }
    }

    public void restaurar() {
        selected.setStatus(1);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetalleVentaUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null;
        }
    }

    public List<DetalleVenta> getItems() {
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

    public DetalleVenta getDetalleVenta(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<DetalleVenta> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DetalleVenta> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DetalleVenta.class)
    public static class DetalleVentaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleVentaController controller = (DetalleVentaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleVentaController");
            return controller.getDetalleVenta(getKey(value));
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
            if (object instanceof DetalleVenta) {
                DetalleVenta o = (DetalleVenta) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetalleVenta.class.getName()});
                return null;
            }
        }

    }

    public void validarPrecios(AjaxBehaviorEvent event) {
        if (selected.getPrecioCompra() >= selected.getPrecioVenta()) {
            bnd = false;
            mensaje = "El precio de venta no puede ser mayor al precio de compra";
        } else {
            if (selected.getPrecioVenta() < 100) {
                bnd = false;
                mensaje = "El precio de venta es muy corto";
            } else {
                bnd = true;
                mensaje = "";
            }
        }
    }

    public void validarCantidad(AjaxBehaviorEvent event) {
        if (selected.getCantidad()>= 20) {
            bnd2 = false;
            mensaje = "La cantidad de venta es excesiva";
        } else {
            bnd2 = true;
            mensaje = "";
        }
    }

}
