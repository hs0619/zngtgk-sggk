<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
<div class="row">
	 <EF:EFRegion id="result" title="待处理信息" fitHeight="true"> 
        <EF:EFGrid blockId="result" autoDraw="false" autoBind="false" sort="setted"  checkMode="hidden,row"  >
        	<EF:EFColumn ename="messageId" cname="消息id" hidden="true" />
        	<EF:EFColumn ename="jumpPage" cname="跳转页面名称" hidden="true" />
             <EF:EFColumn ename="remarks" cname="事件key" hidden="true" />
        	<EF:EFColumn ename="operation" cname="查看" width="40" enable="false"/>
        	<EF:EFColumn ename="title" cname="消息内容"  enable="false"/>
        </EF:EFGrid> 
    </EF:EFRegion>
    
</EF:EFPage>