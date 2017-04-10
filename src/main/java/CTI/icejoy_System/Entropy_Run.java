package CTI.icejoy_System;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Criteria.Stanford_Class;
import Criteria.String_Integer;
import Input.Input_ReadFile;
import Input.Input_ReadFolder;
import Output.Output_SaveTxT;
import Parser.Parser_StanfordNLP;

public class Entropy_Run
{
	String pos="JJ";
	String Change=",";
	String save_Name="JJVB_TF_MIX.csv";
	
	
	Boolean have_same = false;
	Input_ReadFolder ReadFolder = new Input_ReadFolder();
	Input_ReadFile ReadFile = new Input_ReadFile();
	Parser_StanfordNLP stanfordnlp = new Parser_StanfordNLP();
	Output_SaveTxT output = new Output_SaveTxT();

	public void Start(String input)
	{
		Double dd = 0.0;
		String rol1 = "", rol2 = "";
		String_Integer T1, T2;
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> TF_Files = new ArrayList<String>(Read_Files(input));
		// ArrayList<String> T_Files = new
		// ArrayList<String>(Read_Files(input+"/T"));
		// ArrayList<String> F_Files = new
		// ArrayList<String>(Read_Files(input+"/F"));
		DecimalFormat df = new DecimalFormat("#.##");
		for (int i = 0; i < TF_Files.size(); i++)
		{
			// rol1 = ",";
			// rol2 = Files.get(i);
			rol2 = TF_Files.get(i).replace(" ", "_");
			if (TF_Files.get(i).contains("/TF/T"))
			{
				rol2 += Change+"1"+Change;
			}
			else
			{
				rol2 += Change+"0"+Change;
			}
			this.ReadFile.input(TF_Files.get(i));
			this.stanfordnlp.parse(this.ReadFile.get_input());
			// this.stanfordnlp.parse(this.ReadFile(Files.get(i)));
			T1 = String_Sort(this.stanfordnlp.get_parse());
			for (String s : TF_Files)
			{
				// rol1 += s + ",";
				this.ReadFile.input(s);
				this.stanfordnlp.parse(this.ReadFile.get_input());
				T2 = String_Sort(this.stanfordnlp.get_parse());
				dd = D(Match(T1, T2), T1, T2);
				if (dd != null)
				{
					rol2 += df.format(dd) + Change;
				}
				else
				{
					rol2 += "NA"+Change;
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

	public ArrayList<Integer> Match(String_Integer T1, String_Integer T2)
	{
		have_same = false;
		ArrayList<Integer> index = new ArrayList<Integer>();
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
		return index;
	}

	public Double Z(ArrayList<Integer> index, String_Integer T1, String_Integer T2)
	{
		Double z = 0.0;
		Double p1, p2, log1, log2;
		for (int i = 0; i < index.size(); i++)
		{
			p1 = T1.get_count().get(index.get(i)) / T1.get_Size();
			i++;
			p2 = T2.get_count().get(index.get(i)) / T2.get_Size();
			log1 = Math.log(p1);
			log2 = Math.log(p2);
			z += -(p1 * log1) - (p2 * log2);
		}
		return z;
	}

	public Double D(ArrayList<Integer> index, String_Integer T1, String_Integer T2)
	{
		if (!have_same)
		{
			return null;
		}
		else
		{
			Double z, fw, d = 0.0;
			int rank1, rank2;
			z = Z(index, T1, T2);
			for (int i = 0; i < index.size(); i += 2)
			{
				rank1 = T1.get_term().indexOf(T1.get_term().get(index.get(i)));
				rank2 = T2.get_term().indexOf(T2.get_term().get(index.get(i + 1)));
				fw = Entropy(z, i, index, T1, T2);

				d += Math.abs(rank1 - rank2) * fw;
			}
			return d / (index.size() / 2);
		}
	}

	public Double Entropy(Double z, int i, ArrayList<Integer> index, String_Integer T1, String_Integer T2)
	{
		Double fw, p1, p2, log1, log2;
		p1 = T1.get_count().get(index.get(i)) / T1.get_Size();
		i++;
		p2 = T2.get_count().get(index.get(i)) / T2.get_Size();
		log1 = Math.log(p1);
		log2 = Math.log(p2);
		fw = (-(p1 * log1) - (p2 * log2)) / z;
		return fw;
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

	public String_Integer String_Sort(ArrayList<Stanford_Class> content)
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
		Entropy_Run run = new Entropy_Run();
		run.Start("src/main/java/Dataset/TF");
		System.out.println("done");
	}
}
