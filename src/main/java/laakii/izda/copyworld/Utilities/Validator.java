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
            System.out.println("Checking args: " + args);
            if (args.length!=4){
                System.out.println("Illegal Argument parse in //create command");
                return false;
            }
            if(dbHandler.isRegionNameAvailable(args[3])){
                return true;
            }else{
                return false;
            }
        }
        if (commandName.equalsIgnoreCase("ChangeRegion")){
            if (args.length!=2 || args.length!=3){
                System.out.println("Illegal Argument parse in //changeregion command");
                return false;
            }
            return true;
        }
        if (commandName.equalsIgnoreCase("Paste")){
            if (args.length!=1){
                System.out.println("Illegal Argument parse in //paste command");
                return false;
            }
            return true;
        }
        return false;
    }

}
