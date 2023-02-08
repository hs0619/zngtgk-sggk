<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" type="query"
		efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-siteid" cname="监测点ID" colWidth="4"
				disabled="true" />
			<EF:EFInput ename="inqu_status-0-sitename" cname="监测点名称" colWidth="4"
				disabled="true" />
		</div>
	</EF:EFRegion>
	<div class="row">
		<div class="col-md-6">
			<EF:EFRegion id="human" title="人工监测因子">
				<EF:EFGrid blockId="human" autoDraw="false" showCount="false"  sort="single"
					queryMethod="queryHuman" deleteMethod="deleteHuman"
					insertMethod="insertHuman" updateMethod="updateHuman">
					<EF:EFColumn ename="siteid" cname="监测点ID" enable="false"
						hidden="true" />
					<EF:EFColumn ename="type" cname="类别" enable="false" hidden="true" />
					<EF:EFComboColumn ename="factorid" cname="污染因子" blockName="factors"
						labelProperty="factorname" valueProperty="factorid"
						textField="factorname" valueField="factorid" readonly="true"
						required="true" />
					<EF:EFColumn ename="description" cname="描述" />
					<EF:EFComboColumn ename="status" cname="使用" blockName="flagList"
						labelProperty="flagname" valueProperty="flagid"
						textField="flagname" valueField="flagid" required="true"
						defaultValue="1" />
					<EF:EFComboColumn ename="usezs" cname="使用折算值" blockName="flagList"
						labelProperty="flagname" valueProperty="flagid"
						textField="flagname" valueField="flagid" defaultValue="0" />
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
		<div class="col-md-6">
			<EF:EFRegion id="online" title="在线监测因子">
				<EF:EFGrid blockId="online" autoDraw="false" showCount="false"  sort="single"
					queryMethod="queryOnline" deleteMethod="deleteOnline"
					insertMethod="insertOnline" updateMethod="updateOnline">
					<EF:EFColumn ename="siteid" cname="监测点ID" enable="false"
						hidden="true" />
					<EF:EFColumn ename="type" cname="类别" enable="false" hidden="true" />
					<EF:EFComboColumn ename="factorid" cname="污染因子" blockName="factors"
						labelProperty="factorname" valueProperty="factorid"
						textField="factorname" valueField="factorid" readonly="true"
						required="true" />
					<EF:EFColumn ename="description" cname="描述" />
					<EF:EFComboColumn ename="status" cname="使用" blockName="flagList"
						labelProperty="flagname" valueProperty="flagid"
						textField="flagname" valueField="flagid" required="true"
						defaultValue="1" />
					<EF:EFComboColumn ename="usezs" cname="使用折算值" blockName="flagList"
						labelProperty="flagname" valueProperty="flagid"
						textField="flagname" valueField="flagid" 
						defaultValue="0" />
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
	</div>
</EF:EFPage>
