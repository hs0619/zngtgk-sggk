<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage >
    <EF:EFRegion id="inqu" title="查询" type="query" efRegionShowClear="true"
                 efRegionSave="true">
        <div class="row">
            <div class="col-md-1"></div>
            <EF:EFSelect ename="inqu_status-0-reporttype" cname="报表类型"
                         colWidth="5">
                <EF:EFOptions blockId="reportTypeBlock" textField="typename"
                              valueField="reporttype" />
            </EF:EFSelect>
            <EF:EFSelect ename="inqu_status-0-reportname" cname="报表名称"
                         colWidth="5">
                <EF:EFOptions blockId="reportInfoBlock" textField="reportname"
                              valueField="reportname" />
            </EF:EFSelect>
        </div>
        <div class="row">
            <div class="col-md-1"></div>
            <EF:EFDatePicker ename="inqu_status-0-startdate" cname="开始时间"
                             format="yyyy-MM-dd" colWidth="5">
            </EF:EFDatePicker>
            <EF:EFDatePicker ename="inqu_status-0-enddate" cname="结束时间"
                             format="yyyy-MM-dd" colWidth="5">
            </EF:EFDatePicker>
        </div>
    </EF:EFRegion>
    <EF:EFRegion title="结果" id="result" fitHeight="true">
        <EF:EFGrid blockId="result" autoDraw="false" sort="setted"
                   rowNo="true" isFloat="true" checkMode="hidden" >
            <EF:EFColumn ename="filename" cname="报表名称" width="180" />
            <EF:EFColumn ename="reportname" cname="模板名称" hidden="true" />
            <EF:EFColumn ename="datatime" cname="数据时间 " width="70" />
            <EF:EFColumn ename="exist" cname="报表状态" width="70" />
            <EF:EFColumn ename="createtime" cname="报表生成时间" width="70" />
            <EF:EFComboColumn ename="reporttype" cname="报表类型" width="70"
                              blockName="reportTypeBlock" textField="typename"
                              valueField="reporttype" sort="true" />
            <EF:EFColumn ename="operation" cname="操作" width="100" />
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
