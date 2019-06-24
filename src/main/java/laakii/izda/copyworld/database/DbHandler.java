package laakii.izda.copyworld.database;

import laakii.izda.copyworld.Beans.Coordinate;
import laakii.izda.copyworld.Beans.McBlock;
import laakii.izda.copyworld.Beans.Region;
import laakii.izda.copyworld.Beans.RegionBlock;
import org.bukkit.Material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHandler {

    private Connection conn = DbManager.getConnection();

    public boolean createCoord(Coordinate coordToCreate){
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


    public Region getRegionByName(String regionName){
        try {
            PreparedStatement ps = conn.prepareStatement("select * from region where regionName = ? LIMIT 1");
            ps.setString(1,regionName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new Region(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public Coordinate getCoordinateById(int id){
        try {
            PreparedStatement ps = conn.prepareStatement("select * from coordinate where coordinateId = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            System.out.println("QUERY: " + ps);
            if (rs.next()){
                return new Coordinate(rs.getInt("coordinateId"),rs.getInt("xCoord"),rs.getInt("yCoord"),rs.getInt("zCoord"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Coordinate getCoordinateOfBlockById(int blockId){
        try {
            PreparedStatement ps = conn.prepareStatement("select * from mcblock where blockId = ?");
            ps.setInt(1,blockId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
               return getCoordinateById(rs.getInt("blockCoord"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<RegionBlock> getAllRegionBlockByRegion(Region r){
        try {
            List<RegionBlock> allRegionBlock = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement("Select * from regionblock where regionId = ?;");
            ps.setInt(1,r.getRegionId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
               allRegionBlock.add(new RegionBlock(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
            }
            return allRegionBlock;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public void changeAllBlocksInRegionToGivenBlock(String regionName , Material resultBlock){
        Region r = getRegionByName(regionName);
        List<RegionBlock> allRegionBlocks = getAllRegionBlockByRegion(r);
        allRegionBlocks.parallelStream().forEach(regionBlock -> updateBlockTypeByBlockId(new BlockIdRange(regionBlock.getBlockId()),resultBlock));
    }

    public void changeSpecificBlocksInRegionToGivenBlock(String regionName, Material blockToChange, Material resultBlock){
        System.out.println("Called with parameters:\t" + regionName + "\t" + blockToChange.name() + "\t" + resultBlock.name());
        Region r = getRegionByName(regionName);
        List<RegionBlock> allRegionBlocks = getAllRegionBlockByRegion(r);
        allRegionBlocks.parallelStream().forEach(regionBlock -> updateBlockTypeByBlockTypeAndBlockId(new BlockIdRange(regionBlock.getBlockId()),blockToChange, resultBlock));
    }

    public boolean updateBlockTypeByBlockId(BlockIdRange range, Material material){
        try {
            PreparedStatement ps = conn.prepareStatement("update mcblock set blockMat = ? where blockId >= ? && blockId <= ?");
            ps.setString(1,material.name());
            ps.setInt(2,range.getIdStart());
            ps.setInt(3,range.getIdEnd());
            ps.executeLargeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBlockTypeByBlockTypeAndBlockId(BlockIdRange range, Material oldMaterial, Material newMaterial){
        try {
            PreparedStatement ps = conn.prepareStatement("update mcblock set blockMat = ? where blockMat = ? && blockId >= ? && blockId <= ?");
            ps.setString(1,oldMaterial.name());
            ps.setString(2,newMaterial.name());
            ps.setInt(3,range.getIdStart());
            ps.setInt(4,range.getIdEnd());
            ps.executeLargeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public McBlock getBlockById(int id){
        try {
            PreparedStatement ps = conn.prepareStatement("select * from mcblock where blockId = ? LIMIT 1;");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new McBlock(rs.getInt(1),rs.getInt(2),rs.getString(3));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isRegionNameAvailable(String regionNameToCheck){
        try {
            PreparedStatement ps = conn.prepareStatement("select * from region where regionName = ? LIMIT 1;");
            ps.setString(1,regionNameToCheck);
            if (ps.executeQuery().next()){
                System.out.println("Region Name is already used. Use a unique one.");
                return false;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}
