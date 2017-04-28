package Parser;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import Criteria.US_CERT_Class;
import Tool.Read_Config;

public class Parser_URL
{
	String link = "";
	String body = "";
	String title = "";
	String link_attr = "";
	String URL_Front = "";
	String title_split = "";
	ArrayList<US_CERT_Class> uscert = new ArrayList<US_CERT_Class>();

	Read_Config read_Config = new Read_Config();

	public void set_config(String Config_path)
	{
		this.read_Config.ReadConfig(Config_path);
		JSONObject settings = new JSONObject(this.read_Config.get_Config());

		if (settings.containsKey("body"))
		{
			this.body = (String) settings.get("body");
		}
		if (settings.containsKey("title"))
		{
			this.title = (String) settings.get("title");
		}
		if (settings.containsKey("title_split"))
		{
			this.title_split = (String) settings.get("title_split");
		}
		if (settings.containsKey("URL_Front"))
		{
			this.URL_Front = (String) settings.get("URL_Front");
		}
		if (settings.containsKey("link"))
		{
			this.link = (String) settings.get("link");
		}
		if (settings.containsKey("link_attr"))
		{
			this.link_attr = (String) settings.get("link_attr");
		}
	}

	public void parse(Document doc)
	{
		String[] str;
		Elements elements = doc.select(this.body);
		Elements title_element = elements.select(this.title);
		for (int i = 0; i < title_element.size(); i++)
		{
			this.uscert.add(new US_CERT_Class());
			str = title_element.get(i).text().toString().split(this.title_split);
			this.uscert.get(this.uscert.size() - 1).set_Title(str[0]);
			this.uscert.get(this.uscert.size() - 1)
					.set_URL(this.URL_Front + elements.select(this.link).get(i).attr(this.link_attr).toString());
		}
	}

	public ArrayList<US_CERT_Class> get_parse()
	{
		return this.uscert;
	}
}
