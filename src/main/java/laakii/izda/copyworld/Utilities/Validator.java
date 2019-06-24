package laakii.izda.copyworld.Utilities;

import laakii.izda.copyworld.database.DbHandler;

public class Validator {

    private DbHandler dbHandler = new DbHandler();
    private String commandName;

    public Validator(String commandName){
        this.commandName=commandName;
    }

    public boolean validateArgs(String[] args){
        if (commandName.equalsIgnoreCase("Create")){
            return dbHandler.isRegionNameAvailable(args[3]);
        }
        if (commandName.equalsIgnoreCase("ChangeRegion")){
            return true;
        }
        return false;
    }
}
