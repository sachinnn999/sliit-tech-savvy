package model;

public class Soldier extends Position{
   private String colour;


    public Soldier() {
        super();
    }

    public Soldier(String colour, int x, int y) {
        super(x, y);
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Soldier{" +
                "colour='" + colour + '\'' +
                '}';
    }
}
