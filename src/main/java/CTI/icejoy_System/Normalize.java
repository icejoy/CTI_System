package CTI.icejoy_System;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Input.Input_ReadFile;
import Output.Output_SaveTxT;

public class Normalize
{
	String split_with = "";
	Output_SaveTxT output = new Output_SaveTxT();
	Input_ReadFile readFile = new Input_ReadFile();
	ArrayList<String> input_content;
	ArrayList<String> result = new ArrayList<String>();

	DecimalFormat df = new DecimalFormat("#.##");
	ArrayList<ArrayList<Double>> number = new ArrayList<ArrayList<Double>>();

	public void Start(String input_path)
	{
		if (input_path.contains("csv"))
		{
			split_with = ",";
		}
		else if (input_path.contains("tsv"))
		{
			split_with = "\t";
		}

		this.readFile.input(input_path);
		this.input_content = null;
		this.input_content = new ArrayList<String>(readFile.get_input());
		this.number.clear();
		String[] str;
		for (String s : this.input_content)
		{
			str = s.split(split_with);
			this.number.add(new ArrayList<Double>());
			for (int i = 2; i < str.length; i++)
			{
				if (str[i].equals("NA"))
				{
					this.number.get(this.number.size() - 1).add(null);
				}
				else
				{
					this.number.get(this.number.size() - 1).add(Double.parseDouble(str[i]));
				}
			}
		}
		for (int i = 0; i < this.number.get(0).size(); i++)
		{
			Normal(Find_MAX(i), i);
		}

		String rol = "label" + split_with;
		for (int i = 0; i < this.number.get(0).size(); i++)
		{
			if (i + 1 < this.number.get(0).size())
			{
				rol += "col" + (i + 1) + split_with;
			}
			else
			{
				rol += "col" + (i + 1);
			}
		}
		this.result.add("id" + split_with + rol);

		rol = "";
		int T = 1, F = 1;
		for (int i = 0; i < this.input_content.size(); i++)
		{
			str = this.input_content.get(i).split(split_with);
			if (str[0].contains("/TF/F"))
			{
				str[0] = "F" + F;
				F++;
			}
			else
			{
				str[0] = "T" + T;
				T++;
			}
			// str[0] = str[0].replace(" ", "_");
			// str[0] = str[0].replace(",", "-");
			rol = str[0] + split_with + str[1];
			for (Double d : this.number.get(i))
			{
				rol += split_with + df.format(d);
			}
			this.result.add(rol);
		}
		this.output.String_One_ArrayListt_Save("done" + input_path, result, false);
	}

	public Double Find_MAX(int index)
	{
		Double max = Double.MIN_VALUE;
		for (int i = 0; i < this.number.size(); i++)
		{
			if (this.number.get(i).get(index) != null && max < this.number.get(i).get(index))
			{
				max = this.number.get(i).get(index);
			}
		}
		return max;
	}

	public void Normal(Double max, int index)
	{
		for (int i = 0; i < this.number.size(); i++)
		{
			if (this.number.get(i).get(index) != null)
			{
				this.number.get(i).set(index, this.number.get(i).get(index) / max);
			}
			else
			{
				this.number.get(i).set(index, 1.0);
			}
		}
	}

	public static void main(String[] args)
	{
		Normalize normalize = new Normalize();
		normalize.Start("Ture__NNVBJJ_NoD.csv");
		System.out.println("done");
	}

}
