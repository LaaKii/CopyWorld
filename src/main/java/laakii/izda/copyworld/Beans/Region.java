package laakii.izda.copyworld.Beans;

public class Region {
    private int id;
    private Coordinate startCood, endCoord;
    private String name;

    public Region(int id, Coordinate startCood, Coordinate endCoord, String name) {
        this.id = id;
        this.startCood = startCood;
        this.endCoord = endCoord;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordinate getStartCood() {
        return startCood;
    }

    public void setStartCood(Coordinate startCood) {
        this.startCood = startCood;
    }

    public Coordinate getEndCoord() {
        return endCoord;
    }

    public void setEndCoord(Coordinate endCoord) {
        this.endCoord = endCoord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
