<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">

<title>哎呀，密码忘记了</title>
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
<script type="text/javascript" src="EF/EF.js"></script>
<script type="text/javascript" src="EF/jQuery/jquery.js"></script>
</head>
<body>
<div id="wrapper">
<div id="header"></div>
<h1>发送重新设置密码邮件</h1>
<div class="article">
<div>
<p id="desc">填写好您的工号和邮箱后，请点击“发送邮件”按钮，系统将会向您的邮箱发送一个重置密码页面的链接</p>
<p id="info"></p>
</div>
<div class="item extra-tips"><label>用户名</label> <input
	id="workNumber" name="workNumber" type="text" class="basic-input"
	tabindex="1" maxlength="30" /></div>
<div class="item extra-tips"><label>邮箱</label> <input id="email"
	name="email" type="text" class="basic-input" maxlength="30"
	tabindex="2" value="" /></div>
<div class="item-submit"><label>&nbsp;</label><input
	class="btn-submit" id="btn-submit" type="button" value="发送邮件" /></div>
</div>
<div id="footer"><span id="icp" class="fleft gray-link">©
2011 上海宝信软件股份有限公司</span>
<span class="fright"><a href="login.jsp" title="转到登录页">&lt;登录&gt;</a></span></div>
</div>
<script type="text/javascript">


	$("#btn-submit").click(
			function() {
				if (!isInputValid()) {
					return false;
				}

				var workNumber = $("#workNumber").val();
				var email = $("#email").val();
				
				var eiInfo = new EiInfo();
				eiInfo.set("workNumber", workNumber);
				eiInfo.set("email", email);
				
				$("#info").html("正在处理中...");
				
				EiCommunicator.send( "EP10", "recoverPassword" , eiInfo, ajax_callback, false );
				
				/*
				$.ajax( {
					url : "recoverpassword?workNumber=" + workNumber
							+ "&email=" + email + "&r=" + Math.random(),
					beforeSend : function() {
						$("#info").html("正在处理中...");
					},
					success : function(msg) {
						$("#info").html(msg);
					}
				});
				*/
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
	
	function isInputValid() {
		var workNumber = $("#workNumber").val();
		var email = $("#email").val();
		if (workNumber.length < 1) {
			$("#info").html("工号不能为空");
			return false;
		}
		if (email.length < 1) {
			$("#info").html("邮箱不能为空");
			return false;
		}
		return true;
	}
</script>
</body>
</html>