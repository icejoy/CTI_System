package Criteria;

import java.util.ArrayList;

import org.json.simple.JSONArray;

public class Config_Class
{
	int time = 0, type = 0, target = 0, name = 0, ioc = 0, term = 0, ref = 0;
	String ref_path;
	ArrayList<String> term_source = new ArrayList<String>();

	public int String_To_Integer(String temp)
	{
		return Integer.parseInt(temp);
	}

	public void set_time(String temp)
	{
		this.time = String_To_Integer(temp);
	}

	public void set_type(String temp)
	{
		this.type = String_To_Integer(temp);
	}

	public void set_target(String temp)
	{
		this.target = String_To_Integer(temp);
	}

	public void set_name(String temp)
	{
		this.name = String_To_Integer(temp);
	}

	public void set_ioc(String temp)
	{
		this.ioc = String_To_Integer(temp);
	}

	public void set_term(String temp)
	{
		this.term = String_To_Integer(temp);
	}

	public void set_ref(String temp)
	{
		this.ref = String_To_Integer(temp);
	}

	public int get_time()
	{
		return this.time;
	}

	public int get_type()
	{
		return this.type;
	}

	public int get_target()
	{
		return this.target;
	}

	public int get_name()
	{
		return this.name;
	}

	public int get_ioc()
	{
		return this.ioc;
	}

	public int get_term()
	{
		return this.term;
	}

	public int get_ref()
	{
		return this.ref;
	}

	public void set_ref_path(String temp)
	{
		this.ref_path = temp;
	}

	public String get_ref_path()
	{
		return this.ref_path;
	}

	public void set_term_list(JSONArray temp)
	{
		term_source.clear();
		for (Object s : temp)
		{
			term_source.add(s.toString());
		}
	}

	public ArrayList<String> get_term_list()
	{
		return term_source;
	}
}
