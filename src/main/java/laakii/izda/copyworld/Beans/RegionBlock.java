package laakii.izda.copyworld.Beans;

public class RegionBlock {
    private int regionBlockId;
    private int regionId;
    private int blockId;


    public RegionBlock(int regionBlockId, int regionId, int blockId) {
        this.regionBlockId = regionBlockId;
        this.regionId = regionId;
        this.blockId = blockId;
    }

    public int getRegionBlockId() {
        return regionBlockId;
    }

    public void setRegionBlockId(int regionBlockId) {
        this.regionBlockId = regionBlockId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    @Override
    public String toString() {
        return "RegionBlock{" +
                "regionBlockId=" + regionBlockId +
                ", regionId=" + regionId +
                ", blockId=" + blockId +
                '}';
    }
}
