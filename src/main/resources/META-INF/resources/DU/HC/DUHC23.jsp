<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage >
	<EF:EFRegion id="inqu" title="查询条件" type="query"
		efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-monitorid" cname="监测类型" colWidth="3">
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-departmentid" cname="单位名称" colWidth="3">
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-siteid" resultId="site" cname="监测点" colWidth="4" filter="contains">
			</EF:EFSelect>
		</div>
		<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-startdate" cname="开始日期"
				format="yyyy-MM-dd" colWidth="3">
			</EF:EFDatePicker>
			<EF:EFDatePicker ename="inqu_status-0-enddate" cname="结束日期"
				format="yyyy-MM-dd" colWidth="3">
			</EF:EFDatePicker>
			<EF:EFSelect ename="inqu_status-0-plantype"  cname="计划类型" colWidth="4">
				<EF:EFOption label="全部" value="%" />
				<EF:EFOption label="计划内" value="1" />
				<EF:EFOption label="计划外" value="0" />
			</EF:EFSelect>
		</div>
		<%--<div class="row">
			<EF:EFSelect ename="inqu_status-0-valuetype"  cname="数据类型" colWidth="3">
				<EF:EFOption label="全部" value="%" />
				<EF:EFOption label="最大值-实测" value="max-sc" />
				<EF:EFOption label="最大值-折算" value="max-zs" />
				<EF:EFOption label="最小值-实测" value="min-sc" />
				<EF:EFOption label="最小值-折算" value="min-zs" />
				<EF:EFOption label="平均值-实测" value="avg-sc" />
				<EF:EFOption label="平均值-折算" value="avg-zs" />
			</EF:EFSelect>
		</div>--%>
		<div class="row">
			<div class="col-md-12">
				<div id="butdiv" style="float: right;"></div>
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="false" sort="setted" rowNo="true">
            <EF:EFColumn ename="departmentid" cname="厂部ID" hidden="true" enable="false" />
			<EF:EFColumn ename="siteid" cname="监测点ID" hidden="true" enable="false" />
			<EF:EFColumn ename="monitorid" cname="监测类型ID" hidden="true" enable="false" />
			<EF:EFColumn ename="factorid" cname="监测因子ID" hidden="true" enable="false" />
			<EF:EFColumn ename="operationedit" cname="编辑" enable="false" locked="true" />
			<EF:EFColumn ename="sitename" cname="监测点名称"   enable="false" width="220"/>
			<EF:EFColumn ename="planname" cname="计划名称" enable="false" width="340"/>
			<EF:EFColumn ename="factorname" cname="监测因子" enable="false" width="90"/>
			<EF:EFColumn ename="itemtime" cname="采样时间" enable="false" width="145"/>
            <EF:EFColumn ename="itemlimit" cname="限值" enable="false" width="70"/>
			<EF:EFColumn ename="itemvalue" cname="实测值" enable="false" width="70"/>
			<EF:EFColumn ename="itemvaluezs" cname="折算值" enable="false" width="70"/>
			<EF:EFColumn ename="itemunit" cname="单位" enable="false" width="70"/>
			<EF:EFColumn ename="overlimitinfo" cname="超标说明" enable="false" width="145"/>
			<EF:EFColumn ename="itemmark" cname="备注" enable="false" />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="showHM" lazyload="true" width="90%" height="90%"
		refresh="true">
	</EF:EFWindow>
</EF:EFPage>