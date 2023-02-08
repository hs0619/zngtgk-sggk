<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<EF:EFPage title="排口报警短信发送配置">
<link rel="stylesheet" type="text/css" href="${ctx}/js/css/mg_own_hb.css">
	<EF:EFRegion id="inqu" title="查询条件" type="query"
		efRegionShowClear="true" efRegionSave="true">

		<div class="row">
			<div class="col-xs-1" style="margin-top:5px">
			</div>
			<div class="col-xs-9" style="margin-top:5px">
				<EF:EFInput ename="inqu_status-0-mnid" cname="排口编号" colWidth="4" />
				<EF:EFSelect ename="inqu_status-0-departmentid" cname="所在单位" colWidth="4">
					<EF:EFOption label="全部" value="" />
					<EF:EFOptions blockId="companyBlock" textField="departmentName" valueField="departmentid"/>
				</EF:EFSelect>
			</div>
			<div class="col-xs-2" style="margin-top:5px">
			</div>
		</div>	
	</EF:EFRegion>
	<EF:EFRegion id="result"  title="记录集">
		
		<div class="row">
			<div class="col-xs-1" style="margin-top:5px">
			</div>
			<div class="col-xs-9" style="margin-top:5px">
				<EF:EFGrid blockId="result"   autoDraw="false" sort="single" checkMode="multiple, cell">
					<EF:EFColumn ename="siteid" cname="站点id" hidden="true" />	
					<EF:EFColumn ename="ISSEND" cname="是否发送短信" hidden="true" />	
		
					<EF:EFColumn ename="mnid" cname="排口编号" enable="false" />
					<EF:EFColumn ename="sitename" cname="排口名称" enable="false" />	
					
				</EF:EFGrid>
			</div>
			<div class="col-xs-2" style="margin-top:5px">
			</div>
		</div>	
	</EF:EFRegion>
</EF:EFPage>