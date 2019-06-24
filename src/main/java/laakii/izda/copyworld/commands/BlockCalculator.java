package laakii.izda.copyworld.commands;

import laakii.izda.copyworld.Beans.Coordinate;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.Set;

public class BlockCalculator {

    public Set<Block> calculateBlocksBetweenTwoLocations(Location currLocation, Location enteredLocation)
    {
        Set<Block> blocks = new HashSet<>();

        System.out.println("Current Position: " + currLocation.getX() + "/" + currLocation.getY()+"/"+currLocation.getZ());
        System.out.println("Entered Position: " + enteredLocation.getX() + "/" + enteredLocation.getY()+"/"+enteredLocation.getZ());

        int topBlockX = ((int)currLocation.getX() < (int)enteredLocation.getX() ? (int)enteredLocation.getX() : (int)currLocation.getX());
        int bottomBlockX = ((int)currLocation.getX() > (int)enteredLocation.getX() ? (int)enteredLocation.getX() : (int)currLocation.getX());

        System.out.println("Topblock X: " + topBlockX);
        System.out.println("Bottomblock X: " + bottomBlockX);

        int topBlockY = ((int) currLocation.getY() < (int)enteredLocation.getY() ? (int)enteredLocation.getY() : (int)currLocation.getY());
        int bottomBlockY = ((int)currLocation.getY() > (int)enteredLocation.getY() ? (int)enteredLocation.getY() : (int)currLocation.getY());

        System.out.println("Topblock Y: " + topBlockY);
        System.out.println("Bottomblock Y : " + bottomBlockY);

        int topBlockZ = ((int) currLocation.getZ() < (int)enteredLocation.getZ() ? (int)enteredLocation.getZ() : (int)currLocation.getZ());
        int bottomBlockZ = ((int)currLocation.getZ() > (int)enteredLocation.getZ() ? (int)enteredLocation.getZ() : (int)currLocation.getZ());

        System.out.println("Topblock Z: " + topBlockZ);
        System.out.println("Bottomblock Z : " + bottomBlockZ);

        for(int x = bottomBlockX; x <= topBlockX; x++)
        {
            for(int z = bottomBlockZ; z <= topBlockZ; z++)
            {
                for(int y = bottomBlockY; y <= topBlockY; y++)
                {
                    blocks.add(currLocation.getWorld().getBlockAt(x, y, z));
                }
            }
        }

        return blocks;
    }

    public Location calcCoordinate(World world, Location originStartPosition, Location originCoordinate, Location currentPosition){

        System.out.println("Calculating where new blocks have to be...");
       int xDiff = (Math.abs((int)originStartPosition.getX() - (int)originCoordinate.getX()));
        System.out.println("xDiff: " + xDiff);
       int yDiff = (Math.abs((int)originStartPosition.getY() - (int)originCoordinate.getY()));
        System.out.println("yDiff: " + yDiff);
       int zDiff = ((int)Math.abs(originStartPosition.getZ() - (int)originCoordinate.getZ()));
        System.out.println("zDiff: " + zDiff);

        int calcX,calcY,calcZ;
        calcX = (int)currentPosition.getX()+xDiff;
        calcY = (int)currentPosition.getY()+yDiff;
        calcZ = (int)currentPosition.getZ()+zDiff;

        Location resLoc = new Location(world, calcX, calcY, calcZ);

        System.out.println("calculated Coord: " + resLoc.toString());
        return resLoc;
    }

}
