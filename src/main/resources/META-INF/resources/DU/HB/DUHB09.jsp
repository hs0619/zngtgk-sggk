<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >

    <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
        <div class="row">
           	<EF:EFSelect ename="inqu_status-0-monitorid" cname="监测类型" colWidth="4">
					<EF:EFOption label="全部" value="" />
					<EF:EFOptions blockId="monitor" textField="monitorname" valueField="monitorid"/>
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-factorid" cname="监测因子 "  colWidth="4">
					<EF:EFOption label="全部" value="" />
					<EF:EFOptions blockId="factor" textField="factorname" valueField="factorid"/>
			</EF:EFSelect>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" autoDraw="false"  sort="single">
        	<EF:EFComboColumn ename="monitorid" cname="监测类型" blockName="monitor"   filter="contains"
						textField="monitorname" valueField="monitorid" enable="true" readonly="true" />
			<EF:EFComboColumn ename="factorid" cname="监测因子" blockName="factor1"  filter="contains"
						textField="factorname" valueField="factorid" enable="true" readonly="true" />
        	<EF:EFComboColumn ename="status" cname="是否使用" blockName="flagList"
						labelProperty="flagname" valueProperty="flagid"
						textField="flagname" valueField="flagid" defaultValue="1" />
        </EF:EFGrid> 
    </EF:EFRegion>
    <EF:EFWindow id="showMF" width="50%" height="50%" refresh="true">
    </EF:EFWindow>
</EF:EFPage>