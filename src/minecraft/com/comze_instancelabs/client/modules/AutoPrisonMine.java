package com.comze_instancelabs.client.modules;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.comze_instancelabs.client.Module;

public class AutoPrisonMine extends Module
{
	private int[] loc1 = new int[3];
	private int[] loc2 = new int[3];
	
	private int[] startloc = new int[3];
	private int xlength = 0;
	private int ylength = 0;
	private int zlength = 0;
	
	private int cx = 0;
	private int cy = 0;
	private int cz = 0;
	
	int cnext = 20;
	int c = 0;
	
	boolean mining = false;
	
    public AutoPrisonMine()
    {
        super("pnuke", "Prison Servers Legit Nuker.");
        this.setNeedArgs(true);
        this.setHidden(true);
    }

    @Override
    public void update(){
    	c++;
    	if(c > cnext){
    		c = 0;
    		if(mining){
    			// next block
    			System.out.println(cx + " " + cy + " " + cz);
    			if(cx < startloc[0] + xlength){
    				// new Packet14BlockDig(0, k1, l1, i2, 1)
    				Block block = Minecraft.getMinecraft().theWorld.getBlock(cx, cy - 1, cz);
    				if(block != null){
    					Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(0, cx, cy - 1, cz, 1));
    					Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(2, cx, cy - 1, cz, 1));
    				}
    				//Minecraft.getMinecraft().thePlayer.motionX++;
    				cx++;
    			}else{
    				if(cz < startloc[2] + zlength){
    					cz++;
        				cx = startloc[0];
    				}
    			}
    		}
    	}
    	return;
    }
    
    // example; just for me to figure that stuff out
    // 418, 10, 200
    // 410, 5, 220
    // -> 8, 5, 20
    
    @Override
    public void execute(String[] args)
    {
    	System.out.println(args[0]);
    	if(args.length > 1){
    		System.out.println(args[1]);
    		if(args[1].equalsIgnoreCase("set1")){
    			EntityClientPlayerMP p = Minecraft.getMinecraft().thePlayer;
    			loc1[0] = (int) p.posX;
    			loc1[1] = (int) p.posY;
    			loc1[2] = (int) p.posZ;
    	        Minecraft.getMinecraft().ingameGUI.getChatGUI().func_146234_a(new ChatComponentText(EnumChatFormatting.RED + "Successfully set Location 1"), 1);
    		}else if(args[1].equalsIgnoreCase("set2")){
    			EntityClientPlayerMP p = Minecraft.getMinecraft().thePlayer;
    			loc2[0] = (int) p.posX;
    			loc2[1] = (int) p.posY;
    			loc2[2] = (int) p.posZ;
    	        Minecraft.getMinecraft().ingameGUI.getChatGUI().func_146234_a(new ChatComponentText(EnumChatFormatting.RED + "Successfully set Location 2"), 1);
    		}else if(args[1].equalsIgnoreCase("clear")){
    			mining = false;
    			loc1 = new int[3];
    			loc2 = new int[3];
    		}
    	}else{
    		// let's hope the dude didn't fail to set the locations..
    		if(loc1[0] > loc2[0]){ // x
    			startloc[0] = loc2[0];
    			xlength = loc1[0] - loc2[0];
    		}else{
    			startloc[0] = loc1[0];
    			xlength = loc2[0] - loc1[0];
    		}
    		if(loc1[1] > loc2[1]){ // y
    			startloc[1] = loc1[1];
    			ylength = loc1[1] - loc2[1];
    		}else{
    			startloc[1] = loc2[1];
    			ylength = loc2[1] - loc1[1];
    		}
    		if(loc1[2] > loc2[2]){ // z
    			startloc[2] = loc2[2];
    			zlength = loc1[2] - loc2[2];
    		}else{
    			startloc[2] = loc1[2];
    			zlength = loc2[2] - loc1[2];
    		}
    		cx = startloc[0];
    		cy = startloc[1];
    		cz = startloc[2];
    		mining = !mining;
	        Minecraft.getMinecraft().ingameGUI.getChatGUI().func_146234_a(new ChatComponentText(EnumChatFormatting.RED + "Mining state: " + Boolean.toString(mining)), 1);
    	}
    }

}
