package com.repay.app.controller.base;

import io.swagger.annotations.Api;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.repay.app.util.ResultObject;


@Api(value = "文件上传",description="文件上传")
@RestController
@RequestMapping("/upload")
public class UfileController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UfileController.class);
	
	
	@Value("${file.locations}")
	private String locations;
	
	@Value("${file.appUrl}")
	private String appUrl;
	
	
	@RequestMapping(value="/file",method=RequestMethod.POST)
	public ResultObject uploadFile(@RequestBody(required = true) MultipartFile file,HttpServletRequest request){
		ResultObject rs = ResultObject.getFailResult("上传文件失败");
		try{
			 String contentType = file.getContentType();
			 String fileName = file.getOriginalFilename();
			 long fileSize = file.getSize();
			 String suffixName = fileName.substring(fileName.lastIndexOf("."));
			 //按年月日生成名称
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			 SimpleDateFormat dicsdf = new SimpleDateFormat("yyyyMMdd");
			 Date now = new Date();
			 String dateNameStr = sdf.format(now);
			 String dicName = dicsdf.format(now);
			 //文件夹
			 String dicPath = locations+dicName;
			 //String dicPath = "c:"+locations+dicName;
			 System.err.println(dicPath);
			// 如果不存在,创建文件夹
			File upload = new File(dicPath);
			if(!upload.exists()){
				upload.mkdirs();
			}
			//上传
			 file.transferTo(new File(dicPath+"/"+dateNameStr+suffixName));
			 rs = ResultObject.getSuccessResult("上传成功");
			 Map<String,Object> map = new HashMap<String,Object>();
			 String url =appUrl+dicName+"/"+dateNameStr+suffixName;
			 map.put("fileUrl", url);       //图片路劲
			 map.put("fileSize", fileSize); //图片大小
			 rs.setData(map);
		}catch(Exception e){
				LOGGER.error("上传异常", e);
			 rs = ResultObject.getFailResult("上传异常");
		}
		return rs;
	}
	
	
	
	

}
