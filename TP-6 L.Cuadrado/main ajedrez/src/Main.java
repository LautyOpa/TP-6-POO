import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Clase principal: punto 2 del TP (Instanciacion).
 */
public class Main {

public static void mostrarPiezasBD() {

    AccesoDatos accesoBD = null;
    Connection con = null;
    Statement sentencia = null;
    ResultSet rs = null;

    String query = "select * from tipopieza";

    try {
        accesoBD = new AccesoDatos();
        con = accesoBD.getConexion();
        sentencia = con.createStatement();

        rs = sentencia.executeQuery(query);

        while (rs.next()) {
            int idPieza = rs.getInt("idTipoPieza");
            System.out.println("Pieza Nro: " + idPieza
                    + " tipo " + rs.getString("Descripciondepieza"));
        }
    } catch (SQLException e) {
        System.err.println("ERROR AL EJECUTAR LA CONSULTA");
        e.printStackTrace();
    } finally {
        // Cerrar recursos (en orden inverso a como se abrieron)
        try {
            if (rs != null) rs.close();
            if (sentencia != null) sentencia.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    public static void main(String[] args) {

        // 2.a - Instancia del tablero con sus 64 casilleros
        Tablero tablero = new Tablero();
        System.out.println("--- Tablero vacio (colores de los casilleros) ---");
        tablero.MostrarTablero();

        // 2.b y 2.c - Instancias de todas las piezas, un color por lado
        tablero.armarTableroInicial();
        System.out.println("--- Tablero armado (MAYUSCULA = blancas, minuscula = negras) ---");
        tablero.MostrarTableroConPiezas();

        // 2.d - Imprimir todas las piezas de un mismo color con sus atributos
        tablero.MostrarPiezasPorColor("Blanco");

        // Demostracion de POLIMORFISMO:
        // una variable del tipo de la superclase referencia objetos de las subclases
        // y cada una responde de forma distinta al mismo mensaje mover().
        System.out.println("=== Polimorfismo: mismo mensaje mover(), distintas respuestas ===");
        Pieza[] piezas = {
                new Peon("Blanco"),
                new Torre("Blanco"),
                new Caballo("Blanco"),
                new Alfil("Blanco"),
                new Reina("Blanco"),
                new Rey("Blanco")
        };
        for (Pieza p : piezas) {
            System.out.print(p.getClass().getSimpleName() + ": ");
            p.mover();
        }
    System.out.println("\n=== PIEZAS DESDE LA BASE DE DATOS ===");
        mostrarPiezasBD();
    }

}