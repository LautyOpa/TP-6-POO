import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Clase principal: punto 2 del TP (Instanciacion).
 */
public class Main {
    private int obtenerIdColorPorDescripcion(Connection con, String descripcion) throws SQLException {
    String sql = "SELECT idColor FROM Color WHERE Descripcion = ?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, descripcion);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("idColor");
            }
        }
    }
    throw new SQLException("No existe el color: " + descripcion);
}

private int obtenerIdTipoPiezaPorDescripcion(Connection con, String descripcion) throws SQLException {
    String sql = "SELECT idTipoPieza FROM tipopieza WHERE DescripciondePieza = ?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, descripcion);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("idTipoPieza");
            }
        }
    }
    throw new SQLException("No existe el tipo de pieza: " + descripcion);
}

public static void mostrarPiezasBD() {

    AccesoDatos accesoBD = null;
    Connection con = null;
    Statement sentencia = null;
    ResultSet rs = null;

    String query = "select idPieza, \r\n" + //
                        "TP.DescripciondePieza as TipoPieza , \r\n" + //
                        "C.Descripcion as Color, \r\n" + //
                        "P.Descripcion as ResumenPieza, \r\n" + //
                        "Posicion, \r\n" + //
                        "Movimiento, \r\n" + //
                        "Comportamiento, \r\n" + //
                        "Velocidad \r\n" + //
                        "from Pieza P inner join tipopieza TP on P.idtipoPieza = TP.idtipoPieza\r\n" + //
                        "inner join Color C on P.idColor = C.idColor"; 

    try {
        accesoBD = new AccesoDatos();
        con = accesoBD.getConexion();
        sentencia = con.createStatement();

        rs = sentencia.executeQuery(query);

        while (rs.next()) {
        int idPieza = rs.getInt("IDPIEZA");
        String tipoPieza = rs.getString("TIPOPIEZA");
        String color = rs.getString("COLOR");
        String posicion = rs.getString("POSICION");
        String movimiento = rs.getString("MOVIMIENTO");
        String velocidad = rs.getString("VELOCIDAD");
        String comportamiento = rs.getString("COMPORTAMIENTO");

    System.out.println("Pieza Nro: " + idPieza
            + " | Tipo: " + tipoPieza
            + " | Color: " + color
            + " | Posición: " + posicion
            + " | Movimiento: " + movimiento
            + " | Velocidad: " + velocidad
            + " | Comportamiento: " + comportamiento);
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
public void insertarPieza(Pieza pieza, String posicion) {
    String sql = "INSERT INTO PIEZA (IDTIPOPIEZA, DESCRIPCION, POSICION, MOVIMIENTO, CARACTER, IDCOLOR) VALUES (?, ?, ?, ?, ?, ?)";
    AccesoDatos AccesoDatos = new AccesoDatos();
        try {
            Connection conexion = AccesoDatos.getConexion();
            PreparedStatement pstmt = conexion.prepareStatement(sql);

        // Obtener los IDs necesarios
        int idColor = obtenerIdColorPorDescripcion(conexion, pieza.getColor());
        int idTipoPieza = obtenerIdTipoPiezaPorDescripcion(conexion, pieza.getTipo());

        // Asignar los parámetros en la consulta
        pstmt.setInt(1, idTipoPieza);
        pstmt.setString(2, pieza.getTipo());
        pstmt.setString(3, posicion);
        pstmt.setString(4, pieza.getMovimiento());
        pstmt.setString(5, pieza.getComportamiento());
        pstmt.setInt(6, idColor);

        pstmt.executeUpdate();
        System.out.println("Pieza insertada con éxito.");

    } catch (SQLException e) {
        System.err.println("Error al insertar la pieza.");
        e.printStackTrace();
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