<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<div class="row">
		<div class="col-md-12">
			<EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
				<div class="row">
					<EF:EFSelect ename="inqu_status-0-departmentid"  cname="厂部" colWidth="4" index="0">
						<EF:EFOptions blockId="departList" textField="departmentName" valueField="departmentId" />
					</EF:EFSelect>
					<EF:EFSelect ename="inqu_status-0-procedureid"  cname="工序" colWidth="4" >
						<EF:EFOption label="全部" value="" />
						<EF:EFOptions blockId="procedureList" textField="departmentName"  valueField="departmentId" />
					</EF:EFSelect>
				</div>
				<div class="row">
					<EF:EFInput ename="inqu_status-0-facilitycode" cname="污染防治设施编号"
						colWidth="4" />
					<EF:EFInput ename="inqu_status-0-facilityname" cname="污染防治设施名称"
						colWidth="4" />
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="记录集" fitHeight="true">
				<EF:EFGrid blockId="result" autoDraw="false" sort="single" rowNo="true">
					<EF:EFColumn ename="facilityid" cname="设施ID" hidden="true" enable="false" />
					<EF:EFColumn ename="operation" cname="操作" width="90" enable="false" locked="true" />
					<EF:EFColumn ename="deviceInfo" cname="产污设施" enable="false" />
					<EF:EFComboColumn ename="departmentid" cname="厂部"
						blockName="departList" labelProperty="departmentName"
						valueProperty="departmentId" textField="departmentName"
						valueField="departmentId" />
					<EF:EFComboColumn ename="procedureid" cname="工序"
						blockName="procedureList" labelProperty="departmentName"
						valueProperty="departmentId" textField="departmentName"
						valueField="departmentId" />
					<EF:EFColumn ename="facilitycode" cname="污染防治设施编号" width="200" enable="false" />
					<EF:EFColumn ename="facilityname" cname="污染防治设施名称" width="200"	enable="false" />
					<EF:EFColumn ename="mark" cname="备注" width="300"	enable="false" />
					<EF:EFColumn ename="pollutantname" cname="污染物名称" enable="false" />
					<EF:EFColumn ename="numbers" cname="台（套）" enable="false" />
					<EF:EFColumn ename="handability" cname="处理风量（m3/h）" enable="false" />
					<EF:EFColumn ename="runtime" cname="投运时间" enable="false" />
					<EF:EFColumn ename="devicecode" cname="产污设施编号" enable="false" />
					<EF:EFColumn ename="devicenames" cname="产污设施名称" enable="false" />
					<EF:EFColumn ename="devicemodel" cname="风机型号" enable="false" />
					<EF:EFColumn ename="motormodel" cname="电机型号/容量" enable="false" />
				</EF:EFGrid>
				<EF:EFWindow id="showFI" width="80%" height="80%" refresh="true">
				</EF:EFWindow>
				<EF:EFWindow id="showSBI" width="80%" height="80%" refresh="true">
				</EF:EFWindow>
			</EF:EFRegion>

		</div>
	</div>

</EF:EFPage>