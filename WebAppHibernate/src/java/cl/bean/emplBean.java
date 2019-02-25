/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bean;

import cl.util.HibernateUtil;
import d.pojos.Categoria;
import d.pojos.Personal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Prophet
 */
@Named(value = "emplBean")
@RequestScoped
public class emplBean {

    
    private List<Personal>  personal;
    private Categoria categoria;
    private Personal p;
    
    
    public emplBean() {
        
        categoria = new Categoria();
        personal = new ArrayList<>();
        cargarListadoPersonal();
    }

    public Personal getP() {
        return p;
    }

    public void setP(Personal p) {
        this.p = p;
    }

    
    
    
    public List<Personal> getPersonal() {
        return personal;
    }

    public void setPersonal(List<Personal> personal) {
        this.personal = personal;
    }
    
   
    
    private void cargarListadoPersonal()
    {
        SessionFactory miSF = HibernateUtil.getSessionFactory();
        Session sesion = miSF.openSession();
        Query query = sesion.createQuery("From Personal");
        personal = query.list();
    }
    
    
    public String newPersonal()
    {
         
       
        return "index";
    }
    
    
    
}
