package laakii.izda.copyworld;

import laakii.izda.copyworld.commands.Create;
import org.bukkit.plugin.java.JavaPlugin;

public final class CopyWorld extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("/create").setExecutor(new Create());
    }
}
