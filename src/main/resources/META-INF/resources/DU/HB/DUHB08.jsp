<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="监测类型信息">

    <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-monitorid" cname="监测类型ID" colWidth="4"/>
            <EF:EFInput ename="inqu_status-0-monitorname" cname="监测类型名称" colWidth="4"/>
        </div>
        <div class="row">
            <div class="col-md-12">
            <div id="butdiv" style="float:right;"></div>
            </div>
        </div> 
    </EF:EFRegion>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" autoDraw="false"  sort="single">
        	<EF:EFColumn ename="monitorid" cname="监测类型ID" readonly="true" required="true" locked="true"/>
        	<EF:EFColumn ename="monitorname" cname="监测类型名称" required="true" locked="true"/>
        	<EF:EFColumn ename="description" cname="描述"/>
        	<EF:EFColumn ename="sort" cname="排序编号" />
        	<EF:EFComboColumn ename="isonline" cname="是否在线" blockName="onlineList" labelProperty="onlineName" valueProperty="onlineId" 
        	textField="onlineName" valueField="onlineId" required="true" />
        	<EF:EFComboColumn ename="istotal" cname="是否统计" blockName="onlineList" labelProperty="onlineName" valueProperty="onlineId" 
        	textField="onlineName" valueField="onlineId" required="true" />
        	<EF:EFComboColumn ename="status" cname="状态" blockName="flagList" labelProperty="flagname" valueProperty="flagid"
        	 textField="flagname" valueField="flagid" required="true" />
        	<%-- <EF:EFColumn ename="creator" cname="创建者" enable="false"/>
        	<EF:EFColumn ename="createdTime" cname="创建时间" enable="false" />
        	<EF:EFColumn ename="modifier" cname="修改者" enable="false"/>
        	<EF:EFColumn ename="updatedTime" cname="修改时间" enable="false" /> --%>
        </EF:EFGrid> 
    </EF:EFRegion>
</EF:EFPage>