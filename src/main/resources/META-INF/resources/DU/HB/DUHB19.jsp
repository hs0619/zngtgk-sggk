<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
<div class="row">
       <div class="col-md-12">
           <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
		        <div class="row">
					<%-- <EF:EFInput ename="inqu_status-0-deviceid" cname="产污设施编号"  colWidth="4" /> --%>
					<EF:EFInput ename="inqu_status-0-devicename" cname="废水类别" colWidth="4" />
					<EF:EFInput ename="inqu_status-0-portid" cname="排放口编号" colWidth="4"/>
					<EF:EFInput ename="inqu_status-0-portname" cname="排放口名称" colWidth="4"/>
				</div>
				<div class="row">
					
				</div>
			 </EF:EFRegion>
			 <EF:EFRegion id="result" title="记录集" fitHeight="true" >
		        <EF:EFGrid blockId="result" autoDraw="false"  sort="single" rowNo="true" height="502"
			 			serviceName="DUHB19" queryMethod="queryinfo">
		        	<EF:EFColumn ename="envdeviceid" cname="设施ID" hidden="true" enable="false"/>
		        	<EF:EFColumn ename="operation" cname="操作" enable="false" locked="true"/>
		        	<%-- <EF:EFColumn ename="deviceid" cname="产污设施编号" enable="false"/> --%>
		        	<EF:EFColumn ename="devicename" cname="废水类别" enable="false"/>
		        	<EF:EFColumn ename="factorname" cname="污染物种类" enable="false"/>
		        	<EF:EFColumn ename="outto" cname="排放去向" enable="false"/>
		        	<EF:EFColumn ename="portouttype" cname="排放方式" enable="false"/>
		        	<EF:EFColumn ename="outrule" cname="排放规律" enable="false"/>
		        	<EF:EFColumn ename="other" cname="其他信息" enable="false" />
		        	<EF:EFColumn ename="facilityinfo" cname="污染防治设施" enable="false"/>
		        	<EF:EFColumn ename="processid" cname="污染防治设施编号" enable="false" width="180"/>
		        	<EF:EFColumn ename="processname" cname="污染防治设施名称" enable="false" width="180"/>
		        	<EF:EFColumn ename="portinfo" cname="排放口信息" enable="false"/>
		        	<EF:EFColumn ename="portid" cname="排放口编号" enable="false"/>
		        	<EF:EFColumn ename="portname" cname="排放口名称" enable="false"/>
		        	<EF:EFColumn ename="description" cname="备注" enable="false" />	        	
		        </EF:EFGrid> 
		        <EF:EFWindow id="showED"  refresh="true">
                </EF:EFWindow>
                <EF:EFWindow id="showFI" width="90%" height="90%" refresh="true">
                </EF:EFWindow>
                <EF:EFWindow id="showDCP" width="90%" height="90%" refresh="true">
                </EF:EFWindow>
		    </EF:EFRegion>

       </div>
   </div>
    
</EF:EFPage>