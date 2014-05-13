package com.comze_instancelabs.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.comze_instancelabs.client.Module;
import com.comze_instancelabs.client.InstanceRender;

public class ChestESPModule extends Module
{
    public ChestESPModule()
    {
        super("chestesp", "Renders TileEntity ESP (Chest/Enderchest/Sign/Spawner/Piston/Skull/Beacon/Endportal/EnchantmentTable).");
    }
}
