package laakii.izda.copyworld.Beans;

public class McBlock {

    private int blockId;
    private int blockCoordId;
    private String blockMat;

    public McBlock(int blockId, int blockCoordId, String blockMat) {
        this.blockId = blockId;
        this.blockCoordId = blockCoordId;
        this.blockMat = blockMat;
    }

    public McBlock(int blockCoord, String blockMat) {
        this.blockCoordId = blockCoord;
        this.blockMat = blockMat;
    }

    public String getBlockMat() {
        return blockMat;
    }

    public void setBlockMat(String blockMat) {
        this.blockMat = blockMat;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public int getBlockCoordId() {
        return blockCoordId;
    }

    public void setBlockCoordId(int blockCoordId) {
        this.blockCoordId = blockCoordId;
    }

    @Override
    public String toString() {
        return "McBlock{" +
                "blockCoord=" + blockCoordId +
                ", blockMat='" + blockMat + '\'' +
                '}';
    }
}
