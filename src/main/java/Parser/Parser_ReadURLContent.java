package Parser;

import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import Criteria.US_CERT_Class;
import Tool.Read_Config;

public class Parser_ReadURLContent
{
	String body = "";
	String Content = "";
	String Split_Content_Front = "";
	String Split_Content_End = "";
	ArrayList<US_CERT_Class> result;
	ArrayList<String> Description = new ArrayList<String>();

	Read_Config read_Config = new Read_Config();

	public void set_config(String Config_path)
	{
		this.read_Config.ReadConfig(Config_path);
		JSONObject settings = new JSONObject(this.read_Config.get_Config());

		if (settings.containsKey("body"))
		{
			this.body = (String) settings.get("body");
		}
		if (settings.containsKey("Split_Content_Front"))
		{
			this.Split_Content_Front = (String) settings.get("Split_Content_Front");
		}
		if (settings.containsKey("Split_Content_End"))
		{
			this.Split_Content_End = (String) settings.get("Split_Content_End");
		}
		if (settings.containsKey("Content"))
		{
			this.Content = (String) settings.get("Content");
		}
	}

	public void parse(ArrayList<US_CERT_Class> uscet)
	{
		this.result = null;
		this.result = new ArrayList<US_CERT_Class>(uscet);
		Read_URL();
	}

	public void Read_URL()
	{
		Document document;
		Elements ele;
		String[] str;
		for (int i = 0; i < this.result.size(); i++)
		{
			try
			{
				document = null;
				document = Jsoup.parse(new URL(this.result.get(i).get_URL()), 100000);
				ele = document.select(this.body);
				str = ele.toString().split(this.Split_Content_Front);
				str = str[1].split(this.Split_Content_End);
				document = Jsoup.parse(str[0]);
				this.result.get(i).set_Description(document.select(this.Content).text());
				this.Description.add(document.select(this.Content).text());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public ArrayList<String> get_Content()
	{
		return this.Description;
	}
}
