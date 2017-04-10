package Tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Criteria.Stanford_Class;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import edu.stanford.nlp.util.CoreMap;

public class StanfordNLP
{
	Annotation document;
	List<CoreMap> sentences;
	Properties props;
	StanfordCoreNLP pipeline;
	ArrayList<Stanford_Class> result;

	public StanfordNLP()
	{
		this.props = null;
		this.props = new Properties();
		this.result = null;
		this.result = new ArrayList<Stanford_Class>();
		// 获取句子的token（可以是作为分词后的词语）
		// String word = token.get(TextAnnotation.class);
		// 词性标注
		// String pos = token.get(PartOfSpeechAnnotation.class);
		// 命名实体识别
		// String ne = token.get(NamedEntityTagAnnotation.class);
		// 词干化处理
		// String lema=token.get(LemmaAnnotation.class);
		// 句子的解析树
		// Tree tree = sentence.get(TreeAnnotation.class);
		// 句子的依赖图
		// SemanticGraph graph =
		// sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
		this.props.setProperty("annotators", "tokenize, ssplit, pos, lemma");//, ner, parse, dcoref");

		this.pipeline = null;
		this.pipeline = new StanfordCoreNLP(props);
	}

	public void Start(ArrayList<String> content)
	{
		this.result.clear();
		for (String s : content)
		{
			Set_Content(s);
			Start_NLP();
		}
	}

	public void Set_Content(String content)
	{
		this.document = null;
		this.document = new Annotation(content);
		// Map<Integer, CorefChain> corefChains =
		// document.get(CorefChainAnnotation.class);
		this.pipeline.annotate(this.document);
		this.sentences = this.document.get(SentencesAnnotation.class);
	}

	public void Start_NLP()
	{
		for (CoreMap sentence : sentences)
		{
			this.result.add(new Stanford_Class());
			this.result.get(this.result.size() - 1).set_sentence(sentence.toString());
			for (CoreLabel token : sentence.get(TokensAnnotation.class))
			{
				this.result.get(this.result.size() - 1).set_token(token.get(TextAnnotation.class));
				this.result.get(this.result.size() - 1).set_Lemma(token.get(LemmaAnnotation.class));
				this.result.get(this.result.size() - 1).set_Pos(token.get(PartOfSpeechAnnotation.class));
				//this.result.get(this.result.size() - 1).set_Ner(token.get(NamedEntityTagAnnotation.class));
			}
		}
	}

	public ArrayList<Stanford_Class> get_result()
	{
		return this.result;
	}
}