package com.comze_instancelabs.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.comze_instancelabs.client.InstanceMain;
import com.comze_instancelabs.client.Module;
import com.comze_instancelabs.client.InstanceRender;

public class AllChestESPModule extends Module {
	public AllChestESPModule() {
		super("allchestesp", "Renders TileEntity ESP (Chest/Enderchest/Sign/Spawner/Piston/Skull/Beacon/Endportal/EnchantmentTable).");
	}

	@Override
	public void enable() {
		super.enable();
		InstanceMain.getInstance().allchestesp = true;
	}

	@Override
	public void disable() {
		super.disable();
		InstanceMain.getInstance().allchestesp = false;
	}
}
