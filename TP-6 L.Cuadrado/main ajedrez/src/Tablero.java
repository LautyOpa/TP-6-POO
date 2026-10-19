
/**
 * Clase Tablero.
 * Tiene una matriz de 8x8 Casilleros -> COMPOSICION :
 * los casilleros no existen fuera del tablero, por eso los crea el propio Tablero.
 *
 * Convencion de indices:
 *   untablero[fila][columna]
 *   fila 0    -> fila 8 del tablero real (piezas negras)
 *   fila 7    -> fila 1 del tablero real (piezas blancas)
 *   columna 0 -> letra 'A' ... columna 7 -> letra 'H'
 */
public class Tablero {

    private Casillero[][] untablero;

    // Constructor vacio: crea los 64 casilleros con su color y coordenada (punto 2.a)
    public Tablero() {
        untablero = new Casillero[8][8];
        inicializarCasilleros();
    }

    // Constructor con el valor como parametro
    public Tablero(Casillero[][] untablero) {
        this.untablero = untablero;
    }

    // Getter y Setter
    public Casillero[][] getUntablero() {
        return untablero;
    }

    public void setUntablero(Casillero[][] untablero) {
        this.untablero = untablero;
    }

    // ---------------- metodos ----------------

    /** Crea los 64 casilleros: color alternado y coordenada tipo "A1".."H8". */
    private void inicializarCasilleros() {
        for (int fila = 0; fila < 8; fila++) {
            for (int colum = 0; colum < 8; colum++) {
                String color = ((fila + colum) % 2 == 0) ? "Blanco" : "Negro";
                String coordenada = "" + (char) ('A' + colum) + (8 - fila);
                untablero[fila][colum] = new Casillero(color, coordenada, null);
            }
        }
    }

    /** Coloca una pieza en una coordenada del tipo "E4". */
    public void colocarPieza(String coordenada, Pieza pieza) {
        Casillero c = buscarCasillero(coordenada);
        if (c != null) {
            c.setPieza(pieza);
        } else {
            System.out.println("Coordenada invalida: " + coordenada);
        }
    }

    /** Devuelve el casillero de esa coordenada, o null si no existe. */
    public Casillero buscarCasillero(String coordenada) {
        if (coordenada == null || coordenada.length() != 2) {
            return null;
        }
        int colum = Character.toUpperCase(coordenada.charAt(0)) - 'A';
        int fila = 8 - Character.getNumericValue(coordenada.charAt(1));
        if (fila < 0 || fila > 7 || colum < 0 || colum > 7) {
            return null;
        }
        return untablero[fila][colum];
    }

    /** Muestra el tablero vacio, solo con el color de cada casillero. */
    public void MostrarTablero() {
        for (int fila = 0; fila < 8; fila++) {
            for (int colum = 0; colum < 8; colum++) {
                if (untablero[fila][colum].getColor().equals("Blanco")) {
                    System.out.print("[B] ");
                } else {
                    System.out.print("[N] ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /** Muestra el tablero con las piezas: mayusculas = blancas, minusculas = negras. */
    public void MostrarTableroConPiezas() {
        System.out.println("   A   B   C   D   E   F   G   H");
        for (int fila = 0; fila < 8; fila++) {
            System.out.print((8 - fila) + " ");
            for (int colum = 0; colum < 8; colum++) {
                Casillero c = untablero[fila][colum];
                if (c.estaOcupado()) {
                    System.out.print("[" + c.getPieza().getSimbolo() + "] ");
                } else {
                    System.out.print(c.getColor().equals("Blanco") ? "[ ] " : "[.] ");
                }
            }
            System.out.println(" " + (8 - fila));
        }
        System.out.println("   A   B   C   D   E   F   G   H");
        System.out.println();
    }

    /** Punto 2.b: arma el tablero completo con las 32 piezas en su posicion inicial. */
    public void armarTableroInicial() {
        String[] columnas = {"A", "B", "C", "D", "E", "F", "G", "H"};

        // Piezas negras (filas 8 y 7)
        colocarPieza("A8", new Torre("Negro"));
        colocarPieza("B8", new Caballo("Negro"));
        colocarPieza("C8", new Alfil("Negro"));
        colocarPieza("D8", new Reina("Negro"));
        colocarPieza("E8", new Rey("Negro"));
        colocarPieza("F8", new Alfil("Negro"));
        colocarPieza("G8", new Caballo("Negro"));
        colocarPieza("H8", new Torre("Negro"));
        for (int i = 0; i < 8; i++) {
            colocarPieza(columnas[i] + "7", new Peon("Negro"));
        }

        // Piezas blancas (filas 1 y 2)
        colocarPieza("A1", new Torre("Blanco"));
        colocarPieza("B1", new Caballo("Blanco"));
        colocarPieza("C1", new Alfil("Blanco"));
        colocarPieza("D1", new Reina("Blanco"));
        colocarPieza("E1", new Rey("Blanco"));
        colocarPieza("F1", new Alfil("Blanco"));
        colocarPieza("G1", new Caballo("Blanco"));
        colocarPieza("H1", new Torre("Blanco"));
        for (int i = 0; i < 8; i++) {
            colocarPieza(columnas[i] + "2", new Peon("Blanco"));
        }
    }

    /** Punto 2.d: imprime todas las piezas de un mismo color con todos sus atributos. */
    public void MostrarPiezasPorColor(String color) {
        System.out.println("=== Piezas de color " + color + " ===");
        for (int fila = 0; fila < 8; fila++) {
            for (int colum = 0; colum < 8; colum++) {
                Casillero c = untablero[fila][colum];
                if (c.estaOcupado() && c.getPieza().getColor().equalsIgnoreCase(color)) {
                    System.out.println(c.getCoordenada() + " -> " + c.getPieza());
                }
            }
        }
        System.out.println();
    }
}