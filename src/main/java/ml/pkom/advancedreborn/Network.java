package ml.pkom.advancedreborn;

import ml.pkom.advancedreborn.tile.CardboardBoxTile;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class Network {
    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(Defines.CARDBOARD_BOX_CLOSE_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            NbtCompound data = buf.readNbt();
            server.execute(() -> {
                if (data == null) return;
                if (!data.contains("x")) return;
                if (!data.contains("y")) return;
                if (!data.contains("z")) return;
                if (!data.contains("note")) return;
                BlockEntity blockEntity = player.getServerWorld().getBlockEntity(new BlockPos(data.getDouble("x"), data.getDouble("y"), data.getDouble("z")));
                if (!(blockEntity instanceof CardboardBoxTile)) return;
                CardboardBoxTile tile = (CardboardBoxTile) blockEntity;
                tile.setNote(data.getString("note"));
            });
        });
    }
}
