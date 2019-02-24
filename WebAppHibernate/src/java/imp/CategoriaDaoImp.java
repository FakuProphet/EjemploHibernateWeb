/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imp;

import cl.dao.CategoriaDao;
import cl.util.HibernateUtil;
import d.pojos.Categoria;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Prophet
 */
public class CategoriaDaoImp implements CategoriaDao {

    @Override
    public List<Categoria> listarCategorias() {
    
        List <Categoria> listaDeCategorias = null;
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Query consulta = s.createQuery("From Categoria");
        
        try 
        {
            listaDeCategorias = consulta.list();
            t.commit();
            s.close();
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            t.rollback();
        }
        
        return listaDeCategorias;
    }
    
    
}
