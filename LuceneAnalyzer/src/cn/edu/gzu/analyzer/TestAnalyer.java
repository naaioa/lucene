package cn.edu.gzu.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.junit.Test;


public class TestAnalyer {
	@Test
	public void test1()
	{
		Analyzer a1 = new StandardAnalyzer(Version.LUCENE_35);
		Analyzer a2 = new StopAnalyzer(Version.LUCENE_35);
		Analyzer a3 = new SimpleAnalyzer(Version.LUCENE_35);
		Analyzer a4 = new WhitespaceAnalyzer(Version.LUCENE_35);
		
		String txt = "this is my house, I am come from china,my email is wxq92109@gmail.com,my QQ :234297977";
		
		AnalyzerUtil.displayToken(txt, a1);
		AnalyzerUtil.displayToken(txt, a2);
		AnalyzerUtil.displayToken(txt, a3);
		AnalyzerUtil.displayToken(txt, a4);
	}
	@Test
	public void test2()
	{
		Analyzer a1 = new StandardAnalyzer(Version.LUCENE_35);
		Analyzer a2 = new StopAnalyzer(Version.LUCENE_35);
		Analyzer a3 = new SimpleAnalyzer(Version.LUCENE_35);
		Analyzer a4 = new WhitespaceAnalyzer(Version.LUCENE_35);
		
		String txt = "科比正式退役!20年生涯终结 史诗级告别战轰60分";
		
		AnalyzerUtil.displayToken(txt, a1);
		AnalyzerUtil.displayToken(txt, a2);
		AnalyzerUtil.displayToken(txt, a3);
		AnalyzerUtil.displayToken(txt, a4);
	}
	@Test
	public void test3()
	{
		Analyzer a1 = new StandardAnalyzer(Version.LUCENE_35);
		Analyzer a2 = new StopAnalyzer(Version.LUCENE_35);
		Analyzer a3 = new SimpleAnalyzer(Version.LUCENE_35);
		Analyzer a4 = new WhitespaceAnalyzer(Version.LUCENE_35);
		
		String txt = "this is my house, I am come from china,my email is wxq92109@gmail.com,my QQ :234297977";
		
		AnalyzerUtil.displayAllTokenInfo(txt, a1);
		System.out.println("--------------------");
		AnalyzerUtil.displayAllTokenInfo(txt, a2);
		System.out.println("--------------------");
		AnalyzerUtil.displayAllTokenInfo(txt, a3);
		System.out.println("--------------------");
		AnalyzerUtil.displayAllTokenInfo(txt, a4);
	}
	@Test
	public void test4()
	{
		Analyzer a1 = new MystopAnalyzer(new String[]{"I","you","like"});
		String txt = "how are you,thank you,I like you very much!";
		AnalyzerUtil.displayAllTokenInfo(txt, a1);
	}

}
