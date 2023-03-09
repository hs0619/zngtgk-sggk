<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage >
	<EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-monitorid" cname="监测类型" colWidth="3">
				<EF:EFOption label="全部" value="" />
				<EF:EFOptions blockId="monitor" textField="monitorname" valueField="monitorid" />
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-factorname" cname="监测因子" colWidth="3"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="false" sort="setted" rowNo="false">
			<EF:EFColumn ename="edit" cname="编辑" enable="false" />
			<EF:EFComboColumn ename="monitorid" cname="监测类型"  enable="false" 
				blockName="monitor" textField="monitorname" valueField="monitorid"/>
			<EF:EFColumn ename="factorid" cname="因子id" hidden="true" enable="false" />
			<EF:EFColumn ename="factorname" cname="监测因子" enable="false" />
			<EF:EFColumn ename="unit" cname="单位" enable="false" width="145"/>
			<EF:EFComboColumn ename="isplaninner" cname="汇总页面计划内显示" enable="false" width="145"
				blockName="flag" textField="flagname" valueField="flagid"/>
			<EF:EFComboColumn ename="isplanout" cname="汇总页面计划外显示"  enable="false" width="145"
				blockName="flag" textField="flagname" valueField="flagid"/>
			<EF:EFComboColumn ename="israte" cname="兑现率页面显示" enable="false" width="145"
				blockName="flag" textField="flagname" valueField="flagid"/>
			<EF:EFComboColumn ename="iscompare" cname="比对页面显示" enable="false" width="145"
				blockName="flag" textField="flagname" valueField="flagid"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="showHM" lazyload="true" width="70%" height="70%"
		refresh="true">
	</EF:EFWindow>
</EF:EFPage>