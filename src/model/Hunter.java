package model;

public class Hunter extends Position{
    private String name;
    private String colour;

    public Hunter() {
    }

    public Hunter(String name, String colour, int x , int y) {
        super(x, y);
        this.name = name;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Hunter{" +
                "name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
