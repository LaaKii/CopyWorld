package laakii.izda.copyworld.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class Create implements CommandExecutor {

    private Location enteredLocation;
    private Location currLocation;
    private World currWorld;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        currWorld = player.getWorld();

        enteredLocation = new Location(currWorld, Double.parseDouble(args[0]), Double.parseDouble(args[1]),Double.parseDouble(args[2])).subtract(1,1,1);

        currLocation = player.getLocation().subtract(1,1,1);

        Set<Block> allBlocksToDelete = allBlocksBetweenCurrAndEntered();
        allBlocksToDelete.stream().forEach(System.out::println);
        allBlocksToDelete.stream().forEach(block -> block.setType(Material.AIR));

        return true;
    }

    private Set<Block> allBlocksBetweenCurrAndEntered()
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
}
