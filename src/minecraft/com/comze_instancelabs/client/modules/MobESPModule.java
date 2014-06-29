package com.comze_instancelabs.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.comze_instancelabs.client.InstanceMain;
import com.comze_instancelabs.client.Module;
import com.comze_instancelabs.client.InstanceRender;

public class MobESPModule extends Module
{
    public MobESPModule()
    {
        super("mobesp", "Renders Mob ESP.");
    }
    
    @Override
    public void enable()
    {
        super.enable();
        InstanceMain.mobesp = true;
    }

    @Override
    public void disable()
    {
    	InstanceMain.mobesp = false;
        super.disable();
    }
}
