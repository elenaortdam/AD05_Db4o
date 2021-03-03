
/**
 * @author Elena Ortiz
 */
public class Main {

    public static void main(String[] args) {
        Db4o db4o = null;
        try {
            db4o = new Db4o();
            System.out.println("1. Visualizar los jefes que tengan más de 55 años.");
            System.out.println("");
            db4o.jefesMayores55();
            System.out.println("----------------------------------------------------------------");
            
            String nombre = "Miguel";
            System.out.printf("2. Actualizando edad de %s...\n", nombre);
            System.out.println("");
            System.out.printf("Recuperando los datos de %s...\n", nombre);
            db4o.actualizarEdad(nombre);
            System.out.println("----------------------------------------------------------------");

            System.out.println("3. Borrar los jefes que llevan más de 6 años en la empresa.");
            System.out.println("");
            db4o.borrarJefesAntiguos();
            System.out.println("----------------------------------------------------------------");

            System.out.println("4. Visualizar todos los jefes que quedan, "
                    + "incluidos sus hijos, que no han sido borrados anteriormente.");
            System.out.println("");
            db4o.buscarTodos();
        } catch (Exception e) {
            System.err.print("Ha ocurrido un error al realizar la consulta");
            e.printStackTrace();
        } finally {
            if (db4o != null) {
                db4o.cerrarConexion();
            }
        }
    }

}
