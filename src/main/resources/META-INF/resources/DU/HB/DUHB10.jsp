<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >

    <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-factorid" cname="因子ID" colWidth="4"/>
            <EF:EFInput ename="inqu_status-0-factorname" cname="因子名称" colWidth="4"/>
        </div>
        <div class="row">
            <div class="col-md-12">
            <div id="butdiv" style="float:right;"></div>
            </div>
        </div> 
    </EF:EFRegion>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" autoDraw="false"  sort="single">
        	<EF:EFColumn ename="factorid" cname="因子ID" readonly="true" required="true" locked="true"/>
        	<EF:EFColumn ename="factorname" cname="因子名称" required="true" locked="true"/>
        	<EF:EFColumn ename="unit" cname="单位"/>
        	<EF:EFColumn ename="description" cname="描述" />
        	<EF:EFComboColumn ename="status" cname="状态" blockName="flagList" labelProperty="flagname" valueProperty="flagid" textField="flagname" valueField="flagid" required="true" defaultValue="1"/>
        	<%-- <EF:EFColumn ename="creator" cname="创建者" enable="false"/>
        	<EF:EFColumn ename="createdTime" cname="创建时间" enable="false" />
        	<EF:EFColumn ename="modifier" cname="修改者" enable="false"/>
        	<EF:EFColumn ename="updatedTime" cname="修改时间" enable="false" /> --%>
        </EF:EFGrid> 
    </EF:EFRegion>
</EF:EFPage>