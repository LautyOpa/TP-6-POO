
public class Alfil extends Pieza {
    public Alfil(){
        super("", "","sesgo", "media", "oblicuo");

    }

    public Alfil(String color) {
        super(color, "", "sesgo", "media", "oblicuo");
    }

    public Alfil(String color, String comportamiento, String velocidad, String movimiento){
        super(color, comportamiento, velocidad, movimiento, "oblicuo");
    }

    @Override 
    public void mover() {
        System.out.println("el alfil se mueve en diagonal, siempre sobre el casillero del mismo color");

    }

    @Override 
    public String getSimbolo(){
        return getColor().equalsIgnoreCase("Blanco") ? "A" : "a";
        
    }


}

    

