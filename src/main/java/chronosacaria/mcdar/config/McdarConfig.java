package chronosacaria.mcdar.config;

import chronosacaria.mcdar.Mcdar;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@SuppressWarnings("CanBeFinal")
@Config(name = Mcdar.MOD_ID)
public class McdarConfig extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("mcdar_artifacts_stats_config")
    public McdarArtifactsStatsConfig mcdarArtifactsStatsConfig = new McdarArtifactsStatsConfig();

    @ConfigEntry.Category("mcdar_enchantments_config")
    public McdarEnchantmentsConfig mcdarEnchantmentsConfig = new McdarEnchantmentsConfig();

    public static void register() {
        AutoConfig.register(McdarConfig.class,
                PartitioningSerializer.wrap(JanksonConfigSerializer::new)
        );
    }
}
