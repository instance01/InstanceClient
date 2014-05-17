package com.comze_instancelabs.client.modules;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.comze_instancelabs.client.InstanceMain;
import com.comze_instancelabs.client.KeyBinds;
import com.comze_instancelabs.client.Module;

public class KeybindModule extends Module
{
    public KeybindModule()
    {
        super("bind", "Binds a key to a module.");
        this.setNeedArgs(true);
        this.setHidden(true);
    }

    @Override
    public void execute(String[] args)
    {
    	if(args.length > 2){
    		if(InstanceMain.containsMod(args[1])){
        		KeyBinds.binds.put(args[1], Keyboard.getKeyIndex(args[2].toUpperCase()));
        	}
    	}
    	
        //Minecraft.getMinecraft().ingameGUI.getChatGUI().func_146234_a(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "You can get more help by visiting http://instancelabs.eu5.org."), 1);
    }
}
