<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage title="类别配置">
	<EF:EFRegion id="inqu" title="查询条件" type="query"
		efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-typename" cname="类别名称" type="text" colWidth="5" />
		</div>
		<div class="row">
			<div class="col-md-12">
				<div id="butdiv" style="float: right;"></div>
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集" rowNo="true">
		<EF:EFGrid blockId="result" autoDraw="false" sort="single"
			rowNo="true">
			<EF:EFColumn ename="typeid" cname="类别id" enable="false" hidden="true"/>
			<EF:EFColumn ename="typename" cname="类别名称" enable="true" required="true"  />
            <EF:EFColumn ename="sort" cname="排序" enable="true" required="false"  />
			<%-- <EF:EFColumn ename="createman" cname="创建人" enable="false"  />
			<EF:EFColumn ename="createtime" cname="创建时间" enable="false" />
			<EF:EFColumn ename="updateman" cname="修改人" enable="false" />
			<EF:EFColumn ename="updatetime" cname="修改时间" enable="false" /> --%>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>