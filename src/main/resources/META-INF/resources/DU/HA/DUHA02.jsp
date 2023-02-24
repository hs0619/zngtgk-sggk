<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
	<EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-typeid" cname="类型" colWidth="4">
                <EF:EFOption label="--请选择--" value="" />
				<EF:EFOptions blockId="type" textField="typename" valueField="typeid" />
			</EF:EFSelect>

		</div>

		<div class="row">
			<div class="col-md-12">
				<div id="butdiv" style="float: right;"></div>
			</div>
		</div>
	</EF:EFRegion>
	 <EF:EFRegion id="result" title="记录集" >
	     <EF:EFGrid blockId="result" autoDraw="false"  sort="setted" rowNo="false" editHelper="true" isFloat="true">
	     	<EF:EFColumn ename="logicid" cname="主键id" enable="false" hidden="true"/>
             <EF:EFColumn ename="typeid" cname="类型英文名称"  enable="true" readonly="true"/>
	        <EF:EFColumn ename="typename" cname="类型中文名称"  enable="true" readonly="true"/>
	        <EF:EFColumn ename="valuefield" cname="实际值"  enable="true" readonly="true"/>
	        <EF:EFColumn ename="textfield" cname="显示值"  enable="true" readonly="true"/>
             <EF:EFColumn ename="sort" cname="排序"  enable="true"/>
             <EF:EFColumn ename="status" cname="状态"  enable="true"/>
	     </EF:EFGrid> 
	 </EF:EFRegion>
</EF:EFPage>