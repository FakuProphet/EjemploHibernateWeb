/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bean;

import d.pojos.Personal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Prophet
 */
@Named(value = "emplBean")
@RequestScoped
public class emplBean {

    private List<Personal>  listaPersonal;
    
    public emplBean() {
        
        listaPersonal = new ArrayList<>();
        
    }
    
}
