package com.comze_instancelabs.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
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
    	ChatComponentText link = new ChatComponentText("https://github.com/instance01/InstanceClient");
        link.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/instance01/InstanceClient"));
        link.getChatStyle().setUnderlined(Boolean.valueOf(true));
        link.getChatStyle().setColor(EnumChatFormatting.GOLD);
        Minecraft.getMinecraft().ingameGUI.getChatGUI().func_146234_a(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "You can get more help by visiting "), 1);
        Minecraft.getMinecraft().ingameGUI.getChatGUI().func_146234_a(link, 2);
    }
}
