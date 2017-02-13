package XZot1K.plugins.zl.utils.jsonmsgs;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class JSONMessage
{

    private ZotLib plugin = Manager.getPlugin();
    private JSONObject chatObject;

    @SuppressWarnings("unchecked")
    public JSONMessage(String text)
    {
        chatObject = new JSONObject();

        if (text != null)
        {
            getChatObject().put("text", text);
        }
    }

    @SuppressWarnings("unchecked")
    public JSONMessage(String text, ChatColor color)
    {
        chatObject = new JSONObject();

        if (text != null)
        {
            getChatObject().put("text", text);
        }

        if (color != null)
        {
            getChatObject().put("color", color.name().toLowerCase());
        }
    }

    @SuppressWarnings("unchecked")
    public JSONMessage(String text, ChatColor color, JSONFormat format)
    {
        chatObject = new JSONObject();

        if (text != null)
        {
            getChatObject().put("text", text);
        }

        if (color != null)
        {
            getChatObject().put("color", color.name().toLowerCase());
        }

        if (format != null)
        {
            getChatObject().put(format.name().toLowerCase(), true);
        }
    }

    @SuppressWarnings("unchecked")
    public JSONMessage(String text, ChatColor color, List<JSONFormat> formats)
    {
        chatObject = new JSONObject();

        if (text != null)
        {
            getChatObject().put("text", text);
        }

        if (color != null)
        {
            getChatObject().put("color", color.name().toLowerCase());
        }

        if (formats != null && !formats.isEmpty())
        {
            for (JSONFormat format : formats)
            {
                getChatObject().put(format.name().toLowerCase(), true);
            }
        }
    }

    public JSONObject getChatObject()
    {
        return chatObject;
    }

    @SuppressWarnings("unchecked")
    public void addExtra(JSONExtra extraObject)
    {
        if (!chatObject.containsKey("extra"))
        {
            chatObject.put("extra", new JSONArray());
        }
        JSONArray extra = (JSONArray) chatObject.get("extra");
        extra.add(extraObject.getExtraObject());
        getChatObject().put("extra", extra);
    }

    public void sendJSONToPlayer(Player player)
    {
        plugin.getPacketLibrary().getJSONMessageSender().sendJSONMessage(player, getChatObject().toJSONString());
    }

    @SuppressWarnings("unchecked")
    public void setClickEvent(JSONClickAction action, String value)
    {
        JSONObject clickEvent = new JSONObject();
        clickEvent.put("action", action.name().toLowerCase());
        clickEvent.put("value", value);
        getChatObject().put("clickEvent", clickEvent);
    }

    @SuppressWarnings("unchecked")
    public void setHoverEvent(JSONHoverAction action, String value)
    {
        JSONObject hoverEvent = new JSONObject();
        hoverEvent.put("action", action.name().toLowerCase());
        hoverEvent.put("value", value);
        getChatObject().put("hoverEvent", hoverEvent);
    }

}
