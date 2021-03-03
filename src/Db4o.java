
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author elena
 */
public class Db4o {

    private ObjectContainer baseDatos = initializeDb40();

    public ObjectContainer initializeDb40() {

        /*Borramos los que había antes insertados*/
        File fichero = new File("BDJefeHijo");
        fichero.delete();

        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Jefe.class).cascadeOnDelete(true);
        try {
            if (baseDatos == null) {
                ObjectContainer baseDatos = Db4oEmbedded.openFile("BDJefeHijo ");

                baseDatos.store(new Jefe("Ángel", 5, 53, new Hijo("Gustavo", 7)));
                baseDatos.store(new Jefe("Nieves", 3, 45, new Hijo("Iván", 3)));
                baseDatos.store(new Jefe("Jesús", 3, 5, new Hijo("Noelia", 3)));
                baseDatos.store(new Jefe("Dolores", 5, 63, new Hijo("Sergio", 7)));
                baseDatos.store(new Jefe("Vicki", 3, 5, null));
                baseDatos.store(new Jefe("Fátima", 5, 63, new Hijo("Lidia", 27)));
                baseDatos.store(new Jefe("Juan Luís", 3, 5, null));
                baseDatos.store(new Jefe("Elena", 1, 42, new Hijo("David", 19)));
                baseDatos.store(new Jefe("Miguel", 20, 45, new Hijo("Paula", 3)));
                baseDatos.store(new Jefe("Jesús", 19, 44, new Hijo("Rubén", 12)));
                baseDatos.query();

                return baseDatos;
            }
        } catch (Exception e) {
            if(baseDatos != null){
                baseDatos.close(); // cerrar la base de datos antes de salir
            }
        }
        return this.baseDatos;
    }

    public void mostrarConsulta(ObjectSet resul) {
        System.out.println("Recuperados " + resul.size() + " Objetos");
        while (resul.hasNext()) {
            System.out.println(resul.next());
        }
    }

    public void jefesMayores55() {
        Query query = baseDatos.query();
        query.constrain(Jefe.class);
        query.descend("edad").constrain(55).greater();
        ObjectSet result = query.execute();
        mostrarConsulta(result);
    }

    public void actualizarEdad(String nombre) {

        ObjectSet res = baseDatos.queryByExample(new Jefe(nombre, null, null, null));
        Jefe jefe = (Jefe) res.next();
        System.out.printf("Datos de %s: %s\n", nombre, jefe);

        jefe.setEdad(jefe.getEdad() + 1);
        baseDatos.store(jefe);
        res = baseDatos.queryByExample(new Jefe(nombre, null, null, null));
        jefe = (Jefe) res.next();
        System.out.printf("La nueva edad de %s es: %s años \n", jefe.getNombre(), jefe.getEdad());

    }

    public void borrarJefesAntiguos() {
        Query query = baseDatos.query();
        query.constrain(Jefe.class);
        query.descend("anioEmpresa").constrain(6).greater();
        ObjectSet resul = query.execute();
        while (resul.hasNext()) {
            Jefe jefe = (Jefe) resul.next();
            System.out.printf("Borrando a: %s\n", jefe);
            baseDatos.delete(jefe);
        }
    }
    
    public void buscarTodos(){
        Query query = baseDatos.query();
        query.constrain(Jefe.class);
        ObjectSet result = query.execute();
        mostrarConsulta(result);
    }

    public void cerrarConexion() {
        baseDatos.commit();
        baseDatos.close();
    }

}
