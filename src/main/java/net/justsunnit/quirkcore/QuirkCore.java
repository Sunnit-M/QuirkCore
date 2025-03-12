package net.justsunnit.quirkcore;

import net.fabricmc.api.ModInitializer;

import net.justsunnit.quirkcore.Util.IEntityDataSaver;
import net.justsunnit.quirkcore.Util.ModRegistries;
import net.minecraft.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuirkCore implements ModInitializer {
	public static final String MOD_ID = "quirkcore";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public enum QuirkTypes {
		OneForAll,
		Explosion,
		HalfHotHalfCold,
		ZeroGravity,
		Engine,
		Erasure,
		FierceWings,
		NewOrder,
		AllForOne,
		Decay,
		Cremation,
		Transform,
		Double,
		Compress,
		BloodCurdle,
		Overhaul,
		Quirkless,
		Hardening,
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Quirkcore is initializing.");

		ModRegistries.register();
	}

	public static boolean HasQuirkQuirk(Entity entity, QuirkTypes quirk) {
			// Get the quirk from the entity
			if (!entity.isPlayer()) {
				throw new RuntimeException("Error not player");
			}
			IEntityDataSaver playerData = (IEntityDataSaver) entity;

			playerData.getPersistentData().getString("Quirk");

			if (playerData.getPersistentData().getString("Quirk").isEmpty()) {
				return false;
			}

			for (QuirkTypes value : QuirkTypes.values()) {
				if (value.name().equalsIgnoreCase(playerData.getPersistentData().getString("Quirk"))) {
					return true;
				}
			}

			return false;
	}


	public static void SetQuirk(Entity entity, QuirkTypes quirk) {
		// Set the quirk to the entity
		if (!entity.isPlayer()) {
			throw new RuntimeException("Error not player");
		}
		IEntityDataSaver playerData = (IEntityDataSaver) entity;

		playerData.getPersistentData().putString("Quirk", quirk.name());
	}
}