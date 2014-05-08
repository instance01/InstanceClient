package com.comze_instancelabs.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.comze_instancelabs.client.Module;

public class HelpModule extends Module
{
    public HelpModule()
    {
        super("help", "Displays help information.");
        this.setHidden(true);
    }

    @Override
    public void execute()
    {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().func_146234_a(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "You can get more help by visiting http://instancelabs.eu5.org."), 1);
    }
}
