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
					<EF:EFInput ename="inqu_status-0-portid" cname="排口编号" colWidth="4"/>
				</div>
				<div class="row">
					<EF:EFInput ename="inqu_status-0-facilitycode" cname="污染防治设施编号"
						colWidth="4" />
					<EF:EFInput ename="inqu_status-0-facilityname" cname="污染防治设施名称"
						colWidth="4" />
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="记录集" fitHeight="true">
				<EF:EFGrid blockId="result" autoDraw="false" sort="single">
					<EF:EFColumn ename="facilityid" cname="设施ID" hidden="true"
						enable="false" />
					<EF:EFColumn ename="facilitycode" cname="污染防治设施编号" enable="false" />
					<EF:EFColumn ename="facilityname" cname="污染防治设施名称" width="220" enable="false" />
					<EF:EFColumn ename="mark" cname="备注" enable="false" />
					<EF:EFColumn ename="origin" cname="污水来源" enable="false" />
					<EF:EFColumn ename="handmethod" cname="处理方法" enable="false" />
					<EF:EFColumn ename="numbers" cname="台（套）" enable="false" />
					<EF:EFColumn ename="handability" cname="设计处理能力（m3/h）" enable="false" />
					<EF:EFColumn ename="shandnum" cname="处理量（m3/h）" enable="false" />
					<EF:EFColumn ename="runtime" cname="投运时间" enable="false" />
					<EF:EFColumn ename="handfactors" cname="处理因子" enable="false" />
					<EF:EFColumn ename="devicecode" cname="设备编码" enable="false" />
					<EF:EFColumn ename="devicemodel" cname="水泵型号/容量" enable="false" />
					<EF:EFColumn ename="motormodel" cname="电机型号/容量" enable="false" />
					<EF:EFColumn ename="controllermodel" cname="变频器型号/容量"
						enable="false" />
					<EF:EFColumn ename="situation" cname="控制系统情况" enable="false" />

				</EF:EFGrid>
			</EF:EFRegion>

		</div>
	</div>

</EF:EFPage>