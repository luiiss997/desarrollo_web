package control;

import modelo.FotosComponentes;
import control.util.JsfUtil;
import control.util.JsfUtil.PersistAction;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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
import org.primefaces.model.UploadedFile;

@Named("fotosComponentesController")
@SessionScoped
public class FotosComponentesController implements Serializable {

    @EJB
    private control.FotosComponentesFacade ejbFacade;
    private List<FotosComponentes> items = null;
    private List<FotosComponentes> items2 = null;
    private FotosComponentes selected;

    private UploadedFile file;
    private String aux;

    public FotosComponentesController() {
    }

    public List<FotosComponentes> getItems2() {
        if (items2 == null) {
            // items = getFacade().findAll();
            items2 = ejbFacade.listaEliminados();
        }
        return items2;
    }

    public void setItems2(List<FotosComponentes> items2) {
        this.items2 = items2;
    }

    public FotosComponentes getSelected() {
        return selected;
    }

    public void setSelected(FotosComponentes selected) {
        this.selected = selected;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getAux() {
        return aux;
    }

    public void setAux(String aux) {
        this.aux = aux;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FotosComponentesFacade getFacade() {
        return ejbFacade;
    }

    public FotosComponentes prepareCreate() {
        selected = new FotosComponentes();
        initializeEmbeddableKey();
        return selected;
    }

    public void agregarFoto() {
        if (getFile().getFileName().endsWith(".png") || getFile().getFileName().endsWith(".jpg") || getFile().getFileName().endsWith(".pdf")) {
            if (subirFile()) {
                create();
                aux = "";
            } else {
                System.out.println("Error al subir archivo");
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tipo de archivo no es el adecuado", null));
        }
    }

    public void modificarFoto() {
        if (getFile().getFileName().endsWith(".png") || getFile().getFileName().endsWith(".jpg") || getFile().getFileName().endsWith(".pdf")) {
            if (subirFile()) {
                update();
                aux = "";
            } else {
                System.out.println("Error al subir archivo");
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tipo de archivo no es el adecuado", null));
        }
    }

    public void create() {
        selected.setRuta(aux);
        selected.setStatus(1);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FotosComponentesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setRuta(aux);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FotosComponentesUpdated"));
        items = null;
        items2 = null;
    }

    public void destroy() {
        selected.setStatus(0);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FotosComponentesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null;
        }
    }

    public void restaurar() {
        selected.setStatus(1);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FotosComponentesUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null;
        }
    }

    public List<FotosComponentes> getItems() {
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

    public FotosComponentes getFotosComponentes(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<FotosComponentes> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<FotosComponentes> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = FotosComponentes.class)
    public static class FotosComponentesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FotosComponentesController controller = (FotosComponentesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fotosComponentesController");
            return controller.getFotosComponentes(getKey(value));
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
            if (object instanceof FotosComponentes) {
                FotosComponentes o = (FotosComponentes) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), FotosComponentes.class.getName()});
                return null;
            }
        }

    }

    private boolean subirFile() {
        try {
            aux = "/resources/img_componentes";

            File arch = new File(JsfUtil.getPath() + aux);
            if (!arch.exists()) {
                arch.mkdirs();
            }
            copiarArchivo(getFile().getFileName(), getFile().getInputstream());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void copiarArchivo(String nombre_arch, InputStream input) {
        try {
            aux = aux + "/" + nombre_arch;
            OutputStream out = new FileOutputStream(new File(JsfUtil.getPath() + aux));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = input.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            input.close();
            out.flush();
            out.close();
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("Error al salvar el archivo"));
        }
    }

}
