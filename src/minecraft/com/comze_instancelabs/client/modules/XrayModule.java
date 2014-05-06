package com.comze_instancelabs.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.comze_instancelabs.client.Main;
import com.comze_instancelabs.client.Module;
import com.comze_instancelabs.client.Render;

public class XrayModule extends Module {

	public XrayModule() {
		super("xray", "Renders XRay Blocks.");
	}
	
	@Override
	public void execute(){
		super.execute();
		Minecraft.getMinecraft().renderGlobal.loadRenderers();
	}
	
	@Override
	public void enable(){
		super.enable();
		Main.xray = true;
	}

	@Override
	public void disable(){
		super.disable();
		Main.xray = false;
	}
}
