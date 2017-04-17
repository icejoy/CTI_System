package Tool;

import java.util.ArrayList;

import Criteria.String_Integer;

public class Two_TermList_Match
{
	Boolean have_same = false;
	ArrayList<Integer> index = new ArrayList<Integer>();

	public void Start_Match(String_Integer T1, String_Integer T2)
	{
		have_same = false;
		index.clear();
		for (int i = 0; i < T1.get_term().size(); i++)
		{
			if (T2.get_term().contains(T1.get_term().get(i)))
			{
				index.add(i);
				index.add(T2.get_term().indexOf(T1.get_term().get(i)));
			}
		}
		if (index.size() == 0)
		{
			have_same = false;
		}
		else
		{
			have_same = true;
		}
	}

	public Boolean get_Have_Same()
	{
		return have_same;
	}

	public ArrayList<Integer> get_Match()
	{
		return index;
	}
}
