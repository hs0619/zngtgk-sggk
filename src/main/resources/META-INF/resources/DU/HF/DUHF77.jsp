<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage >
	<script src="${ctx}/js/ajaxfileupload.js"></script>
	<EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-excelTemplatePath" cname="文件路径"  colWidth="12" type="hidden"/>
			<EF:EFSelect ename="inqu_status-0-filetype" cname="报告类型" colWidth="4">
				<EF:EFOption label="全部" value="%" />
				<EF:EFOptions blockId="filetype" textField="filetypeName" valueField="filetypeId"/>
			</EF:EFSelect>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div id="butdiv" style="float: right;"></div>
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="记录集">
		<div class="row">
				<EF:EFSelect ename="result_status-0-filetype" cname="报告类型" colWidth="4" required="true">
                    <EF:EFOption label="--请选择--" value="" />
                    <EF:EFOptions blockId="filetype" textField="filetypeName" valueField="filetypeId"/>
				</EF:EFSelect>
				<EF:EFInput ename="result_status-0-file" cname="附件" type="file" colWidth="4" required="true"/>
				<input value="上传附件" class="k-button k-button-icontext"  type="button" id="fileUpload" />
                <SPAN>【上传Word/Excel/Pdf/图片,不超过4M】</SPAN>
		</div>
		<EF:EFGrid blockId="result" autoDraw="false" sort="setted" rowNo="true">
			<EF:EFColumn ename="fileid" cname="辐射报告ID" enable="false" hidden="true"/>
			<EF:EFColumn ename="filepath" cname="文档全路径" enable="false" hidden="true"/>
			<EF:EFComboColumn ename="filetype" cname="报告类型" enable="false"
				blockName="filetype"  textField="filetypeName" valueField="filetypeId"/>
			<EF:EFColumn ename="filename" cname="报告名称" enable="false" />
			<EF:EFColumn ename="uploadman" cname="上传人" enable="false" />
			<EF:EFColumn ename="uploadtime" cname="上传时间" enable="false" sort="true"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>