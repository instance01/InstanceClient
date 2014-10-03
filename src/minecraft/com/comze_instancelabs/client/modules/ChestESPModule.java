package com.comze_instancelabs.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.comze_instancelabs.client.InstanceMain;
import com.comze_instancelabs.client.Module;
import com.comze_instancelabs.client.InstanceRender;

public class ChestESPModule extends Module {
	public ChestESPModule() {
		super("chestesp", "Shows near Chests.");
	}

	@Override
	public void enable() {
		super.enable();
		InstanceMain.getInstance().chestesp = true;
	}

	@Override
	public void disable() {
		super.disable();
		InstanceMain.getInstance().chestesp = false;
	}
}
