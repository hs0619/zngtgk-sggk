<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
        <div class="row">
            <EF:EFSelect ename="inqu_status-0-departmentid" cname="区域单位"  colWidth="4">
                <EF:EFOptions blockId="qdepart" textField="departmentName" valueField="departmentId" />
            </EF:EFSelect>
            <EF:EFDatePicker ename="inqu_status-0-year" cname="年份" format="yyyy" cowidth="3" />
            <EF:EFSelect ename="inqu_status-0-quarter" cname="季度"  colWidth="3" enable="true">
                <EF:EFOption label="第一季度" value="q1" />
                <EF:EFOption label="第二季度" value="q2" />
                <EF:EFOption label="第三季度" value="q3" />
                <EF:EFOption label="第四季度" value="q4" />
            </EF:EFSelect>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div id="butdiv" style="float: right;"></div>
            </div>
        </div>
    </EF:EFRegion>
    <EF:EFRegion id="result" title="记录集">
        <EF:EFGrid blockId="result" autoDraw="false" sort="setted" rowNo="true" editHelper="true" isFloat="true">
            <EF:EFComboColumn ename="departmentid" cname="区域单位"  width="130" blockName="qdepart"
                              textField="departmentName"  valueField="departmentId" />
            <EF:EFColumn ename="reportid" cname="报表编号" enable="false" hidden="true"/>
            <EF:EFColumn ename="year" cname="年份" enable="true" />
            <EF:EFComboColumn ename="quarter" cname="季度" enable="true" >
                <EF:EFOption label="第一季度" value="q1" />
                <EF:EFOption label="第二季度" value="q2" />
                <EF:EFOption label="第三季度" value="q3" />
                <EF:EFOption label="第四季度" value="q4" />
            </EF:EFComboColumn>
            <EF:EFColumn ename="scoringcontent" cname="评价分项内容" enable="false" />
            <EF:EFColumn ename="standardscore" cname="评价标准分" enable="false" />
            <EF:EFColumn ename="score" cname="评价得分" enable="true" />
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>