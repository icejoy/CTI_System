package Criteria;

import java.util.ArrayList;

public class String_Integer
{
	Double All_term_count = 0.0;
	ArrayList<String> term = new ArrayList<String>();
	ArrayList<Integer> count = new ArrayList<Integer>();

	public void Contain_term(String s)
	{
		All_term_count += 1.0;
		if(term.size()==0||(term.size()>0&&!term.contains(s)))
		//if (term.size() > 0 && (!term.contains(s) || term.size() == 0))
		{
			set_term(s);
		}
		else
		{
			add_term_count(s);
		}
	}

	private void set_term(String s)
	{
		term.add(s);
		count.add(1);
	}

	private void add_term_count(String s)
	{
		count.set(term.indexOf(s), count.get(term.indexOf(s)) + 1);
	}

	public ArrayList<String> get_term()
	{
		return term;
	}

	public ArrayList<Integer> get_count()
	{
		return count;
	}

	public Double get_Size()
	{
		return All_term_count;
	}

	public void Sort_Ranking()
	{
		int max = 0;
		int index = 0;
		ArrayList<String> temp = new ArrayList<String>(term);
		ArrayList<Integer> ii = new ArrayList<Integer>(count);
		term.clear();
		count.clear();
		while (temp.size() > 0)
		{
			for (int i = 0; i < temp.size(); i++)
			{
				if (ii.get(i) > max)
				{
					max = ii.get(i);
					index = i;
				}
			}
			term.add(temp.get(index));
			count.add(ii.get(index));
			temp.remove(index);
			ii.remove(index);
			max = 0;
			index = 0;
		}
	}
}
