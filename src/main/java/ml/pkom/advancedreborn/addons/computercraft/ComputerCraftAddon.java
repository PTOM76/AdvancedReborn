package ml.pkom.advancedreborn.addons.computercraft;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import dan200.computercraft.shared.ComputerCraftRegistry;
import dan200.computercraft.shared.turtle.upgrades.*;
import ml.pkom.advancedreborn.AdvancedReborn;
import net.minecraft.util.Identifier;
import techreborn.init.TRContent;

public class ComputerCraftAddon {
    public static TurtleSword RUBY_SWORD = new TurtleSword( new Identifier( "techreborn", "ruby_sword" ), TRContent.RUBY_SWORD);
    public static TurtleShovel RUBY_SHOVEL = new TurtleShovel( new Identifier( "techreborn", "ruby_spade" ), TRContent.RUBY_SPADE);
    public static TurtleTool RUBY_PICKAXE = new TurtleTool( new Identifier( "techreborn", "ruby_pickaxe" ), TRContent.RUBY_PICKAXE);
    public static TurtleAxe RUBY_AXE = new TurtleAxe( new Identifier( "techreborn", "ruby_axe" ), TRContent.RUBY_AXE);
    public static TurtleHoe RUBY_HOE = new TurtleHoe( new Identifier( "techreborn", "ruby_hoe" ), TRContent.RUBY_HOE);

    public static TurtleSword SAPPHIRE_SWORD = new TurtleSword( new Identifier( "techreborn", "sapphire_sword" ), TRContent.SAPPHIRE_SWORD);
    public static TurtleShovel SAPPHIRE_SHOVEL = new TurtleShovel( new Identifier( "techreborn", "sapphire_spade" ), TRContent.SAPPHIRE_SPADE);
    public static TurtleTool SAPPHIRE_PICKAXE = new TurtleTool( new Identifier( "techreborn", "sapphire_pickaxe" ), TRContent.SAPPHIRE_PICKAXE);
    public static TurtleAxe SAPPHIRE_AXE = new TurtleAxe( new Identifier( "techreborn", "sapphire_axe" ), TRContent.SAPPHIRE_AXE);
    public static TurtleHoe SAPPHIRE_HOE = new TurtleHoe( new Identifier( "techreborn", "sapphire_hoe" ), TRContent.SAPPHIRE_HOE);

    public static TurtleSword PERIDOT_SWORD = new TurtleSword( new Identifier( "techreborn", "peridot_sword" ), TRContent.PERIDOT_SWORD);
    public static TurtleShovel PERIDOT_SHOVEL = new TurtleShovel( new Identifier( "techreborn", "peridot_spade" ), TRContent.PERIDOT_SPADE);
    public static TurtleTool PERIDOT_PICKAXE = new TurtleTool( new Identifier( "techreborn", "peridot_pickaxe" ), TRContent.PERIDOT_PICKAXE);
    public static TurtleAxe PERIDOT_AXE = new TurtleAxe( new Identifier( "techreborn", "peridot_axe" ), TRContent.PERIDOT_AXE);
    public static TurtleHoe PERIDOT_HOE = new TurtleHoe( new Identifier( "techreborn", "peridot_hoe" ), TRContent.PERIDOT_HOE);

    public static TurtleSword BRONZE_SWORD = new TurtleSword( new Identifier( "techreborn", "bronze_sword" ), TRContent.BRONZE_SWORD);
    public static TurtleShovel BRONZE_SHOVEL = new TurtleShovel( new Identifier( "techreborn", "bronze_spade" ), TRContent.BRONZE_SPADE);
    public static TurtleTool BRONZE_PICKAXE = new TurtleTool( new Identifier( "techreborn", "bronze_pickaxe" ), TRContent.BRONZE_PICKAXE);
    public static TurtleAxe BRONZE_AXE = new TurtleAxe( new Identifier( "techreborn", "bronze_axe" ), TRContent.BRONZE_AXE);
    public static TurtleHoe BRONZE_HOE = new TurtleHoe( new Identifier( "techreborn", "bronze_hoe" ), TRContent.BRONZE_HOE);




    public static void init() {
        AdvancedReborn.LOGGER.info("Found ComputerCraft");
        ComputerCraftAPI.registerTurtleUpgrade(RUBY_SWORD);
        ComputerCraftAPI.registerTurtleUpgrade(RUBY_SHOVEL);
        ComputerCraftAPI.registerTurtleUpgrade(RUBY_PICKAXE);
        ComputerCraftAPI.registerTurtleUpgrade(RUBY_AXE);
        ComputerCraftAPI.registerTurtleUpgrade(RUBY_HOE);

        ComputerCraftAPI.registerTurtleUpgrade(SAPPHIRE_SWORD);
        ComputerCraftAPI.registerTurtleUpgrade(SAPPHIRE_SHOVEL);
        ComputerCraftAPI.registerTurtleUpgrade(SAPPHIRE_PICKAXE);
        ComputerCraftAPI.registerTurtleUpgrade(SAPPHIRE_AXE);
        ComputerCraftAPI.registerTurtleUpgrade(SAPPHIRE_HOE);

        ComputerCraftAPI.registerTurtleUpgrade(PERIDOT_SWORD);
        ComputerCraftAPI.registerTurtleUpgrade(PERIDOT_SHOVEL);
        ComputerCraftAPI.registerTurtleUpgrade(PERIDOT_PICKAXE);
        ComputerCraftAPI.registerTurtleUpgrade(PERIDOT_AXE);
        ComputerCraftAPI.registerTurtleUpgrade(PERIDOT_HOE);

        ComputerCraftAPI.registerTurtleUpgrade(BRONZE_SWORD);
        ComputerCraftAPI.registerTurtleUpgrade(BRONZE_SHOVEL);
        ComputerCraftAPI.registerTurtleUpgrade(BRONZE_PICKAXE);
        ComputerCraftAPI.registerTurtleUpgrade(BRONZE_AXE);
        ComputerCraftAPI.registerTurtleUpgrade(BRONZE_HOE);

        addRightUpgradeForNormalAsStack(RUBY_SWORD);
        addRightUpgradeForNormalAsStack(RUBY_SHOVEL);
        addRightUpgradeForNormalAsStack(RUBY_PICKAXE);
        addRightUpgradeForNormalAsStack(RUBY_AXE);
        addRightUpgradeForNormalAsStack(RUBY_HOE);
        addRightUpgradeForNormalAsStack(SAPPHIRE_SWORD);
        addRightUpgradeForNormalAsStack(SAPPHIRE_SHOVEL);
        addRightUpgradeForNormalAsStack(SAPPHIRE_PICKAXE);
        addRightUpgradeForNormalAsStack(SAPPHIRE_AXE);
        addRightUpgradeForNormalAsStack(SAPPHIRE_HOE);
        addRightUpgradeForNormalAsStack(PERIDOT_SWORD);
        addRightUpgradeForNormalAsStack(PERIDOT_SHOVEL);
        addRightUpgradeForNormalAsStack(PERIDOT_PICKAXE);
        addRightUpgradeForNormalAsStack(PERIDOT_AXE);
        addRightUpgradeForNormalAsStack(PERIDOT_HOE);
        addRightUpgradeForNormalAsStack(BRONZE_SWORD);
        addRightUpgradeForNormalAsStack(BRONZE_SHOVEL);
        addRightUpgradeForNormalAsStack(BRONZE_PICKAXE);
        addRightUpgradeForNormalAsStack(BRONZE_AXE);
        addRightUpgradeForNormalAsStack(BRONZE_HOE);

        addRightUpgradeForAdvancedAsStack(RUBY_SWORD);
        addRightUpgradeForAdvancedAsStack(RUBY_SHOVEL);
        addRightUpgradeForAdvancedAsStack(RUBY_PICKAXE);
        addRightUpgradeForAdvancedAsStack(RUBY_AXE);
        addRightUpgradeForAdvancedAsStack(RUBY_HOE);
        addRightUpgradeForAdvancedAsStack(SAPPHIRE_SWORD);
        addRightUpgradeForAdvancedAsStack(SAPPHIRE_SHOVEL);
        addRightUpgradeForAdvancedAsStack(SAPPHIRE_PICKAXE);
        addRightUpgradeForAdvancedAsStack(SAPPHIRE_AXE);
        addRightUpgradeForAdvancedAsStack(SAPPHIRE_HOE);
        addRightUpgradeForAdvancedAsStack(PERIDOT_SWORD);
        addRightUpgradeForAdvancedAsStack(PERIDOT_SHOVEL);
        addRightUpgradeForAdvancedAsStack(PERIDOT_PICKAXE);
        addRightUpgradeForAdvancedAsStack(PERIDOT_AXE);
        addRightUpgradeForAdvancedAsStack(PERIDOT_HOE);
        addRightUpgradeForAdvancedAsStack(BRONZE_SWORD);
        addRightUpgradeForAdvancedAsStack(BRONZE_SHOVEL);
        addRightUpgradeForAdvancedAsStack(BRONZE_PICKAXE);
        addRightUpgradeForAdvancedAsStack(BRONZE_AXE);
        addRightUpgradeForAdvancedAsStack(BRONZE_HOE);
    }

    public static void addRightUpgradeForNormalAsStack(ITurtleUpgrade upgrade) {
        AdvancedReborn.addStacksIG.add(ComputerCraftRegistry.ModItems.TURTLE_NORMAL.create(-1, null, -1, null, upgrade, 0, null));
    }

    public static void addRightUpgradeForAdvancedAsStack(ITurtleUpgrade upgrade) {
        AdvancedReborn.addStacksIG.add(ComputerCraftRegistry.ModItems.TURTLE_ADVANCED.create(-1, null, -1, null, upgrade, 0, null));
    }
}
