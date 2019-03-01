/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bean;

import cl.dao.CategoriaDao;
import cl.util.HibernateUtil;
import d.pojos.Categoria;
import d.pojos.Personal;
import imp.CategoriaDaoImp;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Prophet
 */
@Named(value = "cBean")
@RequestScoped
public class empBean {

    private Categoria categoria; 
    private List<Categoria> categorias;
    private List<SelectItem> listaCatCombo;
    private List<Personal>  personal;
    private Personal p;
    
    
    public empBean() {
        categoria = new Categoria();
        categorias = new ArrayList<>();
        p = new Personal();
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
   
    
      public String newPersonal()
    {
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Transaction t = sesion.beginTransaction();
        Personal nuevo = new Personal(p.getNombre(),p.getApellido(), p.getAnioIngreso(),categoria);
        sesion.saveOrUpdate(nuevo);
        t.commit();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Mensaje","Nuevo personal registrado");
        /*agregando mensaje a nuestro ambito de aplicacion*/
        FacesContext.getCurrentInstance().addMessage(null, msg);
        categoria=null;
        p=null;
        cargarCategorias();
        return "index";    
        
    }
    
    
    
    
    
    

    public List<SelectItem> getListaCatCombo() {
        
        this.listaCatCombo = new ArrayList<>();
        CategoriaDao catDao = new CategoriaDaoImp();
        List<Categoria> lc = catDao.listarCategorias();
        listaCatCombo.clear();
        
        for(Categoria c: lc)
        {
            SelectItem catItem = new SelectItem(c.getCodigo(),c.getNombre());
            listaCatCombo.add(catItem);
        }
        
        return listaCatCombo;
    }

  
    
    
  
    
    
    private void cargarCategorias(){
        
        SessionFactory miSF = HibernateUtil.getSessionFactory();
        Session sesion = miSF.openSession();
        Query query = sesion.createQuery("From Categoria");
        categorias = query.list();
        
    }

    
    
    /*Otra forma de obtener la lista*/
    // List<Categoria> miLista2 = sesion.createCriteria(Categoria.class).list();
    
    public List<Categoria> getListaCategorias(){
        return categorias;     
    }
    
    
    public void filaEvento(RowEditEvent e)
    {   /* obtiene Texto u objeto actualizado*/
        Categoria c = (Categoria) e.getObject();
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session se = sf.openSession();
        Categoria seleccionada = (Categoria) se.get(Categoria.class, c.getCodigo()); 
        seleccionada.setNombre(c.getNombre());
        se.update(seleccionada);
        se.beginTransaction().commit();
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Mensaje","Categoria actualizada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        cargarCategorias();
        
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
