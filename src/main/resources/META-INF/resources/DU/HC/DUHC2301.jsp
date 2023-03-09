<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage >
   <EF:EFRegion id="inqu" title="人工监测数据信息"   efRegionShowClear="true" efRegionSave="true">
   		<div class="row">
   			<div class="col-xs-2"></div>
   			<div class="col-xs-4">
   				<EF:EFInput ename="inqu_status-0-oprationType"  cname="操作类型" colWidth="14" type="hidden"/>
   				 <EF:EFSelect ename="inqu_status-0-departmentid"  cname="单位" colWidth="14" />
   			</div>
   			<div class="col-xs-4">
             	<EF:EFSelect ename="inqu_status-0-monitorid"  cname="监测类型" colWidth="14" /> 
   			</div>
        </div>
        <div class="row">
        	<div class="col-xs-2"></div>
        	<div class="col-xs-4">
        		<EF:EFSelect ename="inqu_status-0-siteid" required="true" cname="监测点" colWidth="14" filter="contains"/>
        	</div>
        	<div class="col-xs-4">
        		<EF:EFDatePicker ename="inqu_status-0-itemtime" cname="采样时间"  interval="1" role="datetime" 
             	 format="yyyy-MM-dd HH:mm" colWidth="14" required="true" />
        	</div>
        </div>
        <div class="row">
        	<div class="col-xs-10">
                <EF:EFSelect ename="inqu_status-0-planid"  cname="计划名称" colWidth="14" required="true"/> 
        	</div>
        	<div class="col-xs-2"></div>
        </div>
        <div class="row">
			<div class="col-md-8">
				<div id="lastitemtime"
					style="float: left; margin: 10px auto 0px; font-size: 14.6px; color: #b37400;">上一次采样时间：</div>
			</div>
			<div class="col-md-4">
				<div id="btndiv" style="float: right; margin: 3px auto 0px;"></div>
			</div>
		</div>
	 </EF:EFRegion>
	 <EF:EFRegion id="result" title="人工监测因子">
		<EF:EFGrid blockId="result" autoDraw="false" sort="single" autoBind="false"  rowNo="true"
				queryMethod="queryFactor">
			<EF:EFColumn ename="factorid" cname="因子id"  hidden="true" enable="false"/>
			<EF:EFColumn ename="factorname" cname="污染因子"   enable="false"/>
			<EF:EFColumn ename="itemunit" cname="单位" enable="false"  />
			<EF:EFColumn ename="itemlimit" cname="限值" enable="true" required="false" />
			<EF:EFColumn ename="itemvalue" cname="实测值" enable="true"  required="true"/>
			<EF:EFColumn ename="itemvaluezs" cname="折算值" enable="true"  required="false"/>
			<EF:EFColumn ename="overlimitinfo" cname="超标说明" enable="true"  required="false"/>
			<EF:EFColumn ename="itemmark" cname="备注" enable="true" required="false" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
