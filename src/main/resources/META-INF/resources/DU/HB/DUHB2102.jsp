<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/js/css/infostyle.css">
<EF:EFPage >
	<EF:EFRegion id="envdevic" title="记录集" rowNo="true">
		<EF:EFGrid blockId="envdevic" autoDraw="false" sort="single" rowNo="false" checkMode="single,row">
			<EF:EFColumn ename="envdeviceid" cname="产污设施id" enable="false" hidden="true" />
			<EF:EFColumn ename="deviceid" cname="产污设施编号" enable="false"  hidden="true" />
			<EF:EFColumn ename="devicename" cname="产污设施名称" enable="false" />

		</EF:EFGrid>
	</EF:EFRegion>

   <EF:EFRegion id="inqu" title="生产设施信息编辑" type="query" efRegionShowClear="true" efRegionSave="true">
	   <div class="row divtop"><div class="divtitle"><b>主要信息</b></div></div>
	   <div class="row divtop">
	       <%--  <div class="col-xs-6 divcol">
                 <EF:EFInput ename="inqu_status-0-deviceid"  cname="产污设施编号" colWidth="12" /> 
            </div> --%>
            <div class="col-xs-6 divcol">
                 <EF:EFInput ename="inqu_status-0-devicename"  cname="废水类别" colWidth="12" /> 
            </div>
        </div>
        <div class="row divtop">
	        <div class="col-xs-6" divcol>
               <EF:EFInput ename="inqu_status-0-outto" cname="排放去向" colWidth="12" />
            </div>
            <div class="col-xs-6" divcol>
               <EF:EFInput ename="inqu_status-0-portouttype" cname="排放方式" colWidth="12" />
            </div>
        </div>
        <div class="row divtop">
            <div class="col-xs-6" divcol>
               <EF:EFInput type="textarea" ename="inqu_status-0-outrule" cname="排放规律" colWidth="12" />
            </div>
            <div class="col-xs-6" divcol>
                 <EF:EFInput type="textarea" ename="inqu_status-0-factorname"  cname="污染物种类" colWidth="12" /> 
            </div>
        </div>

       <div class="row divhr"></div>
       <div class="row divtop"><div class="divtitle"><b>排口信息</b></div></div>
        <div class="row divtop">
            <div class="col-xs-6 divcol">
                <EF:EFInput ename="inqu_status-0-portid" readonly="true" cname="排放口编号" colWidth="12" />
            </div>
            <div class="col-xs-6 divcol">
                 <EF:EFInput ename="inqu_status-0-portname" readonly="true" cname="排放口名称" colWidth="12" />
            </div>
       </div>
        
<%--         <div class="row divtop">
            <div class="col-xs-6 divcol">
                 <EF:EFInput ename="inqu_status-0-porttype" cname="排放口类型 " colWidth="12" />
            </div>
            
            <div class="col-xs-6 divcol">
                <EF:EFInput ename="inqu_status-0-isright" cname="排放口设置是否符合要求" colWidth="12" />
            </div>
       </div> --%>
       <div class="row divhr"></div>
       <div class="row divtop"><div class="divtitle"><b>其它信息</b></div></div>
       <div class="row divbottom">
            <div class="col-xs-6 divcol">
                <EF:EFInput type="textarea" ename="inqu_status-0-other" cname="其他信息" colWidth="12" />
            </div>
        </div>
<%--         <div class="row">
            <div class="col-xs-10">
                <EF:EFInput ename="inqu_status-0-envdeviceid" type="hidden" enable="false"/>
            </div>
            <div class="col-xs-2">
                <input value="保存" style="float:right;margin-top:8px;" class="k-button k-button-icontext" type="button" id="submitButton"/>
            </div>
        </div> --%>
        
      	<EF:EFWindow id="showEnvdevic" width="80%" height="80%" refresh="true">
		</EF:EFWindow>
	 </EF:EFRegion>
</EF:EFPage>
