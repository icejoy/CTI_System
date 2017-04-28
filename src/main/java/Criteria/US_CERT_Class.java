package Criteria;

public class US_CERT_Class
{
	String Title = "";
	String URL = "";
	String Description = "";

	public void set_URL(String url)
	{
		this.URL = url;
	}

	public String get_URL()
	{
		return this.URL;
	}

	public void set_Title(String title)
	{
		this.Title = title;
	}

	public String get_Title()
	{
		return this.Title;
	}

	public void set_Description(String description)
	{
		this.Description = description;
	}

	public String get_Description()
	{
		return this.Description;
	}
}
