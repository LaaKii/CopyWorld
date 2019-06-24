package laakii.izda.copyworld.commands;

import laakii.izda.copyworld.Utilities.Validator;
import laakii.izda.copyworld.database.DbHandler;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChangeRegion implements CommandExecutor {

    private Validator validator = new Validator("ChangeRegion");
    private DbHandler dbHandler = new DbHandler();

    //ChangeRegion <regionName> <newBlockMaterial>
    //ChangeRegion <regionName> <oldBlockMaterial> <newBlockMaterial>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        System.out.println("Change region called");
        if(!validator.validateArgs(args)){
            return false;
        }
        int argsLength = args.length;
        if(argsLength>2){
            dbHandler.changeSpecificBlocksInRegionToGivenBlock(args[0], Material.getMaterial(args[1]), Material.getMaterial(args[2]));
        }else{
            dbHandler.changeAllBlocksInRegionToGivenBlock(args[0], Material.getMaterial(args[1]));
        }


        return true;

    }
}
