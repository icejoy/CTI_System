package CTI.icejoy_System;

import java.util.ArrayList;

import Criteria.Config_Class;
import Criteria.Stanford_Class;
import Input.Input_Configs;
import Input.Input_ReadFile;
import Input.Input_ReadFolder;
import Output.Output_StanfordSentences;
import Parser.Parser_MalwareName;
import Parser.Parser_MalwareType;
import Parser.Parser_StanfordNLP;
import Parser.Parser_Target;

public class Once_Run_Debug
{
	String DEBUG = "src/main/java/Dataset/labeled/F/TrendMicro/Time to Upgrade as Microsoft Ends Vista Support.txt";

	ArrayList<ArrayList<Stanford_Class>> content = new ArrayList<ArrayList<Stanford_Class>>();
	String Input_FilePath = "", Output_FilePath = "";

	Input_ReadFile ReadFile = new Input_ReadFile();
	Parser_StanfordNLP stanfordnlp = new Parser_StanfordNLP();
	// Output_StanfordSentences output = new Output_StanfordSentences();
	Input_Configs ic = new Input_Configs();
	Parser_MalwareType parser_MalwareType = new Parser_MalwareType();
	Parser_MalwareName parser_MalwareName = new Parser_MalwareName();
	Parser_Target parser_Target = new Parser_Target();

	public Once_Run_Debug(String input_FilePath, String output_FilePath)
	{
		this.Input_FilePath = input_FilePath;
		this.Output_FilePath = output_FilePath;

	}

	public void init()
	{
		this.content.clear();
	}

	public void Start()
	{
		init();
		this.ReadFile.input(this.DEBUG);
		this.stanfordnlp.parse(this.ReadFile.get_input());
		this.ic.set_Path("src/main/java/PatternFormat");
		this.ic.input("MalwareType");
		System.out.println("MalwareType");
		for (Config_Class cc : this.ic.get_input())
		{
			// parse malwaretype
			this.parser_MalwareType.parse(null, cc, this.stanfordnlp.get_parse());
		}
		this.ic.input("MalwareName");
		System.out.println("MalwareName");
		for (Config_Class cc : this.ic.get_input())
		{
			// parser malwarename
			this.parser_MalwareName.parse(cc, this.stanfordnlp.get_parse());
		}
		this.ic.input("Target");
		System.out.println("Target");
		for (Config_Class cc : this.ic.get_input())
		{
			// parser target
			this.parser_Target.parse(cc, this.stanfordnlp.get_parse());
		}
	}

	public static void main(String[] args)
	{
		// MainRun mr = new MainRun("src/main/java/OutputDatabase/T/test/a.txt",
		// "src/main/java/OutputDatabase/T/test");
		Once_Run_Debug mr = new Once_Run_Debug("src/main/java/Dataset/labeled/T", "src/main/java/OutputDatabase/T/");
		mr.Start();
		System.out.println("done");
	}
}
