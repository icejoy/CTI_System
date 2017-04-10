package Parser;

import java.util.ArrayList;

import Criteria.Config_Class;
import Criteria.Stanford_Class;

public class Parser_Target
{
	ArrayList<Integer> indexs = new ArrayList<Integer>();

	public void parse(Config_Class configs, ArrayList<Stanford_Class> content)
	{
		this.indexs.clear();
		// Find_OrgANDLocation(content);
		Find_Term(configs, content);
	}

	public int get_parse()
	{
		if (this.indexs.size() > 0)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	public void Find_OrgANDLocation(ArrayList<Stanford_Class> content)
	{
		for (int i = 0; i < content.size(); i++)
		{
			if (content.get(i).get_Ner().contains("LOCATION") || content.get(i).get_Ner().contains("ORGANIZATION"))
			{
				indexs.add(i);
			}
		}
	}

	public void Find_Term(Config_Class configs, ArrayList<Stanford_Class> content)
	{
//		int index;

		for (int i = 0; i < content.size(); i++)
		{
			if (content.get(i).get_Ner().contains("LOCATION") || content.get(i).get_Ner().contains("ORGANIZATION"))
			// if (!this.indexs.contains(i))
			{
				for (String s : configs.get_term_list())
				{
					if (content.get(i).get_Lemma().contains(s))
					{
						if ((indexs.size() == 0 || indexs.size() > 0) && !indexs.contains(i))
						{
							indexs.add(i);
							System.out.println(content.get(i).get_sentence());
//							System.out.println(s+"\r\n"+content.get(i).get_sentence());
						}
						// index = content.get(i).get_Lemma().indexOf(s);
						/*
						 * try { for (int j = 0 - configs.get_term(); j < index
						 * + configs.get_term(); j++) { if
						 * (content.get(i).get_Lemma().get(j).contains("NN")) {
						 * this.indexs.add(i); break; } } } catch
						 * (IndexOutOfBoundsException e) { System.out.println(
						 * "this is last one"); }
						 */
					}
				}
			}
		}
	}
}
