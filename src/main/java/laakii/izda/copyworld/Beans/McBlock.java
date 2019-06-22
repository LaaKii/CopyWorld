package laakii.izda.copyworld.Beans;

public class McBlock {

    private Coordinate blockCoord;
    private String blockMat;

    public McBlock(Coordinate blockCoord, String blockMat) {
        this.blockCoord = blockCoord;
        this.blockMat = blockMat;
    }

    public Coordinate getBlockCoord() {
        return blockCoord;
    }

    public void setBlockCoord(Coordinate blockCoord) {
        this.blockCoord = blockCoord;
    }

    public String getBlockMat() {
        return blockMat;
    }

    public void setBlockMat(String blockMat) {
        this.blockMat = blockMat;
    }

    @Override
    public String toString() {
        return "McBlock{" +
                "blockCoord=" + blockCoord +
                ", blockMat='" + blockMat + '\'' +
                '}';
    }
}
