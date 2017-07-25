package Tool;

import java.util.ArrayList;

import Criteria.String_Integer_Class;
import Output.Output_SaveTxT;

public class Two_TermList_Match
{
	Boolean have_same = false;
	Output_SaveTxT output = new Output_SaveTxT();
	ArrayList<Integer> index = new ArrayList<Integer>();
	ArrayList<ArrayList<String>> match = new ArrayList<ArrayList<String>>();

	public void Start_Match(String_Integer_Class T1, String_Integer_Class T2)
	{
		have_same = false;
		index.clear();
		for (int i = 0; i < T1.get_term().size(); i++)
		{
			if (T2.get_term().contains(T1.get_term().get(i)))
			{
//				match.add(new ArrayList<String>());
				index.add(i);
				
//				match.get(match.size()-1).add(T1.get_term().get(i));
//				match.get(match.size()-1).add(T1.get_Rank().get(i).toString());
				
				index.add(T2.get_term().indexOf(T1.get_term().get(i)));
//				match.get(match.size()-1).add(T2.get_Rank().get(T2.get_term().indexOf(T1.get_term().get(i))).toString());
			}
		}
//		output.String_Two_ArrayList_Save("Ture_TwoSharedWords.txt", match, true);
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
