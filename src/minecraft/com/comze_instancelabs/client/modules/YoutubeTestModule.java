package com.comze_instancelabs.client.modules;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.comze_instancelabs.client.InstanceMain;
import com.comze_instancelabs.client.Module;
import com.comze_instancelabs.client.util.JavaYoutubeDownloader;

public class YoutubeTestModule extends Module
{
    public YoutubeTestModule()
    {
        super("yt", "Youtube Test.");
        this.setNeedArgs(true);
        this.setHidden(true);
    }

    
    @Override
    public void execute(String[] args)
    {
    	System.out.println(args[0]);
    	if(args.length > 1){
    		System.out.println(args[1]);
    		String id = args[1];
    		
    		JavaYoutubeDownloader j = new JavaYoutubeDownloader();
    		j.init();
    		
    		String outdir = ".";
    		int format = 18;
    		String encoding = "UTF-8";
    		String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13";
    		String extension = j.getExtension(format);
    		
    		try {
				j.play(id, format, encoding, userAgent, new File(outdir), extension);
			} catch (Throwable e) {
				e.printStackTrace();
			}

    	}
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
