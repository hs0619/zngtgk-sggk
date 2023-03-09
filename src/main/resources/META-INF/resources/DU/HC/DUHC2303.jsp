<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage title="">
	<EF:EFRegion id="inqu" title="查询条件" type="query"
		efRegionShowClear="true" efRegionSave="true">
		<div class="row" >
			<EF:EFSelect ename="inqu_status-0-departmentid" cname="厂部"  colWidth="3" >
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-plantype" cname="监测类别" colWidth="3">
				<EF:EFOption label="废气（计划内）" value="01-1" />
				<EF:EFOption label="废气（计划外）" value="01-2" />
				<EF:EFOption label="废水（计划内）" value="02-1" />
				<EF:EFOption label="废水（计划外）" value="02-2" />
				<EF:EFOption label="噪声（计划内）" value="03-1" />
				<EF:EFOption label="噪声（计划外）" value="03-2" />
				<EF:EFOption label="无组织（计划内）" value="06-1" />
				<EF:EFOption label="无组织（计划外）" value="06-2" />
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-siteid" cname="监测点"  colWidth="4">
			</EF:EFSelect>
		</div>
		<div class="row" >
			<EF:EFDatePicker ename="inqu_status-0-starttime" cname="开始时间"
				format="yyyy-MM-dd" colWidth="3" />
			<EF:EFDatePicker ename="inqu_status-0-endtime" cname="结束时间"
				format="yyyy-MM-dd" colWidth="3" />
		</div>
	</EF:EFRegion>
	 <EF:EFRegion id="artificial" title="记录集"> 
	         <div title="人工监测数据汇总">
                   <div class="k-content" style="overflow: auto">
		            <div id="ef_grid_ardata"></div>
		        </div>
             </div>
	 </EF:EFRegion>
	 <EF:EFWindow id="config" url="${ctx}/web/DUHC230301"
				lazyload="true" width="80%" height="80%" refresh="true">
			</EF:EFWindow>
	 <EF:EFWindow id="export" url="${ctx}/web/DUHC230302"
				lazyload="true" width="95%" height="95%" refresh="true">
			</EF:EFWindow>
</EF:EFPage>