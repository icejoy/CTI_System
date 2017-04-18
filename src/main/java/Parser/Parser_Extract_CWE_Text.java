package Parser;

import java.util.ArrayList;

public class Parser_Extract_CWE_Text
{
	ArrayList<String> Text = new ArrayList<String>();

	public void parse(ArrayList<String> content)
	{
		String[] temp;
		Text.clear();
		for (String str : content)
		{
			if (str.contains("<Text>"))
			{
				temp = str.split("<Text>");
				for (String s : temp)
				{
					if (s.matches("[a-zA-Z]"))
					{
						Text.add(s);
					}
				}
			}
		}
	}

	public ArrayList<String> get_parse()
	{
		return Text;
	}
}
