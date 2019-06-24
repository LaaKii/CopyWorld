package laakii.izda.copyworld;

import laakii.izda.copyworld.commands.ChangeRegion;
import laakii.izda.copyworld.commands.Create;
import laakii.izda.copyworld.commands.Paste;
import org.bukkit.plugin.java.JavaPlugin;

public final class CopyWorld extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("/create").setExecutor(new Create());
        getCommand("/changeRegion").setExecutor(new ChangeRegion());
        getCommand("/paste").setExecutor(new Paste());
    }
}
