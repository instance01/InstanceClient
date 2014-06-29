package com.comze_instancelabs.client.util;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import com.comze_instancelabs.client.modules.FreecamModule;
import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;

public class CEntityPlayer extends AbstractClientPlayer {
	public CEntityPlayer(World p_i45324_1_) {
		super(p_i45324_1_, new GameProfile(getUUID(Minecraft.getMinecraft().thePlayer.getCommandSenderName()), Minecraft.getMinecraft().thePlayer.getCommandSenderName()));
		Minecraft mc = Minecraft.getMinecraft();
		this.setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
		FreecamModule.c = this;
	}

	public static UUID getUUID(String name) {
		UUID var1 = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8));
		return var1;
	}

	@Override
	public void addChatMessage(IChatComponent var1) {

	}

	@Override
	public boolean canCommandSenderUseCommand(int var1, String var2) {
		return false;
	}

	@Override
	public ChunkCoordinates getPlayerCoordinates() {
		return null;
	}
}
