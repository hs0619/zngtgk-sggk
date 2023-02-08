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
	// ������Ӧͷ�����ر�����ļ���
	response.reset();
	response.setContentType("application/x-msdownload");       //windows
	response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(excelName.getBytes("gb2312"),"iso8859-1") + "\"");      
	
	//��ȡ����·��
	String reportPath = request.getParameter("reportPath");
	
	
	//�½��ļ����������
	OutputStream output = null;
	FileInputStream fis = null;
	try{
	  //�½�File����
	  File f = new File(reportPath);
	  //�½��ļ��������������
	  output = response.getOutputStream();
	  //response.reset();
	  fis = new FileInputStream(f);
	  //����ÿ��д�뻺���С
	  byte[] b = new byte[(int)f.length()];
	  //out.print(f.length());
	  //�������д��ͻ���
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