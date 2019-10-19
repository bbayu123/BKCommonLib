package com.bergerkiller.bukkit.common.wrappers;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.ChatColor;

import com.bergerkiller.bukkit.common.utils.StringUtil;
import com.bergerkiller.generated.net.minecraft.server.IChatBaseComponentHandle;
import com.bergerkiller.generated.org.bukkit.craftbukkit.util.CraftChatMessageHandle;

/**
 * Minecraft formatted text represented as chat components, which can be converted between legacy chat messages
 * and JSON formatted text.
 */
public final class ChatText extends BasicWrapper<IChatBaseComponentHandle> {

    private ChatText() {
    }

    /**
     * Gets the chat text as a JSON-formatted String
     * 
     * @return json chat text
     */
    public final String getJson() {
        if (handle == null) {
            return "{}";
        } else {
            return IChatBaseComponentHandle.ChatSerializerHandle.chatComponentToJson(handle);
        }
    }

    /**
     * Sets the chat text using a JSON-formatted String
     * 
     * @param jsonText to set to
     */
    public final void setJson(String jsonText) {
        handle = IChatBaseComponentHandle.ChatSerializerHandle.jsonToChatComponent(jsonText);
    }

    /**
     * Gets the chat text as a legacy format-encoded String
     * 
     * @return chat message
     */
    public final String getMessage() {
        if (handle == null) {
            return "";
        } else {
            return CraftChatMessageHandle.fromComponent(handle);
        }
    }

    /**
     * Sets the chat text using a legacy format-encoded String
     * 
     * @param messageText to set to
     */
    public final void setMessage(String messageText) {
        // Optimization for empty strings
        if (messageText.isEmpty()) {
            handle = IChatBaseComponentHandle.ChatSerializerHandle.empty();
            return;
        }

        // Use CraftBukkit util's method of parsing
        handle = CraftChatMessageHandle.fromString(messageText)[0];

        // Find trailing color formatting characters in the message that would otherwise be dropped
        ArrayList<ChatColor> trailing_formatting_chars = new ArrayList<>(0);
        for (int i = messageText.length() - 2; i >= 0; i -= 2) {
            if (messageText.charAt(i) == StringUtil.CHAT_STYLE_CHAR) {
                ChatColor color = ChatColor.getByChar(messageText.charAt(i + 1));
                if (color != null) {
                    trailing_formatting_chars.add(0, color);
                    continue;
                }
            }
            break;
        }
        if (!trailing_formatting_chars.isEmpty()) {
            handle.addSibling(IChatBaseComponentHandle.ChatSerializerHandle.modifiersToComponent(trailing_formatting_chars));
        }
    }

    /**
     * Appends text to this Chat Text
     * 
     * @param text to append
     */
    public final void append(ChatText text) {
        handle.addSibling(text.handle);
    }

    public static ChatText empty() {
        return fromMessage("");
    }

    public static ChatText fromJson(String jsonText) {
        if (jsonText == null) {
            return null;
        }
        ChatText text = new ChatText();
        text.setJson(jsonText);
        return text;
    }

    public static ChatText fromMessage(String message) {
        if (message == null) {
            return null;
        }
        ChatText text = new ChatText();
        text.setMessage(message);
        return text;
    }

    public static ChatText fromComponent(Object iChatBaseComponentHandle) {
        if (iChatBaseComponentHandle == null) {
            return null;
        }
        ChatText text = new ChatText();
        text.setHandle(IChatBaseComponentHandle.createHandle(iChatBaseComponentHandle));
        return text;
    }

    public static ChatText fromChatColor(ChatColor color) {
        if (color == null) {
            return null;
        }
        ChatText text = new ChatText();
        text.setHandle(IChatBaseComponentHandle.ChatSerializerHandle.modifiersToComponent(Collections.singleton(color)));
        return text;
    }

    /*
     * This was an old function used to convert text to JSON
     * Does this support all chat styling flags CraftBukkit uses?
     * If so, it would be better to use this function over the current conversion method.
     * Saves creating an IChatBaseComponent in between.
     * TODO: Test this! And test performance!
     */
    public static String convertTextToJson_old(String text) {
        if (text == null || text.length() == 0) {
            return "\"\"";
        }
        char c;
        int i;
        int len = text.length();
        StringBuilder sb = new StringBuilder(len + 4);
        String t;
        sb.append('"');
        for (i = 0; i < len; i += 1) {
            c = text.charAt(i);
            switch (c) {
                case '\\':
                case '"':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '/':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (c < ' ') {
                        t = "000" + Integer.toHexString(c);
                        sb.append("\\u").append(t.substring(t.length() - 4));
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
        return sb.toString();
    }
}
