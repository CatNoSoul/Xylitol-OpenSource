//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Administrator\Downloads\Minecraft1.12.2 Mappings"!

//Decompiled by Procyon!

package shop.xiaoda.utils;

import net.minecraft.client.*;
import net.minecraft.util.*;
import shop.xiaoda.*;

public class ChatUtil
{
    private final ChatComponentText message;
    
    private ChatUtil(final ChatComponentText message) {
        this.message = message;
    }
    
    public static String addFormat(final String message, final String regex) {
        return message.replaceAll("(?i)" + regex + "([0-9a-fklmnor])", "\u00a7$1");
    }
    
    public void displayClientSided() {
        Minecraft.getMinecraft().thePlayer.addChatMessage((IChatComponent)this.message);
    }
    
    private ChatComponentText getChatComponent() {
        return this.message;
    }
    
    ChatUtil(final ChatComponentText chatComponentText, final ChatUtil chatUtils) {
        this(chatComponentText);
    }
    
    public static class ChatMessageBuilder
    {
        private static final EnumChatFormatting defaultMessageColor;
        private ChatComponentText theMessage;
        private boolean useDefaultMessageColor;
        private ChatStyle workingStyle;
        private ChatComponentText workerMessage;
        
        public ChatMessageBuilder(final boolean prependDefaultPrefix, final boolean useDefaultMessageColor) {
            this.theMessage = new ChatComponentText("");
            this.useDefaultMessageColor = false;
            this.workingStyle = new ChatStyle();
            this.workerMessage = new ChatComponentText("");
            if (prependDefaultPrefix) {
                Client.instance.getClass();
                this.theMessage.appendSibling((IChatComponent)new ChatMessageBuilder(false, false).appendText(String.valueOf(EnumChatFormatting.LIGHT_PURPLE + Client.NAME + " > ")).setColor(EnumChatFormatting.RED).build().getChatComponent());
            }
            this.useDefaultMessageColor = useDefaultMessageColor;
        }
        
        public ChatMessageBuilder() {
            this.theMessage = new ChatComponentText("");
            this.useDefaultMessageColor = false;
            this.workingStyle = new ChatStyle();
            this.workerMessage = new ChatComponentText("");
        }
        
        public ChatMessageBuilder appendText(final String text) {
            this.appendSibling();
            this.workerMessage = new ChatComponentText(text);
            this.workingStyle = new ChatStyle();
            if (this.useDefaultMessageColor) {
                this.setColor(ChatMessageBuilder.defaultMessageColor);
            }
            return this;
        }
        
        public ChatMessageBuilder setColor(final EnumChatFormatting color) {
            this.workingStyle.setColor(color);
            return this;
        }
        
        public ChatMessageBuilder bold() {
            this.workingStyle.setBold(Boolean.valueOf(true));
            return this;
        }
        
        public ChatMessageBuilder italic() {
            this.workingStyle.setItalic(Boolean.valueOf(true));
            return this;
        }
        
        public ChatMessageBuilder strikethrough() {
            this.workingStyle.setStrikethrough(Boolean.valueOf(true));
            return this;
        }
        
        public ChatMessageBuilder underline() {
            this.workingStyle.setUnderlined(Boolean.valueOf(true));
            return this;
        }
        
        public ChatUtil build() {
            this.appendSibling();
            return new ChatUtil(this.theMessage, null);
        }
        
        private void appendSibling() {
            this.theMessage.appendSibling(this.workerMessage.setChatStyle(this.workingStyle));
        }
        
        static {
            defaultMessageColor = EnumChatFormatting.WHITE;
        }
    }
}
