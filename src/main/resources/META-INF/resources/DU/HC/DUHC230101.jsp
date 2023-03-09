<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage title="导入">
	<script src="${ctx}/js/ajaxfileupload.js"></script>
	<EF:EFRegion id="inqu" title="Excel导入 ">
		<div class="row">
			<div class="col-xs-4">
				<EF:EFInput ename="inqu_status-0-excelTemplatePath" cname="路径"  type="hidden" />
				<%-- <EF:EFSelect ename="inqu_status-0-departmentid" cname="厂部"  colWidth="14" disabled="true">
				</EF:EFSelect> --%>
			</div>
			<div class="col-xs-5">
				<input value="附件" class="k-button k-button-icontext" type="file" width="400"
					id="fileImport" name="fileImport" />
				<div style="float: right;">
					<input value="导入" class="k-button k-button-icontext" type="button"
						id="uploadButton" />
				</div>
			</div>
		</div>
	</EF:EFRegion>
</EF:EFPage>