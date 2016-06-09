package cn.edu.gzu.test;

import org.junit.Test;

public class TestLucene {

	@Test
	public void testIndex(){
		HelloLucene hl = new HelloLucene();
		hl.index();
	}
	@Test
	public void testSearch()
	{
		HelloLucene hl = new HelloLucene();
		hl.searcher();
	}
}
