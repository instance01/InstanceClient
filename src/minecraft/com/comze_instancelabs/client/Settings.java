package com.comze_instancelabs.client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;


public class Settings
{
    public static boolean moduleFramePinned;
    public static boolean moduleListFramePinned;
    
    
    
    public static void saveDefault(){
    	Properties prop = new Properties();
    	OutputStream output = null;
     
    	try {
    		output = new FileOutputStream("instanceclient_default.properties");

    		prop.setProperty("moduleFramePinned", Boolean.toString(moduleFramePinned));
    		prop.setProperty("moduleListFramePinned", Boolean.toString(moduleListFramePinned));

    		prop.store(output, null);
    	} catch (IOException io) {
    		io.printStackTrace();
    	} finally {
    		if (output != null) {
    			try {
    				output.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
    
    public static void saveKeybinds(){
    	Properties prop = new Properties();
    	OutputStream output = null;
     
    	try {
    		output = new FileOutputStream("instanceclient_keybinds.properties");

    		for(String key : KeyBinds.binds.keySet()){
    			prop.setProperty(key, Integer.toString(KeyBinds.binds.get(key)));
    		}
    		
    		prop.store(output, null);
    	} catch (IOException io) {
    		io.printStackTrace();
    	} finally {
    		if (output != null) {
    			try {
    				output.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
    
    public static void saveEnabledMods(){
    	Properties prop = new Properties();
    	OutputStream output = null;
     
    	try {
    		output = new FileOutputStream("instanceclient_enabledmods.properties");

    		for(Module m : InstanceMain.modList){
    			if(m.isEnabled()){
    				prop.setProperty(m.getName(), "true");
    			}
    		}
    		
    		prop.store(output, null);
    	} catch (IOException io) {
    		//io.printStackTrace();
    	} finally {
    		if (output != null) {
    			try {
    				output.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
    
    
    public static void loadDefault(){
    	Properties prop = new Properties();
    	InputStream input = null;
     
    	try {
    		input = new FileInputStream("instanceclient_default.properties");

    		prop.load(input);
    		
    		moduleFramePinned = Boolean.parseBoolean(prop.getProperty("moduleFramePinned"));
    		moduleListFramePinned = Boolean.parseBoolean(prop.getProperty("moduleListFramePinned"));
    		
    	} catch (IOException io) {
    		//io.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
    
    public static void loadKeybinds(){
    	Properties prop = new Properties();
    	InputStream input = null;
     
    	try {
    		input = new FileInputStream("instanceclient_keybinds.properties");

    		prop.load(input);
    		
    		Enumeration<?> e = prop.propertyNames();
    		while (e.hasMoreElements()) {
    			String key = (String) e.nextElement();
    			String value = prop.getProperty(key);
    			KeyBinds.binds.put(key, Integer.parseInt(value));
    		}
    	} catch (IOException io) {
    		//io.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
    
    public static void loadEnabledMods(){
    	Properties prop = new Properties();
    	InputStream input = null;
     
    	try {
    		input = new FileInputStream("instanceclient_enabledmods.properties");

    		prop.load(input);
    		
    		Enumeration<?> e = prop.propertyNames();
    		while (e.hasMoreElements()) {
    			String key = (String) e.nextElement();
    			Boolean b = Boolean.parseBoolean(prop.getProperty(key));
    			if(!b){
    				continue;
    			}
    			if(InstanceMain.getMod(key).getNeedUpdate()){
    				InstanceMain.getMod(key).execute(true);
    				continue;
    			}
    			InstanceMain.getMod(key).execute();
    		}
    	} catch (IOException io) {
    		io.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
    
    public static void saveAll(){
    	saveDefault();
    	saveKeybinds();
    	saveEnabledMods();
    }
    
    public static void loadAll(){
    	loadDefault();
    	loadKeybinds();
    	//loadEnabledMods();
    }

}
