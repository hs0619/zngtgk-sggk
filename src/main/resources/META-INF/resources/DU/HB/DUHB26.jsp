<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage title="报警管理">
<link rel="stylesheet" type="text/css" href="${ctx}/js/css/layui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/css/mg_own_hb.css">
	<div class="row">
		<div class="col-md-3">
			<div id="butdiv" style="float: right;"></div>
		</div>
	</div>
	<div class="row">
	    <div class="col-md-3">
	        <EF:EFRegion id="tree" title="人员信息"  fitHeight="true">
        		<ul class="layui-menu" id="phoneInfo" >
	        	</ul>

		        <EF:EFInput ename="inqu_status-0-personId"  type="hidden" />
	        </EF:EFRegion>
	        
	    </div>
	    
	    
	    <div class="col-md-9">
	        <EF:EFRegion title="管理配置" id="inqu" style="height:100%;"  fitHeight="true">	
	        	<div id="treeview" style="font-size:18px;"></div>
	        </EF:EFRegion>
	    </div>
	</div>


</EF:EFPage>