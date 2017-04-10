package Parser;

import java.util.ArrayList;

import Criteria.Config_Class;
import Criteria.Stanford_Class;
import Tool.ReadFile_ArrayList;

public class Parser_Classify
{
	int Threshold = 3;
	int haved_count = 0;
	Config_Class configs;
	ArrayList<String> list;
	ArrayList<Integer> Filtered_Content_Index = new ArrayList<Integer>();
	ReadFile_ArrayList ra = new ReadFile_ArrayList();
	// ArrayList<String> haved_sentences = new ArrayList<String>();

	public void parse(int threshold, String choose_key, ArrayList<String> input, Config_Class according,
			ArrayList<Stanford_Class> content)
	{
		this.Threshold = threshold;
		this.haved_count = 0;
		this.list = null;
		this.configs = according;
		// ref
		if (choose_key.equals("ref"))
		{
			this.Filtered_Content_Index.clear();
			if (read_ref())
			{
				Find_Candidate_Sentences(content);
				if (this.Filtered_Content_Index.size() > 0)
				{
					this.haved_count = 1;
				}
			}
		}
		else if (this.Filtered_Content_Index.size() > 0)
		{
			// type
			if (choose_key.equals("type"))
			{
				list = new ArrayList<String>(input);
				if (list.size() > 0)
				{
					Match(this.configs.get_type(), content);
				}
			}
			// target
			else if (choose_key.equals("target"))
			{
				list = new ArrayList<String>(input);
				if (list.size() > 0)
				{
					Match(this.configs.get_target(), content);
				}
			}
			// name
			else if (choose_key.equals("name"))
			{
				list = new ArrayList<String>(input);
				if (list.size() > 0)
				{
					Match(this.configs.get_name(), content);
				}
			}
			// ioc
			else if (choose_key.equals("ioc"))
			{
				list = new ArrayList<String>(input);
				if (list.size() > 0)
				{
					Match(this.configs.get_ioc(), content);
				}
			}
			// term
			else if (choose_key.equals("term"))
			{
				list = new ArrayList<String>(this.configs.get_term_list());
				if (list.size() > 0)
				{
					Match(this.configs.get_term(), content);
				}
			}
			// time
			else if (choose_key.equals("time"))
			{
				list = new ArrayList<String>(input);
				if (list.size() > 0)
				{
					Match(this.configs.get_time(), content);
				}
			}
		}
	}

	public int get_parse()
	{
		return haved_count;
	}

	public Boolean read_ref()
	{
		list = null;
		if (!this.configs.get_ref_path().equals("null"))
		{
			ra.ReadFile(this.configs.get_ref_path());
			list = new ArrayList<String>(ra.get_content());
			return true;
		}
		else
		{
			return false;
		}
	}

	public void Find_Candidate_Sentences(ArrayList<Stanford_Class> content)
	{
		for (int i = 0; i < content.size(); i++)
		{
			for (String s : list)
			{
				if (content.get(i).get_sentence().matches("[^a-zA-Z]" + s + "[^a-zA-Z]")
						|| (content.get(i).get_sentence().contains(" " + s)
								&& content.get(i).get_sentence().contains(s + " ")))
				{
					this.Filtered_Content_Index.add(i);
				}
			}
		}
	}

	public void Match(int context_count, ArrayList<Stanford_Class> content)
	{
		String temp = "";
		for (int index = 0; index < this.Filtered_Content_Index.size(); index++)
		{
			for (int i = -context_count; i < context_count + 1; i++)
			{
				for (String s : this.list)
				{
					temp = content.get(index + i).get_sentence();
					if (temp.matches("[^a-zA-Z]" + s + "[^a-zA-Z]")
							|| (temp.contains(" " + s) && temp.contains(s + " ")))
					{
						this.haved_count++;
						index = this.Filtered_Content_Index.size();
						i = context_count + 1;
						break;
					}
				}
			}
		}
	}

}
