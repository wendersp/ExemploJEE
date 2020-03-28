package controller;

import controller.uteis.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.ejb.EstadoFacadeRemoto;
import model.entidades.Estado;

/**
 *
 * @author wender
 */
@Named(value = "estadoMBean")
@SessionScoped
public class EstadoMBean implements Serializable {

    @EJB
    private EstadoFacadeRemoto estadoEJB;

    private Estado estado;
    private List<Estado> lstEstado;
    private String valorPesquisa;

    public EstadoMBean() {
        lstEstado = new ArrayList();
        estado = new Estado();
        valorPesquisa = "";
    }

    public String novo() {
        estado = new Estado();
        return "EstadoFrm";
    }

    public void salvar() {
        try {
            estadoEJB.salvar(estado);
            estado = new Estado();
        } catch (Exception ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }

    public String editar() {
        return "EstadoFrm";
    }

    public void excluir() {
        try {
            estadoEJB.excluir(estado);
        } catch (Exception ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }

    public String getValorPesquisa() {
        return valorPesquisa;
    }

    public void setValorPesquisa(String valorPesquisa) {
        this.valorPesquisa = valorPesquisa;
    }

    public void pesquiar() {
        try {
            lstEstado = estadoEJB.pesquisar(valorPesquisa);
        } catch (Exception ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }

    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getLstEstado() {
        return lstEstado;
    }

    public void setLstEstado(List<Estado> lstEstado) {
        this.lstEstado = lstEstado;
    }

}
