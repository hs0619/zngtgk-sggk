<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage >
   <EF:EFRegion id="inqu" title="工序信息" type="query" efRegionShowClear="true" efRegionSave="true">
	   <hr>
	   <div class="row">
	        <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-departmentId"  cname="厂部id" colWidth="12" type="hidden"/> 
                 <EF:EFInput ename="inqu_status-0-depProId"  cname="工序编号" colWidth="12" disabled="true"/>
            </div>
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-departmentName"  cname="工序名称" colWidth="12" required="true"/> 
            </div>
        </div>
        <hr>
       <div class="row">
            <div class="col-xs-6">
                <EF:EFSelect ename="inqu_status-0-parentid"  cname="所属厂部" colWidth="12" required="true"> </EF:EFSelect>
            </div>
            <div class="col-xs-6">
                 <EF:EFSelect ename="inqu_status-0-type"  cname="类型" colWidth="12" required="true" disabled="true"> </EF:EFSelect>
            </div>
       </div>
       <hr>
       <div class="row">
<%--             <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-level" cname="等级" colWidth="12" />
            </div> --%>
            
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-sort" cname="排序"  onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"  colWidth="12" required="true" />
            </div>
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-description" cname="描述" colWidth="12" />
            </div>
       </div>
       <hr>
       <div class="row">

            <div class="col-xs-6">
                <EF:EFSelect ename="inqu_status-0-status" cname="是否启用"  colWidth="12" required="true"></EF:EFSelect>
            </div>
       </div>
        <hr>
        <div class="row">
            <div class="col-xs-10">
                <EF:EFInput ename="inqu_status-0-oprationType" type="hidden" enable="false"/>
                <EF:EFInput ename="inqu_status-0-departmentId" type="hidden" enable="false"/>
            </div>
            <div class="col-xs-2">
                <input value="保存" class="k-button k-button-icontext" type="button" id="submitButton"/>
            </div>
        </div>
	 </EF:EFRegion>
</EF:EFPage>
