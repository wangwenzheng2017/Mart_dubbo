package com.taotao.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.utils.FastDFSClient;
import com.taotao.utils.JsonUtils;

@Controller
public class PictureController {
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
    public String fileUpload(MultipartFile uploadFile) {
		String originalFilename = uploadFile.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
		try {
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:resource/client.conf");
			String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			String url = IMAGE_SERVER_URL + path;
			Map result = new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
		    String json = JsonUtils.objectToJson(result);
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			String json = JsonUtils.objectToJson(result);
			return json;
		}
	}
}
