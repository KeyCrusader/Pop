package keycrusader.pop;

import net.fabricmc.api.ClientModInitializer;

public class Pop implements ClientModInitializer {
    public static final String MODID = "pop";
    @Override
    public void onInitializeClient() {
        PopConfig.init();
    }
}
