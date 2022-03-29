package ml.pkom.advancedreborn.addons.computercraft;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.ComputerCraftTags;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import dan200.computercraft.shared.Registry;
import dan200.computercraft.shared.computer.items.ItemComputer;
import dan200.computercraft.shared.turtle.upgrades.*;
import ml.pkom.advancedreborn.AdvancedReborn;
import net.minecraft.util.Identifier;
import techreborn.init.TRContent;

public class ComputerCraftAddon {
    public static TurtleTool RUBY_SWORD = new TurtleTool( new Identifier( "techreborn", "ruby_sword" ), TRContent.RUBY_SWORD, 5f, ComputerCraftTags.Blocks.TURTLE_SWORD_BREAKABLE);
    public static TurtleTool RUBY_SHOVEL = new TurtleTool( new Identifier( "techreborn", "ruby_spade" ), TRContent.RUBY_SPADE, 1f, ComputerCraftTags.Blocks.TURTLE_SHOVEL_BREAKABLE);
    public static TurtleTool RUBY_PICKAXE = new TurtleTool( new Identifier( "techreborn", "ruby_pickaxe" ), TRContent.RUBY_PICKAXE, 1f, null);
    public static TurtleTool RUBY_AXE = new TurtleTool( new Identifier( "techreborn", "ruby_axe" ), TRContent.RUBY_AXE, 3f, null);
    public static TurtleTool RUBY_HOE = new TurtleTool( new Identifier( "techreborn", "ruby_hoe" ), TRContent.RUBY_HOE, 1f, ComputerCraftTags.Blocks.TURTLE_HOE_BREAKABLE);

    public static TurtleTool SAPPHIRE_SWORD = new TurtleTool( new Identifier( "techreborn", "sapphire_sword" ), TRContent.SAPPHIRE_SWORD, 5f, ComputerCraftTags.Blocks.TURTLE_SWORD_BREAKABLE);
    public static TurtleTool SAPPHIRE_SHOVEL = new TurtleTool( new Identifier( "techreborn", "sapphire_spade" ), TRContent.SAPPHIRE_SPADE, 1f, ComputerCraftTags.Blocks.TURTLE_SHOVEL_BREAKABLE);
    public static TurtleTool SAPPHIRE_PICKAXE = new TurtleTool( new Identifier( "techreborn", "ruby_pickaxe" ), TRContent.SAPPHIRE_PICKAXE, 1f, null);
    public static TurtleTool SAPPHIRE_AXE = new TurtleTool( new Identifier( "techreborn", "sapphire_axe" ), TRContent.SAPPHIRE_AXE, 3f, null);
    public static TurtleTool SAPPHIRE_HOE = new TurtleTool( new Identifier( "techreborn", "sapphire_hoe" ), TRContent.SAPPHIRE_HOE, 1f, ComputerCraftTags.Blocks.TURTLE_HOE_BREAKABLE);

    public static TurtleTool PERIDOT_SWORD = new TurtleTool( new Identifier( "techreborn", "peridot_sword" ), TRContent.PERIDOT_SWORD, 5f, ComputerCraftTags.Blocks.TURTLE_SWORD_BREAKABLE);
    public static TurtleTool PERIDOT_SHOVEL = new TurtleTool( new Identifier( "techreborn", "peridot_spade" ), TRContent.PERIDOT_SPADE, 1f, ComputerCraftTags.Blocks.TURTLE_SHOVEL_BREAKABLE);
    public static TurtleTool PERIDOT_PICKAXE = new TurtleTool( new Identifier( "techreborn", "peridot_pickaxe" ), TRContent.PERIDOT_PICKAXE, 1f, null);
    public static TurtleTool PERIDOT_AXE = new TurtleTool( new Identifier( "techreborn", "peridot_axe" ), TRContent.PERIDOT_AXE, 3f, null);
    public static TurtleTool PERIDOT_HOE = new TurtleTool( new Identifier( "techreborn", "peridot_hoe" ), TRContent.PERIDOT_HOE, 1f, ComputerCraftTags.Blocks.TURTLE_HOE_BREAKABLE);

    public static TurtleTool BRONZE_SWORD = new TurtleTool( new Identifier( "techreborn", "bronze_sword" ), TRContent.BRONZE_SWORD, 5f, ComputerCraftTags.Blocks.TURTLE_SWORD_BREAKABLE);
    public static TurtleTool BRONZE_SHOVEL = new TurtleTool( new Identifier( "techreborn", "bronze_spade" ), TRContent.BRONZE_SPADE, 1f, ComputerCraftTags.Blocks.TURTLE_SHOVEL_BREAKABLE);
    public static TurtleTool BRONZE_PICKAXE = new TurtleTool( new Identifier( "techreborn", "bronze_pickaxe" ), TRContent.BRONZE_PICKAXE, 1f, null);
    public static TurtleTool BRONZE_AXE = new TurtleTool( new Identifier( "techreborn", "bronze_axe" ), TRContent.BRONZE_AXE, 3f, null);
    public static TurtleTool BRONZE_HOE = new TurtleTool( new Identifier( "techreborn", "bronze_hoe" ), TRContent.BRONZE_HOE, 1f, ComputerCraftTags.Blocks.TURTLE_HOE_BREAKABLE);



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

        // あまりいい方法とは言えませんが、ここで静的変数を読み込んでおくことによりエラーを防ぎます。
        ItemComputer computerNormal = Registry.ModItems.COMPUTER_NORMAL;
        //　変数は不要なので捨てる
        computerNormal = null;

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
        //AdvancedReborn.addStacksIG.add(ComputerCraftRegistry.ModItems.TURTLE_NORMAL.create(-1, null, -1, null, upgrade, 0, null));
        AdvancedReborn.addStacksIG.add(Registry.ModItems.TURTLE_NORMAL.create(-1, null, -1, null, upgrade, 0, null));
    }

    public static void addRightUpgradeForAdvancedAsStack(ITurtleUpgrade upgrade) {
        //AdvancedReborn.addStacksIG.add(ComputerCraftRegistry.ModItems.TURTLE_ADVANCED.create(-1, null, -1, null, upgrade, 0, null));
        AdvancedReborn.addStacksIG.add(Registry.ModItems.TURTLE_ADVANCED.create(-1, null, -1, null, upgrade, 0, null));
    }
}
