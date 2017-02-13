package XZot1K.plugins.zl.utils.jsonmsgs;

import org.bukkit.ChatColor;
import org.json.simple.JSONObject;

import java.util.List;

public class JSONExtra
{
    private JSONObject extraObject;

    @SuppressWarnings("unchecked")
    public JSONExtra(String text)
    {
        extraObject = new JSONObject();

        if (text != null)
        {
            getExtraObject().put("text", text);
        }
    }

    @SuppressWarnings("unchecked")
    public JSONExtra(String text, ChatColor color)
    {
        extraObject = new JSONObject();

        if (text != null)
        {
            getExtraObject().put("text", text);
        }

        if (color != null)
        {
            getExtraObject().put("color", color.name().toLowerCase());
        }
    }

    @SuppressWarnings("unchecked")
    public JSONExtra(String text, ChatColor color, JSONFormat format)
    {
        extraObject = new JSONObject();

        if (text != null)
        {
            getExtraObject().put("text", text);
        }

        if (color != null)
        {
            getExtraObject().put("color", color.name().toLowerCase());
        }

        if (format != null)
        {
            getExtraObject().put(format.name().toLowerCase(), true);
        }
    }

    @SuppressWarnings("unchecked")
    public JSONExtra(String text, ChatColor color, List<JSONFormat> formats)
    {
        extraObject = new JSONObject();

        if (text != null)
        {
            getExtraObject().put("text", text);
        }

        if (color != null)
        {
            getExtraObject().put("color", color.name().toLowerCase());
        }

        if (formats != null && !formats.isEmpty())
        {
            for (JSONFormat format : formats)
            {
                getExtraObject().put(format.name().toLowerCase(), true);
            }
        }
    }

    public JSONObject getExtraObject()
    {
        return extraObject;
    }

    @SuppressWarnings("unchecked")
    public void setClickEvent(JSONClickAction action, String value)
    {
        JSONObject clickEvent = new JSONObject();
        clickEvent.put("action", action.name().toLowerCase());
        clickEvent.put("value", value);
        getExtraObject().put("clickEvent", clickEvent);
    }

    @SuppressWarnings("unchecked")
    public void setHoverEvent(JSONHoverAction action, String value)
    {
        JSONObject hoverEvent = new JSONObject();
        hoverEvent.put("action", action.name().toLowerCase());
        hoverEvent.put("value", value);
        getExtraObject().put("hoverEvent", hoverEvent);
    }

}
