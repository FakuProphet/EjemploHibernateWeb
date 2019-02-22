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
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Prophet
 */
@Named(value = "cBean")
@RequestScoped
public class CategoriaBean {

    /**
     * Creates a new instance of CategoriaBean
     */
    public CategoriaBean() {
    }
    
    public List<Categoria> getListaCategorias(){
        
        SessionFactory miSF = HibernateUtil.getSessionFactory();
        Session sesion = miSF.openSession();
        List<Categoria> miLista = sesion.createQuery("From Categoria").list();
        /*Otra forma de obtener la lista*/
        List<Categoria> miLista2 = (List<Categoria>) sesion.createCriteria(Categoria.class);
        
        return miLista;
        
    }
    
    
}
