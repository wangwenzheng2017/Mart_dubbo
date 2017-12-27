package com.taotao.item.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HtmlGenController {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@RequestMapping("/genhtml")
	@ResponseBody
	public String genHtml() throws Exception {
		// TODO Auto-generated method stub
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		Template template = configuration.getTemplate("hello.ftl");
		Map dataModel = new HashMap<>();
		dataModel.put("hello", "1000");
		Writer out = new FileWriter(new File("D:/home/mark/code_file/GitHub/busMarket/taotao-item-web/src/main/webapp/WEB-INF/test/spring-freemarker.html"));
		template.process(dataModel, out);
		out.close();
		return "OK";
	}
}
