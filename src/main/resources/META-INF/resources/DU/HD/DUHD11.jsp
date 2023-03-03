<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询" type="query" efRegionShowClear="true"
                 efRegionSave="true">
            <EF:EFInput ename="inqu_status-0-formulaname" cname="费用名称"/>

    </EF:EFRegion>
    <EF:EFRegion id="result" title="记录集" >
        <EF:EFGrid blockId="result" autoDraw="false" sort="setted"
                   rowNo="true" queryMethod="query" deleteMethod="delete" insertMethod="insert"
                   isFloat="true">
            <EF:EFColumn ename="formulaid" cname="主键ID"  colWidth="6" hidden="true"/>
            <EF:EFColumn ename="formulaname" cname="费用名称"  readonly="true"/>
            <EF:EFColumn ename="formula" cname="计算公式"  enable="true"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>