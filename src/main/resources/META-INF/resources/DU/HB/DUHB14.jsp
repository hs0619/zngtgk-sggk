<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="排口信息">

    <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-dischargeportid" cname="排口ID" colWidth="4"/>
            <EF:EFInput ename="inqu_status-0-dischargeportname" cname="排口名称" colWidth="4"/>
        </div>
        <div class="row">
            <div class="col-md-12">
            <div id="butdiv" style="float:right;"></div>
            </div>
        </div> 
    </EF:EFRegion>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" autoDraw="false"  sort="single">
        	<EF:EFColumn ename="dischargeportid" cname="排口ID" readonly="true" required="true" locked="true"/>
        	<EF:EFColumn ename="dischargeportname" cname=" 排口名称" required="true" locked="true"/>
        	<EF:EFComboColumn ename="isformal" cname="是否正式" blockName="formalList" labelProperty="formalname" valueProperty="formalid" textField="formalname" valueField="formalid" required="true"/>
        	<EF:EFComboColumn ename="procid" cname="工序" blockName="procedureList" labelProperty="procedureName" valueProperty="procedureId" textField="procedureName" valueField="procedureId" required="true"/>
        	<EF:EFComboColumn ename="monitorid" cname="监测类型" blockName="monitorList" labelProperty="monitorname" valueProperty="monitorid" textField="monitorname" valueField="monitorid" required="true"/>
        	<EF:EFColumn ename="signform" cname="标志牌形式"/>
        	<EF:EFColumn ename="dischargemode" cname="排放方式"/>
        	<EF:EFColumn ename="longitude" cname="经度"/>
        	<EF:EFColumn ename="latitude" cname="纬度"/>
        	<EF:EFColumn ename="position" cname="位置"/>
        	<EF:EFColumn ename="executionstandard" cname="执行标准"/>
        	<EF:EFComboColumn ename="setright" cname="符合要求" blockName="flagList" labelProperty="flagname" valueProperty="flagid" textField="flagname" valueField="flagid" />
        	<EF:EFColumn ename="importantport" cname="级别"/>
        	<EF:EFComboColumn ename="ismap" cname="地图" blockName="flagList" labelProperty="flagname" valueProperty="flagid" textField="flagname" valueField="flagid" />
        	<EF:EFColumn ename="description" cname="备注"/>
        	<EF:EFColumn ename="outto" cname="排放去向"/>
        	<EF:EFColumn ename="outtype" cname="排放形式"/>
        	<EF:EFColumn ename="outrule" cname="排放规律"/>
            <EF:EFComboColumn ename="countrypoint" cname="国控点" blockName="flagList" labelProperty="flagname" valueProperty="flagid" textField="flagname" valueField="flagid" />
            <EF:EFComboColumn ename="citypoint" cname="市控点" blockName="flagList" labelProperty="flagname" valueProperty="flagid" textField="flagname" valueField="flagid" />
            <EF:EFComboColumn ename="companypoint" cname="内控点" blockName="flagList" labelProperty="flagname" valueProperty="flagid" textField="flagname" valueField="flagid" />
            <EF:EFComboColumn ename="cleanpoint" cname="超低排放点" blockName="flagList" labelProperty="flagname" valueProperty="flagid" textField="flagname" valueField="flagid" />
        	<EF:EFComboColumn ename="status" cname="状态" blockName="flagList" labelProperty="flagname" valueProperty="flagid" textField="flagname" valueField="flagid" required="true" defaultValue="1"/>
        	<%-- <EF:EFColumn ename="creator" cname="创建者" enable="false"/>
        	<EF:EFColumn ename="createdTime" cname="创建时间" enable="false" />
        	<EF:EFColumn ename="modifier" cname="修改者" enable="false"/>
        	<EF:EFColumn ename="updatedTime" cname="修改时间" enable="false" /> --%>
        </EF:EFGrid> 
    </EF:EFRegion>
    
    <EF:EFWindow id="showSF" width="90%" height="90%" refresh="true">
     </EF:EFWindow> 
</EF:EFPage>