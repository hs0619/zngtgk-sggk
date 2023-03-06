<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@page import="com.baosight.iplat4j.core.FrameworkInfo" %>
<%@page import="com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext" %>

<%@ taglib prefix="EF" uri="/WEB-INF/framework/tlds/EF-2.0.tld" %>

<%@ page language="java" import="com.baosight.iplat4j.ed.util.CodesetBiz" %>
<%@ page language="java" import="java.util.HashMap" %>
<%@ page language="java" import="java.util.List" %>
<%@ page language="java" %>
<%@ page language="java" %>
<%@ page language="java" %>

<html>
<head>
    <title><%=FrameworkInfo.getProjectCname() %>[<%=FrameworkInfo.getProjectTypeDesc() %>]登录界面</title>
    <link href="./login.css" rel="stylesheet" type="text/css"/>
    <%
        String domain = FrameworkInfo.getProjectAppTopDomain();
        if (domain != null && domain.startsWith(".")) {
            domain = domain.substring(1);
    %>
    <script type="text/javascript">
        try {
            document.domain = '<%= domain %>';
        } catch (ex) {
            alert('model not valid[<%= domain %>]');
        }
    </script>
    <%
        }
    %>


    <script type="text/javascript" src="./EF/iplat-ui-2.0.js"></script>
    <script type="text/javascript" src="./login.js"></script>
    <script language="JavaScript" type="text/javascript">
        self.parent.document.loginType = "popup";
    </script>
    <style>
        * html .wrap {
            position: static;
        }
    </style>

</head>

<body>
<form id="ef-LoginForm" action="login" method="POST">
    <table class="wrap" cellpadding="0" cellspacing="0">
        <tr>
            <td class="middle-wrap">
                <div class="middle">
                    <div class="middle-top">
                        <table>
                            <tr>
                                <td>
                                    <img class="theme-img" src="./EF/Images/login/pic.png">
                                </td>
                                <td>
                                    <table cellpadding="0" cellspacing="0" class="box">
                                        <tr>
                                            <td>
                                                <table cellpadding="0" cellspacing="0">
                                                    <tr>
                                                        <td colSpan="2">
                                                            <span id="loginTip"></span>
                                                        </td>
                                                        <td>
                                                        </td>

                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <div class="user">用户名:</div>
                                                        </td>
                                                        <td>
                                                            <div class="user">
                                                                <input id="p_username" name="p_username" type="text"
                                                                       readonly="readonly" class="lg-input"
                                                                       onclick="return false;">
                                                            </div>
                                                            <script language="JavaScript" type="text/javascript">
                                                                var topWnd = getTopWnd();
                                                                var loggedUser = topWnd.document.getElementById("userName").value;
                                                                $('#p_username').val(loggedUser);
                                                            </script>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <div class="pwd">密&nbsp;&nbsp;码:</div>
                                                        </td>
                                                        <td>
                                                            <div class="pwd">
                                                                <input id="p_password" name="p_password" type="password"
                                                                       class="lg-input" onFocus="passwordOnFocus()"
                                                                       onkeypress="keyPressLogin(event)">
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <input type="hidden" id="RELOGIN" name="RELOGIN" value="1">
                                                    </tr>
                                                    <%
                                                        boolean isInternational = FrameworkInfo.isInternational();
                                                        if (isInternational) {
                                                            CodesetBiz codeBiz = (CodesetBiz) PlatApplicationContext
                                                                    .getBean("codesetBiz");

                                                            List optionList = codeBiz.getCodeSet("iplat.locale", null);

                                                            out.print("<tr><td><div class='language'><div>语&nbsp;&nbsp;言:</div></td>");
                                                            out.print("<td><div class='language'><select name='locale' id='locale' class='languageSelect'>");

                                                            StringBuffer sBuf = new StringBuffer();
                                                            String optionValue = null;
                                                            String optionLabel = null;
                                                            for (int i = 0; i < optionList.size(); i++) {
                                                                optionValue = ((HashMap) optionList.get(i)).get("value").toString();
                                                                optionLabel = ((HashMap) optionList.get(i)).get("label").toString();
                                                                sBuf.append("<option value=\"");
                                                                sBuf.append(optionValue);


                                                                sBuf.append("\" >");

                                                                sBuf.append(optionLabel);
                                                                sBuf.append("</option>\n");
                                                            }
                                                            out.print(sBuf.toString());
                                                            out.println("</select></div>");
                                                            out.print("</td></tr>");
                                                        }
                                                    %>
                                                    <%
                                                        if (FrameworkInfo.isCaptchaEnabled()) {
                                                    %>
                                                    <tr>
                                                        <td>
                                                            <div class="id-code">验证码:</div>
                                                        </td>
                                                        <td>
                                                            <div class="id-code">
                                                                <input id="p_captcha" name="p_captcha" type="text"
                                                                       class="id-code-input"
                                                                       style="vertical-align:top;"
                                                                       onFocus="captchaOnFocus()"
                                                                       onkeypress="keyPressLogin(event)">
                                                                <img class="id-code-img" src="./captcha.jpg"
                                                                     style="vertical-align:top;cursor:pointer;"
                                                                     title="点击刷新"
                                                                     onclick="this.src='./captcha.jpg?'+(new Date()).getTime();"/>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <%
                                                        }
                                                    %>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="middle-bottom">
                        <table>
                            <tr>
                                <td>
                                    <div class="hotline-msg">
                                        运维平台热线<br>
                                        8008200220&nbsp;73145&nbsp;66680339
                                    </div>
                                </td>
                                <td>
                                    <div class="btn-box">
                                        <div id="loginbutton">
                                            <a class="buttonLink" href="#">
                                                <div id=loginbuttondiv class="lg-btn"></div>
                                             </a>
                                            <a class="buttonLink" href="DispatchAction.do?efFormEname=EP09">
                                                <div id=resetbuttondiv class="reset-btn"></div>
                                            </a></div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

