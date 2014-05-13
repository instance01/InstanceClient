package com.comze_instancelabs.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

import org.lwjgl.input.Keyboard;

import com.comze_instancelabs.client.InstanceMain;
import com.comze_instancelabs.client.Module;
import com.comze_instancelabs.client.util.CEntityPlayer;

public class FreecamModule extends Module
{
    public static CEntityPlayer c;

    public FreecamModule()
    {
        super("freecam", "Freecam");
    }

    @Override
    public void execute()
    {
        super.execute();

        if (this.isEnabled())
        {
        	if(Minecraft.getMinecraft().theWorld != null){
                Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new CEntityPlayer(Minecraft.getMinecraft().theWorld));
        	}
        }
    }

    @Override
    public void enable()
    {
        super.enable();
        InstanceMain.freecam = true;
    }

    @Override
    public void disable()
    {
        super.disable();
        InstanceMain.freecam = false;

        if (c != null)
        {
            Minecraft.getMinecraft().theWorld.removeEntity(c);
        }
    }
    
    
    public static void fly(EntityPlayerSP s){
    	s.onGround = false;
    	s.motionX = 0.0D;
		s.motionY = 0.0D;
		s.motionZ = 0.0D;
		if (Keyboard.isKeyDown(57) && Minecraft.getMinecraft().inGameHasFocus) {
			s.motionY++;
		} else if (Keyboard.isKeyDown(42) && Minecraft.getMinecraft().inGameHasFocus) {
			s.motionY--;
		}
		double d5 = s.rotationPitch + 90F;
		double d7 = s.rotationYaw + 90F;
		boolean flag4 = Keyboard.isKeyDown(17) && Minecraft.getMinecraft().inGameHasFocus;
		boolean flag6 = Keyboard.isKeyDown(31) && Minecraft.getMinecraft().inGameHasFocus;
		boolean flag8 = Keyboard.isKeyDown(30) && Minecraft.getMinecraft().inGameHasFocus;
		boolean flag10 = Keyboard.isKeyDown(32) && Minecraft.getMinecraft().inGameHasFocus;
		if (flag4) {
			if (flag8) {
				d7 -= 45D;
			} else if (flag10) {
				d7 += 45D;
			}
		} else if (flag6) {
			d7 += 180D;
			if (flag8) {
				d7 += 45D;
			} else if (flag10) {
				d7 -= 45D;
			}
		} else if (flag8) {
			d7 -= 90D;
		} else if (flag10) {
			d7 += 90D;
		}
		if (flag4 || flag8 || flag6 || flag10) {
			s.motionX = Math.cos(Math.toRadians(d7));
			s.motionZ = Math.sin(Math.toRadians(d7));
		}
    }
}
