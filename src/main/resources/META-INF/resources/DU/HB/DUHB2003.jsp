<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/js/css/infostyle.css">
<EF:EFPage >
	<EF:EFRegion id="inqu" title="查询条件" type="query"
				efRegionShowClear="true" efRegionSave="true" >
		<div class="row">
			<EF:EFInput ename="inqu_status-0-facilityid" type="hidden" enable="false"/>
			<EF:EFInput ename="inqu_status-0-deviceid" cname="产污设施编号"
				colWidth="4" />
			<EF:EFInput ename="inqu_status-0-devicename" cname="产污设施名称"
				colWidth="4" />
		</div>
	 	
	 </EF:EFRegion>

	<EF:EFRegion id="envdevic" title="记录集" rowNo="true">
		<EF:EFGrid blockId="envdevic" autoDraw="false" sort="single" rowNo="true" 
					checkMode="multiple,row" height="502">
			<EF:EFColumn ename="envdeviceid" cname="生产设施id" enable="false" hidden="true" />
			<EF:EFColumn ename="facilityid" cname="治理设施id" enable="false" hidden="true" />
			<EF:EFColumn ename="deviceid" cname="产污设施编号" enable="false" locked="true" />
			<EF:EFColumn ename="devicename" cname="产污设施名称" enable="false" />
			<EF:EFColumn ename="sourcename" cname="对应产污设施名称" enable="false" />
			<EF:EFColumn ename="factorname" cname="污染物种类" enable="false" />
		</EF:EFGrid>
	</EF:EFRegion>

</EF:EFPage>
