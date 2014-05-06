package com.comze_instancelabs.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.comze_instancelabs.client.InstanceMain;
import com.comze_instancelabs.client.Module;

public class FullbrightModule extends Module
{
    public FullbrightModule()
    {
        super("fullbright", "High Brightness.");
    }

    @Override
    public void execute()
    {
        if (this.isEnabled())
        {
            Minecraft.getMinecraft().gameSettings.gammaSetting = 1F;
            disable();
        }
        else
        {
            Minecraft.getMinecraft().gameSettings.gammaSetting = 10000F;
            enable();
        }
    }
}
