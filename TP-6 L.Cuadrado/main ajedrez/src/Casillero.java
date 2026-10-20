

public class Casillero {
    
    private String color;
    private String Coordenada;
    private Pieza pieza;

    //constructor vacio
    public Casillero() {
        this.color = "";
        this.Coordenada = "";
        this.pieza = null; 
    }

    public Casillero(String color, String coordenada, Pieza pieza) {
        this.color = color;
        this.Coordenada = coordenada;
        this.pieza = pieza;
    }

    //getters y setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCoordenada() {
        return Coordenada;
    }

    public void setCoordenada(String Coordenada) {
        this.Coordenada = Coordenada;
    }

    public Pieza getPieza(){
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public boolean estaOcupado() {
        return pieza != null;
    }

    @Override
    public String toString() {
        return "Casillero " + Coordenada + " (" + color + ")" + (estaOcupado() ? "ocupado por" + pieza.getClass().getSimpleName() + " " + pieza.getColor()
                                     : " vacio");
    }

}
