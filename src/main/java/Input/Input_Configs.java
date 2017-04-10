package Input;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Criteria.Config_Class;
import Tool.Get_Config_Key;

public class Input_Configs
{
	String Mom_Path;
	JSONObject settings;
	Get_Config_Key gck = new Get_Config_Key();
	JSONParser jsonParser = new JSONParser();
	ArrayList<Config_Class> Config_Content = new ArrayList<Config_Class>();

	public void set_Path(String MomPath)
	{
		this.Mom_Path = MomPath;
	}

	public void input(String choose)
	{
		if (choose.equals("MalwareType"))
		{
			choose = "/MalwareType/";
			read_Config_content(this.Mom_Path + choose, Find_JsonFile(choose));
		}
		else if (choose.equals("MalwareName"))
		{
			choose = "/MalwareName/";
			read_Config_content(this.Mom_Path + choose, Find_JsonFile(choose));
		}
		else if (choose.equals("Target"))
		{
			choose = "/Target/";
			read_Config_content(this.Mom_Path + choose, Find_JsonFile(choose));
		}
		else if (choose.equals("IOC"))
		{
			choose = "/IOC/";
			read_Config_content(this.Mom_Path + choose, Find_JsonFile(choose));
		}
		else if (choose.equals("Time"))
		{
			choose = "/Time/";
			read_Config_content(this.Mom_Path + choose, Find_JsonFile(choose));
		}
	}

	public ArrayList<Config_Class> get_input()
	{
		return this.Config_Content;
	}

	public void read_Config_content(String path, ArrayList<String> config_path)
	{
		this.Config_Content.clear();
		try
		{
			for (String p : config_path)
			{
				settings = (JSONObject) jsonParser.parse(new FileReader(path + p));
				this.gck.set_settings(settings);
				this.gck.start_get_key();
				this.Config_Content.add(this.gck.get_keys());
			}

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public ArrayList<String> Find_JsonFile(String path)
	{
		ArrayList<String> config_list = new ArrayList<String>();
		File folder = new File(this.Mom_Path + path);
		String[] list = folder.list();
		for (String s : list)
		{
			if (s.indexOf(".json") > -1)
			{
				config_list.add(s);
			}
		}
		return config_list;
	}
}
