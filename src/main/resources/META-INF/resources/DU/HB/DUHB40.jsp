<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >

    <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-loginname" cname="登录账号" colWidth="4"/>
            <EF:EFInput ename="inqu_status-0-username" cname="用户姓名" colWidth="4"/>
            <EF:EFInput ename="inqu_status-0-departname" cname="厂部名称" colWidth="4"/>
        </div> 
    </EF:EFRegion>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" autoDraw="false"  sort="single">
        	<EF:EFColumn ename="loginname" cname="登录账号" enable="false" locked="true"/>
        	<EF:EFColumn ename="username" cname="用户姓名" enable="false" locked="true"/>
        <%-- 	<EF:EFColumn ename="departiddesc" cname="厂部ID" enable="false"/> --%>
        	<EF:EFComboColumn ename="departid" cname="厂部名称" blockName="departList" labelProperty="departmentName" valueProperty="departmentId" textField="departmentName" valueField="departmentId" />
        </EF:EFGrid> 
    </EF:EFRegion>
</EF:EFPage>