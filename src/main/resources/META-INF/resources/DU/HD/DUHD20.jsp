<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage >
    <script src="${ctx}/js/ajaxfileupload.js"></script>
    <EF:EFRegion id="inqu" title="查询" type="query" efRegionShowClear="true"
                 efRegionSave="true">
        <div class="row">
            <EF:EFSelect ename="inqu_status-0-reporttype" cname="报表类型"
                         colWidth="4">
                <EF:EFOptions blockId="reportTypeBlock" textField="typename"
                              valueField="reporttype" />
            </EF:EFSelect>
            <EF:EFInput ename="inqu_status-0-file" cname="上传模板" type="file"
                        colWidth="4" required="true" />
            <div class="col-md-2">
                <button id="upload" class='i-btn-sm' style="height: 23px;width: 50px;">上传</button>
            </div>
        </div>
    </EF:EFRegion>
    <EF:EFRegion title="结果" id="result" fitHeight="true">
        <EF:EFGrid blockId="result" autoDraw="false" sort="setted"
                   rowNo="true" isFloat="true">
            <EF:EFColumn ename="reportname" cname="报表名称" width="180"
                         readonly="true" />
            <EF:EFComboColumn ename="reporttype" cname="报表类型" width="100"
                              blockName="reportTypeBlock" textField="typename"
                              valueField="reporttype" sort="true" readonly="true" />
            <EF:EFColumn ename="uploadman" cname="上传人" width="100" sort="true"
                         readonly="true" />
            <EF:EFColumn ename="uploadtime" cname="上传时间" width="100" sort="true"
                         readonly="true" />
        </EF:EFGrid>
    </EF:EFRegion>
</EF:EFPage>
