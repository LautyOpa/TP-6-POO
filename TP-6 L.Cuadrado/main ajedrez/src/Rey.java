public class Rey extends Pieza {
    
    public Rey() {
        super();
    }

    public Rey(String color) {
        super();
    }

    public Rey(String color, String comportamiento, String velocidad, String movimiento) {
        super();
    }

    @Override
    public void mover(){
        System.out.println("El rey se mueve un solo casillero en cualquier direccion.");

    }

    @Override
    public String getSimbolo() {
        return getColor().equalsIgnoreCase("Blanco") ? "R" : "r";
    }
}
