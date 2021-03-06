package com.comze_instancelabs.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class CmdTextboxGUI extends GuiScreen
{
    private static final Logger logger = LogManager.getLogger();
    private String field_146410_g = "";
    private int field_146416_h = -1;
    private boolean field_146417_i;
    private boolean field_146414_r;
    private int field_146413_s;
    private List field_146412_t = new ArrayList();
    protected GuiTextField textbox;
    protected GuiTextField textbox2;
    private String field_146409_v = "";
    private static final String __OBFID = "CL_00000682";
    private boolean typed_u = false;
    private String currentcmd = "";

    public CmdTextboxGUI()
    {
    }

    public CmdTextboxGUI(String par1Str)
    {
        this.field_146409_v = par1Str;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.textbox = new GuiTextField(100, this.fontRendererObj, 4, 4, this.width - 4, 12);
        this.textbox.setFocused(true);
        this.textbox.setText(this.field_146409_v);
        this.textbox2 = new GuiTextField(101, this.fontRendererObj, 4, 22, this.width - 4, 30);
        this.textbox2.setText(this.field_146409_v);
    }

    /**
     * "Called when the screen is unloaded. Used to disable keyboard repeat events."
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
        this.mc.ingameGUI.getChatGUI().resetScroll();
        if(Minecraft.getMinecraft().m.getRender().cmdTab){
    		Minecraft.getMinecraft().m.getRender().cmdTab = false;
    	}
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.textbox.updateCursorCounter();
    }

    /**
     * Fired when a key is typed. This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        this.field_146414_r = false;

        if (this.mc.m.containsMod(this.textbox.getText()))
        {
            currentcmd = this.textbox.getText();
        }

        if (par2 == 15)
        {
            // TAB
            this.sendTab();
        }
        else
        {
            this.field_146417_i = false;
        }

        if (par2 == 22 && !typed_u)
        {
            // U key
            typed_u = true;
            return;
        }
        
        if(!typed_u){
        	typed_u = true;
        }

        if (par2 == 1)
        {
            // ESC
            this.mc.displayGuiScreen((GuiScreen) null);
        }
        else if (par2 != 28 && par2 != 156)
        {
            if (par2 == 200)   // UP KEY
            {
                //this.getHistory(-1);
            }
            else if (par2 == 208)     // DOWN KEY
            {
                //this.getHistory(1);
            }
            else if (par2 == 201)
            {
                //this.mc.ingameGUI.getChatGUI().func_146229_b(this.mc.ingameGUI.getChatGUI().func_146232_i() - 1);
            }
            else if (par2 == 209)
            {
                //this.mc.ingameGUI.getChatGUI().func_146229_b(-this.mc.ingameGUI.getChatGUI().func_146232_i() + 1);
            }
            else
            {
                this.textbox.textboxKeyTyped(par1, par2);

                if (this.textbox.getText().length() > 0 && this.mc.m.containsMod(this.textbox.getText()))
                {
                    if (ChatAllowedCharacters.isAllowedCharacter(par1))
                    {
                        this.textbox2.setText(currentcmd + Character.toString(par1));
                        currentcmd = this.textbox.getText() + Character.toString(par1);
                    }
                    else
                    {
                        this.textbox2.setText(currentcmd);
                        currentcmd = this.textbox.getText();
                    }
                }
                else
                {
                    currentcmd = "";
                    this.textbox2.setText("");
                }
            }
        }
        else
        {
            String var3 = this.textbox.getText().trim();

            if (var3.length() > 0)
            {
                this.sendCommand(var3);
            }

            this.mc.displayGuiScreen((GuiScreen) null);
        }
    }

    public void sendCommand(String cmd)
    {
        Minecraft.getMinecraft().m.executeCMD(cmd);
        //this.mc.ingameGUI.getChatGUI().func_146239_a(cmd);
        currentcmd = "";
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput()
    {
        try {
			super.handleMouseInput();
		} catch (IOException e) {
			e.printStackTrace();
		}
        int var1 = Mouse.getEventDWheel();

        if (var1 != 0)
        {
            if (var1 > 1)
            {
                var1 = 1;
            }

            if (var1 < -1)
            {
                var1 = -1;
            }

            if (!isShiftKeyDown())
            {
                var1 *= 7;
            }

            //this.mc.ingameGUI.getChatGUI().func_146229_b(var1);
        }
    }

    public void sendTab()
    {
        String var3;
        System.out.println(this.textbox.getText());
        String t = this.mc.m.getModName(currentcmd);
        this.textbox.setText(t);
        this.textbox2.setText(t);
        currentcmd = t;
    }

    private void func_146405_a(String p_146405_1_, String p_146405_2_)
    {
        if (p_146405_1_.length() >= 1)
        {
            this.mc.thePlayer.sendQueue.addToSendQueue(new C14PacketTabComplete(p_146405_1_));
            this.field_146414_r = true;
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        drawRect(2, 2, this.width - 2, 14, 0x99111111);

        if (currentcmd.length() > 0)
        {
            drawRect(2, 20, this.width - 2, 32, 0x55111111);
        }

        this.textbox.drawTextBox();
        this.textbox2.drawTextBox();
        //IChatComponent var4 = this.mc.ingameGUI.getChatGUI().func_146236_a(Mouse.getX(), Mouse.getY());
        super.drawScreen(par1, par2, par3);
    }

    public void func_146406_a(String[] p_146406_1_)
    {
        if (this.field_146414_r)
        {
            this.field_146417_i = false;
            this.field_146412_t.clear();
            String[] var2 = p_146406_1_;
            int var3 = p_146406_1_.length;

            for (int var4 = 0; var4 < var3; ++var4)
            {
                String var5 = var2[var4];

                if (var5.length() > 0)
                {
                    this.field_146412_t.add(var5);
                }
            }

            if (this.field_146412_t.size() > 0)
            {
                this.field_146417_i = true;
                this.sendTab();
            }
        }
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in
     * single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
