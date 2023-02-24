<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="放射源管理">
	<EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-departmentid" cname="放射源单位" colWidth="4">
				<EF:EFOptions blockId="qdepart" textField="departmentName" valueField="departmentId" />
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-radiatetype" cname="类别"
				serviceName="DUHF71" methodName="initLoad" resultId="qtype"
				textField="typename" valueField="typeid" colWidth="4">
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-recordname" cname="登记人"   colWidth="4"/>
		</div>
		<%--<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-beginDate" cname="登记起始日期" format="yyyy-MM-dd" colWidth="4"/>
			<EF:EFDatePicker ename="inqu_status-0-endDate" cname="登记截至日期" format="yyyy-MM-dd" colWidth="4"/>
		</div>--%>
		<div class="row">
			<div class="col-md-12">
				<div id="butdiv" style="float: right;"></div>
			</div>
		</div>
	</EF:EFRegion>
	 <EF:EFRegion id="result" title="记录集" >
	     <EF:EFGrid blockId="result" autoDraw="false"  sort="setted" rowNo="false" editHelper="true" isFloat="true">
	     	<EF:EFColumn ename="radiateid" cname="放射源ID" enable="false" hidden="true"/>
	     	<EF:EFColumn ename="operation" cname="编辑"  enable="false" locked="true"/>
             <EF:EFComboColumn ename="status" cname="状态"  enable="false" locked="true"
                               blockName="status"  textField="statusname" valueField="statusid"/>
             <EF:EFComboColumn ename="radiatetype" cname="类别"  enable="false"
                               blockName="qtype"  textField="typename" valueField="typeid"/>
	        <EF:EFComboColumn ename="departmentid" cname="放射源单位"  enable="false"  locked="true"
	        	blockName="qdepart"  textField="departmentName" valueField="departmentId"/>
             <EF:EFColumn ename="radiatename" cname="放射源名称"  enable="false"/>
             <EF:EFColumn ename="address" cname="使用位置"  enable="false"/>
	        <EF:EFColumn ename="purpose" cname="装置用途"  enable="false"/>
             <EF:EFColumn ename="nuclide" cname="核素名称"  enable="false"/>
             <EF:EFColumn ename="number" cname="数量"  enable="false"/>
	        <EF:EFColumn ename="supplier" cname="供应商"  enable="false"/>
	        <EF:EFColumn ename="prounit" cname="生产单位"  enable="false"/>
             <EF:EFColumn ename="comedate" cname="出厂日期"  enable="false"/>
             <EF:EFColumn ename="comeactivity" cname="出厂活度（Bq）"  enable="false"/>
	        <EF:EFComboColumn ename="iswork" cname="是否停用"  enable="false"
	        	blockName="use"  textField="usename" valueField="useid"/>
             <EF:EFColumn ename="radiatecode" cname="编码"  enable="false"/>
	        <EF:EFColumn ename="recorddate" cname="登记日期"  enable="false"/>
	        <EF:EFColumn ename="recordid" cname="登记人id"  enable="false" hidden="true"/>
	        <EF:EFColumn ename="recordname" cname="登记人"  enable="false"/>
	        <EF:EFColumn ename="remark" cname="备注"  enable="false"/>
	     </EF:EFGrid> 
	 </EF:EFRegion>
	 <EF:EFWindow id="showHM" url="${ctx}/web/DUHF7101" lazyload="true"
		width="80%" height="80%" refresh="true">
	</EF:EFWindow>
</EF:EFPage>