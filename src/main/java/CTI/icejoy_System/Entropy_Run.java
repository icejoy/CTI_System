package CTI.icejoy_System;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Criteria.Stanford_Class;
import Criteria.String_Integer_Class;
import Input.Input_ReadFile;
import Input.Input_ReadFileNames;
import Input.Input_USCERT;
import Output.Output_SaveTxT;
import Parser.Parser_Extract_TermList;
import Parser.Parser_Extract_CWE_Text;
import Parser.Parser_ReadURLContent;
import Parser.Parser_StanfordNLP;
import Parser.Parser_USCERT;
import Tool.Entropy_Similarity;
import Tool.Two_TermList_Match;

public class Entropy_Run
{
	String pos = "JJ";
	String Change = ",";
	String save_Name = "JJVB_TF_MIX.csv";
	String XmlPath = "2011_CWE_SANS_Top25.xml";

	Boolean have_same = false;

	Input_USCERT url_input = new Input_USCERT();
	Parser_USCERT url_parse = new Parser_USCERT();
	Parser_ReadURLContent readURL = new Parser_ReadURLContent();
	Parser_Extract_CWE_Text cwe_read = new Parser_Extract_CWE_Text();
	Parser_Extract_TermList Extract_terms = new Parser_Extract_TermList();
	Two_TermList_Match match = new Two_TermList_Match();
	Entropy_Similarity similarity = new Entropy_Similarity();
	Input_ReadFileNames ReadFolder = new Input_ReadFileNames();
	Input_ReadFile ReadFile = new Input_ReadFile();
	Parser_StanfordNLP stanfordnlp = new Parser_StanfordNLP();
	Output_SaveTxT output = new Output_SaveTxT();

	public void CWE_Check()
	{
		File vb = new File("CWE_VBList.txt");
		File jj = new File("CWE_JJList.txt");
		if (!jj.exists() || !vb.exists())
		{
			this.ReadFile.input(this.XmlPath);
			this.cwe_read.parse(this.ReadFile.get_input());
			this.Extract_terms.set_FileName("CWE");
			this.Extract_terms.parse(this.cwe_read.get_parse());
		}
	}

	public void USCERT_Check()
	{
		// set url
		this.url_input.set_config("https://www.us-cert.gov/ncas/alerts");
		this.url_input.input();
		if (this.url_input.get_haved_read())
		{
			this.url_parse.set_config("Config/US-CERT_List.json");
			this.url_parse.parse(this.url_input.get_document());
			this.readURL.set_config("Config/US-CERT_Content.json");
			this.readURL.parse(this.url_parse.get_parse());
			this.Extract_terms.set_FileName("USCERT");
			this.Extract_terms.parse(this.readURL.get_Content());
		}
	}

	public void Start(String input)
	{
		// CWE_Check();
		USCERT_Check();
		Double dd = 0.0;
		String rol1 = "", rol2 = "";
		String_Integer_Class T1, T2;
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

	public String_Integer_Class Find_Terms_Sorting(ArrayList<Stanford_Class> content)
	{
		String_Integer_Class term_count = new String_Integer_Class();
		for (Stanford_Class stanford_Class : content)
		{
			for (int i = 0; i < stanford_Class.get_Lemma().size(); i++)
			{
				/*
				 * if ((pos.equals("VB") &&
				 * this.cwe_terms.get_VBList().contains(stanford_Class.get_Lemma
				 * ().get(i))) || (pos.equals("JJ") &&
				 * this.cwe_terms.get_JJList().contains(stanford_Class.get_Lemma
				 * ().get(i))))
				 */
				if ((stanford_Class.get_Pos().get(i).contains("VB")
						&& this.Extract_terms.get_VBList().contains(stanford_Class.get_Lemma().get(i)))
						|| (stanford_Class.get_Pos().get(i).contains("JJ")
								&& this.Extract_terms.get_JJList().contains(stanford_Class.get_Lemma().get(i))))
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
		Entropy_Run run = new Entropy_Run();
		// run.Start("C:/Users/Islab/Desktop/Dataset/TF");
		run.Start("src/main/java/Dataset/TF");
		System.out.println("done");
	}
}
