<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="排口监测因子信息">
   <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
	    <div class="row">
	        <EF:EFInput ename="inqu_status-0-dischargeportid" cname="排口ID" colWidth="4" disabled="true"/>
            <EF:EFInput ename="inqu_status-0-dischargeportname" cname="排口名称" colWidth="4" disabled="true"/>
        </div>
	 </EF:EFRegion>

       <EF:EFTab id="info">
            <div title="排口监测因子信息">
                <EF:EFRegion id="portfs" title="记录集">
	                <EF:EFGrid blockId="portfs" autoDraw="false" sort="single" queryMethod="queryPORTFS" deleteMethod="deletePORTFS" >
	                    <EF:EFColumn ename="factorid" cname="监测因子ID" enable="false" hidden="true"/>
	                    <EF:EFColumn ename="portid" cname="排口ID" enable="false" hidden="true"/>
	                    <EF:EFColumn ename="factorname" cname="监测因子名称" enable="false" />
			            <EF:EFColumn ename="highlimit" cname="国控上限" enable="false"/>
			            <EF:EFColumn ename="lowlimit" cname="国控下限" enable="false"/>
			            <EF:EFColumn ename="nkhighlimit" cname="内控上限" enable="false"/>
			            <EF:EFColumn ename="nklowlimit" cname="内控下限" enable="false"/>
			            <EF:EFColumn ename="description" cname="描述" enable="false"/>
        	            <EF:EFComboColumn ename="status" cname="状态" blockName="flagList" labelProperty="flagname" valueProperty="flagid" textField="flagname" valueField="flagid" required="true" defaultValue="1"/>
	                </EF:EFGrid>
                </EF:EFRegion>
                <EF:EFWindow id="showPF" width="90%" height="90%" refresh="true">
                </EF:EFWindow> 
            </div>
            <div title="监测点信息">
                <EF:EFRegion id="sitefs" title="记录集">
			        <EF:EFGrid blockId="sitefs" autoDraw="false"  sort="single" queryMethod="querySITEFS">
			        	<EF:EFColumn ename="siteid" cname="监测点ID" enable="false"/>
			        	<EF:EFColumn ename="sitename" cname=" 监测点名称" enable="false"/>
			        	<EF:EFComboColumn ename="isartificial" cname="是否人工" enable="false"/>
			            <EF:EFComboColumn ename="classifyid" cname="人工监测类型" enable="false"/>
			            <EF:EFComboColumn ename="isonline" cname="是否在线" enable="false"/>
			            <EF:EFComboColumn ename="monitorid" cname="在线监测类型" enable="false"/>
			        	<EF:EFColumn ename="mnid" cname="MN编号" enable="false"/>
			        	<EF:EFColumn ename="description" cname="描述" enable="false"/>
			        	<EF:EFColumn ename="sort" cname="排序编号" enable="false"/>
			        	<EF:EFComboColumn ename="status" cname="状态" enable="false"/>
			        </EF:EFGrid> 
			    </EF:EFRegion>
            </div>
            
        </EF:EFTab>
</EF:EFPage>
