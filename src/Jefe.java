/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elena
 */
public class Jefe {

   private String nombre;
   private Integer anioEmpresa;
   private Integer edad;
   private Hijo hijo;

    public Jefe(String nombre, Integer anioEmpresa, Integer edad, Hijo hijo) {
        this.nombre = nombre;
        this.anioEmpresa = anioEmpresa;
        this.edad = edad;
        this.hijo = hijo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioEmpresa() {
        return anioEmpresa;
    }

    public void setAnioEmpresa(int anioEmpresa) {
        this.anioEmpresa = anioEmpresa;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Hijo getHijo() {
        return hijo;
    }

    public void setHijo(Hijo hijo) {
        this.hijo = hijo;
    }

    @Override
    public String toString() {
        String datos = "Nombre: " + nombre + ", años en la Empresa " + anioEmpresa +
                ", edad " + edad + " años ";
        if(this.getHijo() != null){
            datos += ", hijo: " + hijo;
        }
        return datos;
    }
    
    

    
}
