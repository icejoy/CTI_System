package Input;

import java.io.File;
import java.util.ArrayList;

public class Input_ReadFileNames
{
	ArrayList<String> Names = new ArrayList<String>();

	public void input(String Full_PathName)
	{
		Names.clear();
		File f = new File(Full_PathName);
		File[] filelist = f.listFiles();
		for (File s : filelist)
		{
			Names.add(s.getName());
		}
	}

	public ArrayList<String> get_input()
	{
		return Names;
	}
}
