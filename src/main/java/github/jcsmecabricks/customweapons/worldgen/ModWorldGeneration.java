package github.jcsmecabricks.customweapons.worldgen;

public class ModWorldGeneration {
    public static void generateWorldGeneration() {
        ModGeodeGeneration.loadGeodes();
        ModOreGeneration.load();
    }
}
