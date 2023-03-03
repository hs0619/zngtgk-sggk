<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
    <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
        <div class="row">
            <EF:EFDatePicker ename="inqu_status-0-datatime" cname="时间" format="yyyy-MM" cowidth="3" />
            <EF:EFInput ename="inqu_status-0-dangertype" cname="危废类别"/>
            <EF:EFInput ename="inqu_status-0-dangername" cname="危废名称"/>
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
            <EF:EFColumn ename="datatime" cname="日期"  width="60" enable="false" />
            <EF:EFComboColumn ename="departmentid" cname="产生单位" required="true" width="130" readonly="true"
                              blockName="depart" textField="department_name"  valueField="department_id" />
            <EF:EFColumn ename="dangertype" cname="危废类别"  required="true" readonly="true"/>
            <EF:EFColumn ename="dangercode" cname="危废代码"    required="true" readonly="true" />
            <EF:EFColumn ename="dangername" cname="危废名称"   required="true" readonly="true"/>
            <EF:EFColumn ename="prodresource" cname="产生源"    />
            <EF:EFComboColumn ename="dangerform" cname="危废形态"
                              blockName="form" textField="textfield"  valueField="valuefield" />
            <EF:EFComboColumn ename="promethod" cname="产生方式" >
                <EF:EFOption label="生产" value="1"/>
                <EF:EFOption label="清理" value="2"/>
            </EF:EFComboColumn>
            <EF:EFColumn ename="output" cname="产生量" />
            <EF:EFColumn ename="disposeValue" cname="处置量"   />
            <EF:EFColumn ename="storgeValue" cname="贮存量" />
            <EF:EFColumn ename="disposeUnit" cname="处置单位" />
            <EF:EFColumn ename="disposeMethod" cname="处置方式"   />
            <EF:EFColumn ename="isupdate" cname="是否修改"  hidden="true" />
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>