package laakii.izda.copyworld.database;

import laakii.izda.copyworld.Beans.Coordinate;
import laakii.izda.copyworld.Beans.McBlock;
import laakii.izda.copyworld.Beans.Region;
import laakii.izda.copyworld.Beans.RegionBlock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbHandler {

    private Connection conn = DbManager.getConnection();

    public boolean createCoord(Coordinate coordToCreate){

        try {
            PreparedStatement ps = conn.prepareStatement("Insert into table coords(xCoord, yCoord, zCoord) values (?,?,?)");
            ps.setInt(1,coordToCreate.getX());
            ps.setInt(2,coordToCreate.getY());
            ps.setInt(3,coordToCreate.getZ());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createBlock(McBlock blockToCreate){
        try {
            PreparedStatement ps = conn.prepareStatement("Insert into table McBlock(blockCoord, blockMat) values (?,?)");
            ps.setInt(1,blockToCreate.getBlockCoord().getId());
            ps.setString(2,blockToCreate.getBlockMat());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean createRegion(Region regionToCreate){
        try {
            PreparedStatement ps = conn.prepareStatement("Insert into table Region(regionName, startCoord, endCoord) values (?,?,?)");
            ps.setString(1,regionToCreate.getName());
            ps.setInt(2,regionToCreate.getStartCood().getId());
            ps.setInt(3,regionToCreate.getEndCoord().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean createRegionBlock(RegionBlock regionBlockToCreate){
        try {
            PreparedStatement ps = conn.prepareStatement("Insert into table RegionBlock(regionId, blockId) values (?,?)");
            ps.setInt(1,regionBlockToCreate.getRegionId());
            ps.setInt(2,regionBlockToCreate.getBlockId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
