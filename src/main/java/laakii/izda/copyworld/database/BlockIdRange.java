package laakii.izda.copyworld.database;

public class BlockIdRange {

    private int idStart, idEnd;

    public BlockIdRange(int idStart, int idEnd) {
        this.idStart = idStart;
        this.idEnd = idEnd;
    }

    public BlockIdRange(int idStart) {
        this.idStart = idStart;
        this.idEnd = idStart;
    }

    public int getIdStart() {
        return idStart;
    }

    public void setIdStart(int idStart) {
        this.idStart = idStart;
    }

    public int getIdEnd() {
        return idEnd;
    }

    public void setIdEnd(int idEnd) {
        this.idEnd = idEnd;
    }
}
