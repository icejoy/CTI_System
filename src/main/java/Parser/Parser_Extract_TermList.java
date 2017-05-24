package Parser;

import java.util.ArrayList;

import Criteria.Stanford_Class;
import Input.Input_ReadFile;
import Output.Output_SaveTxT;

public class Parser_Extract_TermList
{
	String FileName = "";

	Input_ReadFile ReadFile = new Input_ReadFile();
	Output_SaveTxT output = new Output_SaveTxT();
	Parser_StanfordNLP stanfordNLP = new Parser_StanfordNLP();
	ArrayList<String> NN = new ArrayList<String>();
	ArrayList<String> VB = new ArrayList<String>();
	ArrayList<String> JJ = new ArrayList<String>();
	ArrayList<String> StopWordList;

	public void set_FileName(String filename)
	{
		this.FileName = filename;
	}

	public void parse(ArrayList<String> content)
	{
		this.stanfordNLP.parse(content);
		Load_StopWordList();
		Find_NN(this.stanfordNLP.get_parse());
		Find_VB(this.stanfordNLP.get_parse());
		Find_JJ(this.stanfordNLP.get_parse());
	}

	private void Load_StopWordList()
	{
		this.ReadFile.input("stop-word-list.txt");
		this.StopWordList = new ArrayList<String>(this.ReadFile.get_input());
	}

	private void Find_NN(ArrayList<Stanford_Class> content)
	{
		for (Stanford_Class stanfordclass : content)
		{
			for (int i = 0; i < stanfordclass.get_Lemma().size(); i++)
			{
				if (stanfordclass.get_Pos().get(i).contains("NN")
						&& !this.StopWordList.contains(stanfordclass.get_Lemma().get(i)))
				{
					if(NN.size()==0||(NN.size()>0&&!NN.contains(stanfordclass.get_Lemma().get(i))))
					{NN.add(stanfordclass.get_Lemma().get(i));}
				}
			}
		}
		this.output.String_One_ArrayListt_Save(this.FileName+"_NNList.txt", NN, false);
	}

	private void Find_VB(ArrayList<Stanford_Class> content)
	{
		for (Stanford_Class stanfordclass : content)
		{
			for (int i = 0; i < stanfordclass.get_Lemma().size(); i++)
			{
				if (stanfordclass.get_Pos().get(i).contains("VB")
						&& !this.StopWordList.contains(stanfordclass.get_Lemma().get(i)))
				{
					if(VB.size()==0||(VB.size()>0&&!VB.contains(stanfordclass.get_Lemma().get(i))))
					{VB.add(stanfordclass.get_Lemma().get(i));}
				}
			}
		}
		this.output.String_One_ArrayListt_Save(this.FileName+"_VBList.txt", VB, false);
	}

	private void Find_JJ(ArrayList<Stanford_Class> content)
	{
		for (Stanford_Class stanfordclass : content)
		{
			for (int i = 0; i < stanfordclass.get_Lemma().size(); i++)
			{
				if (stanfordclass.get_Pos().get(i).contains("JJ")
						&& !this.StopWordList.contains(stanfordclass.get_Lemma().get(i)))
				{
					if(JJ.size()==0||(JJ.size()>0&&!JJ.contains(stanfordclass.get_Lemma().get(i))))
					{JJ.add(stanfordclass.get_Lemma().get(i));}
				}
			}
		}
		this.output.String_One_ArrayListt_Save(this.FileName+"_JJList.txt", JJ, false);
	}

	public ArrayList<String> get_NNList()
	{
		if (NN.size() > 0)
		{
			return NN;
		}
		else
		{
			this.ReadFile.input("MIX_NN.txt");
			//this.ReadFile.input(this.FileName+"_NNList.txt");
			NN = null;
			NN = new ArrayList<String>(this.ReadFile.get_input());
			return NN;
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
			this.ReadFile.input("MIX_VB.txt");
			//this.ReadFile.input(this.FileName+"_VBList.txt");
			VB = null;
			VB = new ArrayList<String>(this.ReadFile.get_input());
			return VB;
		}
	}

	public ArrayList<String> get_JJList()
	{
		if (JJ.size() > 0)
		{
			return JJ;
		}
		else
		{
			this.ReadFile.input("MIX_JJ.txt");
			//this.ReadFile.input(this.FileName+"_JJList.txt");
			JJ = null;
			JJ = new ArrayList<String>(this.ReadFile.get_input());
			return JJ;
		}
	}
}
