package Input;

import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import Tool.Read_Config;

public class Input_USCERT
{
	URL url;
	String Start_Page = "";
	ArrayList<Document> document = new ArrayList<Document>();
	Read_Config read_Config = new Read_Config();

	public void set_config(String config_path)
	{
		try
		{
			this.read_Config.ReadConfig(config_path);
			JSONObject settings = new JSONObject(this.read_Config.get_Config());

			if (settings.containsKey("Start_Page"))
			{
				this.Start_Page = (String) settings.get("Start_Page");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void input()
	{
		Document doc;
		for (int i = 0;; i++)
		{
			try
			{
				this.url = new URL(this.Start_Page + i);
				doc = Jsoup.parse(this.url, 100000);
				if (doc.select("div").size() > 0)
				{
					this.document.add(doc);
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				break;
			}
		}
	}

	public ArrayList<Document> get_document()
	{
		return this.document;
	}

	public Boolean get_haved_read()
	{
		if (document.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
