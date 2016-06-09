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
			//1. 创建Directory
			//Directory directory = new RAMDirectory();//建立在内存中
			directory = FSDirectory.open(new File("D:/lucene/index0"));
			//2. 创建IndexWriter
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35,new StandardAnalyzer(Version.LUCENE_35));
		
			writer = new IndexWriter(directory, iwc);
			//3. 创建Document对象
			Document document = null;
			//4.  为Document添加Field
			File f = new File("D:/lucene/example");
			for(File file:f.listFiles()){
				document = new Document();
				
				String content = FileUtils.readFileToString(file);
				System.out.println(content);
				
				document.add(new Field("content",new FileReader(file)));
				document.add(new Field("filename",file.getName(),Field.Store.YES,Field.Index.NOT_ANALYZED));
				document.add(new Field("path",file.getAbsolutePath(),Field.Store.YES,Field.Index.NOT_ANALYZED));
				//5. 通过IndexWriter添加文档到索引中
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
			//1 创建Directory
			Directory directory = FSDirectory.open(new File("D:/lucene/index0"));
			//2.创建IndexReader
			IndexReader reader = IndexReader.open(directory);
			//3、根据IndexReader创建IndexSearch
			IndexSearcher searcher = new IndexSearcher(reader);
			//4、创建搜索Query
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
			//创建Query 表示搜索域为content中包含的java文档
			Query query = parser.parse("a");
			//5、根据search搜索并返回TopDocs
			TopDocs tds = searcher.search(query, 10);
			//6、根据TopDocs获取scoreDoc对象获取具体的Document对象
			ScoreDoc[] sds = tds.scoreDocs;
			for(ScoreDoc sd:sds){
				//7 根据searcher和scoreDoc对象获取具体的Document对象
				Document d = searcher.doc(sd.doc);
				//根据Document对象 获取需要的值
				System.out.println(d.get("filename")+"["+d.get("path")+"]");
			}
			//关闭reader
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ParseException e){
			
		}
		
	}
}
