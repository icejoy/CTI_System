package CTI.icejoy_System;

import java.util.ArrayList;

import Criteria.Config_Class;
import Criteria.Stanford_Class;
import Input.Input_ReadFile;
import Input.Input_ReadFolder;
import Input.Input_Configs;
import Output.Output_SaveTxT;
import Parser.Parser_Classify;
import Parser.Parser_MalwareName;
import Parser.Parser_MalwareType;
import Parser.Parser_StanfordNLP;
import Parser.Parser_Target;

public class MainRun
{
	int T_0 = 0, F_0 = 0, T_1 = 0, F_1 = 0, T_2 = 0, F_2 = 0, T_3 = 0, F_3 = 0;
	int total_T = 0, total_F = 0;
	int MalwareType_Haved = 0;
	Input_ReadFolder ReadFolder = new Input_ReadFolder();
	Input_ReadFile ReadFile = new Input_ReadFile();
	Input_Configs ic = new Input_Configs();
	Output_SaveTxT txt = new Output_SaveTxT();
	// Parser_Classify Classify = new Parser_Classify();
	Parser_MalwareType parser_MalwareType = new Parser_MalwareType();
	Parser_MalwareName parser_MalwareName = new Parser_MalwareName();
	Parser_Target parser_Target = new Parser_Target();
	Parser_StanfordNLP stanfordnlp = new Parser_StanfordNLP();
	// ArrayList<ArrayList<Stanford_Class>> content = new
	// ArrayList<ArrayList<Stanford_Class>>();
	ArrayList<String> Folder;
	ArrayList<String> File;
	ArrayList<ArrayList<String>> result;
	ArrayList<ArrayList<String>> Target_result, MalwareType_result, MalwareName_result;
	int Target_F = 0, Target_T = 0, MalwareName_F = 0, MalwareName_T = 0, MalwareType_F = 0, MalwareType_T = 0;

	public MainRun(String config_path)
	{
		this.Folder = null;
		this.File = null;
		this.ic.set_Path(config_path);

		this.Target_result = new ArrayList<ArrayList<String>>();
		this.Target_result.add(new ArrayList<String>());
		this.Target_result.get(0).add("Target\r\n\r\nF:\r\n");
		this.Target_result.add(new ArrayList<String>());
		this.Target_result.get(1).add("T:\r\n");
		this.MalwareType_result = new ArrayList<ArrayList<String>>();
		this.MalwareType_result.add(new ArrayList<String>());
		this.MalwareType_result.get(0).add("MalwareType\r\n\r\nF:\r\n");
		this.MalwareType_result.add(new ArrayList<String>());
		this.MalwareType_result.get(1).add("T:\r\n");
		this.MalwareName_result = new ArrayList<ArrayList<String>>();
		this.MalwareName_result.add(new ArrayList<String>());
		this.MalwareName_result.get(0).add("MalwareName\r\n\r\nF:\r\n");
		this.MalwareName_result.add(new ArrayList<String>());
		this.MalwareName_result.get(1).add("T:\r\n");
		this.result = new ArrayList<ArrayList<String>>();
		this.result.add(new ArrayList<String>());
		this.result.get(0).add("0:\r\n");
		this.result.add(new ArrayList<String>());
		this.result.get(1).add("1:\r\n");
		this.result.add(new ArrayList<String>());
		this.result.get(2).add("2:\r\n");
		this.result.add(new ArrayList<String>());
		this.result.get(3).add("3:\r\n");
		// this.result.add(new ArrayList<String>());
		// this.result.get(4).add("4:\r\n");
		// this.result.add(new ArrayList<String>());
		// this.result.get(5).add("5:\r\n");
	}

	public void init_CTI_Element_Count()
	{

		this.MalwareType_Haved = 0;
	}

	public void Start(String input_path, String output_path)
	{
		// int CTI_Element = 0;
		String Full_Path = input_path;
		this.total_T = 0;
		this.total_F = 0;
		this.ReadFolder.input(input_path);
		this.txt.Set_FileConfig(output_path);
		this.Folder = new ArrayList<String>(this.ReadFolder.get_input());
		for (String folder : this.Folder)
		{
			Full_Path = input_path + "/" + folder;
			this.ReadFolder.input(Full_Path);
			this.File = new ArrayList<String>(this.ReadFolder.get_input());
			for (String files : this.File)
			{
				Full_Path = input_path + "/" + folder + "/" + files;
				this.ReadFolder.input(Full_Path);

				for (String s : ReadFolder.get_input())
				{
					if (folder.equals("T"))
					{
						this.total_T++;
					}
					else
					{
						this.total_F++;
					}
					// content.clear();
					init_CTI_Element_Count();
					this.ReadFile.input(Full_Path + "/" + s);
					this.stanfordnlp.parse(ReadFile.get_input());
					// content.add(stanfordnlp.get_parse());
					this.ic.input("MalwareType");
					for (Config_Class cc : this.ic.get_input())
					{
						// parse malwaretype
						this.parser_MalwareType.parse(null, cc, this.stanfordnlp.get_parse());
					}
					this.ic.input("MalwareName");
					for (Config_Class cc : this.ic.get_input())
					{
						// parser malwarename
						this.parser_MalwareName.parse(cc, this.stanfordnlp.get_parse());
					}
					this.ic.input("Target");
					for (Config_Class cc : this.ic.get_input())
					{
						// parser target
						this.parser_Target.parse(cc, this.stanfordnlp.get_parse());
					}

					this.Target_result.get(this.parser_Target.get_parse()).add(Full_Path + "/" + s);
					this.MalwareType_result.get(this.parser_MalwareType.get_parse()).add(Full_Path + "/" + s);
					this.MalwareName_result.get(this.parser_MalwareName.get_parse()).add(Full_Path + "/" + s);

					this.result.get(this.parser_MalwareName.get_parse() + this.parser_MalwareType.get_parse()
							+ this.parser_Target.get_parse()).add(Full_Path + "/" + s);
					switch (this.parser_MalwareName.get_parse() + this.parser_MalwareType.get_parse()
							+ this.parser_Target.get_parse())
					{
					case 0:
						if (folder.equals("T"))
						{
							this.T_0++;
						}
						else
						{
							this.F_0++;
						}
						break;

					case 1:
						if (folder.equals("T"))
						{
							this.T_1++;
						}
						else
						{
							this.F_1++;
						}
						break;

					case 2:
						if (folder.equals("T"))
						{
							this.T_2++;
						}
						else
						{
							this.F_2++;
						}
						break;
					case 3:
						if (folder.equals("T"))
						{
							this.T_3++;
						}
						else
						{
							this.F_3++;
						}
						break;

					default:
						break;
					}

					if (folder.equals("T"))
					{
						this.Target_T += this.parser_Target.get_parse();
						this.MalwareType_T += this.parser_MalwareType.get_parse();
						this.MalwareName_T += this.parser_MalwareName.get_parse();
					}
					else
					{
						this.Target_F += this.parser_Target.get_parse();
						this.MalwareType_F += this.parser_MalwareType.get_parse();
						this.MalwareName_F += this.parser_MalwareName.get_parse();
					}

				}
			}
		}

		this.Target_result.get(0).add(this.Target_F + "");
		this.Target_result.get(1).add(this.Target_T + "");
		this.MalwareType_result.get(0).add(this.MalwareType_F + "");
		this.MalwareType_result.get(1).add(this.MalwareType_T + "");
		this.MalwareName_result.get(0).add(this.MalwareName_F + "");
		this.MalwareName_result.get(1).add(this.MalwareName_T + "");
		this.txt.String_Two_ArrayList_Save("3class.txt", this.Target_result, true);
		this.txt.String_Two_ArrayList_Save("3class.txt", this.MalwareType_result, true);
		this.txt.String_Two_ArrayList_Save("3class.txt", this.MalwareName_result, true);

		this.result.get(0).add("total:" + (this.result.get(0).size() - 2) + "\r\nT:" + this.T_0 + " F:" + this.F_0);
		this.result.get(1).add("total:" + (this.result.get(1).size() - 2) + "\r\nT:" + this.T_1 + " F:" + this.F_1);
		this.result.get(2).add("total:" + (this.result.get(2).size() - 2) + "\r\nT:" + this.T_2 + " F:" + this.F_2);
		this.result.get(3).add("total:" + (this.result.get(3).size() - 2) + "\r\nT:" + this.T_3 + " F:" + this.F_3);

		this.txt.String_Two_ArrayList_Save("classify.txt", this.result, true);
		System.out.println("T total have :" + this.total_T + "\r\nF total have :" + this.total_F);
	}

	public static void main(String[] args)
	{
		MainRun run = new MainRun("src/main/java/PatternFormat");
		run.Start("src/main/java/Dataset/labeled", "src/main/java/");

		System.out.println("done");
	}

}
