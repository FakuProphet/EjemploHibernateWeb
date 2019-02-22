package d.pojos;
// Generated 22-feb-2019 20:13:25 by Hibernate Tools 4.3.1



/**
 * Personal generated by hbm2java
 */
public class Personal  implements java.io.Serializable {


     private Integer codigo;
     private Categoria categoria;
     private String nombre;
     private String apellido;
     private int anioIngreso;

    public Personal() {
    }

    public Personal(Categoria categoria, String nombre, String apellido, int anioIngreso) {
       this.categoria = categoria;
       this.nombre = nombre;
       this.apellido = apellido;
       this.anioIngreso = anioIngreso;
    }
   
    public Integer getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getAnioIngreso() {
        return this.anioIngreso;
    }
    
    public void setAnioIngreso(int anioIngreso) {
        this.anioIngreso = anioIngreso;
    }




}

