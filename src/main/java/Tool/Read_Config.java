package Tool;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Read_Config
{
	JSONParser jsonParser = new JSONParser();
	JSONObject settings;

	public void ReadConfig(String path)
	{
		try
		{
			settings = (JSONObject) jsonParser.parse(new FileReader(path));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public JSONObject get_Config()
	{
		return this.settings;
	}
}
