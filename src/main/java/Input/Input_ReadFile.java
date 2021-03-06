package Input;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Input_ReadFile
{
	ArrayList<String> input = new ArrayList<String>();

	public void input(String Full_FileName)
	{
		String str = "";
		this.input.clear();
		try
		{
			BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(Full_FileName)));
			while (read.ready())
			{
				str = read.readLine();
				if (str.contains("[dot]"))
				{
					str = str.replace("[dot]", ".");
				}
				if (str.contains("[.]"))
				{
					str = str.replace("[.]", ".");
				}
				input.add(str);
			}
			read.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public ArrayList<String> get_input()
	{
		return input;
	}
}
