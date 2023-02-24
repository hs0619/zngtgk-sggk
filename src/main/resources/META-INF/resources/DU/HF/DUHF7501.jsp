<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
	<EF:EFRegion id="inqu" title="编辑区域" type="query" efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<div class="col-xs-2">
				<EF:EFInput ename="inqu_status-0-logicid" cname="编号" colWidth="12" type="hidden"/>
				<EF:EFInput ename="inqu_status-0-operation" cname="操作" colWidth="12" type="hidden"/> 
				<EF:EFInput ename="inqu_status-0-status" cname="状态ID" colWidth="12"  type="hidden"/>
				
			</div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-departmentid" cname="单位" colWidth="12" 
					serviceName="DUHF7501" methodName="initLoad"
					resultId="qdepart" textField="departmentName" valueField="departmentId" required="true">
				</EF:EFSelect>
			</div>
			<div class="col-xs-4">
				<EF:EFInput ename="inqu_status-0-workerid" cname="用户工号" colWidth="12"  required="true" />
			</div>
			<div class="col-xs-2">
				
			</div>
		</div>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<EF:EFInput ename="inqu_status-0-workername" cname="姓名"  colWidth="12" required="true" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-gender" cname="性别"  colWidth="12" required="true"
                    serviceName="DUHF7501" methodName="initLoad" resultId="gender"
                    textField="textfield" valueField="valuefield" >
				</EF:EFSelect>
			</div>
			<div class="col-xs-4">
				<EF:EFDatePicker ename="inqu_status-0-birthdate" cname="出生日期" format="yyyy-MM-dd" colWidth="12" required="true">
				</EF:EFDatePicker>
			</div>
			<div class="col-xs-2"></div>
		</div>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<EF:EFInput ename="inqu_status-0-graschool" cname="毕业学校"  colWidth="12" required="true"/>
			</div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-education" cname="学历"  colWidth="12" 
					serviceName="DUHF7501" methodName="initLoad" resultId="education"
					textField="textfield" valueField="valuefield" required="true">
				</EF:EFSelect>
			</div>
			<div class="col-xs-2"></div>
		</div>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<EF:EFInput ename="inqu_status-0-eventtype" cname="专业"  colWidth="12" required="true"/>
			</div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-certificatetype" cname="证件类型"  colWidth="12" 
					serviceName="DUHF7501" methodName="initLoad" resultId="certificate"
					textField="textfield" valueField="valuefield" required="true">
				</EF:EFSelect>
			</div>
			<div class="col-xs-2"></div>
		</div>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<EF:EFInput ename="inqu_status-0-certificatenumber" cname="证件号码"  colWidth="12"  required="true"/>
			</div>
			<div class="col-xs-4">
				<EF:EFInput ename="inqu_status-0-operatingpost" cname="工作岗位"  colWidth="12" required="true"/>
			</div>
			<div class="col-xs-2"></div>
		</div>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<EF:EFDatePicker ename="inqu_status-0-deadlinedate" cname="培训证书有效期" format="yyyy-MM-dd" colWidth="12" required="true">
				</EF:EFDatePicker>
			</div>
			<div class="col-xs-4">
				<EF:EFInput ename="inqu_status-0-certificateno" cname="培训/考试编号"  colWidth="12" data-rules="positive_integer" required="true"/>
			</div>
			<div class="col-xs-2"></div>
		</div>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<EF:EFInput ename="inqu_status-0-certificatename" cname="培训机构"  colWidth="12"  />
			</div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-iswork" cname="是否在岗"  colWidth="12" required="true">
					<EF:EFOption label="在岗" value="0" />
					<EF:EFOption label="离岗" value="1" />
				</EF:EFSelect>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">	
				<EF:EFInput ename="inqu_status-0-createdman" cname="录入人"  colWidth="12" readonly="true" />
				
			</div>
			<div class="col-xs-4">
				<EF:EFInput ename="inqu_status-0-createdtime" cname="录入时间"    colWidth="12"  readonly="true">
				</EF:EFInput>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-10">	
				<EF:EFInput ename="inqu_status-0-remark" cname="备注" type="textarea" colWidth="12" />
			</div>
			<div class="col-xs-2"></div>
		</div>
		<div class="row" style="margin-top:10px;">
			<div class="col-xs-12">
				<div id="butdiv" style="display: block;text-align:center;">
				</div>
			</div>
		</div>
		<%--<div class="row" style="margin-top:10px;">
			<div class="col-xs-12">
				<div id="remark"   style="float: left;  font-size: 14.6px; color: #b37400;">
					注：提交后只能修改“培训证书有效期”、“培训/考试编号”、“培训机构”、“是否在岗”！
				</div>
			</div>
		</div>--%>
	</EF:EFRegion>
</EF:EFPage>
