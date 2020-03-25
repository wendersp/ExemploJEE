/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.application.FacesMessage;
import javax.faces.convert.ConverterException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sound.midi.Soundbank;
import model.ejb.EstadoFacadeRemoto;
import model.entidades.Estado;

/**
 *
 * @author wender
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EstadoFacadeRemoto estadoFacadeRemoto;
        try {
            estadoFacadeRemoto = (EstadoFacadeRemoto) new InitialContext().lookup("model.ejb.EstadoFacadeRemoto");
            System.out.println("EJB ok");
            Estado estado = new Estado();
            estado.setNome("GOAIS");
            estado.setSigla("GO");
            estadoFacadeRemoto.salvar(estado);
            System.out.println("estado salvo com sucesso...");
        } catch (NamingException ex) {
            System.out.println("Error ao obter EstadoFacade. " + ex.getMessage());            
            
        }
    }
    
}
