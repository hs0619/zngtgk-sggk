<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询">
        <div class="row">

            <EF:EFInput ename="inqu_status-0-rfName" cname="燃料名称"/>


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
            <EF:EFColumn ename="rfId" cname="主键ID" sort="true" readonly="true" hidden="true"/>
            <EF:EFColumn ename="rfName" cname="燃料名称" sort="true" readonly="true"/>
            <EF:EFColumn ename="rfAsh" cname="灰分(%)" sort="true" readonly="true"/>
            <EF:EFColumn ename="rfSulfurContent" cname="硫分(%)" sort="true" readonly="true"/>
            <EF:EFColumn ename="rfVolatile" cname="挥发分(%)" sort="true" readonly="true"/>
            <EF:EFColumn ename="rfHeatValue" cname="热值(MJ/kg、MJ/m³)" sort="true" readonly="true"/>
            <EF:EFColumn ename="rfMaxAmount" cname="年最大使用量（万t/a、万m3/a）" sort="true" readonly="true"/>
            <EF:EFColumn ename="rfOtherInformation" cname="其他信息" sort="true" readonly="true"/>
        </EF:EFGrid>
    </EF:EFRegion>
    <EF:EFWindow id="showDP" width="90%" height="90%" refresh="true">
    </EF:EFWindow>
</EF:EFPage>