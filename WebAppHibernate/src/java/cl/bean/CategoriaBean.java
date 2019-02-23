/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bean;

import cl.util.HibernateUtil;
import d.pojos.Categoria;
import java.util.ArrayList;
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
    private List<Categoria> categorias;
   
    public CategoriaBean() {
        categoria = new Categoria();
        categorias = new ArrayList<>();
        cargarCategorias(); //Cuando se carga el form se carga la lista de categorias
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

  
    

  
    
    
    private void cargarCategorias(){
        
        SessionFactory miSF = HibernateUtil.getSessionFactory();
        Session sesion = miSF.openSession();
        categorias = sesion.createQuery("From Categoria").list();
    }

   
    
    /*Otra forma de obtener la lista*/
    // List<Categoria> miLista2 = sesion.createCriteria(Categoria.class).list();
    
    public List<Categoria> getListaCategorias(){
        return categorias;     
    }
    
    
    
    
    public String newCategoria(){
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Transaction t = sesion.beginTransaction();
        Categoria c = new Categoria(categoria.getNombre());
        sesion.saveOrUpdate(c);
        t.commit();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Mensaje","Categoria ingresadaa");
        /*agregando mensaje a nuestro ambito de aplicacion*/
        FacesContext.getCurrentInstance().addMessage(null, msg);
        categoria.setNombre("");
        cargarCategorias();
        return "index";
    }
    
    public String deleteCategoria(){
    
        
        for (Categoria c : categorias) {
            if(c.isSeleccionado())
            {
                SessionFactory sf = HibernateUtil.getSessionFactory();
                Session sesion = sf.openSession();
                Categoria cat = (Categoria) sesion.get(Categoria.class, c.getCodigo());
                sesion.delete(cat);
                sesion.beginTransaction().commit();
              
            }
        }
        
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Mensaje","Categoria eliminada");        
        FacesContext.getCurrentInstance().addMessage(null, msg);
        this.cargarCategorias();

        
        
        return "index";
    }
    
}
