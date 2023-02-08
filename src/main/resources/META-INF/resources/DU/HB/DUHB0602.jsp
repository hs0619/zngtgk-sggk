<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<div class="row">
		<div class="col-md-12">
			<EF:EFRegion id="inqu" title="查询条件" type="query"
				efRegionShowClear="true" efRegionSave="true">
				<div class="row">
					<EF:EFSelect ename="inqu_status-0-type" cname="站点类型" colWidth="4">
                        <EF:EFOption label="全部" value="" />
						<EF:EFOption label="在线" value="01" />
						<EF:EFOption label="手工" value="02" />
					</EF:EFSelect>
					<EF:EFSelect ename="inqu_status-0-monitorid" cname="监测类型"
						colWidth="4">
						<EF:EFOption label="全部" value="" />
						<EF:EFOptions blockId="monitorList" textField="monitorname"
							valueField="monitorid" />
					</EF:EFSelect>
					<EF:EFSelect ename="inqu_status-0-status" cname="状态" colWidth="4">
						<EF:EFOption label="全部" value="" />
						<EF:EFOption label="使用" value="1" />
						<EF:EFOption label="停用" value="0" />
					</EF:EFSelect>
				</div>
				<div class="row">
					<EF:EFSelect ename="inqu_status-0-departid" cname="厂部" colWidth="4">
						<EF:EFOption label="全部" value="" />
						<EF:EFOptions blockId="departList" textField="departmentName"
							valueField="departmentId" />
					</EF:EFSelect>
					<EF:EFInput ename="inqu_status-0-sitename" cname="监测点名称"
						colWidth="4" />
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="记录集"  fitHeight="true">
				<EF:EFGrid blockId="result" autoDraw="false" sort="setted">
					<EF:EFColumn ename="siteid" cname="监测点ID" locked="true"
						enable="false" />
					<EF:EFColumn ename="sitename" cname="监测点名称" locked="true"
						sort="true" width="200"/>
					<EF:EFColumn ename="departname" cname="厂部名称" />
					<EF:EFColumn ename="procname" cname="工序名称" />
					<EF:EFComboColumn ename="isartificial" cname="是否人工"
						blockName="flagList" labelProperty="flagname"
						valueProperty="flagid" textField="flagname" valueField="flagid"
						sort="true" />
					<EF:EFComboColumn ename="isonline" cname="是否在线"
						blockName="flagList" labelProperty="flagname"
						valueProperty="flagid" textField="flagname" valueField="flagid"
						sort="true" />
					<EF:EFComboColumn ename="classifyid" cname="人工监测类型"
						blockName="monitorList" labelProperty="monitorname"
						valueProperty="monitorid" textField="monitorname"
						valueField="monitorid" />
					<EF:EFComboColumn ename="monitorid" cname="在线监测类型"
						blockName="monitorList" labelProperty="monitorname"
						valueProperty="monitorid" textField="monitorname"
						valueField="monitorid" />
					<EF:EFColumn ename="mnid" cname="MN编号" sort="true" />
					<EF:EFColumn ename="description" cname="描述" />
					<EF:EFColumn ename="sort" cname="排序" />
					<EF:EFComboColumn ename="status" cname="是否使用" blockName="flagList"
						labelProperty="flagname" valueProperty="flagid"
						textField="flagname" valueField="flagid" />

				</EF:EFGrid>
			</EF:EFRegion>

		</div>
	</div>

</EF:EFPage>