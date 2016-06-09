package cn.edu.gzu.analyzer;

import java.io.Reader;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LetterTokenizer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.Version;

public class MystopAnalyzer extends Analyzer{

	private Set stops;
	
	public MystopAnalyzer(String[]sws){
		System.out.println("StopAnalyzer Default Rules:"+StopAnalyzer.ENGLISH_STOP_WORDS_SET);
		stops = StopFilter.makeStopSet(Version.LUCENE_35, sws, true);
		//StopAnalyzer.ENGLISH_STOP_WORDS_SET Ĭ�ϵķִʹ��˹���
		//�����ǽ�ԭ�е�ͣ�ôʼ��뵽���ڵ�ͣ�ô�
		stops.addAll(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	}
	public MystopAnalyzer(){
		//��ȡԭ�е�ͣ�ô�
		stops = StopAnalyzer.ENGLISH_STOP_WORDS_SET;
	}
	@Override
	public TokenStream tokenStream(String filename, Reader reader) {
		return new StopFilter(Version.LUCENE_35, 
				new LowerCaseFilter(Version.LUCENE_35,
				new LetterTokenizer(Version.LUCENE_35, reader)) , stops);
	}
	
}
