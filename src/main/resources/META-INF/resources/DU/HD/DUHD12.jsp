<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询" type="query" efRegionShowClear="true"
                 efRegionSave="true">
        <EF:EFInput ename="inqu_status-0-indexName" cname="指标名称"/>

    </EF:EFRegion>
    <EF:EFRegion id="result" title="记录集" >
        <EF:EFGrid blockId="result" autoDraw="false" sort="setted"
                   rowNo="true" queryMethod="query" deleteMethod="delete" insertMethod="insert"
                   isFloat="true">
            <EF:EFColumn ename="indexId" cname="指标ID"  colWidth="6" hidden="true"/>
            <EF:EFColumn ename="indexName" cname="指标名称"  readonly="true" required="true"/>
            <EF:EFComboColumn ename="status" cname="是否使用" blockName="flagList"
                              labelProperty="flagname" valueProperty="flagid"
                              textField="flagname" valueField="flagid" required="true" />
            <EF:EFColumn ename="description" cname="备注"  readonly="true" />
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>