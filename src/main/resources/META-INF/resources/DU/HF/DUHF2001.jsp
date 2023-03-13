<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-scoringcontent" cname="评价分项内容"/>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div id="butdiv" style="float: right;"></div>
            </div>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" autoDraw="false" sort="setted" rowNo="true" editHelper="true" isFloat="true">
            <EF:EFColumn ename="reportid" cname="报表编号" enable="false" hidden="true"/>
            <EF:EFColumn ename="scoringcontent" cname="评价分项内容" enable="true" />
            <EF:EFColumn ename="standardscore" cname="评价标准分" enable="true" />
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>