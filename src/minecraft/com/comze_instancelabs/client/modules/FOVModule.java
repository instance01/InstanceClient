package com.comze_instancelabs.client.modules;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Timer;

import com.comze_instancelabs.client.InstanceMain;
import com.comze_instancelabs.client.KeyBinds;
import com.comze_instancelabs.client.Module;

public class FOVModule extends Module
{
    public FOVModule()
    {
        super("fov", "Change the FOV.");
        this.setNeedArgs(true);
        this.setHidden(true);
    }

    @Override
    public void execute(String[] args)
    {
    	System.out.println(args[0]);
    	if(args.length > 1){
    		System.out.println(args[1]);
    		if(InstanceMain.isNumeric(args[1])){
    			Minecraft.getMinecraft().gameSettings.fovSetting = Float.parseFloat(args[1]);
    		}
    	}
    	
        //Minecraft.getMinecraft().ingameGUI.getChatGUI().func_146234_a(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "You can get more help by visiting http://instancelabs.eu5.org."), 1);
    }
}
