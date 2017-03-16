package com.sohu.mrd.hotword.util;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Jin Guopan
 * @creation 2016年12月8日 分词的帮助类
 */
public class SegmentKit{
	private static  Logger LOG = Logger.getLogger(SegmentKit.class);
	/**
	 * 对句子进行切分
	 * @param sentence
	 * @return
	 */
	public static  List<String>   segmentSentence(String sentence)
	{
		List<String>  words = new ArrayList<String>();
		List<Term> terms = NlpAnalysis.parse(sentence).getTerms();
		for(int i=0;i<terms.size();i++)
		{
			words.add(terms.get(i).getName());
		}
		return words;
	}


}
