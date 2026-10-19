public class Peon extends Pieza {
    //constructor vacio: ya dejo cargado los valores propios del tipo de pieza
    public Peon () {
        super( "", "agresor", "lenta", "ladino");
    }

    //constructor con parametros: solo cambia el color, el resto es propio del peon
    public Peon(String color){
        super(color, "agresor", "lenta" , "ladino");
    }
    
    //constructor con todos los valores como parametro
    public Peon(String color, String comportamiento, String velocidad, String movimiento){
        super(color, comportamiento, velocidad, movimiento);
    }
    
    @Override
    public void mover() {
        System.out.println("El peon avanza un casillero hacia delante (dos en su primer movimiento) y captura en diagonal"); 
    }

    @Override
    public String getSimbolo(){
        return getColor().equalsIgnoreCase("Blanco") ? "P" : "p";

    }
}
