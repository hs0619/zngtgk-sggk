<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
<div class="row">
       <div class="col-md-3">
            <EF:EFTree  bindId="departmentTree" ename="departmentId" textField="text" valueField="label" hasChildren="leaf"
                           serviceName="DUHB0101" methodName="query" style="height:550px;" >
            </EF:EFTree>       
       </div>
       <div class="col-md-9">
           <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
		        <div class="row">
		            <EF:EFInput ename="inqu_status-0-parentid" cname="所属单位ID" colWidth="4" disabled="true"/> 
		            <%-- <EF:EFInput ename="inqu_status-0-parentname" cname="所属厂部" colWidth="4" /> --%>
                    <EF:EFInput ename="inqu_status-0-departmentName" cname="厂部名称" colWidth="4"/>
                </div>
			 </EF:EFRegion>
			 <EF:EFRegion id="result" title="记录集">
		        <EF:EFGrid blockId="result" autoDraw="false"  sort="setted">
		        	<EF:EFColumn ename="departmentId" cname="厂部ID" hidden="true" enable="false"/>
		        	<EF:EFColumn ename="operation" cname="操作" enable="false" locked="true"/>
		        	<EF:EFColumn ename="departmentName" cname="厂部名称" enable="false" />
		        	<EF:EFColumn ename="parentname" cname="所属单位" enable="false"/>
		        	<EF:EFComboColumn ename="type" cname="类型" blockName="typeList" 
		        		labelProperty="typename" valueProperty="typeid" textField="typename" valueField="typeid" />
		        	<EF:EFColumn ename="description" cname="描述"  enable="false"/>
		        	<EF:EFColumn ename="level" cname="等级"  enable="false"/>
		        	<EF:EFColumn ename="totalplan" cname="标志位"  enable="false"/>
		        	<EF:EFColumn ename="sort" cname="排序" enable="false"/>
		        	<EF:EFComboColumn ename="status" cname="是否启用" blockName="flagList" 
		        		labelProperty="flagname" valueProperty="flagid" textField="flagname" valueField="flagid" />
		        </EF:EFGrid> 
		        <EF:EFWindow id="showDP" width="90%" height="90%" refresh="true">
                </EF:EFWindow>
		    </EF:EFRegion>

       </div>
   </div>
    
</EF:EFPage>