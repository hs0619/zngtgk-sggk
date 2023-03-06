<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.baosight.iplat4j.core.FrameworkInfo" %>
<HTML>
<head><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>错误信息</title>
	<link href="./EF/EF.css" rel="stylesheet" type="text/css"/>
	<%
	String domain = FrameworkInfo.getProjectAppTopDomain();
	if (domain != null && domain.startsWith(".")) {
		domain = domain.substring(1);
	%>
	<script type="text/javascript">
	try {
		document.domain='<%= domain %>';
	} catch (ex) {
		alert('model not valid[<%= domain %>]');
	}
	</script>
	<%
	}
	%>
	<script type="text/javascript" src="./EF/iplat-ui-2.0.js"></script>
</HEAD>
<body>
<div id="ef_region_iplat" title="异常描述" width="300px">
	<div width="300px" style="align:center;">
		<table>	  
		  <tr>
		    <td nowrap>不能重复登录！</td>
		  </tr>
		  <tr>
		    <td nowrap>请注销已登录用户[<%=session.getAttribute("loginname")%>]!</td>
		  </tr>	  
		</table>
	</div>	
</div>
</body>
<script type="text/javascript">

	efregion.show("ef_region_iplat");
	var ef_region_buttonbar = new efbuttonbar();
	ef_region_buttonbar.paint("ef_region_iplat");
</script>
</html>