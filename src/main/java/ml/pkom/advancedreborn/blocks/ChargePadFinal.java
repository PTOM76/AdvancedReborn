package ml.pkom.advancedreborn.blocks;

import ml.pkom.advancedreborn.api.Energy;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleBatteryItem;

public class ChargePadFinal extends ChargePad {

    public ChargePadFinal(FabricBlockSettings settings, int multiple) {
        super(settings, multiple);
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient()) return;
        if (!(entity instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) entity;
        boolean needCharge = false;
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack invStack = player.getInventory().getStack(i);

            if (invStack.isEmpty()) {
                continue;
            }

            if (Energy.isHolder(invStack)) {
                SimpleBatteryItem energy = Energy.of(invStack);
                if (energy.getStoredEnergy(invStack) >= energy.getEnergyCapacity()) continue;
                needCharge = true;
            }
        }
        if (needCharge) {
            super.onEntityCollision(state, world, pos, entity);
        }
    }
}
