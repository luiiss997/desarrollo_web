package control;

import modelo.Componentes;
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
import modelo.Marca;
import modelo.Modelo;

@Named("componentesController")
@SessionScoped
public class ComponentesController implements Serializable {

    @EJB
    private control.ComponentesFacade ejbFacade;
    private List<Componentes> items = null;
    private List<Componentes> items2 = null;
    private Componentes selected;
    private boolean bnd = false, bnd2 = false;
    private String mensaje = "";

    @EJB
    private control.MarcaFacade ejbMarca;
    @EJB
    private control.ModeloFacade ejbModelo;
    private List<Marca> listMarcas;
    private List<Modelo> listModelos;

    public ComponentesController() {
    }

    public List<Componentes> getItems2() {
        if (items2 == null) {
            // items = getFacade().findAll();
            items2 = ejbFacade.listaEliminados();
        }
        return items2;
    }

    public void setItems2(List<Componentes> items2) {
        this.items2 = items2;
    }

    public Componentes getSelected() {
        return selected;
    }

    public void setSelected(Componentes selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public String getMensaje() {
        return mensaje;
    }

    private ComponentesFacade getFacade() {
        return ejbFacade;
    }

    public MarcaFacade getEjbMarca() {
        return ejbMarca;
    }

    public void setEjbMarca(MarcaFacade ejbMarca) {
        this.ejbMarca = ejbMarca;
    }

    public ModeloFacade getEjbModelo() {
        return ejbModelo;
    }

    public void setEjbModelo(ModeloFacade ejbModelo) {
        this.ejbModelo = ejbModelo;
    }

    public List<Marca> getListMarcas() {
        return listMarcas;
    }

    public void setListMarcas(List<Marca> listMarcas) {
        this.listMarcas = listMarcas;
    }

    public List<Modelo> getListModelos() {
        return listModelos;
    }

    public void setListModelos(List<Modelo> listModelos) {
        this.listModelos = listModelos;
    }

    public Componentes prepareCreate() {
        selected = new Componentes();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if (bnd && bnd2) {
            selected.setStatus(1);
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ComponentesCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos Incorrectos, Intentelo de nuevo", null));
        }
    }

    public void update() {
        if (bnd && bnd2) {
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ComponentesUpdated"));
            items = null;
            items2 = null;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos Incorrectos, Intentelo de nuevo", null));
        }
    }

    public void destroy() {
        selected.setStatus(0);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ComponentesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null;
        }
    }

    public void restaurar() {
        selected.setStatus(1);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ComponentesUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null;
        }
    }

    public List<Componentes> getItems() {
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

    public Componentes getComponentes(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Componentes> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Componentes> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Componentes.class)
    public static class ComponentesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ComponentesController controller = (ComponentesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "componentesController");
            return controller.getComponentes(getKey(value));
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
            if (object instanceof Componentes) {
                Componentes o = (Componentes) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Componentes.class.getName()});
                return null;
            }
        }

    }

    public void obtenerMarcas(AjaxBehaviorEvent event) {
        System.out.println("id categoria = " + selected.getIdCategoria().getId());
        listMarcas = ejbMarca.busquedaCategorias(selected.getIdCategoria().getId());
        listModelos = ejbModelo.busquedaMarcas(1, selected.getIdCategoria().getId());
    }

    public void obtenerModelos(AjaxBehaviorEvent event) {
        System.out.println("id marca = " + selected.getIdMarca().getId());
        listModelos = ejbModelo.busquedaMarcas(selected.getIdMarca().getId(), selected.getIdCategoria().getId());
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

    public void validarExistencias(AjaxBehaviorEvent event) {
        if (selected.getExistencia() >= selected.getStock()) {
            bnd2 = false;
            mensaje = "La existencia no puede ser mayor que el stock";
        } else {
            bnd2 = true;
            mensaje = "";
        }
    }

}
