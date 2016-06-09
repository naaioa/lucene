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
			//����һ�����ԣ�������Ի���ӵ����У�����TokenStream����
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
			//λ�����������ԣ��洢��Ԫ֮��ľ���
			PositionIncrementAttribute pis = stream.addAttribute(PositionIncrementAttribute.class);
			//ÿ���ʻ㵥Ԫ��λ��ƫ����
			OffsetAttribute oa = stream.addAttribute(OffsetAttribute.class);
			//�洢ÿ����Ԫ����Ϣ���ִʵ�Ԫ��Ϣ��
			CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
			//ʹ�÷ִ�����������Ϣ
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
