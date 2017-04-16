package CTI.icejoy_System;

import java.util.ArrayList;

import Criteria.Stanford_Class;
import Criteria.String_Integer;
import Input.Input_ReadFile;
import Input.Input_ReadFolder;
import Output.Output_SaveTxT;
import Parser.Parser_StanfordNLP;

public class Top30Vocab_Run
{
	String pos = "VB";
	String next = ",";
	String SaveFileName = "All_VB_top30.csv";

	Input_ReadFolder ReadFolder = new Input_ReadFolder();
	Input_ReadFile ReadFile = new Input_ReadFile();
	Parser_StanfordNLP StanfordNLP = new Parser_StanfordNLP();
	Output_SaveTxT output = new Output_SaveTxT();

	public void Start(String input)
	{
		String rol = "";
		String_Integer term_count;
		ArrayList<String> File_Names = new ArrayList<String>(ReadFiles(input));
		ArrayList<ArrayList<String>> Result = new ArrayList<ArrayList<String>>();
		for (String file : File_Names)
		{
			rol = "";
			Result.add(new ArrayList<String>());
			rol = file.replace(" ", "-");
			rol = rol.replace(",", "_");
			if (file.contains("/TF/T"))
			{
				rol += next + '1';
			}
			else
			{
				rol += next + '0';
			}
			Result.get(Result.size() - 1).add(rol);
			rol = "";

			Result.add(new ArrayList<String>());
			this.ReadFile.input(file);
			this.StanfordNLP.parse(this.ReadFile.get_input());
			term_count = Vocab_Sort(this.StanfordNLP.get_parse());
			for (int i = 0; i < 30; i++)
			{
				rol += term_count.get_term().get(i) + next;
			}
			Result.get(Result.size() - 1).add(rol);
			Result.add(new ArrayList<String>());
			rol = "";
			for (int i = 0; i < 30; i++)
			{
				rol += term_count.get_count().get(i) + next;
			}
			Result.get(Result.size() - 1).add(rol);
		}
		this.output.String_Two_ArrayList_Save(SaveFileName, Result, false);
	}

	public ArrayList<String> ReadFiles(String input)
	{
		ArrayList<String> files = new ArrayList<String>();
		this.ReadFolder.input(input);
		ArrayList<String> Folder = new ArrayList<String>(this.ReadFolder.get_input());
		for (String folder : Folder)
		{
			this.ReadFolder.input(input + "/" + folder);
			for (String file : this.ReadFolder.get_input())
			{
				files.add(input + "/" + folder + "/" + file);
			}
		}
		return files;
	}

	public String_Integer Vocab_Sort(ArrayList<Stanford_Class> content)
	{
		String_Integer term_count = new String_Integer();
		for (Stanford_Class stanford_Class : content)
		{
			for (int i = 0; i < stanford_Class.get_Lemma().size(); i++)
			{
				if (stanford_Class.get_Pos().get(i).contains(pos))
				{
					term_count.Contain_term(stanford_Class.get_Lemma().get(i));
				}
			}
		}
		term_count.Sort_Ranking();
		return term_count;
	}

	public static void main(String[] args)
	{

	}

}
