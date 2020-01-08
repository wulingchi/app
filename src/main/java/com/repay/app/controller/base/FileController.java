/*package com.repay.app.controller.base;

import io.swagger.annotations.Api;




import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.repay.app.util.GsonUtil;
import com.repay.app.util.ResultObject;



@Api(value = "文件",description="文件")
@RestController
@RequestMapping("/file")
public class FileController {
	
  @Value("${apiUrl}")
  private String apiUrl = "http://localhost:8080";
  
  @Autowired
  private OauthFeign oauthFeign;
	
  @RequestMapping(value="/uploadFile",method=RequestMethod.POST)
  @ApiImplicitParams(
			 { @ApiImplicitParam(name = "Authorization", value = "token值", required = true, dataType = "String", paramType = "header")
			   })
  public ResultObject aa(@RequestParam("file")MultipartFile file,HttpServletRequest request){
	   ResultObject rs = ResultObject.getFailResult("上传文件失败");
	   String token = request.getHeader("Authorization");
	  
	  final String remote_url = apiUrl+"/c:/file/uploadFile";// 第三方服务器请求地址
      CloseableHttpClient httpClient = HttpClients.createDefault();
      String result = "";
      try {
          String fileName = file.getOriginalFilename();
          HttpPost httpPost = new HttpPost(remote_url);
          MultipartEntityBuilder builder = MultipartEntityBuilder.create();
          builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
          builder.addTextBody("filename", fileName);// 类似浏览器表单提交，对应input的name和value
          HttpEntity entity = builder.build();
          httpPost.setEntity(entity);
          httpPost.setHeader("Authorization",token);
          HttpResponse response = httpClient.execute(httpPost);// 执行提交
          HttpEntity responseEntity = response.getEntity();
          if (responseEntity != null) {
              // 将响应内容转换为字符串
              result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
              rs = GsonUtil.GsonToBean(result, ResultObject.class);
          }
      } catch (IOException e) {
          e.printStackTrace();
      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          try {
              httpClient.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      return rs;
  }
  
  @RequestMapping(value="/{date}/{fileName:.*}",method=RequestMethod.GET)
  @ApiImplicitParams(
			 { @ApiImplicitParam(name = "Authorization", value = "token值", required = true, dataType = "String", paramType = "header")
			   })
  public void downLoadFile(@PathVariable("fileName")String fileName,@PathVariable("date")String date,HttpServletRequest request,HttpServletResponse response){
	   ResultObject rs = ResultObject.getFailResult("上传文件失败");
	   String token = request.getHeader("Authorization");
	  
	  final String remote_url = apiUrl+"/file/"+date+"/"+fileName;// 第三方服务器请求地址
      CloseableHttpClient httpClient = HttpClients.createDefault();
      String result = "";
      ServletOutputStream outputStream =null;
      try {
          HttpPost httpPost = new HttpPost(remote_url);
          MultipartEntityBuilder builder = MultipartEntityBuilder.create();
          httpPost.setHeader("Authorization",token);
          HttpResponse resp = httpClient.execute(httpPost);// 执行提交
          HttpEntity responseEntity = resp.getEntity();
          Header[] allHeaders = resp.getAllHeaders();
          if (responseEntity != null) {
              // 将响应内容转换为字符串 
        	  Header contentType = responseEntity.getContentType();
        	  String value = contentType.getValue();
        	  for(Header head:allHeaders){
        		  
        		  response.setHeader(head.getName(), head.getValue());// 设置在下载框默认显示的文件名  
        	  }
              
              // 读出文件到response  
              // 这里是先需要把要把文件内容先读到缓冲区  
              // 再把缓冲区的内容写到response的输出流供用户下载  
              outputStream = response.getOutputStream();
              responseEntity.writeTo(outputStream);   
          }
      } catch (IOException e) {
          e.printStackTrace();
      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          try {
        	  outputStream.flush();  
              outputStream.close();  
              httpClient.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }
    
    
}
*/