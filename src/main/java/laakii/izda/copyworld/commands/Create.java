package laakii.izda.copyworld.commands;

import laakii.izda.copyworld.Beans.Coordinate;
import laakii.izda.copyworld.Beans.McBlock;
import laakii.izda.copyworld.Beans.Region;
import laakii.izda.copyworld.Beans.RegionBlock;
import laakii.izda.copyworld.database.DbHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class Create implements CommandExecutor {

    private Location enteredLocation;
    private Location currLocation;
    private World currWorld;
    private BlockCalculator blockCalculator = new BlockCalculator();
    private DbHandler dbHandler = new DbHandler();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        currWorld = player.getWorld();

        enteredLocation = new Location(currWorld, Double.parseDouble(args[0]), Double.parseDouble(args[1]),Double.parseDouble(args[2])).subtract(1,1,1);

        currLocation = player.getLocation().subtract(1,1,1);

        Set<Block> allBlocksToCopy = blockCalculator.calculateBlocksBetweenTwoLocations(currLocation,enteredLocation);
        allBlocksToCopy.stream().forEach(System.out::println);

        Region region = new Region();
        region.setName(args[3]);
        Coordinate startCoord = new Coordinate((int)currLocation.getX(),(int)currLocation.getY(),(int)currLocation.getZ());
        dbHandler.createCoord(startCoord);
        startCoord.setId(dbHandler.getLatestCoord().getId());
        Coordinate endCoord = new Coordinate((int)enteredLocation.getX(),(int)enteredLocation.getY(),(int)enteredLocation.getZ());
        dbHandler.createCoord(endCoord);
        endCoord.setId(dbHandler.getLatestCoord().getId());
        region.setStartCoordId(startCoord.getId());
        region.setEndCoordId(endCoord.getId());
        dbHandler.createRegion(region);
        region.setRegionId(dbHandler.getLatestRegion().getRegionId());

        RegionBlock regionBlock = new RegionBlock();
        regionBlock.setRegionId(region.getRegionId());

        for(Block b : allBlocksToCopy){
            Coordinate coord = new Coordinate(b.getX(),b.getY(),b.getZ());
            dbHandler.createCoord(coord);
            coord.setId(dbHandler.getLatestCoord().getId());
            McBlock mcBlock = new McBlock(coord.getId(),b.getBlockData().getMaterial().name());
            dbHandler.createBlock(mcBlock);
            mcBlock.setBlockId(dbHandler.getLatestBlock().getBlockId());

            regionBlock.setBlockId(mcBlock.getBlockId());
            dbHandler.createRegionBlock(regionBlock);
        }
        return true;
    }
}
