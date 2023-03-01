<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
    <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
        <div class="row">
            <EF:EFDatePicker ename="inqu_status-0-datatime" cname="时间" format="yyyy-MM" cowidth="3" />

            <EF:EFSelect ename="inqu_status-0-solidType" cname="固废类型" >
                <EF:EFOption label="全部" value=""/>
                <EF:EFOptions blockId="solidType" textField="solidType" valueField="solidType"/>
            </EF:EFSelect>
            <EF:EFInput ename="inqu_status-0-solidName" cname="固废名称"/>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div id="butdiv" style="float: right;"></div>
            </div>
        </div>
    </EF:EFRegion>


    <EF:EFRegion id="result" title="结果集" >
        <EF:EFGrid blockId="result" autoDraw="false" sort="setted" rowNo="false"
                   editHelper="true" isFloat="true" >
            <EF:EFColumn ename="solidId" cname="固废id"  width="100"   hidden="true"/>
            <EF:EFColumn ename="datatime" cname="日期"  width="60" enable="false" />
            <EF:EFColumn ename="rank" cname="序号"  width="60" enable="false" />
            <EF:EFColumn ename="solidType" cname="固废类型"  width="100" enable="false"   />
            <EF:EFColumn ename="solidName" cname="固废名称"  width="100" enable="false"   />
            <EF:EFColumn ename="solidCode" cname="固废代码"  width="100" enable="false"   />
            <EF:EFColumn ename="utilization" cname="利用量(吨)"  width="100" enable="true"
                         data-rules="non_negative_number" minLength="1" maxLength="10"/>
            <EF:EFColumn ename="output" cname="产生量(吨)"  width="100" enable="true"
                         data-rules="non_negative_number" minLength="1" maxLength="10"/>
            <EF:EFColumn ename="backOutput" cname="反生产利用量(吨)"  width="100" enable="true"
                         data-rules="non_negative_number" minLength="1" maxLength="10"/>
            <EF:EFColumn ename="outerValue" cname="外销量(吨)"  width="100" enable="true"
                         data-rules="non_negative_number" minLength="1" maxLength="10"/>
            <EF:EFColumn ename="disposeValue" cname="处置量(吨)"  width="100" enable="true"
                         data-rules="non_negative_number" minLength="1" maxLength="10"/>
            <EF:EFColumn ename="isupdate" cname="是否修改"  hidden="true" />
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>