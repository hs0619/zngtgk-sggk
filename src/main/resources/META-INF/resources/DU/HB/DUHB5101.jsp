<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <EF:EFRegion id="inqu" title="燃料编辑项" efRegionShowClear="true"
                 efRegionSave="true">

        <div class="row">
            <EF:EFInput ename="inqu_status-0-rfId" cname="主键" type="hidden" colWidth="6"/>
        </div>
        <hr>
        <div class="row">

            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-rfName" cname="燃料名称" colWidth="12"/>

            </div>

            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-rfAsh" cname="灰分(%)" colWidth="12"/>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-rfSulfurContent" cname="硫分(%)" colWidth="12" />

            </div>
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-rfVolatile" cname="挥发分(%)" colWidth="12" />

            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-rfHeatValue" cname="热值(MJ/kg、MJ/m³)" colWidth="12" />

            </div>

            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-rfMaxAmount" cname="年最大使用量（万t/a、万m3/a）" colWidth="12" />

            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-rfOtherInformation" cname="其他信息" colWidth="12" type="textarea"/>
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