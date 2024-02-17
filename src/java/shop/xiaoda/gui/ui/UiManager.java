//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Administrator\Downloads\Minecraft1.12.2 Mappings"!

//Decompiled by Procyon!

package shop.xiaoda.gui.ui;

import net.minecraft.client.*;
import shop.xiaoda.gui.ui.modules.*;
import shop.xiaoda.event.rendering.*;
import org.lwjgl.input.*;
import java.awt.*;
import shop.xiaoda.utils.render.*;
import java.util.*;
import shop.xiaoda.event.*;
import java.lang.reflect.*;
import java.util.List;

public class UiManager
{
    public final List<UiModule> UiModules;
    private final Minecraft mc;
    public double mouseX;
    public double mouseY;
    
    public UiManager() {
        this.UiModules = new ArrayList<UiModule>();
        this.mc = Minecraft.getMinecraft();
    }
    
    public void init() {
        EventManager.register(this);
        System.out.println("Init UiModules...");
        this.addModule((UiModule)new ModuleList());
        this.addModule((UiModule)new Debug());
        this.addModule((UiModule)new TargetHud());
        this.addModule((UiModule)new Information());
        this.addModule((UiModule)new PotionsInfo());
    }
    
    @EventTarget
    public void moveUi(final EventRender2D event) {
        if (!this.mc.ingameGUI.getChatGUI().getChatOpen()) {
            return;
        }
        for (final UiModule m : this.UiModules) {
            if (!m.getState()) {
                continue;
            }
            final double xpos = m.getPosX();
            final double ypos = m.getPosY();
            final double mwidth = m.getWidth();
            final double mheight = m.getHeight();
            final double mousex = this.mouseX;
            final double mousey = this.mouseY;
            if (mousex > xpos && mousey > ypos && mousex < xpos + mwidth && mousey < ypos + mheight && Mouse.isButtonDown(0)) {
                RenderUtil.drawBorderedRect((float)xpos, (float)ypos, (float)(xpos + mwidth), (float)(ypos + mheight), 2.0f, new Color(225, 225, 225).getRGB(), 0);
                if (m.moveX == 0.0 && m.moveY == 0.0) {
                    m.moveX = (float)this.mouseX - xpos;
                    m.moveY = (float)this.mouseY - ypos;
                }
                else {
                    double setX = this.mouseX - m.moveX;
                    double setY = this.mouseY - m.moveY;
                    setX = Math.min(Math.max(0.0, setX), RenderUtil.width() - m.width);
                    setY = Math.min(Math.max(0.0, setY), RenderUtil.height() - m.height);
                    m.setPosX(setX);
                    m.setPosY(setY);
                }
            }
            else {
                if (m.moveX == 0.0 && m.moveY == 0.0) {
                    continue;
                }
                m.moveX = 0.0;
                m.moveY = 0.0;
            }
        }
    }
    
    public void addModule(final UiModule module) {
        for (final Field field : module.getClass().getDeclaredFields()) {
            field.setAccessible(true);
        }
        this.UiModules.add(module);
    }
    
    public UiModule getModule(final String name) {
        for (final UiModule m : this.UiModules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
    
    public UiModule getModule(final Class<?> cls) {
        for (final UiModule m : this.UiModules) {
            if (m.getClass() == cls) {
                return m;
            }
        }
        return null;
    }
    
    public List<UiModule> getModules() {
        return this.UiModules;
    }
}
