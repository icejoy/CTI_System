package Input;

import java.io.File;
import java.util.ArrayList;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class Input_XML
{
	String Path = "";

	public void set_InputPath(String path)
	{
		this.Path = path;
	}

	public void input(ArrayList<String> key)
	{
		File XmlFile = new File(this.Path);
		SAXBuilder builder = new SAXBuilder();
		try
		{
			Document document = (Document) builder.build(XmlFile);
			Element root_element = document.getRootElement();
			root_element.getChildren("Text");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
