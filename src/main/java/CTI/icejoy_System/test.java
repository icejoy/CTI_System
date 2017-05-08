package CTI.icejoy_System;

import java.util.ArrayList;

import Criteria.Stanford_Class;
import Parser.Parser_StanfordNLP;

public class test
{

	public static void main(String[] args)
	{
		Parser_StanfordNLP stanford = new Parser_StanfordNLP();
		String a = "The campaign relies heavily on spamming victims and tricking them into opening a rigged .zip file that’s disguised as an e-fax file. Once the .zip file is opened, an .exe file that’s nested inside is activated and code – in the form of a malicious DLL (MSIMG32.dll) – is dropped onto the victim’s machine. The malware uses DLL hijacking to write system usernames and passwords to a text file, “Log%s#%.3u.txt,” and send them along to the attacker’s command-and-control server.";
		String b = "Once the user clicks on the link, a JavaScript file (JS_NEMUCOD) disguised as the invoice document will be downloaded to the victim’s computer. When the user tries to open the fake invoice, another obfuscated JavaScript file will be downloaded to memory, after which the TorrentLocker payload will be downloaded and executed in the system. A notable feature of the new TorrentLocker variants is that they are packaged as NSIS installers to avoid detection, a technique also used by other prominent ransomware such as CERBER, LOCKY, SAGE and SPORA.";
		ArrayList<String> compare = new ArrayList<String>();
		compare.add(a);
		stanford.parse(compare);
		ArrayList<Stanford_Class> A = new ArrayList<Stanford_Class>(stanford.get_parse());
		compare.clear();
		compare.add(b);
		stanford.parse(compare);
		ArrayList<Stanford_Class> B = new ArrayList<Stanford_Class>(stanford.get_parse());
		int count = 0;
		for (int i = 0; i < A.size(); i++)
		{
			for (int j = 0; j < A.get(i).get_Lemma().size(); j++)
			{
				for (int k = 0; k < B.size(); k++)
				{
					if (B.get(k).get_Lemma().contains(A.get(i).get_Lemma().get(j)))
					{
						count++;
						System.out.println(A.get(i).get_Lemma().get(j) + "---"
								+ B.get(k).get_Lemma().get(B.get(k).get_Lemma().indexOf(A.get(i).get_Lemma().get(j))));
						break;
					}
				}
			}
		}
		System.out.println("A:"+count);
		count=0;
		for (int i = 0; i < B.size(); i++)
		{
			for (int j = 0; j < B.get(i).get_Lemma().size(); j++)
			{
				for (int k = 0; k < A.size(); k++)
				{
					if (A.get(k).get_Lemma().contains(B.get(i).get_Lemma().get(j)))
					{
						count++;
						System.out.println(B.get(i).get_Lemma().get(j) + "---"
								+ A.get(k).get_Lemma().get(A.get(k).get_Lemma().indexOf(B.get(i).get_Lemma().get(j))));
						break;
					}
				}
			}
		}
		System.out.println("B:"+count);
	}

}
