package org.xtremeturmoil.hadoop.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.InvalidFormatException;

public class NounExtractor {

	private POSTaggerME tagger;
	private Logger logger = Logger.getLogger(getClass());
	public final static String ENGLISH_MODEL = "en-pos-maxent.bin";
	private final static String NOUN = "NN";
	private final static String FULL_STOP = ".";

	public NounExtractor(String model) {
		InputStream is = getClass().getClassLoader().getResourceAsStream(model);
		try {
			POSModel posModel = new POSModel(is);
			tagger = new POSTaggerME(posModel);
		} catch (InvalidFormatException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public List<Entity> process(String text) throws IOException {
		List<Entity> entities = new ArrayList<Entity>();
		List<String> sentence = parseKeywords(new StandardAnalyzer(Version.LUCENE_44), text);
		String[] tags = tagger.tag(sentence.toArray(new String[sentence.size()]));
		for(int i=0; i < tags.length; i++) {
			String tag = tags[i];
			String word = sentence.get(i);
			if(tag.startsWith(NOUN)&&!word.contains(FULL_STOP)&&word.length()>2) {
				entities.add(new Entity(word, tag));
			}
		}
		return entities;
	}


	private List<String> parseKeywords(StandardAnalyzer analyzer, String text) throws IOException {
		List<String> result = new ArrayList<String>();
		if(text!=null&&text.length()>0) {
			TokenStream stream = analyzer.tokenStream(null, new StringReader(text));
			CharTermAttribute cattr = stream.addAttribute(CharTermAttribute.class);
			stream.reset();
			while (stream.incrementToken()) {
				result.add(cattr.toString());
			}
			stream.end();
			stream.close();
		}
		return result;
	}  
}
