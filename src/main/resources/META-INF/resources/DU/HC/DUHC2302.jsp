<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/js/common/common.js"></script>
<EF:EFPage title="人工监测计划管理" >
	<EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
		<div class="row" id="inqu1">
			<EF:EFDatePicker ename="inqu_status-0-clockyear" cname="年份" format="yyyy" colWidth="3">
			</EF:EFDatePicker>
			<EF:EFSelect ename="inqu_status-0-plantype"  cname="计划类型" colWidth="3">
				<EF:EFOption label="全部" value="%" />
				<EF:EFOption label="计划内" value="1" />
				<EF:EFOption label="计划外" value="0" />
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-status" cname="是否在用" colWidth="4">
				<EF:EFOption label="全部" value="%" />
				<EF:EFOption label="是" value="1" />
				<EF:EFOption label="否" value="0" />
			</EF:EFSelect>
		</div>
		<div class="row" id="inqu2">
			<EF:EFSelect ename="inqu_status-0-monitorid" cname="监测类型"  colWidth="3">
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-departmentid" cname="厂部"  colWidth="3">
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-siteid" cname="监测点名称"  colWidth="4" filter="contains">
			</EF:EFSelect>
		</div>

	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="no" sort="single" rowNo="true" showCount="false">
			<EF:EFColumn ename="planid" cname="计划编号" hidden="true" enable="false" />
			<EF:EFColumn ename="monitorid" cname="监测类型ID" hidden="true" enable="false" />
			<EF:EFColumn ename="departmentid" cname="厂部ID" hidden="true" enable="false" />
			<EF:EFColumn ename="siteid" cname="监测点id" hidden="true" enable="false" />
			<EF:EFColumn ename="portid" cname="排口id" hidden="true" enable="portid" />
			<EF:EFColumn ename="licenserate" cname="监测频率" hidden="true" enable="false" />
			<EF:EFColumn ename="licensecount" cname="监测频次" hidden="true" enable="false" />
			<EF:EFColumn ename="clockyear" cname="年度" enable="false" width="70"/>
			<EF:EFColumn ename="departmentname" cname="厂部" enable="false" width="120"/>
			<EF:EFColumn ename="sitename" cname="监测点名称" enable="false" width="180"/>
			<EF:EFColumn ename="planname" cname="计划名称" enable="false" width="400"/>
			<EF:EFColumn ename="operationedit" cname="计划编辑" enable="false" locked="false" />
			<EF:EFColumn ename="operation" cname="因子配置" enable="false" locked="false" />
			<EF:EFComboColumn ename="plantype" cname="计划类型" enable="false" blockName="plantype" width="95"
				textField="plantypename" valueField="plantypeid"/>
			<EF:EFComboColumn ename="iscompare" cname="比对" enable="false" blockName="iscompare" width="95"
				textField="iscomparename" valueField="iscompareid"/>
			<EF:EFComboColumn ename="status" cname="使用状态" enable="false" blockName="status" width="95"
				textField="statusname" valueField="statusid"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="showHM"  width="90%" height="90%" refresh="true">
	</EF:EFWindow>
</EF:EFPage>