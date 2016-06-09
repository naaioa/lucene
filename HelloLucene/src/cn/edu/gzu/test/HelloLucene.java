package cn.edu.gzu.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexReader.FieldOption;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class HelloLucene {

	public void index()
	{
		Directory directory = null;
		IndexWriter writer = null;
		try {
			//1. ����Directory
			//Directory directory = new RAMDirectory();//�������ڴ���
			directory = FSDirectory.open(new File("D:/lucene/index0"));
			//2. ����IndexWriter
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35,new StandardAnalyzer(Version.LUCENE_35));
		
			writer = new IndexWriter(directory, iwc);
			//3. ����Document����
			Document document = null;
			//4.  ΪDocument���Field
			File f = new File("D:/lucene/example");
			for(File file:f.listFiles()){
				document = new Document();
				
				String content = FileUtils.readFileToString(file);
				System.out.println(content);
				
				document.add(new Field("content",new FileReader(file)));
				document.add(new Field("filename",file.getName(),Field.Store.YES,Field.Index.NOT_ANALYZED));
				document.add(new Field("path",file.getAbsolutePath(),Field.Store.YES,Field.Index.NOT_ANALYZED));
				//5. ͨ��IndexWriter����ĵ���������
				writer.addDocument(document);
			}
			
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(writer != null){
				try {
					writer.close();
				} catch (CorruptIndexException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	public void searcher(){
		
		try {
			//1 ����Directory
			Directory directory = FSDirectory.open(new File("D:/lucene/index0"));
			//2.����IndexReader
			IndexReader reader = IndexReader.open(directory);
			//3������IndexReader����IndexSearch
			IndexSearcher searcher = new IndexSearcher(reader);
			//4����������Query
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
			//����Query ��ʾ������Ϊcontent�а�����java�ĵ�
			Query query = parser.parse("a");
			//5������search����������TopDocs
			TopDocs tds = searcher.search(query, 10);
			//6������TopDocs��ȡscoreDoc�����ȡ�����Document����
			ScoreDoc[] sds = tds.scoreDocs;
			for(ScoreDoc sd:sds){
				//7 ����searcher��scoreDoc�����ȡ�����Document����
				Document d = searcher.doc(sd.doc);
				//����Document���� ��ȡ��Ҫ��ֵ
				System.out.println(d.get("filename")+"["+d.get("path")+"]");
			}
			//�ر�reader
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ParseException e){
			
		}
		
	}
}
