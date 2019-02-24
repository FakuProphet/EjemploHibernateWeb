/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dao;

import d.pojos.Categoria;
import java.util.List;

/**
 *
 * @author Prophet
 */
public interface CategoriaDao {
    
    public List<Categoria> listarCategorias();
    
}
