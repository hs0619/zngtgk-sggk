<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
<EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
                <div class="row">
                    <EF:EFSelect ename="inqu_status-0-departmentid" cname="厂部"  colWidth="4"> </EF:EFSelect>
                    <EF:EFSelect ename="inqu_status-0-porttypeid" cname="监测点类型"  colWidth="4"></EF:EFSelect>  
                    <EF:EFSelect ename="inqu_status-0-siteid" cname="监测点"  colWidth="4"> </EF:EFSelect> 
                </div>
		        <div class="row">
		            <EF:EFSelect ename="inqu_status-0-typeid"  cname="数据类型"  serviceName="DUHC2004"  methodName="initSelectData" resultId="type"  textField="typename" valueField="typeid" colWidth="4"> </EF:EFSelect> 
                    <EF:EFDatePicker ename="inqu_status-0-startdate" cname="开始日期" format="yyyy-MM-dd" colWidth="4">
				    </EF:EFDatePicker>
				    <EF:EFDatePicker ename="inqu_status-0-enddate" cname="结束日期"  format="yyyy-MM-dd" colWidth="4">
				    </EF:EFDatePicker>
                    
                </div>
                <div class="row">
		            
                </div>
			 </EF:EFRegion>
			 <EF:EFRegion id="online" title="记录集" > 
	               <div>
                        <div class="k-content" style="overflow: auto">
				            <div id="ef_grid_oldbdata"></div>
				        </div>
		            </div>
			 </EF:EFRegion>
</EF:EFPage>