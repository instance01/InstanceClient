package com.comze_instancelabs.client;

public class Module
{
    String name = "";
    String desc = "";
    boolean enabled = false;

    public Module(String name, String description)
    {
        this.name = name;
        this.desc = description;
    }

    public void execute()
    {
        if (isEnabled())
        {
            disable();
            return;
        }

        enable();
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void enable()
    {
        enabled = true;
        InstanceMain.getRender().updateModuleListFrame();
    }

    public void disable()
    {
        enabled = false;
        InstanceMain.getRender().updateModuleListFrame();
    }

    public String getName()
    {
        return name;
    }
}
