//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Administrator\Downloads\Minecraft1.12.2 Mappings"!

//Decompiled by Procyon!

package shop.xiaoda.event.rendering;

import shop.xiaoda.event.api.events.*;

public class EventRender3D implements Event
{
    private static float ticks;
    
    public EventRender3D(final float Ticks) {
        EventRender3D.ticks = Ticks;
    }
    
    public float getPartialTicks() {
        return EventRender3D.ticks;
    }
}