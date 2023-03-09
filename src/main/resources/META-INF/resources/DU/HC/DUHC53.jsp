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
	height: 2.7vw;
	line-height: 2.7vw;
	position: relative;
	color: #366b1d;
	font-size: 1.5vw;
	font-weight: bold;
	width: 100%;
	padding: 0px 10px;
}

.line {
	margin-top: 0px;
	margin-bottom: 5px;
}

html {
	-webkit-text-size-adjust: none;
}
</style>
<EF:EFPage>

	<%--  <link rel="stylesheet" href="${ctx}/js/tippy.js/dist/tippy.css">
    <script type="text/javascript" src="${ctx}/js/tippy.js/dist/tippy-bundle.umd.min.js"></script> --%>
	<EF:EFRegion id="inqu" title="查询条件" type="query"
		efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-monitorid" cname="监测类型"
				colWidth="4">
				<EF:EFOptions blockId="monitor" textField="monitorname"
					valueField="monitorid" />
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-porttypeid" cname="监测点类型"
				colWidth="4">
				<EF:EFOptions blockId="porttype" textField="typename"
					valueField="typeid" />
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集" fitHeight="true">
		<link rel="stylesheet"
			href="${ctx}/js/AdminLTE/dist/css/adminlte.min.css">
		<script type="text/javascript"
			src="${ctx}/js/AdminLTE/dist/js/adminlte.min.js"></script>
		<style>
.info-box .info-box-icon {
	width: 2.2vw;
	height: 2vw;
	margin: auto;
	display: inline-block;
	vertical-align: bottom;
}

.info-box {
	min-height: 5.8vw;
	max-height: 5.8vw;
	padding: 0.1vw 0.4vw 0.5vw;
	margin-bottom: 0.4rem;
}

.info-box .info-box-content {
	padding: 0px 3px;
}

.info-box .info-box-sitename {
	display: block;
	font-size: 1.2vw;
	line-height: 1.4vw;
}

.info-box .info-box-text {
	display: inline-block;
	font-size: 1.4vw;
	margin: 0 1vw;
}

.col-md-2 {
	padding-right: 4px;
	padding-left: 4px;
}
</style>
		<div id="siteinfo"></div>
	</EF:EFRegion>

</EF:EFPage>
