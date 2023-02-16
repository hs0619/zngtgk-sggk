<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <EF:EFRegion id="inqu" title="原辅料编辑项" efRegionShowClear="true"
                 efRegionSave="true">

        <div class="row">
            <EF:EFInput ename="inqu_status-0-rmId" cname="主键" type="hidden" colWidth="6"/>
        </div>
        <hr>
        <div class="row">
        <div class="col-xs-6">
            <EF:EFSelect ename="inqu_status-0-rmType" cname="类别" colWidth="12">
                <EF:EFOption label="" value=""/>
                <EF:EFOption label="原料" value="原料"/>
                <EF:EFOption label="辅料" value="辅料"/>
            </EF:EFSelect>
        </div>

            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-rmName" cname="名称" colWidth="12"/>

            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-rmMaxAmount" cname="年最大使用量" colWidth="12" />

            </div>
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-rmUnit" cname="单位" colWidth="12" />

            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-rmSulfurContent" cname="硫分(%)" colWidth="12" />

            </div>

            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-rmVolatile" cname="挥发分(%)" colWidth="12" />

            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-rmOtherInformation" cname="其他信息" colWidth="12" type="textarea"/>
            </div>

        </div>
        <hr>
        <div class="row">
            <div class="col-md-12">
                <div id="btndiv" style="float: right; margin: 5px auto;"></div>
            </div>
        </div>


    </EF:EFRegion>
</EF:EFPage>