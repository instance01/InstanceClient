package com.comze_instancelabs.client.modules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3Pool;

import com.comze_instancelabs.client.InstanceMain;
import com.comze_instancelabs.client.Module;
import com.comze_instancelabs.client.util.CEntityPlayer;

public class AutoFishModule extends Module
{
    public static CEntityPlayer c;

    public AutoFishModule()
    {
        super("autofish", "Automatically catches fish.");
    }

    //int ccc = 0;
    int cc = 0;
    int c_ = 0;
    
    @Override
    public void execute()
    {
        if (this.isEnabled())
        {
        	if (Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem() != null && Item.getIdFromItem(Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem().getItem()) == 346) {
				if (Minecraft.getMinecraft().thePlayer.fishEntity != null) {
					if(cc > 0){
						cc--;
						return;
					}
					if (Minecraft.getMinecraft().thePlayer.fishEntity.motionX == 0 && Minecraft.getMinecraft().thePlayer.fishEntity.motionZ == 0 && Minecraft.getMinecraft().thePlayer.fishEntity.motionY < -0.02) {
						System.out.println(String.format("%.2f", Minecraft.getMinecraft().thePlayer.fishEntity.motionY));
						cc = 40;
						this.sendPackets();
					}
				} else {
					c_--;
					if (c_ < 1) {
						c_ = 30;
						this.sendPackets();
					}
				}
			}
        }else{
        	enable();
        }
    }

    @Override
    public void enable()
    {
        super.enable();
        InstanceMain.autofish = true;
    }

    @Override
    public void disable()
    {
    	InstanceMain.autofish = false;
        super.disable();
    }
    
    public void sendPackets(){ // EntityFishHook fishEntity
    	Robot bot;
		try {
			bot = new Robot();
	        bot.mousePress(InputEvent.BUTTON3_MASK);
	        bot.mouseRelease(InputEvent.BUTTON3_MASK);
		} catch (AWTException e) {
			e.printStackTrace();
		}
    	//Minecraft.getMinecraft().playerController.onPlayerRightClick(Minecraft.getMinecraft().thePlayer, Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem(), (int)Minecraft.getMinecraft().thePlayer.posX, (int)Minecraft.getMinecraft().thePlayer.posY, (int)Minecraft.getMinecraft().thePlayer.posZ, 1, Vec3.createVectorHelper(1D, 1D, 1D));
    	//ItemFishingRod i = (ItemFishingRod) Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem();
    	//i.onItemRightClick(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem(), Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft().thePlayer);
    	/*EntityPlayer var1 = Minecraft.getMinecraft().thePlayer;
    	if (fishEntity != null)
        {
            int var4 = fishEntity.func_146034_e();
            var1.swingItem();
        }
        else
        {
            if (!Minecraft.getMinecraft().theWorld.isClient)
            {
            	Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new EntityFishHook(Minecraft.getMinecraft().theWorld, var1));
            }

            var1.swingItem();
        }*/
    	System.out.println("sent");
		//Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(-1, -1, -1, -1, Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem(), 0.0F, 0.0F, 0.0F));
		//Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation(Minecraft.getMinecraft().thePlayer, 0));
    }
}
