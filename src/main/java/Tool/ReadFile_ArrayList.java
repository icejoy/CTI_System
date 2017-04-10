package Tool;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadFile_ArrayList
{
	ArrayList<String> content = new ArrayList<String>();

	public void ReadFile(String path)
	{
		content.clear();
		try
		{
			BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

			while (read.ready())
			{
				content.add(read.readLine());
			}
			read.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public ArrayList<String> get_content()
	{
		return this.content;
	}
}
