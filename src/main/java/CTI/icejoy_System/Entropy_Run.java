package CTI.icejoy_System;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Criteria.Stanford_Class;
import Criteria.String_Integer;
import Input.Input_ReadFile;
import Input.Input_ReadFolder;
import Output.Output_SaveTxT;
import Parser.Parser_Extract_CWETermList;
import Parser.Parser_Extract_CWE_Text;
import Parser.Parser_StanfordNLP;
import Tool.Entropy_Similarity;
import Tool.Two_TermList_Match;

public class Entropy_Run
{
	String pos = "VB";
	String Change = ",";
	String save_Name = "TrendMicro_JJVB_TF_MIX.csv";
	String XmlPath = "2011_CWE_SANS_Top25.xml";

	Boolean have_same = false;

	Parser_Extract_CWE_Text cwe_read = new Parser_Extract_CWE_Text();
	Parser_Extract_CWETermList cwe_terms = new Parser_Extract_CWETermList();
	Two_TermList_Match match = new Two_TermList_Match();
	Entropy_Similarity similarity = new Entropy_Similarity();
	Input_ReadFolder ReadFolder = new Input_ReadFolder();
	Input_ReadFile ReadFile = new Input_ReadFile();
	Parser_StanfordNLP stanfordnlp = new Parser_StanfordNLP();
	Output_SaveTxT output = new Output_SaveTxT();

	public void CWE_Check()
	{
		File jj = new File("CWE_JJList.txt");
		File vb = new File("CWE_VBList.txt");
		if (!jj.exists() || !vb.exists())
		{
			this.ReadFile.input(this.XmlPath);
			this.cwe_read.parse(this.ReadFile.get_input());
			this.cwe_terms.parse(this.cwe_read.get_parse());
		}
	}

	public void Start(String input)
	{
		Double dd = 0.0;
		String rol1 = "", rol2 = "";
		String_Integer T1, T2;
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> TF_Files = new ArrayList<String>(Read_Files(input));
		DecimalFormat df = new DecimalFormat("#.##");
		for (int i = 0; i < TF_Files.size(); i++)
		{
			rol2 = TF_Files.get(i).replace(" ", "_");
			rol2 = rol2.replace(",", "-");
			if (TF_Files.get(i).contains("/TF/T"))
			{
				rol2 += Change + "1" + Change;
			}
			else
			{
				rol2 += Change + "0" + Change;
			}
			this.ReadFile.input(TF_Files.get(i));
			this.stanfordnlp.parse(this.ReadFile.get_input());
			// this.stanfordnlp.parse(this.ReadFile(Files.get(i)));
			T1 = Find_Terms_Sorting(this.stanfordnlp.get_parse());
			for (String s : TF_Files)
			{
				// rol1 += s + ",";
				this.ReadFile.input(s);
				this.stanfordnlp.parse(this.ReadFile.get_input());
				T2 = Find_Terms_Sorting(this.stanfordnlp.get_parse());
				this.match.Start_Match(T1, T2);
				if (this.match.get_Have_Same())
				{
					dd = similarity.Similarity_Calculate(this.match.get_Match(), T1, T2);
				}
				else
				{
					dd = null;
				}
				if (dd != null)
				{
					rol2 += df.format(dd) + Change;
				}
				else
				{
					rol2 += "NA" + Change;
				}
			}
			// result.add(rol1);
			result.add(rol2);
			// rol1 = "";
			// rol2 = "";
		}
		this.output.Set_FileConfig("");
		this.output.String_One_ArrayListt_Save(save_Name, result, false);
	}

	public ArrayList<String> Read_Files(String path)
	{
		ArrayList<String> Folder;
		ArrayList<String> array = new ArrayList<String>();
		this.ReadFolder.input(path);
		Folder = new ArrayList<String>(this.ReadFolder.get_input());
		for (String folder : Folder)
		{
			this.ReadFolder.input(path + "/" + folder);
			for (String file : this.ReadFolder.get_input())
			{
				array.add(path + "/" + folder + "/" + file);
			}
		}
		return array;
	}

	public String_Integer Find_Terms_Sorting(ArrayList<Stanford_Class> content)
	{
		String_Integer term_count = new String_Integer();
		for (Stanford_Class stanford_Class : content)
		{
			for (int i = 0; i < stanford_Class.get_Lemma().size(); i++)
			{
				if (stanford_Class.get_Pos().get(i).contains(pos))
				{
					if ((pos.equals("VB") && this.cwe_terms.get_VBList().contains(stanford_Class.get_Pos().get(i)))
							|| (pos.equals("JJ")
									&& this.cwe_terms.get_JJList().contains(stanford_Class.get_Pos().get(i))))
					{
						term_count.Contain_term(stanford_Class.get_Lemma().get(i));
					}
				}
			}
		}
		term_count.Sort_Ranking();
		return term_count;
	}

	public static void main(String[] args)
	{
		Entropy_Run run = new Entropy_Run();
		// run.Start("C:/Users/Islab/Desktop/Dataset/TF");
		run.Start("src/main/java/Dataset/TF");
		System.out.println("done");
	}
}
