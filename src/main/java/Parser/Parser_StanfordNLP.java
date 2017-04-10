package Parser;

import java.util.ArrayList;

import Criteria.Stanford_Class;
import Tool.StanfordNLP;

public class Parser_StanfordNLP
{
	StanfordNLP nlp = new StanfordNLP();

	public void parse(ArrayList<String> content)
	{
		this.nlp.Start(content);
	}

	public ArrayList<Stanford_Class> get_parse()
	{
		return this.nlp.get_result();
	}
}
