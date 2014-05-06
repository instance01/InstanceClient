package com.comze_instancelabs.client.modules;

import net.minecraft.client.Minecraft;

import com.comze_instancelabs.client.Main;
import com.comze_instancelabs.client.Module;
import com.comze_instancelabs.client.util.CEntityPlayer;

public class FreecamModule extends Module {

	public static CEntityPlayer c;
	
	public FreecamModule() {
		super("freecam", "Freecam");
	}
	
	@Override
	public void execute(){
		super.execute();
		if(this.isEnabled()){
			Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new CEntityPlayer(Minecraft.getMinecraft().theWorld));
		}
	}
	
	@Override
	public void enable(){
		super.enable();
		Main.freecam = true;
	}

	@Override
	public void disable(){
		super.disable();
		Main.freecam = false;
		if(c != null){
			Minecraft.getMinecraft().theWorld.removeEntity(c);
		}
	}
}
