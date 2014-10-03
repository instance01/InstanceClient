package com.comze_instancelabs.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.comze_instancelabs.client.Module;
import com.comze_instancelabs.client.util.IYoutubeDownloader;

public class YoutubeTestModule extends Module {
	public YoutubeTestModule() {
		super("yt", "Youtube Test.");
		this.setHidden(true);
	}

	@Override
	public void execute(String[] args) {
		if (args.length > 1) {
			System.out.println(args[1]);
			String id = args[1];

			IYoutubeDownloader yd = new IYoutubeDownloader(id);
		}
	}
}
