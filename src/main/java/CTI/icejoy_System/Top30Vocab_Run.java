package CTI.icejoy_System;

import java.util.ArrayList;

import Criteria.Stanford_Class;
import Criteria.String_Integer_Class;
import Input.Input_ReadFile;
import Input.Input_ReadFileNames;
import Output.Output_SaveTxT;
import Parser.Parser_Extract_TermList;
import Parser.Parser_Extract_CWE_Text;
import Parser.Parser_StanfordNLP;

public class Top30Vocab_Run
{
	// String pos = "VB";
	String next = ",";
	// String SaveFileName = "All_VB_top30.csv";

	Parser_StanfordNLP StanfordNLP = new Parser_StanfordNLP();
	Output_SaveTxT output = new Output_SaveTxT();
	Input_ReadFile ReadFile = new Input_ReadFile();
	Input_ReadFileNames ReadFolder = new Input_ReadFileNames();
	Parser_Extract_TermList cwe_terms = new Parser_Extract_TermList();

	ArrayList<String> result = new ArrayList<String>();
	ArrayList<String_Integer_Class> Verb = new ArrayList<String_Integer_Class>();
	ArrayList<String_Integer_Class> Noun = new ArrayList<String_Integer_Class>();
	ArrayList<String_Integer_Class> Adject = new ArrayList<String_Integer_Class>();

	public ArrayList<String_Integer_Class> get_NN()
	{
		return this.Noun;
	}

	public ArrayList<String_Integer_Class> get_VB()
	{
		return this.Verb;
	}

	public ArrayList<String_Integer_Class> get_JJ()
	{
		return this.Adject;
	}

	public ArrayList<String> get_parse()
	{
		return this.result;
	}

	public void parse(String input)
	{

		this.result.clear();
		String rol = "";
		String_Integer_Class term_count;
		ArrayList<String> File_Names = new ArrayList<String>(ReadFiles(input));
		ArrayList<String> ResultName = new ArrayList<String>();

		for (String file : File_Names)
		{
			rol = "";
			/*
			 * rol = file.replace(" ", "-"); rol = rol.replace(",", "_");
			 */
			if (file.contains("/TF/T"))
			{
				rol = "T";
				// rol = '1' + next + rol;
				// rol += next + '1';
			}
			else
			{
				rol = "F";
				// rol = '0' + next + rol;
				// rol += next + '0';
			}
			result.add(rol);
			// ResultName.add(rol);
			rol = "";

			this.ReadFile.input(file);
			this.StanfordNLP.parse(this.ReadFile.get_input());
			Noun.add(Vocab_Sort("NN", this.StanfordNLP.get_parse()));
			Verb.add(Vocab_Sort("VB", this.StanfordNLP.get_parse()));
			Adject.add(Vocab_Sort("JJ", this.StanfordNLP.get_parse()));
		}
		/*
		 * for (int i = 0; i < ResultName.size(); i++) { try {
		 * result.add(ResultName.get(i)); result.add("NN");
		 * result.add(ArrayListToString(Noun.get(i))); result.add("VB");
		 * result.add(ArrayListToString(Verb.get(i))); result.add("JJ");
		 * result.add(ArrayListToString(Adject.get(i))); } catch
		 * (IndexOutOfBoundsException e) { e.printStackTrace(); } }
		 */
	}

	public String ArrayListToString(String_Integer_Class list)
	{
		String rol1 = list.get_term().get(0);
		String rol2 = list.get_count().get(0) + "";
		if (list.get_term().size() >= 30)
		{
			for (int i = 1; i < 30; i++)
			{
				rol1 += next + list.get_term().get(i);
				rol2 += next + list.get_count().get(i);
			}
		}
		else
		{
			for (int i = 1; i < list.get_term().size(); i++)
			{
				rol1 += next + list.get_term().get(i);
				rol2 += next + list.get_count().get(i);
			}
		}
		return rol1 + "\r\n" + rol2;
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

	public String_Integer_Class Vocab_Sort(String pos, ArrayList<Stanford_Class> content)
	{
		String_Integer_Class term_count = new String_Integer_Class();
		for (Stanford_Class stanford_Class : content)
		{
			for (int i = 0; i < stanford_Class.get_Lemma().size(); i++)
			{
				if (stanford_Class.get_Pos().get(i).contains(pos) && ((pos.equals("VB")
						&& this.cwe_terms.get_VBList().contains(stanford_Class.get_Lemma().get(i)))
						|| (pos.equals("NN") && this.cwe_terms.get_NNList().contains(stanford_Class.get_Lemma().get(i)))
						|| (pos.equals("JJ")
								&& this.cwe_terms.get_JJList().contains(stanford_Class.get_Lemma().get(i)))))
				{
					term_count.Contain_term(stanford_Class.get_Lemma().get(i));
				}
			}
		}
		term_count.Sort_Ranking();
		return term_count;
	}
}
