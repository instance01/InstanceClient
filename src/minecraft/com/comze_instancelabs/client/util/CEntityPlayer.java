package com.comze_instancelabs.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import com.comze_instancelabs.client.modules.FreecamModule;
import com.mojang.authlib.GameProfile;

public class CEntityPlayer extends AbstractClientPlayer
{
    public CEntityPlayer(World p_i45324_1_)
    {
        super(p_i45324_1_, new GameProfile("", Minecraft.getMinecraft().thePlayer.getCommandSenderName()));
        Minecraft mc = Minecraft.getMinecraft();
        this.setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
        FreecamModule.c = this;
    }

    @Override
    public void addChatMessage(IChatComponent var1)
    {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean canCommandSenderUseCommand(int var1, String var2)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ChunkCoordinates getPlayerCoordinates()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
