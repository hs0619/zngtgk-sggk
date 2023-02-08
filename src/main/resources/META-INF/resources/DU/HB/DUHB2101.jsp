<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx}/js/css/infostyle.css">
<EF:EFPage >
   <EF:EFRegion id="inqu" title="废水污染防治设施信息" type="query" efRegionShowClear="true" efRegionSave="true">
	   <div class="row divtop"><div class="divtitle"><b>主要信息</b></div></div>
       <div class="row divtop">
           <div class="col-xs-6">
               <EF:EFSelect ename="inqu_status-0-departmentid"  cname="厂部" colWidth="12" >
                   <EF:EFOptions blockId="departList" textField="departmentName" valueField="departmentId" />
               </EF:EFSelect>
           </div>
           <div class="col-xs-6">
               <EF:EFSelect ename="inqu_status-0-procedureid"  cname="工序" colWidth="12" >
                   <EF:EFOption label="" value="" />
                   <EF:EFOptions blockId="procedureList" textField="departmentName"  valueField="departmentId" />
               </EF:EFSelect>
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
                 <EF:EFInput ename="inqu_status-0-origin"  cname="废水类别" colWidth="12" /> 
            </div> --%>
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-handmethod"  cname="处理工艺" colWidth="12" /> 
            </div>
        </div>
		<div class="row divtop">
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-handfactors" cname="污染物种类" colWidth="12" />
            </div>
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-numbers" cname="台（套）" colWidth="12" />
            </div>
       </div>
       
       <div class="row divtop">
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-handability" cname="设计处理能力（m3/h）" colWidth="12" />
            </div>
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-shandnum" cname="处理量（m3/h）" colWidth="12" />
            </div>
            
       	</div>
	 	<div class="row divtop">
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-isexecute" cname="是否为可行技术" colWidth="12" />
            </div>
            <div class="col-xs-6">
               <EF:EFInput ename="inqu_status-0-runtime" cname="投运时间" colWidth="12" />
            </div>
       	</div>
       	<%-- <div class="row divtop">
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-facilitydepart" cname="污染防治设施单位" colWidth="12" />
            </div>
            <div class="col-xs-6">
                <EF:EFInput ename="inqu_status-0-outstandard" cname="排放标准" colWidth="12" />
            </div>
      	</div> --%>
       
       	<div class="row divtop">
            <div class="col-xs-6"  >
                <EF:EFInput type="textarea" ename="inqu_status-0-remark" style="width:100%;" rows="5"  cname="污染防治设施其他信息" colWidth="12" />
            </div>
            <div class="col-xs-6"  >
                <EF:EFInput type="textarea" ename="inqu_status-0-mark" style="width:100%;" rows="5"  cname="备注" colWidth="12"
                            />
            </div>
            <SPAN>【无污染防治设施时，备注填写“废水类别-无对应的污染防治设施”。例如：超滤浓水-无对应的污染防治设施】</SPAN>
       	</div>
        <div class="row divhr"></div>
        <div class="row divtop"><div class="divtitle"><b>设备参数</b></div></div>
        <div class="row divtop">
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-devicecode" cname="产污设施编码"   readonly="true" colWidth="12" />
            </div>
            <div class="col-xs-6">
                 <EF:EFInput ename="inqu_status-0-devicenames" cname="产污设施名称"   readonly="true" colWidth="12" />
            </div>
       </div>
        
        <div class="row divtop">
            <div class="col-xs-6">
                <EF:EFInput type="textarea" ename="inqu_status-0-devicemodel" cname="水泵型号/容量" colWidth="12" />
            </div>
            <div class="col-xs-6">
                 <EF:EFInput type="textarea" ename="inqu_status-0-motormodel" cname="电机型号/容量" colWidth="12" />
            </div>
       	</div>
        
        <div class="row divtop">
            <div class="col-xs-6">
                <EF:EFInput type="textarea" ename="inqu_status-0-controllermodel" cname="变频器型号/容量" colWidth="12" />
            </div>
            <div class="col-xs-6">
                 <EF:EFInput type="textarea" ename="inqu_status-0-situation" cname="控制系统情况" colWidth="12" />
            </div>
       </div>
        <div class="row">
            <div class="col-xs-10">
                <EF:EFInput ename="inqu_status-0-oprationType" type="hidden" enable="false"/>
                <EF:EFInput ename="inqu_status-0-facilityid" type="hidden" enable="false"/>
            </div>
            <div class="col-xs-2">
                <input value="保存" style="float:right;margin-top:8px;" class="k-button k-button-icontext" type="button" id="submitButton"/>
            </div>
        </div>
	 </EF:EFRegion>
</EF:EFPage>
