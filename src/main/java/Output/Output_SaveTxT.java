package Output;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Output_SaveTxT
{
	String FilePath = "";

	public void Set_FileConfig(String FilePath)
	{
		this.FilePath = FilePath;
	}

	public void String_One_ArrayListt_Save(String FileName, ArrayList<String> content, Boolean Continue)
	{

		try
		{
			FileWriter fw = new FileWriter(this.FilePath + FileName, Continue);
			for (String s : content)
			{
				fw.write(s + "\r\n");
			}
			fw.flush();
			fw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void String_Two_ArrayList_Save(String FileName, ArrayList<ArrayList<String>> content, Boolean Continue)
	{

		try
		{
			FileWriter fw = new FileWriter(this.FilePath + FileName, Continue);
			for (ArrayList<String> list : content)
			{
				for (String s : list)
				{
					fw.write(s + "\r\n");
				}
				fw.write("\r\n==========\r\n");
			}
			fw.flush();
			fw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
