<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage title="报警人信息配置">

	<EF:EFRegion id="inqu" title="查询条件" type="query"
		efRegionShowClear="true" efRegionSave="true">

		<div class="row">
			<div class="col-xs-1" style="margin-top:5px">
			</div>
			<div class="col-xs-9" style="margin-top:5px">
				<EF:EFInput ename="inqu_status-0-alarmPersonLoginname" cname="人员ID" colWidth="4" />
				<EF:EFInput ename="inqu_status-0-alarmPersonName" cname="人员姓名" colWidth="4" />
				<EF:EFSelect ename="inqu_status-0-departmentid" cname="所属部门" colWidth="4">
					<EF:EFOption label="全部" value="" />
					<EF:EFOptions blockId="companyBlock" textField="departmentName" valueField="departmentid"/>
				</EF:EFSelect>
			</div>
			<div class="col-xs-2" style="margin-top:5px">
			</div>
		</div>	
	</EF:EFRegion>
	<EF:EFRegion id="result"  title="记录集">	
		<EF:EFGrid blockId="result"   autoDraw="false" sort="single" checkMode="multiple, cell">
			<EF:EFColumn ename="alarmPersonIdentity" cname="报警人id" hidden="true" />	
			<EF:EFColumn ename="alarmPersonLoginname" cname="ID" enable="false" />
			<EF:EFColumn ename="alarmPersonName" cname="姓名" enable="false" />	
			<EF:EFColumn ename="departmentName" cname="所属部门" enable="false" />	
			<EF:EFColumn ename="phone" cname="电话号码" enable="false" />
			<EF:EFColumn ename="monitorType" cname="监控类型" enable="false" />
			<EF:EFColumn ename="alarmStartTime" cname="发送报警开始时间" enable="false" />
			<EF:EFColumn ename="alarmEndTime" cname="发送报警结束时间" enable="false" />
			<EF:EFColumn ename="status" cname="使用状态" enable="false" />
			
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="insertOrUpdate" url="${ctx}/web/DUHB2501" lazyload="true"
		width="50%" height="80%" refresh="true">
	</EF:EFWindow>
</EF:EFPage>