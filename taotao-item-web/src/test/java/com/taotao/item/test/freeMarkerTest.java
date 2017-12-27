package com.taotao.item.test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class freeMarkerTest {

	@Test
	public void getFile() throws Exception {
		// TODO Auto-generated method stub

		Configuration configuration = new Configuration(Configuration.getVersion());
		///taotao-item-web/src/main/webapp/WEB-INF/ftl
		configuration.setDirectoryForTemplateLoading(new File("D:/home/mark/code_file/GitHub/busMarket/taotao-item-web/src/main/webapp/WEB-INF/ftl"));
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("hello.ftl");
		Map dataModel = new HashMap<>();
		dataModel.put("hello", "this is a first freemarker test.");
		Writer out = new FileWriter(new File("D:/home/mark/code_file/GitHub/busMarket/taotao-item-web/src/main/webapp/WEB-INF/test/hello.html"));
		
		template.process(dataModel, out);
		
		out.close();
	}
}
