package net.minecraft.server;

public class MinecraftServerFIX extends Thread {
	private static final String __OBFID = "CL_00001418";

	MinecraftServer temp = null;
	
	MinecraftServerFIX(MinecraftServer paramMinecraftServer, String x0) {
		super(x0);
		this.temp = paramMinecraftServer;
	}

	public void run() {
		try{
			temp.run();
		}catch(Exception e){
			MinecraftServer.getServer().run();
		}
	}
}
