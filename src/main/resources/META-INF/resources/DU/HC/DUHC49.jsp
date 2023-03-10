<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
#siteinfo .title-box {
	box-shadow: 0 0 1px rgba(0, 0, 0, .125), 0 1px 3px rgba(0, 0, 0, .2);
	border-radius: 0;
	background: #ddf2d3;
	margin-bottom: 5px;
	height: 2.5vw;
	line-height: 2.5vw;
	position: relative;
	color: #366b1d;
	font-size: 1.4vw;
	font-weight: bold;
	width: 100%;
	padding: 0px 10px;
}

.line {
	margin-top: 0px;
	margin-bottom: 5px;
}
</style>
<EF:EFPage>

	<%--  <link rel="stylesheet" href="${ctx}/js/tippy.js/dist/tippy.css">
    <script type="text/javascript" src="${ctx}/js/tippy.js/dist/tippy-bundle.umd.min.js"></script> --%>
	<EF:EFRegion id="inqu" title="查询条件" type="query"
		efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-monitorid" cname="类型" colWidth="3">
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-porttypeid" cname="监测点类型"  colWidth="3" index="1"></EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-departmentid" cname="厂部"  colWidth="3">
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-siteid" cname="监测点"  colWidth="3">
			</EF:EFSelect>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div id="butdiv" style="float: right;"></div>
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集" fitHeight="true">
		<link rel="stylesheet"
			href="${ctx}/js/AdminLTE/dist/css/adminlte.min.css">
		<script type="text/javascript"
			src="${ctx}/js/AdminLTE/dist/js/adminlte.min.js"></script>
		<link rel="stylesheet" href="${ctx}/js/alarm/APlayer.min.css">
		<script type="text/javascript" src="${ctx}/js/alarm/APlayer.min.js"></script>
		<link rel="stylesheet" href="${ctx}/js/alarm/lobibox.min.css">
		<script type="text/javascript" src="${ctx}/js/alarm/lobibox.min.js"></script>
		<style>
.info-box {
	margin-bottom: 0.6rem;
	padding: 0.2vw 0.4vw;
	min-height: 4.7vw;
	max-height: 4.7vw;
}

.info-box .info-box-icon {
	width: 4vw;
	height: 4vw;
	margin: auto;
	display: inline-block;
	vertical-align: bottom;
}

.info-box .info-box-content {
	padding: 1px 8px;
}
</style>
		<div id="aplayer" style="display: none;"></div>
		<div id="siteinfo"></div>
	</EF:EFRegion>

</EF:EFPage>
