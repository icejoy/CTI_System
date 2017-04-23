package Tool;

import java.util.ArrayList;

import Input.Input_ReadFile;
import Input.Input_ReadFolder;
import Output.Output_SaveTxT;

public class Count_Line
{
	static String input = "src/main/java/Dataset/TF";
	static Input_ReadFolder ReadFolder = new Input_ReadFolder();
	static Input_ReadFile ReadFile = new Input_ReadFile();
	static Output_SaveTxT output = new Output_SaveTxT();

	public static void main(String[] args)
	{
		ArrayList<String> count = new ArrayList<String>();
		ArrayList<String> TF_Files = new ArrayList<String>(Read_Files(input));
		for (int i = 0; i < TF_Files.size(); i++)
		{
			ReadFile.input(TF_Files.get(i));
			count.add(ReadFile.get_input().size() + "");
		}
		output.String_One_ArrayListt_Save("line_count.txt", count, false);
	}

	public static ArrayList<String> Read_Files(String path)
	{
		ArrayList<String> Folder;
		ArrayList<String> array = new ArrayList<String>();
		ReadFolder.input(path);
		Folder = new ArrayList<String>(ReadFolder.get_input());
		for (String folder : Folder)
		{
			ReadFolder.input(path + "/" + folder);
			for (String file : ReadFolder.get_input())
			{
				array.add(path + "/" + folder + "/" + file);
			}
		}
		return array;
	}
}
