<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage title="报警人信息明细">

	<EF:EFRegion id="inqu" title="信息完善" type="query"
		efRegionShowClear="true" efRegionSave="true">
		
		<div class="row">
			<div class="col-xs-1" style="margin-top:10px">
			</div>
			<div class="col-xs-9" style="margin-top:10px">
				<EF:EFSelect ename="inqu_status-0-alarmPersonIdentity" cname="人员ID"  colWidth="4"  required="true">
					<EF:EFOption label="--请选择--"  value="" />
					<EF:EFOptions blockId="userBlock" textField="loginName" valueField="userId"/>
				</EF:EFSelect>
			</div>
			<div class="col-xs-2" style="margin-top:10px">
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-1" style="margin-top:10px">
			</div>
			<div class="col-xs-9" style="margin-top:10px">
				<EF:EFInput ename="inqu_status-0-alarmPersonName" cname="人员姓名"  required="true" colWidth="4" />
			</div>
			<div class="col-xs-2" style="margin-top:10px">
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-1" style="margin-top:10px">
			</div>
			<div class="col-xs-9" style="margin-top:10px">
				<EF:EFInput ename="inqu_status-0-departmentName" cname="所属部门"  required="true" colWidth="4" />
			</div>
			<div class="col-xs-2" style="margin-top:10px">
			</div>
		</div>
		
		
		<div class="row">
			<div class="col-xs-1" style="margin-top:10px">
			</div>
			<div class="col-xs-9" style="margin-top:10px">
				<EF:EFInput ename="inqu_status-0-phone" cname="电话号码 "  oninput="value=value.replace(/\D/g,'')" pattern="[0-9]*"  maxLength = "11" required="true" colWidth="4" />
			</div>
			<div class="col-xs-2" style="margin-top:10px">
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-4" style="margin-top:5px;text-align:right;padding-right:0px;padding-top:7px;">
				<label>监控类型</label>
			</div>
			<div class="col-xs-7" style="margin-top:10px">
				<EF:EFInput ename="inqu_status-0-monitorType" class="monitorType" cname="实时监控"  value="1"  inline="true"   type="radio"/>
				<EF:EFInput ename="inqu_status-0-monitorType" class="monitorType" cname="小时监控"  value="2"  inline="true"   type="radio"/>
				<EF:EFInput ename="inqu_status-0-monitorType" class="monitorType" cname="日均监控"  value="3"  inline="true" checked="checked"  type="radio"/>
				<EF:EFInput ename="inqu_status-0-monitorType" class="monitorType" cname="全部"  value="99"  inline="true"   type="radio"/>
			</div>
			<div class="col-xs-1" style="margin-top:10px">
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-4" style="margin-top:5px;text-align:right;padding-right:0px;padding-top:7px;">
				<label>发送报警开始时间</label>
			</div>
			<div class="col-xs-7" style="margin-top:10px;margin-left:3px;">
				<input type="text" id="inqu_status-0-startHour" oninput="value=value.replace(/\D/g,'')" pattern="[0-9]*"  maxLength = "2" style="width:40px;" />时
				<input type="text" id="inqu_status-0-startMin" oninput="value=value.replace(/\D/g,'')" pattern="[0-9]*"  maxLength = "2" style="width:40px;" />分
			</div>
			<div class="col-xs-1" style="margin-top:10px">
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-4" style="margin-top:5px;text-align:right;padding-right:0px;padding-top:4px;">
				<label>发送报警结束时间</label>
			</div>
			
			<div class="col-xs-7" style="margin-top:10px;margin-left:3px;">
				<input type="text" id="inqu_status-0-endHour" oninput="value=value.replace(/\D/g,'')" pattern="[0-9]*"  maxLength = "2" style="width:40px;" />时
				<input type="text" id="inqu_status-0-endMin" oninput="value=value.replace(/\D/g,'')" pattern="[0-9]*"  maxLength = "2" style="width:40px;" />分
			</div>
			<div class="col-xs-1" style="margin-top:10px">
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-4" style="margin-top:5px;text-align:right;padding-right:0px;padding-top:4px;">
				<label>使用状态</label>
			</div>
			
			<div class="col-xs-7" style="margin-top:10px">	
				
				<EF:EFInput ename="inqu_status-0-status" cname="正在使用"   checked="checked"  value="1"  inline="true"   type="radio"/>
				<EF:EFInput ename="inqu_status-0-status" cname="未使用"   value="0 "  inline="true"   type="radio"/>
			</div>
			<div class="col-xs-1" style="margin-top:10px">
			</div>
		</div>
		
		<div class="row" style="margin-top:10px;">
			<EF:EFInput ename="operate" cname="操作类型"  type="hidden" />
			<EF:EFInput ename="inqu_status-0-departmentid" cname="部门id"  type="hidden" />
			<EF:EFInput ename="inqu_status-0-startHourTemp" cname="开始小时时间"  type="hidden" />
			<EF:EFInput ename="inqu_status-0-startMinTemp" cname="开始分钟时间"  type="hidden" />
			<EF:EFInput ename="inqu_status-0-endHourTemp" cname="结束小时时间"  type="hidden" />
			<EF:EFInput ename="inqu_status-0-endMinTemp" cname="结束分钟时间"  type="hidden" />
			<EF:EFInput ename="inqu_status-0-creator" cname="创建人" type="hidden" />
			<EF:EFInput ename="inqu_status-0-createdtime" cname="创建时间"  type="hidden" />
			<div class="col-md-12">
				<div id="butdiv" style="display: block;text-align:center;"></div>
			</div>
		</div>
		
	</EF:EFRegion>
</EF:EFPage>