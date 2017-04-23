package Parser;

import java.util.ArrayList;

import edu.stanford.nlp.util.StringParsingTask;

public class Parser_Extract_CWE_Text
{
	String sentence = "";
	ArrayList<String> Text = new ArrayList<String>();

	public void parse(ArrayList<String> content)
	{
		Boolean open = false;
		String[] temp;
		Text.clear();
		for (String str : content)
		{
			if (str.contains("<Text>") || str.contains("</Text>"))
			{
				if (str.contains("<Text>") && !open)
				{
					open = true;
					temp = str.split("<Text>");
					add_termlist(temp, open);
				}
				else if (open)
				{
					if (str.contains("</Text>"))
					{
						open = false;
					}
					temp = str.split("</Text>");
					add_termlist(temp, open);
				}

			}
		}
	}

	private void add_termlist(String[] temp, Boolean open)
	{
		for (String s : temp)
		{
			if (s.matches(".+[a-zA-Z].+"))
			{
				s=s.replace("\t", " ");
				s=s.replaceAll("\\s+", " ");
				sentence += s;
			}
		}
		if (!open)
		{
			Text.add(sentence);
			sentence = "";
		}
	}

	public ArrayList<String> get_parse()
	{
		return Text;
	}
}
