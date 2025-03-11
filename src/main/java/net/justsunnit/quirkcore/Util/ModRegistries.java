package net.justsunnit.quirkcore.Util;

import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.justsunnit.quirkcore.commands.SetQuirkCommand;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.util.Identifier;

public class ModRegistries {
    public static void register() {
        // Register your items, blocks, entities, etc here
        registerCommands();
        registerMisc();
    }

    public static void registerCommands() {
        // Register your commands here
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, thing) -> {
            SetQuirkCommand.register(dispatcher, dedicated);
        });
    }

    public static void registerMisc() {
        // Register other things here
        ArgumentTypeRegistry.registerArgumentType(
                new Identifier("quirkcore", "quirkenum"),
                QuirkAgumentType.class,
                ConstantArgumentSerializer.of(QuirkAgumentType::enumArg)
        );
    }
}
