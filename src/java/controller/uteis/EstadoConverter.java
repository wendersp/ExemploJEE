package controller.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.ejb.EstadoFacadeRemoto;
import model.entidades.Estado;

/**
 *
 * @author wender
 */
@FacesConverter("estadoConverter")
public class EstadoConverter implements Converter {

    private EstadoFacadeRemoto estadoEJB;

    private EstadoFacadeRemoto getSessionEJB() {
        try {
            return (EstadoFacadeRemoto) new InitialContext()
                    .lookup("model.ejb.EstadoFacadeRemoto");
        } catch (NamingException ex) {
            FacesMessage fmsg = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Converter Estado.", "Error ao obter EstadoFacade. "
                    + ex.getMessage());
            throw new ConverterException(fmsg);
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            estadoEJB = getSessionEJB();
            if (value != null && value.trim().length() > 0) {
                Long id = Long.parseLong(value);
                Estado estado = (Estado) estadoEJB.pesquisar(id);
                return estado;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        if (value != null) {
            Estado estado = (Estado) value;
            return estado.getId().toString();
        }
        return null;
    }

}
