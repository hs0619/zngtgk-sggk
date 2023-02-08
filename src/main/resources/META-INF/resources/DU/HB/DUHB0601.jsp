<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="因子编辑">
   <EF:EFRegion id="inqu" title="因子信息" type="query" efRegionShowClear="true" efRegionSave="true">
   
       <div class="row">
            <div class="col-xs-2"></div>
            <div class="col-xs-8">
                 <EF:EFInput ename="inqu_status-0-pfportid" cname="排口ID" colWidth="12" disabled="true"/>
            </div>
            <div class="col-xs-2"></div>
        </div>
        
        <div class="row">
            <div class="col-xs-2"></div>
            <div class="col-xs-8">
                 <EF:EFInput ename="inqu_status-0-pfportname" cname="排口名称" colWidth="12" disabled="true"/>
            </div>
            <div class="col-xs-2"></div>
        </div>
        
        
       <div class="row">
            <div class="col-xs-2"></div>
            <div class="col-xs-8">
                 <EF:EFSelect ename="inqu_status-0-pfmonitorid"  cname="监测类型" colWidth="12" required="true"> 
                 </EF:EFSelect>
            </div>
            <div class="col-xs-2"></div>
        </div>
        
        <div class="row">
            <div class="col-xs-2"></div>
            <div class="col-xs-8">
            	<EF:EFMultiSelect colWidth="12" cname="排口因子" ename="inqu_status-0-pffactorid" ratio="4:8" required="true" autoClose="false" >
		        </EF:EFMultiSelect>
            </div>
            <div class="col-xs-2"></div>
        </div>
        
        
        <div class="row">
            <div class="col-xs-2"></div>
            <div class="col-xs-8">
                 <EF:EFSelect ename="inqu_status-0-status" cname="是否启用" required="true" colWidth="12"> 
				</EF:EFSelect>
            </div>
            <div class="col-xs-2"></div>
        </div>
        
        <div class="row">
            <div class="col-xs-10">
                <EF:EFInput ename="inqu_status-0-oprationType" type="hidden" enable="false"/>
            </div>
            <div class="col-xs-2">
                <input value="保存" class="k-button k-button-icontext" type="button" id="submitButton"/>
            </div>
        </div>
	 </EF:EFRegion>
</EF:EFPage>
