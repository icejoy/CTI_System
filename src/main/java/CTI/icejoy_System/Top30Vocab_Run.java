package CTI.icejoy_System;

import java.util.ArrayList;

import Criteria.Stanford_Class;
import Criteria.String_Integer;
import Input.Input_ReadFile;
import Input.Input_ReadFileNames;
import Output.Output_SaveTxT;
import Parser.Parser_StanfordNLP;

public class Top30Vocab_Run
{
	// String pos = "VB";
	String next = ",";
	String SaveFileName = "All_VB_top30.csv";

	Parser_StanfordNLP StanfordNLP = new Parser_StanfordNLP();
	Output_SaveTxT output = new Output_SaveTxT();
	Input_ReadFile ReadFile = new Input_ReadFile();
	Input_ReadFileNames ReadFolder = new Input_ReadFileNames();

	public void Start(String input)
	{
		String rol = "";
		String_Integer term_count;
		ArrayList<String> File_Names = new ArrayList<String>(ReadFiles(input));
		ArrayList<String> ResultName = new ArrayList<String>();
		ArrayList<String_Integer> Verb = new ArrayList<String_Integer>();
		ArrayList<String_Integer> Noun = new ArrayList<String_Integer>();
		ArrayList<String_Integer> Adject = new ArrayList<String_Integer>();
		for (String file : File_Names)
		{
			rol = "";
			rol = file.replace(" ", "-");
			rol = rol.replace(",", "_");
			if (file.contains("/TF/T"))
			{
				rol = '1' + next + rol;
				// rol += next + '1';
			}
			else
			{
				rol = '0' + next + rol;
				// rol += next + '0';
			}
			ResultName.add(rol);
			rol = "";

			this.ReadFile.input(file);
			this.StanfordNLP.parse(this.ReadFile.get_input());
			ResultName.add(rol);
			Verb.add(Vocab_Sort("VB", this.StanfordNLP.get_parse()));
			Noun.add(Vocab_Sort("NN", this.StanfordNLP.get_parse()));
			Adject.add(Vocab_Sort("JJ", this.StanfordNLP.get_parse()));
		}
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < ResultName.size(); i++)
		{
			result.add(ResultName.get(i));
			result.add("NN");
			result.add(ArrayListToString(Noun.get(i)));
			result.add("VB");
			result.add(ArrayListToString(Verb.get(i)));
			result.add("JJ");
			result.add(ArrayListToString(Adject.get(i)));
		}
		this.output.String_One_ArrayListt_Save("All_DocumentVerb.txt", result, false);
	}

	public String ArrayListToString(String_Integer list)
	{
		String rol = list.get_term().get(0);
		for (int i = 1; i < list.get_term().size(); i++)
		{
			rol += next + list.get_term().get(i);
		}
		rol += "\r\n" + list.get_count().get(0);
		for (int i = 1; i < list.get_count().size(); i++)
		{
			rol += next + list.get_count().get(i);
		}
		return rol;
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

	public String_Integer Vocab_Sort(String pos, ArrayList<Stanford_Class> content)
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
		Top30Vocab_Run run = new Top30Vocab_Run();
		run.Start("src/main/java/Dataset/TF");
		System.out.println("done");
	}

}
