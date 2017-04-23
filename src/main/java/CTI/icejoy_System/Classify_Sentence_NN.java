package CTI.icejoy_System;

import java.util.ArrayList;

import Criteria.Stanford_Class;
import Input.Input_ReadFile;
import Input.Input_ReadFileNames;
import Output.Output_SaveTxT;
import Parser.Parser_StanfordNLP;

public class Classify_Sentence_NN
{
	String Save_Path = "";
	Input_ReadFileNames ReadFolder = new Input_ReadFileNames();
	Input_ReadFile ReadFile = new Input_ReadFile();
	ArrayList<String> FolderPath, FilePath;
	ArrayList<ArrayList<String>> result;;
	Parser_StanfordNLP stanfordnlp = new Parser_StanfordNLP();
	Output_SaveTxT txt = new Output_SaveTxT();

	public Classify_Sentence_NN()
	{
		this.FolderPath = null;
		this.FilePath = null;
		this.result = new ArrayList<ArrayList<String>>();
		this.result.add(new ArrayList<String>());
		this.result.get(this.result.size() - 1).add("F :\r\n");
		this.result.add(new ArrayList<String>());
		this.result.get(this.result.size() - 1).add("T :\r\n");
	}

	public void Start(String Folder_Path)
	{
		this.ReadFolder.input(Folder_Path);
		this.FolderPath = new ArrayList<String>(this.ReadFolder.get_input());
		for (String folder : this.FolderPath)
		{
			this.Save_Path = Folder_Path + "/" + folder;
			this.ReadFolder.input(this.Save_Path);
			this.FilePath = new ArrayList<String>(this.ReadFolder.get_input());
			for (String file : this.FilePath)
			{
				this.ReadFolder.input(this.Save_Path + "/" + file);
				for (String temp : this.ReadFolder.get_input())
				{
					this.ReadFile.input(this.Save_Path + "/" + file + "/" + temp);
					this.stanfordnlp.parse(this.ReadFile.get_input());
					if (folder.equals("F"))
					{
						Find_NNSentences(0, this.stanfordnlp.get_parse());
					}
					else
					{
						Find_NNSentences(1, this.stanfordnlp.get_parse());
					}
				}
			}
		}
		this.txt.String_Two_ArrayList_Save("SentenceNN.html", this.result, true);
	}

	public void Find_NNSentences(int index, ArrayList<Stanford_Class> content)
	{
		for (Stanford_Class s : content)
		{
			if (s.get_Pos().contains("NN"))
			{
				this.result.get(index).add(s.get_sentence());
			}
		}
	}

	public static void main(String[] args)
	{
		Classify_Sentence_NN nn = new Classify_Sentence_NN();
		nn.Start("src/main/java/Dataset/labeled");
		System.out.println("done");
	}
}
