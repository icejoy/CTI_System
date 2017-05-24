package Tool;

import java.util.ArrayList;

import Criteria.String_Integer_Class;

public class Entropy_Similarity
{

	public Double Similarity_Calculate(ArrayList<Integer> index, String_Integer_Class T1, String_Integer_Class T2)
	{
		return D(index, T1, T2);

	}

	private Double Z(ArrayList<Integer> index, String_Integer_Class T1, String_Integer_Class T2)
	{
		Double z = 0.0;
		Double p1, p2, log1, log2;
		for (int i = 0; i < index.size(); i++)
		{
			p1 = T1.get_count().get(index.get(i)) / T1.get_Size();
			i++;
			p2 = T2.get_count().get(index.get(i)) / T2.get_Size();
			log1 = Math.log(p1);
			log2 = Math.log(p2);
			z += -(p1 * log1) - (p2 * log2);
		}
		return z;
	}

	private Double D(ArrayList<Integer> index, String_Integer_Class T1, String_Integer_Class T2)
	{
		Double z, fw, d = 0.0;
		int rank1, rank2;
		z = Z(index, T1, T2);
		for (int i = 0; i < index.size(); i += 2)
		{
			rank1 = T1.get_Rank().get(index.get(i));
			rank2 = T2.get_Rank().get(index.get(i+ 1) );
			fw = Entropy(z, i, index, T1, T2);

			d += Math.abs(rank1 - rank2) * fw;
		}
		return d / (index.size() / 2);
	}

	private Double Entropy(Double z, int i, ArrayList<Integer> index, String_Integer_Class T1, String_Integer_Class T2)
	{
		Double fw, p1, p2, log1, log2;
		p1 = T1.get_count().get(index.get(i)) / T1.get_Size();
		p2 = T2.get_count().get(index.get(i+1)) / T2.get_Size();
		log1 = Math.log(p1);
		log2 = Math.log(p2);
		fw = (-(p1 * log1) - (p2 * log2)) / z;
		return fw;
	}
}
