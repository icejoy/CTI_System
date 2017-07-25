package Parser;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import Tool.Read_Config;

public class Parser_IOC_Calculator
{
	Boolean Haved = false;
	ArrayList<String> result = new ArrayList<String>();
	String URL, HOST, IP, EMAIL, MD5, SHA1, SHA256, CVE, REGISTRY;
	String FILENAME, FILEPATH, PATH;//, DETECTED_MALWARENAME, EXTENSION
	Read_Config read_Config = new Read_Config();

	public void set_config(String Config_Path)
	{
		try
		{
			this.read_Config.ReadConfig(Config_Path);
			JSONObject settings = new JSONObject(this.read_Config.get_Config());

			if (settings.containsKey("URL"))
			{
				this.URL = (String) settings.get("URL");
			}
			if (settings.containsKey("HOST"))
			{
				this.HOST = (String) settings.get("HOST");
			}
			if (settings.containsKey("IP"))
			{
				this.IP = (String) settings.get("IP");
			}
			if (settings.containsKey("EMAIL"))
			{
				this.EMAIL = (String) settings.get("EMAIL");
			}
			if (settings.containsKey("MD5"))
			{
				this.MD5 = (String) settings.get("MD5");
			}
			if (settings.containsKey("SHA1"))
			{
				this.SHA1 = (String) settings.get("SHA1");
			}
			if (settings.containsKey("SHA256"))
			{
				this.SHA256 = (String) settings.get("SHA256");
			}
			if (settings.containsKey("CVE"))
			{
				this.CVE = (String) settings.get("CVE");
			}
			if (settings.containsKey("REGISTRY"))
			{
				this.REGISTRY = (String) settings.get("REGISTRY");
			}
			if (settings.containsKey("FILENAME"))
			{
				this.FILENAME = (String) settings.get("FILENAME");
			}
			if (settings.containsKey("FILEPATH"))
			{
				this.FILEPATH = (String) settings.get("FILEPATH");
			}
//			if (settings.containsKey("DETECTED_MALWARENAME")),"DETECTED_MALWARENAME":"[_]*[a-zA-Z0-9]{3,}[._/][a-zA-Z0-9]{3,}([._/][a-zA-Z0-9]*)*"
//			{
//				this.DETECTED_MALWARENAME = (String) settings.get("DETECTED_MALWARENAME");
//			}
//			if (settings.containsKey("EXTENSION")),"EXTENSION":"[.][a-z]{2,}"
//			{
//				this.EXTENSION = (String) settings.get("EXTENSION");
//			}
			if (settings.containsKey("PATH"))
			{
				this.PATH = (String) settings.get("PATH");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void Check_IOC_Paragraph(ArrayList<String> content)
	{
		Haved = false;
		int count = 0;
		// int Paragraph = 0;
		this.result.clear();
		ArrayList<String> r;
		for (int i = 0; i < content.size(); i++)
		{
			// if (content.get(i).length() == 0)
			// {
			// Paragraph = i;
			// }
			// else
			// {
			r = null;
			r = new ArrayList<String>(ReplaceIOC(content.get(i)));
			if (r.get(0).equals("Haved"))
			{
				Haved = true;
				for (i = 0; i < content.size(); i++)
				{
					result.add(ReplaceIOC(content.get(i)).get(1));
				}
				// result.add(r.get(1));
				// count = 0;
				// for (i = Paragraph; i < content.size(); i++)
				// {
				// if (content.get(i).length() > 0)
				// {
				// result.add(ReplaceIOC(content.get(i)).get(1));
				// count++;
				// }
				// else if (count > 0 && content.get(i).length() == 0)
				// {
				// Paragraph = i;
				// break;
				// }
				// }
				// }
			}
			else
			{
				result.add(r.get(1));
			}
		}
	}

	public ArrayList<String> ReplaceIOC(String content)
	{
		String temp = "";
		Boolean IOC = false;
		ArrayList<String> r = new ArrayList<String>();

		for (String str : content.split(" "))
		{
			str = ClearString(str);
			if (str.matches(this.URL))
			{
				if (temp == "")
				{
					temp = "URL";
				}
				else
				{
					temp += " URL";
				}
				IOC = true;
			}
			else if (str.matches(this.HOST))
			{
				// if (str.contains("[dot]"))
				// {
				// System.out.println(str);
				// }
				if (temp == "")
				{
					temp = "HOST";
				}
				else
				{
					temp += " HOST";
				}
				IOC = true;
			}
			else if (str.matches(this.IP))
			{
				if (temp == "")
				{
					temp = "IP";
				}
				else
				{
					temp += " IP";
				}
				IOC = true;
			}
			else if (str.matches(this.EMAIL))
			{
				if (temp == "")
				{
					temp = "EMAIL";
				}
				else
				{
					temp += " EMAIL";
				}
				IOC = true;
			}
			else if (str.matches(this.MD5))
			{
				if (temp == "")
				{
					temp = "MD5";
				}
				else
				{
					temp += " MD5";
				}
				IOC = true;
			}
			else if (str.matches(this.SHA1))
			{
				if (temp == "")
				{
					temp = "SHA1";
				}
				else
				{
					temp += " SHA1";
				}
				IOC = true;
			}
			else if (str.matches(this.SHA256))
			{
				if (temp == "")
				{
					temp = "SHA256";
				}
				else
				{
					temp += " SHA256";
				}
				IOC = true;
			}
			else if (str.matches(this.CVE))
			{
				if (temp == "")
				{
					temp = "CVEID";
				}
				else
				{
					temp += " CVEID";
				}
				IOC = true;
			}
			else if (str.matches(this.REGISTRY))
			{
				if (temp == "")
				{
					temp = "REGISTRY";
				}
				else
				{
					temp += " REGISTRY";
				}
				IOC = true;
			}
			else if (str.matches(this.FILENAME))
			{
				if (temp == "")
				{
					temp = "FILENAME";
				}
				else
				{
					temp += " FILENAME";
				}
				IOC = true;
			}
			else if (str.matches(this.FILEPATH))
			{
				if (temp == "")
				{
					temp = "FILEPATH";
				}
				else
				{
					temp += " FILEPATH";
				}
				IOC = true;
			}
//			else if (str.matches(this.DETECTED_MALWARENAME))
//			{
//				if (temp == "")
//				{
//					temp = "DETECTED_MALWARENAME";
//				}
//				else
//				{
//					temp += " DETECTED_MALWARENAME";
//				}
//				IOC = true;
//			}
//			else if (str.matches(this.EXTENSION))
//			{
//				if (temp == "")
//				{
//					temp = "EXTENSION";
//				}
//				else
//				{
//					temp += " EXTENSION";
//				}
//				IOC = true;
//			}
			else if (str.matches(this.PATH))
			{
				if (temp == "")
				{
					temp = "PATH";
				}
				else
				{
					temp += " PATH";
				}
				IOC = true;
			}
			else
			{
				if (temp == "")
				{
					temp = str;
				}
				else
				{
					temp += " " + str;
				}
			}
		}
		if (IOC)
		{
			r.add("Haved");
			r.add(temp);
		}
		else
		{
			r.add("NULL");
			r.add(temp);
		}
		return r;
	}

	public String ClearString(String str)
	{
		if (str.contains("\""))
		{
			str = str.replace("\"", "");
		}
		if (str.contains(","))
		{
			str = str.replace(",", "");
		}
		if (str.contains("("))
		{
			str = str.replace("(", "");
		}
		if (str.contains(")"))
		{
			str = str.replace(")", "");
		}
		return str;
	}

	public Boolean get_Check()
	{
		return Haved;
	}

	public ArrayList<String> get_IOC_Paragraph()
	{
		return this.result;
	}

}
