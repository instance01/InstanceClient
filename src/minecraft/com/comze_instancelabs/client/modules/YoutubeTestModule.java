package com.comze_instancelabs.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.comze_instancelabs.client.Module;

public class YoutubeTestModule extends Module
{
    public YoutubeTestModule()
    {
        super("yt", "Youtube Test.");
        this.setHidden(true);
    }

    @Override
    public void execute()
    {
    	/*Platform.runLater(new Runnable() {
			@Override
			public void run() {
				YoutubePlayerTest t = new YoutubePlayerTest();
		    	t.start();
			}
		});
    	new Thread() {
			public void run() {
				YoutubePlayerTest t = new YoutubePlayerTest();
		    	t.start();
			}
		}.start();*/
    	//YoutubePlayerTest.main(null);
    }
}