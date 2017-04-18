package Parser;

import java.util.ArrayList;

import Criteria.Stanford_Class;
import Input.Input_ReadFile;
import Output.Output_SaveTxT;

public class Parser_Extract_CWETermList
{
	Input_ReadFile ReadFile = new Input_ReadFile();
	Output_SaveTxT output = new Output_SaveTxT();
	Parser_StanfordNLP stanfordNLP = new Parser_StanfordNLP();
	ArrayList<String> JJ = new ArrayList<String>();
	ArrayList<String> VB = new ArrayList<String>();

	public void parse(ArrayList<String> content)
	{
		this.stanfordNLP.parse(content);
	}

	public void Find_JJ(ArrayList<Stanford_Class> content)
	{
		ArrayList<String> result = new ArrayList<String>();
		for (Stanford_Class stanfordclass : content)
		{
			for (int i = 0; i < stanfordclass.get_Lemma().size(); i++)
			{
				if (stanfordclass.get_Pos().get(i).contains("JJ"))
				{
					result.add(stanfordclass.get_Lemma().get(i));
					JJ.add(stanfordclass.get_Lemma().get(i));
				}
			}
		}
		this.output.String_One_ArrayListt_Save("CWE_JJList.txt", result, false);
	}

	public void Find_VB(ArrayList<Stanford_Class> content)
	{
		ArrayList<String> result = new ArrayList<String>();
		for (Stanford_Class stanfordclass : content)
		{
			for (int i = 0; i < stanfordclass.get_Lemma().size(); i++)
			{
				if (stanfordclass.get_Pos().get(i).contains("VB"))
				{
					result.add(stanfordclass.get_Lemma().get(i));
					VB.add(stanfordclass.get_Lemma().get(i));
				}
			}
		}
		this.output.String_One_ArrayListt_Save("CWE_VBList.txt", result, false);
	}

	public ArrayList<String> get_JJList()
	{
		if (JJ.size() > 0)
		{
			return JJ;
		}
		else
		{
			this.ReadFile.input("CWE_JJList.txt");
			JJ = null;
			JJ = new ArrayList<String>(this.ReadFile.get_input());
			return JJ;
		}
	}

	public ArrayList<String> get_VBList()
	{
		if (VB.size() > 0)
		{
			return VB;
		}
		else
		{
			this.ReadFile.input("CWE_VBList.txt");
			VB = null;
			VB = new ArrayList<String>(this.ReadFile.get_input());
			return VB;
		}
	}
}
