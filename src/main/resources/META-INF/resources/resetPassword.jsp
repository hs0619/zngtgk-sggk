<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page language="java"
         import="com.baosight.iplat4j.core.FrameworkInfo" %>
<%@ page language="java"
	import="com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext"%>
<%@ page language="java"
	import="com.baosight.iplat4j.core.resource.Resources"%>
<%@ page language="java" import="com.baosight.iplat4j.security.sso.SSOCredential" %>
<%@ page language="java" %>

<%
	String username = request.getParameter("p_username");
	String cred = request.getParameter("p_password");
	String authen = request.getParameter("p_authen");

	boolean valid = false;

	if (username != null && cred != null & authen != null) {
		SSOCredential credentialChecker = (SSOCredential) PlatApplicationContext
				.getBean("PwdReset");
		
		try {
			valid = credentialChecker.validateCredential(cred,
					username);
		} catch (Exception e) {
			valid = false;
		}
		
	}
	
	String host = Resources.getValue("iPlatConfig", "serviceHostAddr");
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>忘记密码-重新设置</title>
<style type="text/css">
body,td,th {
	font: 12px Arial, Helvetica, sans-serif;
	line-height: 162%;
}

#wrapper {
	width: 1000px;
	margin: 0 auto;
}

#header {
	background: transparent url('banner.png') 0px 0px no-repeat;
	height: 70px;
}

p {
	padding: 0 5px;
	color: #999;
}

#info {
	color: #D00;
	height: 15px;
}

h1 {
	word-wrap: break-word;
	display: block;
	font-size: 14px;
	font-weight: bold;
	color: #494949;
	margin: 0;
	padding: 0 0 15px 0;
	line-height: 1.1;
}

.item {
	margin: 15px 0;
}

label {
	display: inline-block;
	float: left;
	margin-right: 15px;
	text-align: right;
	width: 60px;
	font-size: 12px;
	vertical-align: middle;
}

.basic-input {
	width: 200px;
	height: 18px;
	font-size: 12px;
	border: 1px solid #C9C9C9;
}

input {
	margin-right: 3px;
	vertical-align: middle;
}

.btn-submit {
	cursor: pointer;
	font-size: 12px;
	padding: 2px 6px;
	background: #FAEF9B;
	color: #625804;
	border-right: 1px solid #BDB682;
	border-bottom: 1px solid #BDB682;
	border-left: 1px solid white;
	border-top: 1px solid white;
}

#footer {
	color: #999;
	padding-top: 6px;
	margin-top: 20px;
	overflow: auto;
	zoom: 1;
	border-top: 1px dashed #DDD;
}

.readonly {
	border: 0px;
}

.prompt {
	font-size: 12px;
	margin-left: 15px;
	vertical-align: middle;
}

#redirectInfo {
	width: 500px;
	margin: 0 auto;
	text-align: center;
	padding: 20px;
	border: 1px solid blue;
}

.fright {
	float: right;
}

a {
	color: #0096FF;
}

a:link {
	color: #0096FF;
	text-decoration: none;
}
</style>
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
</head>
<body>
<div id="wrapper">
<div id="header"></div>
<h1>设置密码</h1>
<div class="wrapper">
<div>
<p id="desc">请重新设置您的密码</p>
<p id="info"></p>
</div>
<form id="rpform" name="rpform" method="post" action="resetpassword"><input
	type="hidden" id="p_password" value="${param.p_password}" /> <input
	type="hidden" id="p_authen" value="${param.p_authen}" />
<div class="item extra-tips"><label>用户名</label> <input
	id="p_username" name="p_username" class="basic-input readonly"
	readonly="readonly" maxlength="20" type="text"
	value="${param.p_username}" /></div>
<div class="item extra-tips"><label>新口令</label> <input
	id="password" name="password" class="basic-input" tabindex="1"
	maxlength="40" type="password" /><span class="prompt">密码强度</span><span
	id="passwordStrength" class="prompt"></span></div>
<div class="item extra-tips"><label>确认新口令</label> <input
	id="repassword" name="repassword" class="basic-input" maxlength="40"
	tabindex="2" value="" type="password" /><span id="repasswordPrompt"
	class="prompt"></span></div>
<div class="item-submit"><label>&nbsp;</label> <input id="btn-submit"
	class="btn-submit" type="button" value="修改密码" /></div>
</form>
<div id="footer"><span id="icp" class="fleft gray-link">©
2011 上海宝信软件股份有限公司</span><span class="fright"><a href="login.jsp" title="转到登录页">&lt;登录&gt;</a></span></div>
</div>
</div>
<script type="text/javascript">
	var times=55;

	$('#btn-submit').click(function() {
		if(checkInput()!=true) {
			return false;
		} else {
			var eiInfo = new EiInfo();
			eiInfo.set("p_password", $("#p_password").val());
			eiInfo.set("p_authen", $("#p_authen").val());
			eiInfo.set("p_username", $("#p_username").val());
			eiInfo.set("password", $("#password").val());
			eiInfo.set("repassword", $("#repassword").val());
			
			$("#info").html("正在处理中...");
			
			EiCommunicator.send( "EP10", "resetPassword" , eiInfo, ajax_callback, false );
		}
	});

	function checkInput() {
		var password = $("#password").val();
		var repassword = $("#repassword").val();
		if (password != repassword) {
			$("#repasswordPrompt").text("密码不一致");
			$("#info").text("密码不一致!");
			return false;
		} else {
			$("#repasswordPrompt").text("");
		}
		if(password=="") {
			$("#info").text("密码不能为空!");
			return false;
		}
		return true;
	}
	
	$(document).ready(function() {
		if(!<%=valid%>) {
							$("#wrapper").hide();
							$('body')
									.append(
											"<div id='redirectInfo'>对不起，认证信息不正确或已失效！<br/>系统<span id='dingshi'>"+times+"</span>秒钟后将转向登录页...<br/><a href='<%=host%>'>直接进入登录页</a></div>");
							var timesTra = window.setInterval("timeInterval()",
									1000);
						}
					});
	
	var ajax_callback = 
	{
		onSuccess : 
    		function(eiInfo)
			{
				$("#info").html(eiInfo.getMsg());
    		},
  		onFail: 
    		function(eMsg) 
			{
  				$("#info").html(eMsg);
			}
	}; 	

	/*
	function showRequest() {
		$("#info").html("正在处理中...");
	}

	function showResponse(responseXML, statusText) {
		$("#info").html($(responseXML).find('message').text());
	}
	*/
	function timeInterval() {
		times--;
		document.getElementById("dingshi").innerHTML = times;
		if (times == 0) {
			window.top.location.href = '<%=host%>';
		}
	}

	$("#password").keyup(function() {
		checkPasswordStength();
	});

	$("#password").blur(function() {
		checkPasswordStength();
	});

	function checkPasswordStength() {
		var s = $("#password").val();

		var ls = -1;

		if (s.match(/[a-z]/ig)) {
			ls++;
		}
		if (s.match(/[0-9]/ig)) {
			ls++;
		}
		if (s.match(/(.[^a-z0-9])/ig)) {
			ls++;
		}
		if (ls > 0 && s.length < 6) {
			ls--;
		}

		switch (ls) {
		case 0:
			$("#passwordStrength").text("弱");
			$("#passwordStrength").css("color", "red");
			break;
		case 1:
			$("#passwordStrength").text("中");
			$("#passwordStrength").css("color", "orange");
			break;
		case 2:
			$("#passwordStrength").text("强");
			$("#passwordStrength").css("color", "green");
			break;
		default:
			$("#passwordStrength").text("");
		}
	}

//	$("#repassword").bind('focusout', function() {
//		var password = $("#password").val();
//		var repassword = $("#repassword").val();
//		if (password != repassword) {
//			$("#repasswordPrompt").text("密码不一致");
//		} else {
//			$("#repasswordPrompt").text("");
//		}
//	});
</script>
</body>
</html>