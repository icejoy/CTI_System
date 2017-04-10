package Output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Criteria.Stanford_Class;

public class Output_StanfordSentences
{

	String FilePath = "";

	public void Set_FileConfig(String FilePath)
	{
		this.FilePath = FilePath;
	}

	public void Stanford_Save(String FileName, ArrayList<Stanford_Class> nlp)
	{
		try
		{
			File f = new File(this.FilePath);
			if (!f.exists())
			{
				f.mkdirs();
			}
			FileWriter fw = new FileWriter(f + "/" + FileName);
			fw.write("word pos ner\r\n");
			for (Stanford_Class s : nlp)
			{
				fw.write(s.get_sentence() + "\r\n");
				fw.write(s.get_Pos().toString()+"\r\n");
				fw.write(s.get_Ner().toString()+"\r\n");
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
