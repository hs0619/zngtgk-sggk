<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >

<EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
		        <div class="row">
                    <EF:EFSelect ename="inqu_status-0-monitorid"  cname="类型"   colWidth="3"> </EF:EFSelect>
                    <EF:EFSelect ename="inqu_status-0-porttypeid" cname="监测点类型"  colWidth="3">
                    	
                    </EF:EFSelect>
                    <EF:EFSelect ename="inqu_status-0-departmentid" cname="厂部"   colWidth="3"> </EF:EFSelect> 
                    <EF:EFSelect ename="inqu_status-0-siteid" cname="监测点"   colWidth="3"> </EF:EFSelect>
                </div>
                <div id="remark"   style="float: left; margin: 10px auto 0px; font-size: 14.6px; color: #b37400;">
                    备注：“日期”显示黄色背景表示该监测点已经离线(120分钟内无数据)
                </div>
			 </EF:EFRegion>
			 <EF:EFRegion id="online" title="记录集"> 
			         <div title="在线数据列表">
	                    <div class="k-content" style="overflow: auto">
				            <div id="ef_grid_oldata"></div>
				        </div>
		             </div>
			 </EF:EFRegion>
</EF:EFPage>