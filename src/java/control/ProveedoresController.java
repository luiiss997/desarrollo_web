package control;

import modelo.Proveedores;
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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import modelo.Estados;
import modelo.Municipios;
import modelo.Paises;

@Named("proveedoresController")
@SessionScoped
public class ProveedoresController implements Serializable {

    @EJB
    private control.ProveedoresFacade ejbFacade;
    private List<Proveedores> items = null;
    private List<Proveedores> items2 = null;
    private Proveedores selected;
    
    @EJB
    private control.EstadosFacade ejbEstados;
    @EJB
    private control.MunicipiosFacade ejbFacadeMunicipios;
       
    private Paises pais;
    private List<Estados> listaEstados;
    private List<Municipios> listaMunicipios;
    
    public ProveedoresController() {
    }
    
    public List<Proveedores> getItems2() {
        if (items2 == null) {
           // items = getFacade().findAll();
            items2 = ejbFacade.listaEliminados();
        }
        return items2;
    }

    public void setItems2(List<Proveedores> items2) {
        this.items2 = items2;
    }

    public Proveedores getSelected() {
        return selected;
    }

    public void setSelected(Proveedores selected) {
        this.selected = selected;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public List<Estados> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estados> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<Municipios> getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List<Municipios> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProveedoresFacade getFacade() {
        return ejbFacade;
    }

    public Proveedores prepareCreate() {
        selected = new Proveedores();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setStatus(1);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProveedoresCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProveedoresUpdated"));
        items = null;
        items2 = null; 
    }

    public void destroy() {
        selected.setStatus(0);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProveedoresDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null; 
        }
    }
    
    public void restaurar() {
        selected.setStatus(1);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProveedoresUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null; 
        }
    }

    public List<Proveedores> getItems() {
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

    public Proveedores getProveedores(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Proveedores> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Proveedores> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Proveedores.class)
    public static class ProveedoresControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProveedoresController controller = (ProveedoresController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "proveedoresController");
            return controller.getProveedores(getKey(value));
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
            if (object instanceof Proveedores) {
                Proveedores o = (Proveedores) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Proveedores.class.getName()});
                return null;
            }
        }

    }
    
    
    public void obtenerEstados(AjaxBehaviorEvent event){
        System.out.println("id pais="+pais.getId());
        listaEstados = ejbEstados.busquedaPais(pais.getId());
        listaMunicipios = null;
    }
    
    public void obtenerMunicipios(AjaxBehaviorEvent event){
        System.out.println("id estado = "+selected.getIdEstado().getId());
        listaMunicipios=ejbFacadeMunicipios.busquedaEstado(selected.getIdEstado().getId());
    }

}
