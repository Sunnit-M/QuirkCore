package net.justsunnit.quirkcore.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.justsunnit.quirkcore.QuirkCore;
import net.justsunnit.quirkcore.Util.IEntityDataSaver;
import net.justsunnit.quirkcore.Util.QuirkAgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Collection;
public class SetQuirkCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess dedicated) {
        dispatcher.register(CommandManager.literal("Quirk")
                .then(CommandManager.argument("Target", EntityArgumentType.players())
                        .then(CommandManager.argument("Quirk", QuirkAgumentType.enumArg())
                                .executes(SetQuirkCommand::run)
                        )
                )
        );

    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        QuirkCore.QuirkTypes quirk = QuirkAgumentType.getEnum(context, "Quirk");
        Collection<ServerPlayerEntity> gotPlayers = EntityArgumentType.getPlayers(context, "Target");
        ServerPlayerEntity[] players = gotPlayers.toArray(new ServerPlayerEntity[0]);
        for (ServerPlayerEntity player : players) {
            IEntityDataSaver playerData = (IEntityDataSaver) player;
            playerData.getPersistentData().putString("Quirk", quirk.name());
        }
        return 1;
    }
}
