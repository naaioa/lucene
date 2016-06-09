package cn.edu.gzu.analyzer;

import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class AnalyzerUtil {
	
	public static void displayToken(String str,Analyzer analyer){
		try {
			TokenStream stream = analyer.tokenStream("content", new StringReader(str));
			//创建一个属性，这个属性会添加到流中，随着TokenStream增加
			CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
			
			while(stream.incrementToken()){
				System.out.print("["+cta+"]");
			}
			System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void displayAllTokenInfo(String str,Analyzer a){
		try
		{
			TokenStream stream = a.tokenStream("content", new StringReader(str));
			//位置增量的属性，存储单元之间的距离
			PositionIncrementAttribute pis = stream.addAttribute(PositionIncrementAttribute.class);
			//每个词汇单元的位置偏移量
			OffsetAttribute oa = stream.addAttribute(OffsetAttribute.class);
			//存储每个单元的信息（分词单元信息）
			CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
			//使用分词器的类型信息
			TypeAttribute ta = stream.addAttribute(TypeAttribute.class);
			for(;stream.incrementToken();){
				System.out.print(pis.getPositionIncrement()+":");
				System.out.print(oa.startOffset()+"-"+oa.endOffset()+":");
				System.out.print(cta+":");
				System.out.println(ta.toString());
			}
			System.out.println();
		}catch(IOException e)
		{
			
		}
	
	}
}
