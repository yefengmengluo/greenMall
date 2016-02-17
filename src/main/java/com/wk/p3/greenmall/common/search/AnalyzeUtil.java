package com.wk.p3.greenmall.common.search;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class AnalyzeUtil {
	
	@SuppressWarnings("resource")
	public static Set<String>analyze(String centence){
		String keyWord = centence;
		Set<String>kwList=new HashSet<String>();
		try {
			TokenStream tokenStream =new IKAnalyzer(true).tokenStream("content", new StringReader(keyWord));
			tokenStream.addAttribute(CharTermAttribute.class);
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				CharTermAttribute charTermAttribute = (CharTermAttribute) tokenStream
						.getAttribute(CharTermAttribute.class);
				kwList.add(charTermAttribute.toString());
			}
			tokenStream.end();
			tokenStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kwList;
	}
}
