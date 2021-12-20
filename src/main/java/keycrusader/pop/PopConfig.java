package keycrusader.pop;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Pop.MODID)
public class PopConfig implements ConfigData {
    @ConfigEntry.Gui.Excluded
    public static PopConfig INSTANCE;

    public static void init()
    {
        AutoConfig.register(PopConfig.class, JanksonConfigSerializer::new);
        INSTANCE = AutoConfig.getConfigHolder(PopConfig.class).getConfig();
    }

    @Comment("Normal bubbles play splashing sounds.")
    public boolean splashesPlaySounds = true;
    @Comment("Column bubbles play splashing sounds.")
    public boolean columnsPlaySounds = true;
    @Comment("Chance to pop")
    @ConfigEntry.BoundedDiscrete(max=100)
    public int chanceToPop = 30;

}
