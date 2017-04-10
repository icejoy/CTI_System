package Input;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Input_ReadJson
{
	String Path;
	JSONObject settings;
	JSONParser jsonParser = new JSONParser();

	public void set_Path(String path)
	{
		this.Path = path;
	}

	public void input()
	{
		try
		{
			settings = (JSONObject) jsonParser.parse(new FileReader(this.Path));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public JSONObject get_input()
	{
		return settings;
	}
}
