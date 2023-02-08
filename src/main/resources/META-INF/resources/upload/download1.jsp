<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="GBK"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/js/export.js"></script>
<% 
	request.setCharacterEncoding("GBK");
	response.setContentType("text/html;charset=GBK");
	String excelName = request.getParameter("excelName");
	// 设置响应头和下载保存的文件名
	response.reset();
	response.setContentType("application/x-msdownload");       //windows
	response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(excelName.getBytes("gb2312"),"iso8859-1") + "\"");      
	
	//获取下载路径
	String reportPath = request.getParameter("reportPath");
	
	
	//新建文件输入输出流
	OutputStream output = null;
	FileInputStream fis = null;
	try{
	  //新建File对象
	  File f = new File(reportPath);
	  //新建文件输入输出流对象
	  output = response.getOutputStream();
	  //response.reset();
	  fis = new FileInputStream(f);
	  //设置每次写入缓存大小
	  byte[] b = new byte[(int)f.length()];
	  //out.print(f.length());
	  //把输出流写入客户端
	  int i = 0;
	  while((i = fis.read(b)) > 0){
	    output.write(b, 0, i);
	  }
	  output.flush();
	}
	catch(Exception e){
	  e.printStackTrace();
	}
	finally{
	  if(fis != null){
	    fis.close();
	    fis = null;
	  }
	  if(output != null){
	    output.close();
	    output = null;
	  }
	}
 
%>