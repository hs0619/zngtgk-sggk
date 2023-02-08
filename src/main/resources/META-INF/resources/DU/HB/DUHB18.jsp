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
					<EF:EFInput ename="inqu_status-0-deviceid" cname="产污设施编号"  colWidth="3" />
					<EF:EFInput ename="inqu_status-0-devicename" cname="产污设施名称" colWidth="3" />
					<EF:EFSelect ename="inqu_status-0-outtype" cname="排放形式"  colWidth="3">
						<EF:EFOption label="全部" value="" />
						<EF:EFOption label="有组织" value="有组织" />
						<EF:EFOption label="无组织" value="无组织" />
					</EF:EFSelect>
				</div>
				<div class="row">
					<EF:EFInput ename="inqu_status-0-portid" cname="排放口编号" colWidth="3"/>
					<EF:EFInput ename="inqu_status-0-portname" cname="排放口名称" colWidth="3"/>
				</div>
			</EF:EFRegion>
			<EF:EFRegion id="result" title="记录集" fitHeight="true">
				<EF:EFGrid blockId="result" autoDraw="false" sort="single" rowNo="true" height="502" 
						serviceName="DUHB18" queryMethod="queryinfo">
					<EF:EFColumn ename="envdeviceid" cname="设施ID" hidden="true" enable="false" />
					<EF:EFColumn ename="operation" cname="操作" enable="false" locked="true" />
					<EF:EFColumn ename="deviceid" cname="产污设施编号" enable="false" />
					<EF:EFColumn ename="devicename" cname="产污设施名称" enable="false" />
					<EF:EFColumn ename="sourcename" cname="对应产污环节名称" enable="false" />
					<EF:EFColumn ename="factorname" cname="污染物种类" enable="false" />
					<EF:EFColumn ename="outtype" cname="排放形式" enable="false" />
					<EF:EFColumn ename="other" cname="其他信息" enable="false" />
					<EF:EFColumn ename="facilityinfo" cname="污染防治设施" enable="false" />
					<EF:EFColumn ename="processid" cname="污染防治设施编号" enable="false" width="200"/>
					<EF:EFColumn ename="processname" cname="污染防治设施名称" enable="false" width="200"/>
					<EF:EFColumn ename="portinfo" cname="排口信息" enable="false" />
					<EF:EFColumn ename="portid" cname="排放口编号" enable="false" />
					<EF:EFColumn ename="portname" cname="排放口名称" enable="false" />
					<EF:EFColumn ename="description" cname="备注" enable="false" />
				</EF:EFGrid>
				<EF:EFWindow id="showED"  refresh="true">
				</EF:EFWindow>
				<EF:EFWindow id="showFI" width="90%" height="90%" refresh="true">
				</EF:EFWindow>
				<EF:EFWindow id="showDCP" width="90%" height="90%" refresh="true">
				</EF:EFWindow>
			</EF:EFRegion>

		</div>
	</div>

</EF:EFPage>