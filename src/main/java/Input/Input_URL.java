package Input;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Input_URL
{
	URL url;
	Document doc;
	Boolean have_html_content = false;

	public void set_URL(String url_path)
	{
		try
		{
			this.url = new URL(url_path);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void input()
	{
		have_html_content = false;
		try
		{
			this.doc = Jsoup.parse(this.url, 100000);
			if (this.doc.select("div").size() > 0)
			{
				have_html_content = true;
			}
			else
			{
				have_html_content = false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Document get_document()
	{
		return this.doc;
	}

	public Boolean get_haved_read()
	{
		return this.have_html_content;
	}
}
