<%@page import="com.sun.javafx.scene.web.Debugger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.io.*"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="org.json.simple.*"%>

<%
String status = "";//上传状态
String msg = "";//上传提示信息
String filePath = request.getParameter("filePath");//获取URL传过来的path
String maxSize = request.getParameter("maxSize");;//获取URL传过来的maxSize
HashMap<String, String> extMap = new HashMap<String, String>();
extMap.put("file", "xls,xlsx,jpg,png,doc,docx,pdf");//设置上传文件到文件夹file下，文件类型只能为doc docx...这几类
/* long maxSize = 1000000000;//设置上传的文件默认大小最大为1000000000 */
response.setContentType("text/html; charset=UTF-8");//字符编码

Map obj=new LinkedHashMap();
obj.put("name","");
obj.put("size","");
obj.put("path","");

if(filePath != null && !"".equals(filePath)){
	if(ServletFileUpload.isMultipartContent(request)){
		File uploadDir = new File(filePath);//new一个file 路径为rootPath-savePath
		if(!uploadDir.isDirectory()){
			uploadDir.mkdirs();
		}
		if(uploadDir.canWrite()){//上传目录file是否有写入的权限
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List items = upload.parseRequest(request);
			if(items.size() > 0){
				Iterator itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					String fileName = item.getName();
					if(fileName != null && !"".equals(fileName)){
						
						long fileSize = item.getSize();
						if (!item.isFormField()) {
							//检查文件大小
							if(item.getSize() > Long.parseLong(maxSize)){
								status = "7";
								msg = "文件大小超过上传限制";//上传文件大小超过限制
							}else{
								String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();//后缀名
								boolean isOk=Arrays.<String>asList(extMap.get("file").split(",")).contains(fileExt);
								if(!isOk){
									status = "8";
									msg = "文件是未允许上传的类型";//上传文件扩展名是不允许的扩展名
								}else{
									try{
										SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
										String nowtime=sdf.format(new Date());
										fileName=nowtime+fileName;
										File uploadedFile = new File(filePath, fileName);
										item.write(uploadedFile);
										obj.remove("name");
										obj.put("name",fileName);
										obj.remove("size");
										obj.put("size",fileSize);
										obj.remove("path");
										obj.put("path",filePath);
										obj.remove("nowtime");
										obj.put("nowtime",nowtime);
										status = "9";
										msg = "上传文件成功";//上传文件成功
									}catch(Exception e){
										status = "-1";
										msg = e.toString();//上传文件失败
									}
								}
							}
						}else{
							status = "6";
							msg = "不是文件上传域";//不是文件上传域
						}
					}else{
						status = "5";
						msg = "文件名为空";//文件名为空
					}
				}
			}else{
				status = "4";
				msg = "没有选中文件";//没有选中文件
			}
		}else{
			status = "3";
			msg = "目录没有写权限";//上传目录没有写权限
		}
	}else{
		status = "2";
		msg = "不是带文件上传的表单";//不是带文件上传的表单
	}
}else{
	status = "1";
	msg = "路径名称为空";//路径名称为空
}
 
obj.put("status",status);
obj.put("msg",msg);
String jsonText = JSONValue.toJSONString(obj);
response.getWriter().write(jsonText);
 %>