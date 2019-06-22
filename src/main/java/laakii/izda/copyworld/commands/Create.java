package laakii.izda.copyworld.commands;

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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        currWorld = player.getWorld();

        enteredLocation = new Location(currWorld, Double.parseDouble(args[0]), Double.parseDouble(args[1]),Double.parseDouble(args[2])).subtract(1,1,1);

        currLocation = player.getLocation().subtract(1,1,1);

        Set<Block> allBlocksToCopy = blockCalculator.calculateBlocksBetweenTwoLocations(currLocation,enteredLocation);
        allBlocksToCopy.stream().forEach(System.out::println);
        allBlocksToCopy.stream().forEach(block -> block.setType(Material.AIR));

        return true;
    }


}
