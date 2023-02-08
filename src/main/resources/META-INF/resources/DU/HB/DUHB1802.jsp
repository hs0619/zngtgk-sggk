<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/js/css/infostyle.css">
<EF:EFPage >
	<EF:EFRegion id="result" title="记录集" rowNo="true">
			<EF:EFGrid blockId="result" autoDraw="false" sort="single" showCount="true"
				rowNo="false" checkMode="single,row">
				<EF:EFColumn ename="facilityid" cname="污染防治设施id" enable="false" hidden="true" />
				<EF:EFColumn ename="facilitycode" cname="污染防治设施编号" enable="false" locked="true" width="200"/>
				<EF:EFColumn ename="facilityname" cname="污染防治设施名称" enable="false" />
				<EF:EFColumn ename="mark" cname="备注" enable="false" />
			</EF:EFGrid>
		</EF:EFRegion>
	<EF:EFRegion id="inqu" title="废气污染防治设施信息" type="query" efRegionShowClear="true" efRegionSave="true">
		<EF:EFInput ename="inqu_status-0-processid" cname="设施编号"  type="hidden" colWidth="12" />
		
		<div class="row divtop">
			<div class="divtitle">
				<b>主要信息</b>
			</div>
		</div>
		<div class="row divtop">
	        <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-departmentname"  cname="厂部" colWidth="12" /> 
            </div>
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-procedurename"  cname="工序" colWidth="12" /> 
            </div>
        </div>
		<div class="row divtop">
	        <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-facilitycode"  cname="污染防治设施编号" colWidth="12" /> 
            </div>
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-facilityname"  cname="污染防治设施名称" colWidth="12" /> 
            </div>
        </div>
		<div class="row divtop">
           <%--  <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-facilitytype"  cname="污染防治设施类型" colWidth="12" /> 
            </div> --%>
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-isexecute" cname="是否为可行技术" colWidth="12" />
            </div>
            <div class="col-xs-6">
               <EF:EFInput ename="inqu_status-0-runtime" cname="投运时间" colWidth="12" />
            </div>
       	</div>
       	<div class="row divtop">
           <div class="col-xs-6">
               <EF:EFInput ename="inqu_status-0-craft" cname="污染防治设施工艺" colWidth="12" />
           </div>
           <div class="col-xs-6">
               <EF:EFInput ename="inqu_status-0-numbers" cname="台（套）" colWidth="12" />
           </div>
       </div>
        <div class="row divtop">
	        <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-pollutantname"  cname="污染物名称" colWidth="12" /> 
            </div>
             <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-handability" cname="设计能力（m3/h）" colWidth="12" />
            </div>
        </div>
        
        
        <div class="row divtop">
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-handabilityAct" cname="实际处理能力（m3/h）" colWidth="12" />
            </div>
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-devicearea" cname="过滤面积（m2）" colWidth="12" />
            </div>
       	</div>
       	
       	<div class="row divtop">
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-deviceflow" cname="过滤风速（m/s）" colWidth="12" />
            </div>
             <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-filter" cname="滤料材质" colWidth="12" />
            </div>
       	</div>
       	
		<div class="row divtop">
            <div class="col-xs-6"  >
                <EF:EFInput type="textarea" ename="inqu_status-0-remark" style="width:100%;" rows="5"  cname="污染防治设施其他信息" colWidth="12" />
            </div>
            <div class="col-xs-6"  >
                <EF:EFInput type="textarea" ename="inqu_status-0-mark" style="width:100%;" rows="5"  cname="备注" colWidth="12" />
            </div>
       	</div>
		<div class="row divtop"></div>
		<div class="row divhr"></div>
		<div class="row divtop">
			<div class="divtitle">
				<b>设备参数</b>
			</div>
		</div>
 	 <div class="row divtop">
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-devicecode" cname="产污设施编码"  readonly="true" colWidth="12" />
            </div>
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-devicenames" cname="产污设施名称"  readonly="true" colWidth="12" />
            </div>
       	</div>
       	<div class="row divtop">
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-situation" cname="控制系统情况" colWidth="12" />
            </div>
       </div>
       	
		<div class="row divtop">
			<div class="col-xs-6">
                <EF:EFInput type="textarea" ename="inqu_status-0-devicemodel" cname="风机型号" colWidth="12" />
            </div>
            <div class="col-xs-6">
                 <EF:EFInput type="textarea" ename="inqu_status-0-motormodel" cname="电机型号/容量" colWidth="12" />
            </div>
       </div>
		<div class="row divtop">
			<div class="col-xs-10">
				<EF:EFInput ename="inqu_status-0-oprationType" type="hidden"
					enable="false" />
				<EF:EFInput ename="inqu_status-0-facilityid" type="hidden"
					enable="false" />
			</div>
			<!-- <div class="col-xs-2">
				<input value="保存" style="float: right; margin-top: 8px;"
					class="k-button k-button-icontext" type="button" id="submitButton" />
			</div> -->
		</div>
	</EF:EFRegion>
</EF:EFPage>
