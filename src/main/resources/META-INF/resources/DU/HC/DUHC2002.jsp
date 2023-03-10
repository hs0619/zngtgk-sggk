<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
<div class="row">
       <div class="col-md-12">
           <EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
               <div class="row">
                    <EF:EFSelect ename="inqu_status-0-departmentid" cname="厂部"  colWidth="4"> </EF:EFSelect>
                    <EF:EFSelect ename="inqu_status-0-porttypeid" cname="监测点类型"  colWidth="4"></EF:EFSelect>  
                    <EF:EFSelect ename="inqu_status-0-siteid" cname="监测点"  colWidth="4"> </EF:EFSelect> 
                </div>
		        <div class="row">
                    <EF:EFSelect ename="inqu_status-0-typeid"  cname="数据类型"  serviceName="DUHC2002"  methodName="initSelectData" resultId="type"  textField="typename" valueField="typeid" colWidth="4"> </EF:EFSelect> 
                    <EF:EFDatePicker ename="inqu_status-0-startdate" cname="开始日期" format="yyyy-MM-dd" colWidth="4">
				    </EF:EFDatePicker>
				    <EF:EFDatePicker ename="inqu_status-0-enddate" cname="结束日期"  format="yyyy-MM-dd" colWidth="4">
				    </EF:EFDatePicker>
                </div>
               <%--1、日期显示蓝色表示该时刻为停运期间。--%>
               <div id="remark"   style="float: left; margin: 10px auto 0px; font-size: 14.6px; color: #b37400;">
                   备注：2、N:正常，F:停运，St:启炉，Sd:停炉，B:闷炉，T:超过测量上限，C:校准，M:维护、修理，D:故障、断电，Md:无数据。
               </div>
			 </EF:EFRegion>
			 <EF:EFRegion id="online" title="记录集"  fitHeight="true"> 
			         <script src="${ctx}/js/echarts/dist/echarts.min.js"></script> 
	                 <EF:EFTab id="onlineinfo">
			                <div title="在线数据列表">
			                    <div class="k-content" style="overflow: auto">
						            <div id="ef_grid_oldata"></div>
						        </div>
				            </div>
				            <div title="在线数据图表">
				                <div class="row" >
					                <div class="col-xs-4"></div>
					                <div class="col-xs-4">
						                 <EF:EFSelect ename="inqu_status-0-zstype"  cname="是否折算" colWidth="12" index="0"> 
						                     <EF:EFOption label="实测" value="sc"/>
						                     <EF:EFOption label="折算" value="zs"/>
						                 </EF:EFSelect>
						            </div>
						            <div class="col-xs-4">
						                 <EF:EFSelect ename="inqu_status-0-factortype"  cname="监测因子" colWidth="12"> </EF:EFSelect>
						            </div>  
                                </div>
				                <div id="olchart" style="width: 100%;height: 450px;"></div>
				            </div>
			        </EF:EFTab> 
			 </EF:EFRegion>
            </div>

   </div>
</EF:EFPage>