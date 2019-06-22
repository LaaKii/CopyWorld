package laakii.izda.copyworld.database;

import laakii.izda.copyworld.Beans.Coordinate;
import laakii.izda.copyworld.Beans.McBlock;
import laakii.izda.copyworld.Beans.Region;
import laakii.izda.copyworld.Beans.RegionBlock;

import java.sql.*;

public class DbHandler {

    private Connection conn = DbManager.getConnection();

    public boolean createCoord(Coordinate coordToCreate){
        System.out.println("Creating new Coord: " + coordToCreate.toString());
        try {
            String sql = "Insert into coordinate(xCoord, yCoord, zCoord) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
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

    public Coordinate getLatestCoord(){
        try {
            Statement ps;
            ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("select * from coordinate order by coordinateId desc LIMIT 1;");
            if (rs.next()){
                Coordinate latestCoord = new Coordinate(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
                System.out.println("returning latest coord: " + latestCoord);
                return latestCoord;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public McBlock getLatestBlock(){
        try {
            Statement ps;
            ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("select * from mcblock order by blockId desc LIMIT 1;");
            if (rs.next()){
                return new McBlock(rs.getInt(1),rs.getInt(2),rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Region getLatestRegion(){
        try {
            Statement ps;
            ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("select * from region order by regionId desc LIMIT 1;");
            if (rs.next()){
                return new Region(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createBlock(McBlock blockToCreate){
        System.out.println("creating new block: " + blockToCreate.toString());
        try {
            PreparedStatement ps = conn.prepareStatement("Insert into mcblock(blockCoord, blockMat) values (?,?)");
            ps.setInt(1,blockToCreate.getBlockCoordId());
            ps.setString(2,blockToCreate.getBlockMat());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean createRegion(Region regionToCreate){
        System.out.println("Create new Region: " + regionToCreate.toString());
        try {
            String sql = "Insert into region(regionName, startCoord, endCoord) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,regionToCreate.getName());
            ps.setInt(2,regionToCreate.getStartCoordId());
            ps.setInt(3,regionToCreate.getEndCoordId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean createRegionBlock(RegionBlock regionBlockToCreate){
        System.out.println("creating new regionblock: " + regionBlockToCreate.toString());
        try {
            PreparedStatement ps = conn.prepareStatement("Insert into regionblock(regionId, blockId) values (?,?)");
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
