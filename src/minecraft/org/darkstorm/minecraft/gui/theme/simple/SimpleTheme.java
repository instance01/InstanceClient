package org.darkstorm.minecraft.gui.theme.simple;

import java.awt.Font;

import net.minecraft.client.Minecraft;

import net.minecraft.client.gui.FontRenderer;
import org.darkstorm.minecraft.gui.theme.AbstractTheme;

public class SimpleTheme extends AbstractTheme
{
    private final net.minecraft.client.gui.FontRenderer fontRenderer;

    public SimpleTheme()
    {
        //fontRenderer = new UnicodeFontRenderer(new Font("Trebuchet MS", Font.PLAIN, 15));
        fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        installUI(new SimpleFrameUI(this));
        installUI(new SimplePanelUI(this));
        installUI(new SimpleLabelUI(this));
        installUI(new SimpleButtonUI(this));
        installUI(new SimpleCheckButtonUI(this));
        installUI(new SimpleComboBoxUI(this));
        installUI(new SimpleSliderUI(this));
        installUI(new SimpleProgressBarUI(this));
    }

    public net.minecraft.client.gui.FontRenderer getFontRenderer()
    {
        return fontRenderer;
    }
}
