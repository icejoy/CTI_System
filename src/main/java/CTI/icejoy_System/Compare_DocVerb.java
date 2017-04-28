package CTI.icejoy_System;

import java.util.ArrayList;

import Criteria.String_Integer_Class;
import Output.Output_SaveTxT;

public class Compare_DocVerb
{
	String next = ",";
	Top30Vocab_Run run = new Top30Vocab_Run();
	Output_SaveTxT output = new Output_SaveTxT();

	ArrayList<String> NN = new ArrayList<String>();
	ArrayList<String> VB = new ArrayList<String>();
	ArrayList<String> JJ = new ArrayList<String>();
	ArrayList<String> result = new ArrayList<String>();

	public void Start()
	{
		this.run.parse("Dataset/TF");
		ArrayList<String> TF = new ArrayList<String>(this.run.get_parse());
		for (int i = 0; i < TF.size(); i++)
		{
			Marge("NN", this.run.get_NN().get(i));
			Marge("VB", this.run.get_VB().get(i));
			Marge("JJ", this.run.get_JJ().get(i));
		}

		result.clear();
		int index = 0, count = 0;
		int T = 0, F = 0;
		String rol = "";

		result.add("NN");
		for (String s : NN)
		{
			rol = s;
			for (int i = 0; i < TF.size(); i++)
			{
				if (this.run.get_NN().get(i).get_term().contains(s))
				{
					// count++;
					if (TF.get(i).equals("T"))
					{
						T++;
					}
					else
					{
						F++;
					}
					// index = this.run.get_NN().get(i).get_term().indexOf(s);
					// rol += next + TF.get(i) + next +
					// this.run.get_NN().get(i).get_count().get(index);
				}
			} /*
				 * if (count > 10) { result.add(rol); }
				 */
			// if (F > 5 || T > 5)
			{
				result.add(rol + next + "F:" + next + F + next + "T:" + next + T);
			}
			if (F == 0 && T == 0)
			{
				System.out.println("ERROR");
			}
			rol = "";
			T = 0;
			F = 0;
			// count = 0;
		}

		count = 0;
		T = 0;
		F = 0;
		result.add("\r\nVB");
		for (String s : VB)
		{
			rol = s;
			for (int i = 0; i < TF.size(); i++)
			{
				if (this.run.get_VB().get(i).get_term().contains(s))
				{
					// count++;
					if (TF.get(i).equals("T"))
					{
						T++;
					}
					else
					{
						F++;
					}
					// index = this.run.get_NN().get(i).get_term().indexOf(s);
					// rol += next + TF.get(i) + next +
					// this.run.get_NN().get(i).get_count().get(index);
				}
			} /*
				 * if (count > 10) { result.add(rol); }
				 */
			// if (F > 5 || T > 5)
			{
				result.add(rol + next + "F:" + next + F + next + "T:" + next + T);
			}
			if (F == 0 && T == 0)
			{
				System.out.println("ERROR");
			}
			rol = "";
			T = 0;
			F = 0;
			// count = 0;
		}

		count = 0;
		T = 0;
		F = 0;
		result.add("\r\nJJ");
		for (String s : JJ)
		{
			rol = s;
			for (int i = 0; i < TF.size(); i++)
			{
				if (this.run.get_JJ().get(i).get_term().contains(s))
				{
					// count++;
					if (TF.get(i).equals("T"))
					{
						T++;
					}
					else
					{
						F++;
					}
					// index = this.run.get_NN().get(i).get_term().indexOf(s);
					// rol += next + TF.get(i) + next +
					// this.run.get_NN().get(i).get_count().get(index);
				}
			} /*
				 * if (count > 10) { result.add(rol); }
				 */
			// if (F > 5 || T > 5)
			{
				result.add(rol + next + "F:" + next + F + next + "T:" + next + T);
			}
			if (F == 0 && T == 0)
			{
				System.out.println("ERROR");
			}
			rol = "";
			T = 0;
			F = 0;
			// count = 0;
		}

		this.output.String_One_ArrayListt_Save("All_Top30Verb.csv", result, false);
	}

	public void Marge(String Choose, String_Integer_Class list)
	{
		if (Choose == "NN")
		{
			for (String s : list.get_term())
			{
				if (NN.size() == 0 || !NN.contains(s))
				{
					NN.add(s);
				}
			}
		}
		else if (Choose == "VB")
		{
			for (String s : list.get_term())
			{
				if (VB.size() == 0 || !VB.contains(s))
				{
					VB.add(s);
				}
			}
		}
		else
		{
			for (String s : list.get_term())
			{
				if (JJ.size() == 0 || !JJ.contains(s))
				{
					JJ.add(s);
				}
			}
		}
	}

	public static void main(String[] args)
	{
		Compare_DocVerb cc = new Compare_DocVerb();
		cc.Start();
		System.out.println("done");
	}

}
