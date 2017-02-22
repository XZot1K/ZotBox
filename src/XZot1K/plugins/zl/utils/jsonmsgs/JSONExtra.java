package XZot1K.plugins.zl.utils.jsonmsgs;

import XZot1K.plugins.zl.ZotLib;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

public class JSONExtra
{
    private ZotLib plugin = ZotLib.getInstance();
    private JSONObject extraObject;

    @SuppressWarnings("unchecked")
    public JSONExtra(String text)
    {
        extraObject = new JSONObject();

        if (text != null)
        {
            getExtraObject().put("text", plugin.getGeneralLibrary().color(text));
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
        clickEvent.put("value", plugin.getGeneralLibrary().color(value));
        getExtraObject().put("clickEvent", clickEvent);
    }

    @SuppressWarnings("unchecked")
    public void setHoverEvent(JSONHoverAction action, String value)
    {
        JSONObject hoverEvent = new JSONObject();
        hoverEvent.put("action", action.name().toLowerCase());
        hoverEvent.put("value", plugin.getGeneralLibrary().color(value));
        getExtraObject().put("hoverEvent", hoverEvent);
    }

    public String itemToJSON(ItemStack itemStack)
    {
        return plugin.getPacketLibrary().getJSONItemGetter().getJSONItem(itemStack);
    }

}
