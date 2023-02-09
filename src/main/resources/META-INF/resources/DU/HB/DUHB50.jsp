<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询">
        <div class="row">

            <EF:EFInput ename="inqu_status-0-rmName" cname="原辅料名称"/>

            <EF:EFSelect ename="inqu_status-0-rmType" cname="原辅料类别">
                <EF:EFOption label="全部" value=""/>
                <EF:EFOption label="原料" value="原料"/>
                <EF:EFOption label="辅料" value="辅料"/>
                <%--                <EF:EFOptions blockId="procedureblock" textField="procedureName" valueField="procedureId"/>--%>
            </EF:EFSelect>

        </div>

        <div class="row">
            <div class="col-md-12">
                <div id="btndiv" style="float: right; margin: 2px auto;"></div>
            </div>
        </div>
    </EF:EFRegion>

    <EF:EFRegion title="结果" id="result" fitHeight="true">
        <EF:EFGrid blockId="result" autoDraw="false" sort="setted"
                   rowNo="true" queryMethod="query" deleteMethod="delete"
                   isFloat="true">
            <EF:EFColumn ename="rmType" cname="类别" sort="true" readonly="true"/>
            <EF:EFColumn ename="rmName" cname="名称" sort="true" readonly="true"/>
            <EF:EFColumn ename="rmMaxAmount" cname="年最大使用量" sort="true" readonly="true"/>
            <EF:EFColumn ename="rmUnit" cname="单位" sort="true" readonly="true"/>
            <EF:EFColumn ename="rmSulfurContent" cname="硫分(%)" sort="true" readonly="true"/>
            <EF:EFColumn ename="rmVolatile" cname="挥发分(%)" sort="true" readonly="true"/>
            <EF:EFColumn ename="rmOtherInformation" cname="其他信息" sort="true" readonly="true"/>
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>