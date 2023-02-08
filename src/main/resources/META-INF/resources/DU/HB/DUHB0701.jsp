<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/js/css/infostyle.css">
<EF:EFPage >
   <EF:EFRegion id="inqu" title="监测点信息" type="query" efRegionShowClear="true" efRegionSave="true">
	   <div class="row divtop"><div class="divtitle"><b>主要信息</b></div></div>
	   <div class="row divtop">
	        <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-sitename"  cname="监测点名称" colWidth="12" required="true"/> 
            </div>
            
        </div>
        <div class="row divtop">
			<div class="col-xs-6">
                 <EF:EFSelect ename="inqu_status-0-departid"  cname="厂部" colWidth="12" required="true"> </EF:EFSelect>
            </div>
	        <div class="col-xs-6">
                 <EF:EFSelect ename="inqu_status-0-procid"  cname="工序" colWidth="12" required="true"> </EF:EFSelect>
            </div>
        </div>
        <div class="row divtop">
        	<div class="col-xs-6">
                 <EF:EFSelect ename="inqu_status-0-portid"  cname="排口" colWidth="12" required="true"> </EF:EFSelect>
            </div>
			<div class="col-xs-6">
                 <EF:EFSelect ename="inqu_status-0-status"  cname="是否使用" colWidth="12" required="true"> </EF:EFSelect>
            </div>
        </div>
        
       <div class="row divtop"><div class="divtitle"><b>监测信息</b></div></div>
       <div class="row divtop">
            <div class="col-xs-6">
                 <EF:EFSelect ename="inqu_status-0-isartificial"  cname="是否人工" colWidth="12" required="true"> </EF:EFSelect>
            </div>
            <div class="col-xs-6">
                 <EF:EFSelect ename="inqu_status-0-isonline"  cname="是否在线" colWidth="12" required="true"> </EF:EFSelect>
            </div>
       </div>
       
       <div class="row divtop">
            <div class="col-xs-6">
                 <EF:EFSelect ename="inqu_status-0-classifyid"  cname="人工监测类型" colWidth="12" required="true"> </EF:EFSelect>
            </div>
            <div class="col-xs-6">
                 <EF:EFSelect ename="inqu_status-0-monitorid"  cname="在线监测类型" colWidth="12" required="true"> </EF:EFSelect>
            </div>
       </div>
       
       <div class="row divtop" >
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-mnid" cname="MN编号" colWidth="12" />
            </div>
            
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-sort" cname="排序" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"  required="true" colWidth="12" />
            </div>
       </div>
        
        <div class="row divbottom">
             <div class="col-xs-6">
                 <EF:EFInput type="textarea" ename="inqu_status-0-description" cname="描述" colWidth="12" />
            </div>
       </div>
        
        <div class="row">
            <div class="col-xs-10">
                <EF:EFInput ename="inqu_status-0-oprationType" type="hidden" enable="false"/>
                <EF:EFInput ename="inqu_status-0-siteid" type="hidden" enable="false"/>
            </div>
            <div class="col-xs-2">
                <input value="保存" style="float:right;margin-top:8px;" class="k-button k-button-icontext" type="button"
					id="submitButton" />
            </div>
        </div>
	 </EF:EFRegion>
</EF:EFPage>
