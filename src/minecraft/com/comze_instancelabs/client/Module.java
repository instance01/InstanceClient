package com.comze_instancelabs.client;

public class Module
{
    String name = "";
    String desc = "";
    boolean enabled = false;
    boolean needsargs = false;
    boolean needsupdate = false;
    
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
    
    public void execute(boolean update){
    	
    }
    
    public void execute(String[] args)
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
        Settings.saveAll();
    }

    public void disable()
    {
        enabled = false;
        InstanceMain.getRender().updateModuleListFrame();
        Settings.saveAll();
    }

    public String getName()
    {
        return name;
    }
    
    public boolean getNeedArgs(){
    	return needsargs;
    }
    
    public void setNeedArgs(boolean b){
    	this.needsargs = b;
    }
    
    public boolean getNeedUpdate(){
    	return needsupdate;
    }
    
    public void setNeedUpdate(boolean b){
    	this.needsupdate = b;
    }
}
