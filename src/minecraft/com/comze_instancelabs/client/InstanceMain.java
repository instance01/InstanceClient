package com.comze_instancelabs.client;

import java.util.ArrayList;
import java.util.List;

import org.darkstorm.minecraft.gui.InstanceGuiManager;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;

import com.comze_instancelabs.client.modules.AutoFishModule;
import com.comze_instancelabs.client.modules.ChestESPModule;
import com.comze_instancelabs.client.modules.ColoredMobsModule;
import com.comze_instancelabs.client.modules.FreecamModule;
import com.comze_instancelabs.client.modules.FullbrightModule;
import com.comze_instancelabs.client.modules.HelpModule;
import com.comze_instancelabs.client.modules.MobESPModule;
import com.comze_instancelabs.client.modules.XrayModule;

public class InstanceMain
{
    static Render r;
    static InstanceGuiManager guimanager;

    public static boolean xray = false;
    public static boolean freecam = false;
    public static boolean autofish = false;
    
    public static List<Module> modList = new ArrayList<Module>();

    public InstanceMain()
    {
        init();
    }

    public static void init()
    {
        r  = new Render();
        modList.add(new HelpModule());
        modList.add(new FullbrightModule());
        modList.add(new ColoredMobsModule());
        modList.add(new MobESPModule());
        modList.add(new ChestESPModule());
        modList.add(new XrayModule());
        modList.add(new FreecamModule());
        modList.add(new AutoFishModule());
    }

    public static void initGUI()
    {
        guimanager = new InstanceGuiManager();
        guimanager.setTheme(new SimpleTheme());
        guimanager.setup();
    }

    public static Render getRender()
    {
        return r;
    }

    public static InstanceGuiManager getGuiManager()
    {
        return guimanager;
    }

    public void executeCMD(String cmd)
    {
        // search in modules and try to enable
        if (containsMod(cmd))
        {
            Module m = getMod(cmd);

            if (m != null)
            {
                m.execute();
            }
        }
    }

    public static boolean containsMod(String modname)
    {
        for (Module m : modList)
        {
            if (m.name.toLowerCase().startsWith(modname.toLowerCase()))
            {
                return true;
            }
        }

        return false;
    }

    public static String getModName(String modname)
    {
        for (Module m : modList)
        {
            if (m.name.toLowerCase().startsWith(modname.toLowerCase()))
            {
                return m.name;
            }
        }

        return "";
    }

    public static Module getMod(String modname)
    {
        for (Module m : modList)
        {
            if (m.name.toLowerCase().startsWith(modname.toLowerCase()))
            {
                return m;
            }
        }

        return null;
    }
}
