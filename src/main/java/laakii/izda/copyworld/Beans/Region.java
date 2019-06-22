package laakii.izda.copyworld.Beans;

public class Region {
    private int regionId;
    private int startCoordId, endCoordId;
    private String name;

    public Region(int regionId, String regionName, int startCoordId, int endCoordId) {
        this.regionId = regionId;
        this.startCoordId = startCoordId;
        this.endCoordId = endCoordId;
        this.name = regionName;
    }

    public Region(int startCoordId, int endCoordId, String name) {
        this.startCoordId = startCoordId;
        this.endCoordId = endCoordId;
        this.name = name;
    }

    public Region(){

    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getStartCoordId() {
        return startCoordId;
    }

    public void setStartCoordId(int startCoordId) {
        this.startCoordId = startCoordId;
    }

    public int getEndCoordId() {
        return endCoordId;
    }

    public void setEndCoordId(int endCoordId) {
        this.endCoordId = endCoordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Region{" +
                "regionId=" + regionId +
                ", startCoordId=" + startCoordId +
                ", endCoordId=" + endCoordId +
                ", name='" + name + '\'' +
                '}';
    }
}
