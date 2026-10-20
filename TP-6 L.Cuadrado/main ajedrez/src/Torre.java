// subclase Torre Borges: torre homerica y torre directa
public class Torre extends Pieza {

    public Torre (){
        super();

    }

    public Torre (String color) {
        super();
    }
    
    public Torre (String color, String comportamiento, String velocidad, String movimiento) {
        super();
    }

    @Override 
    public void mover () {
        System.out.println("la torre se mueve en linea recta: horinzontal o vertical, tantos casilleros como quiera.");
    }

    @Override 
    public String getSimbolo(){
        return getColor().equalsIgnoreCase("Blanco") ? "T" : "t";
    }
}