package com.comze_instancelabs.client;

import java.util.ArrayList;
import java.util.List;

import org.darkstorm.minecraft.gui.InstanceGuiManager;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;

import com.comze_instancelabs.client.modules.AllChestESPModule;
import com.comze_instancelabs.client.modules.AutoFishModule;
import com.comze_instancelabs.client.modules.AutoPrisonMine;
import com.comze_instancelabs.client.modules.ChestESPModule;
import com.comze_instancelabs.client.modules.ColoredMobsModule;
import com.comze_instancelabs.client.modules.FOVModule;
import com.comze_instancelabs.client.modules.FreecamModule;
import com.comze_instancelabs.client.modules.FullbrightModule;
import com.comze_instancelabs.client.modules.HelpModule;
import com.comze_instancelabs.client.modules.KeybindModule;
import com.comze_instancelabs.client.modules.MobESPModule;
import com.comze_instancelabs.client.modules.TimerModule;
import com.comze_instancelabs.client.modules.XrayModule;
import com.comze_instancelabs.client.modules.YoutubeTestModule;

public class InstanceMain {
	static InstanceRender r;
	static InstanceGuiManager guimanager;

	static InstanceMain instance;

	public boolean xray = false;
	public boolean freecam = false;
	public boolean autofish = false;
	public boolean mobesp = false;
	public boolean coloredmobs = false;
	public boolean allchestesp = false;
	public boolean chestesp = false;

	public static List<Module> modList = new ArrayList<Module>();

	public InstanceMain() {
		instance = this;
		init();
	}

	public static void init() {
		r = new InstanceRender();
		modList.add(new HelpModule());
		modList.add(new FullbrightModule());
		modList.add(new ColoredMobsModule());
		modList.add(new MobESPModule());
		modList.add(new ChestESPModule());
		modList.add(new AllChestESPModule());
		modList.add(new XrayModule());
		modList.add(new FreecamModule());
		modList.add(new AutoFishModule());
		modList.add(new KeybindModule());
		modList.add(new TimerModule());
		modList.add(new AutoPrisonMine());
		modList.add(new FOVModule());
		modList.add(new YoutubeTestModule());
		Settings.loadAll();
	}

	public static void initGUI() {
		guimanager = new InstanceGuiManager();
		guimanager.setTheme(new SimpleTheme());
		guimanager.setup();
		Settings.loadEnabledMods();
	}

	public static InstanceRender getRender() {
		return r;
	}

	public static InstanceGuiManager getGuiManager() {
		return guimanager;
	}

	public void executeCMD(String cmd) {
		String cmd_ = cmd;
		if (cmd.contains(" ")) {
			cmd_ = cmd.split(" ")[0];
		}

		// search in modules and try to enable
		if (containsMod(cmd_)) {
			Module m = getMod(cmd_);

			if (m != null) {
				if (m.getNeedArgs()) {
					m.execute(cmd.split(" "));
					return;
				} else if (m.getNeedUpdate()) {
					m.execute(true);
				}
				m.execute();
			}
		}
	}

	public static boolean containsMod(String modname) {
		for (Module m : modList) {
			if (m.name.toLowerCase().startsWith(modname.toLowerCase())) {
				return true;
			}
		}

		return false;
	}

	public static String getModName(String modname) {
		for (Module m : modList) {
			if (m.name.toLowerCase().startsWith(modname.toLowerCase())) {
				return m.name;
			}
		}

		return "";
	}

	public static Module getMod(String modname) {
		for (Module m : modList) {
			if (m.name.toLowerCase().startsWith(modname.toLowerCase())) {
				return m;
			}
		}

		return null;
	}

	public static boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}

	public static void update() {
		for (Module m : modList) {
			m.update();
		}
	}

	public static InstanceMain getInstance() {
		return instance;
	}
}
