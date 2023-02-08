<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="用户">
	<EF:EFRegion id="inqu" title="用户" efRegionShowClear="true"
		efRegionSave="true">
		<div class="row">
			<div class="col-xs-2"></div>
            <div class="col-xs-4">
                <EF:EFSelect ename="inqu_status-0-departmentId"  cname="所属厂部" colWidth="12" required="true" disabled="true"
                serviceName="DUHB2802" methodName="initLoad" resultId="qdepart"
				textField="departmentName" valueField="departmentId"> 
				</EF:EFSelect>
            </div>
            <div class="col-xs-4">
                 <%-- <EF:EFSelect ename="inqu_status-0-type"  cname="类型" colWidth="12" required="true" disabled="true"> </EF:EFSelect> --%>
                  <EF:EFSelect ename="inqu_status-0-user" cname="用户" colWidth="12" serviceName="DUHB2802" methodName="initLoad" resultId="quser"
				textField="userName" valueField="loginName"></EF:EFSelect>
            </div>
            <div class="col-xs-2"></div>
       </div>
		<div class="row">
			<div class="col-xs-2"></div>
            <div class="col-xs-4">
                <%-- <EF:EFSelect ename="inqu_status-0-user" cname="用户" colWidth="12" serviceName="DUHF65" methodName="initLoad" resultId="qmonitorid"
				textField="monitorname" valueField="monitorid"> 
				
			</EF:EFSelect>--%>
            </div>
            <div class="col-xs-4">
                 <%-- <EF:EFSelect ename="inqu_status-0-type"  cname="类型" colWidth="12" required="true" disabled="true"> </EF:EFSelect> --%>
            </div>
            <div class="col-xs-2"></div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div id="butdiv" style="float: right;"></div>
			</div>
		</div>
	</EF:EFRegion>
</EF:EFPage>