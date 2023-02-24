<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage >
	<EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
			<div class="row">
				<EF:EFSelect ename="inqu_status-0-departmentid" cname="单位"  colWidth="4">
					<EF:EFOptions blockId="qdepart" textField="departmentName" valueField="departmentId" />
				</EF:EFSelect>
				<EF:EFInput ename="inqu_status-0-name" cname="姓名" colWidth="4">
				</EF:EFInput>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div id="butdiv" style="float: right;"></div>
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<EF:EFGrid blockId="result" autoDraw="false" sort="setted" rowNo="false" editHelper="true" isFloat="true">
			<EF:EFColumn ename="logicid" cname="辐射工作人员ID" enable="false" hidden="true"/>
			<EF:EFColumn ename="operation" cname="编辑"  enable="false" locked="true"/>
	        <EF:EFComboColumn ename="departmentid" cname="单位"  enable="false" blockName="qdepart"
	        	textField="departmentName" valueField="departmentId"/>
	        <EF:EFComboColumn ename="status" cname="状态"  enable="false"  
	        	blockName="status"  textField="statusname" valueField="statusid"/>
	        <EF:EFColumn ename="workerid" cname="工号" enable="false" />
			<EF:EFColumn ename="workername" cname="姓名" enable="false" />
			<EF:EFComboColumn ename="gender " cname="性别" enable="false" 
				blockName="gender"  textField="textfield" valueField="valuefield"/>
			<EF:EFColumn ename="birthdate" cname="出生日期" enable="false" />
			<EF:EFComboColumn ename="iswork" cname="是否在岗" enable="false" 
				blockName="iswork"  textField="isworkname" valueField="isworkid"/>
			<EF:EFColumn ename="graschool" cname="毕业学校" enable="false" />
			<EF:EFComboColumn ename="education" cname="学历" enable="false" 
				blockName="education"  textField="textfield" valueField="valuefield"/>
			<EF:EFColumn ename="eventtype" cname="专业" enable="false" />
			<EF:EFComboColumn ename="certificatetype" cname="证件类型" enable="false" 
				blockName="certificate"  textField="textfield" valueField="valuefield"/>
			<EF:EFColumn ename="certificatenumber" cname="证件号码" enable="false" />
			<EF:EFColumn ename="operatingpost" cname="工作岗位" enable="false" />
			<EF:EFColumn ename="deadlinedate" cname="培训证书有效期" enable="false" />
			<EF:EFColumn ename="certificateno" cname="培训/考试编号" enable="false" />
			<EF:EFColumn ename="certificatename" cname="培训机构" enable="false" />
			<EF:EFColumn ename="remark" cname="备注" enable="false" />
			 <EF:EFColumn ename="createdid" cname="创建人"  enable="false" hidden="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="showHM" url="${ctx}/web/DUHF7501" lazyload="true"
		width="80%" height="80%" refresh="true">
	</EF:EFWindow>
</EF:EFPage>