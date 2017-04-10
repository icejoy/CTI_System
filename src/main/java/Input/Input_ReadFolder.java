package Input;

import java.io.File;
import java.util.ArrayList;

public class Input_ReadFolder
{
	ArrayList<String> FolderName = new ArrayList<String>();

	public void input(String Full_PathName)
	{
		FolderName.clear();
		File f = new File(Full_PathName);
		File[] filelist = f.listFiles();
		for (File s : filelist)
		{
			FolderName.add(s.getName());
		}
	}

	public ArrayList<String> get_input()
	{
		return FolderName;
	}
}
