package Criteria;

import java.util.ArrayList;

public class Stanford_Class
{
	String sentence;
	ArrayList<String> ssp = new ArrayList<String>();
	ArrayList<String> ner = new ArrayList<String>();
	ArrayList<String> pos = new ArrayList<String>();
	ArrayList<String> lemma = new ArrayList<String>();

	public void set_sentence(String w)
	{
		this.sentence = w;
	}

	public String get_sentence()
	{
		return this.sentence;
	}

	public void set_token(String s)
	{
		this.ssp.add(s);
	}

	public ArrayList<String> get_token()
	{
		return this.ssp;
	}

	public void set_Ner(String n)
	{
		this.ner.add(n);
	}

	public ArrayList<String> get_Ner()
	{
		return this.ner;
	}

	public void set_Pos(String p)
	{
		this.pos.add(p);
	}

	public ArrayList<String> get_Pos()
	{
		return this.pos;
	}

	public void set_Lemma(String l)
	{
		this.lemma.add(l.toLowerCase());
	}

	public ArrayList<String> get_Lemma()
	{
		return this.lemma;
	}
}
