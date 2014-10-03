package com.comze_instancelabs.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.comze_instancelabs.client.InstanceMain;
import com.comze_instancelabs.client.Module;
import com.comze_instancelabs.client.InstanceRender;

public class ColoredMobsModule extends Module {
	public ColoredMobsModule() {
		super("coloredmobs", "Renders Yellow Mobs (failed ESP).");
	}

	@Override
	public void enable() {
		super.enable();
		InstanceMain.getInstance().coloredmobs = true;
	}

	@Override
	public void disable() {
		super.disable();
		InstanceMain.getInstance().coloredmobs = false;
	}
}
