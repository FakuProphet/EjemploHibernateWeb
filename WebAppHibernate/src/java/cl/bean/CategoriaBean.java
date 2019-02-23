/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bean;

import cl.util.HibernateUtil;
import d.pojos.Categoria;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Prophet
 */
@Named(value = "cBean")
@RequestScoped
public class CategoriaBean {

    private Categoria categoria; 
    
   
    public CategoriaBean() {
        categoria = new Categoria();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
   
    
    

   
    
    
    
    public List<Categoria> getListaCategorias(){
        
        SessionFactory miSF = HibernateUtil.getSessionFactory();
        Session sesion = miSF.openSession();
        List<Categoria> miLista = sesion.createQuery("From Categoria").list();
        /*Otra forma de obtener la lista*/
        List<Categoria> miLista2 = sesion.createCriteria(Categoria.class).list();
        
        return miLista;
        
    }
    
    public String newCategoria(){
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Transaction t = sesion.beginTransaction();
        Categoria c = new Categoria(categoria.getNombre());
        sesion.saveOrUpdate(c);
        t.commit();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Mensaje","Categoria");
        /*agregando mensaje a nuestro ambito de aplicacion*/
        FacesContext.getCurrentInstance().addMessage(null, msg);
        categoria.setNombre("");
        return "index";
    }
    
    
    
}
