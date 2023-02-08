<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
<div class="row">
       <div class="col-md-3">
            <EF:EFTree  bindId="departmentTree" ename="departmentId" textField="text" valueField="label" hasChildren="leaf"
                           serviceName="DUHB2801" methodName="query" style="height:550px;" >
            </EF:EFTree>       
       </div>
       <div class="col-md-9">
           <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
		        <div class="row">
		            <EF:EFInput ename="inqu_status-0-departmentId" cname="所属厂部ID" colWidth="4" disabled="true"/> 
		            <%-- <EF:EFInput ename="inqu_status-0-parentname" cname="所属厂部" colWidth="4" /> --%>
                    <EF:EFInput ename="inqu_status-0-userId" cname="工号" colWidth="4"/>
                </div>
			 </EF:EFRegion>
			 <EF:EFRegion id="result" title="记录集">
		        <EF:EFGrid blockId="result" autoDraw="false"  sort="setted">
		        	<EF:EFColumn ename="rid" cname="ID" hidden="true" enable="false"/>
		        	<%-- <EF:EFColumn ename="operation" cname="操作" enable="false" locked="true"/> --%>
		        	<EF:EFComboColumn ename="departmentId" cname="单位" enable="false" blockName="qdepart"  textField="departmentName" valueField="departmentId"/>
		        	<EF:EFColumn ename="userId" cname="工号" enable="false"/>
		        	<EF:EFColumn ename="userName" cname="用户名"  enable="false"/>
		        	<EF:EFColumn ename="mobile" cname="手机号"  enable="false"/>
		        	<EF:EFColumn ename="email" cname="邮箱"  enable="false"/>
		        	
		        </EF:EFGrid> 
		        <EF:EFWindow id="showDP" width="50%" height="50%" refresh="true">
                </EF:EFWindow>
		    </EF:EFRegion>

       </div>
   </div>
    
</EF:EFPage>