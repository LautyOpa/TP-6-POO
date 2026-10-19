/**
 * SUBCLASE Caballo. Borges: "ligero Caballo"
 */

public class Caballo extends Pieza {

    public Caballo() { 
        super("", "ligero", "rapida", "en L");
    }
    
    public Caballo(String color) {
        super(color, "ligero", "rapida", "en L");
    }

    public Caballo(String color, String comportamiento, String velocidad, String movimiento) {
        super (color, comportamiento, velocidad, movimiento);
    }

    @Override
    public void mover() {
        System.out.println("El caballo se mueve en forma de L y es la unica pieza que puede saltar por sobre las demas.");
    }

    @Override
    public String getSimbolo (){
        return getColor().equalsIgnoreCase("Blanco") ? "C" : "c";
    }


}
