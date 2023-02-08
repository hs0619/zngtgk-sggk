<%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 2019/12/5
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.*" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.net.URLEncoder" %>
<%
    String path = request.getParameter("path");
    path = URLDecoder.decode(path, "UTF-8");
    File file = new File(path);
    if (file.exists()) {
        InputStream in = new FileInputStream(file);
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename=" +
                URLEncoder.encode(file.getName(), "UTF-8"));
        byte[] buff = new byte[1024];
        //所读取的内容使用n来接收
        int n;
        //当没有读取完时,继续读取,循环
        while ((n = in.read(buff)) != -1) {
            //将字节数组的数据全部写入到输出流中
            outputStream.write(buff, 0, n);
        }
        //强制将缓存区的数据进行输出
        outputStream.flush();
        //关流
        outputStream.close();
        in.close();
    } else {
        response.getWriter().println("文件不存在!");
    }
    out.clear();
    out=pageContext.pushBody();
%>
<html>
<body>
</body>
</html>
