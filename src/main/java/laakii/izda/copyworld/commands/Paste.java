package laakii.izda.copyworld.commands;

import laakii.izda.copyworld.Beans.Coordinate;
import laakii.izda.copyworld.Beans.McBlock;
import laakii.izda.copyworld.Beans.Region;
import laakii.izda.copyworld.Beans.RegionBlock;
import laakii.izda.copyworld.Utilities.Validator;
import laakii.izda.copyworld.database.DbHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paste implements CommandExecutor {

    private Validator validator = new Validator("Paste");
    private DbHandler dbHandler = new DbHandler();
    private BlockCalculator blockCalculator = new BlockCalculator();

    //paste <regionName>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if(!validator.validateArgs(args)){
//            return false;
//        }

        System.out.println("paste region called for region: " + args[0]);
        Player player = (Player) sender;

        Region region = dbHandler.getRegionByName(args[0]);
        List<RegionBlock> allRegionBlock = dbHandler.getAllRegionBlockByRegion(region);
        System.out.println("all regionBlock: ");
        allRegionBlock.stream().forEach(System.out::println);
        Coordinate startCoord = dbHandler.getCoordinateById(region.getStartCoordId());
        Location originStartPosi = new Location(player.getWorld(), startCoord.getX(),startCoord.getY(),startCoord.getZ());
        List<Map<Location, McBlock>> coordinatesInNewWorld = new ArrayList<>();
        Map<Location, McBlock> mapLocToMcBlock = new HashMap<>();

        for (RegionBlock rb : allRegionBlock){
            Coordinate temp = dbHandler.getCoordinateOfBlockById(rb.getBlockId());
            System.out.println("Coord for id: " + rb.getBlockId() + "\t"+ temp.toString());
            McBlock mcBlock = dbHandler.getBlockById(rb.getBlockId());
            System.out.println("Block for id: " + rb.getBlockId()+  "\t" + mcBlock.toString());
            Location originCoordinate = new Location(player.getWorld(), temp.getX(), temp.getY(), temp.getZ());
            mapLocToMcBlock.put(blockCalculator.calcCoordinate(player.getWorld(), originStartPosi, originCoordinate, player.getLocation().subtract(1,1,1)),mcBlock);
            coordinatesInNewWorld.add(mapLocToMcBlock);
        }


        for (Map<Location, McBlock> map : coordinatesInNewWorld) {
            for (Map.Entry<Location, McBlock> entry : map.entrySet()) {
                entry.getKey().getBlock().setType(Material.getMaterial(entry.getValue().getBlockMat()));
            }
        }

        return true;
    }
}
