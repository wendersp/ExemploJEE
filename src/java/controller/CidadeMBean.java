/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.uteis.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.ejb.CidadeFacadeRemoto;
import model.ejb.EstadoFacadeRemoto;
import model.entidades.Cidade;
import model.entidades.Estado;

/**
 *
 * @author wender
 */
@Named(value = "cidadeMBean")
@SessionScoped
public class CidadeMBean implements Serializable {

    @EJB
    private CidadeFacadeRemoto cidadeEJB;
    @EJB
    private EstadoFacadeRemoto estadoEJB;
    private Cidade cidade;
    private List<Cidade> lstCidade;
    private List<Estado> lstEstado;

    private String valorPesquisa;

    /**
     * Creates a new instance of CidadeConsMBean
     */
    public CidadeMBean() {
        lstCidade = new ArrayList();
        cidade = new Cidade();
        valorPesquisa = "";

    }

    @PostConstruct
    public void init() {
        carregarListaEstados();
    }

    public String novo() {
        this.cidade = new Cidade();
        return "CidadeFrm?faces-redirect=true";
    }

    public void salvar() {
        try {
            cidadeEJB.salvar(cidade);
            cidade = cidadeEJB.novo();
            carregarListaEstados();
        } catch (Exception ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }

    public String editar() {
        carregarListaEstados();
        return "CidadeFrm?faces-redirect=true";
    }

    private void carregarListaEstados() {
        try {
            lstEstado = estadoEJB.listarTodos();
            Estado estado = estadoEJB.pesquisar(1l);
            System.out.println("Estado : " + estado.getNome());
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
            lstCidade = cidadeEJB.pesquisar(valorPesquisa);
        } catch (Exception ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }

    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getLstCidade() {
        return lstCidade;
    }

    public void setLstCidade(List<Cidade> lstCidade) {
        this.lstCidade = lstCidade;
    }

    public List<Estado> getLstEstado() {
        return lstEstado;
    }

}
