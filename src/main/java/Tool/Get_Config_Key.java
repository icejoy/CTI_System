package Tool;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Criteria.Config_Class;

public class Get_Config_Key
{
	JSONObject Settings;
	Config_Class keys;

	public void set_settings(JSONObject settings)
	{
		this.Settings = settings;
	}

	public Config_Class get_keys()
	{
		return this.keys;
	}

	public void start_get_key()
	{
		keys = null;
		keys = new Config_Class();
		if (this.Settings.containsKey("term"))
		{
			keys.set_term_list((JSONArray) this.Settings.get("term"));
		}
		if (this.Settings.containsKey("ref_source"))
		{
			keys.set_ref_path(this.Settings.get("ref_source").toString());
		}

		if (this.Settings.containsKey("Other_Time"))
		{
			keys.set_time(this.Settings.get("Other_Time").toString());
		}
		if (this.Settings.containsKey("Other_Type"))
		{
			keys.set_type(this.Settings.get("Other_Type").toString());
		}
		if (this.Settings.containsKey("Other_Target"))
		{
			keys.set_target(this.Settings.get("Other_Target").toString());
		}
		if (this.Settings.containsKey("Other_Name"))
		{
			keys.set_name(this.Settings.get("Other_Name").toString());
		}
		if (this.Settings.containsKey("Other_IOC"))
		{
			keys.set_ioc(this.Settings.get("Other_IOC").toString());
		}
		if (this.Settings.containsKey("Other_term"))
		{
			keys.set_term(this.Settings.get("Other_term").toString());
		}
		if (this.Settings.containsKey("Other_ref"))
		{
			keys.set_ref(this.Settings.get("Other_ref").toString());
		}
	}
}
