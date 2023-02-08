<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/js/css/infostyle.css">
<EF:EFPage >
   <EF:EFRegion id="inqu" title="产排污节点信息" type="query" efRegionShowClear="true" efRegionSave="true">
	   <div class="row divtop"><div class="divtitle"><b>主要信息</b></div></div>
	   <div class="row divtop">
	        <%-- <div class="col-xs-6 ">
                 <EF:EFInput ename="inqu_status-0-deviceid"  cname="生产设施编号" colWidth="12" /> 
            </div> --%>
            <div class="col-xs-6 ">
                 <EF:EFInput ename="inqu_status-0-devicename"  cname="废水类别" colWidth="12" /> 
            </div>
        </div>
		<div class="row divtop">
            <div class="col-xs-6">
               <EF:EFInput ename="inqu_status-0-outto" cname="排放去向" colWidth="12" />
            </div>
            <div class="col-xs-6">
               <EF:EFInput ename="inqu_status-0-portouttype" cname="排放方式" colWidth="12" />
            </div>
       </div>
       <div class="row divtop">
            <div class="col-xs-6">
               <EF:EFInput type="textarea" ename="inqu_status-0-outrule" cname="排放规律" colWidth="12" />
            </div>
            <div class="col-xs-6">
                 <EF:EFInput type="textarea" ename="inqu_status-0-factorname"  cname="污染物种类" colWidth="12" /> 
            </div>
       	</div>
        <div class="row divhr"></div>
       	<div class="row divtop"><div class="divtitle"><b>污染防治设施</b></div></div>
        <div class="row divtop">
	        <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-processid" readonly="true" cname="污染防治设施编号" colWidth="12" />
            </div>
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-processname" readonly="true" cname="污染防治设施名称" colWidth="12" />
            </div>
        </div>
<%-- 		<div class="row divtop">
            <div class="col-xs-6">
               <EF:EFInput ename="inqu_status-0-canuse" cname="是否为可行技术" colWidth="12" />
            </div>
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-processinfo" cname="污染治理设施其他信息" colWidth="12" />
            </div>
       	</div> --%>
        <div class="row divhr"></div>
       	<div class="row divtop"><div class="divtitle"><b>排口信息</b></div></div>
		<div class="row divtop">
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-portid" readonly="true" cname="排放口编号" colWidth="12" />
            </div>
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-portname" readonly="true" cname="排放口名称" colWidth="12" />
            </div>
       </div>
        
<%--         <div class="row divtop">
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-porttype" cname="排放口类型 " colWidth="12" />
            </div>
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-isright" cname="排放口设置是否符合要求" colWidth="12" />
            </div>
       </div> --%>
        <div class="row divhr"></div>
       	<div class="row divtop"><div class="divtitle"><b>其他信息</b></div></div>
        <div class="row divbottom">
            <div class="col-xs-6">
                <EF:EFInput type="textarea" ename="inqu_status-0-other" cname="其他信息" colWidth="12" />
            </div>
       </div>
        
        <div class="row">
            <div class="col-xs-10">
                <EF:EFInput ename="inqu_status-0-oprationType" type="hidden" enable="false"/>
                <EF:EFInput ename="inqu_status-0-envdeviceid" type="hidden" enable="false"/>
            </div>
            <div class="col-xs-2">
                <input value="保存" style="float:right;margin-top:8px;" class="k-button k-button-icontext" type="button" id="submitButton"/>
            </div>
        </div>
	 </EF:EFRegion>
</EF:EFPage>
