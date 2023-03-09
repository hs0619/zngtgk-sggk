<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/js/common/common.js"></script>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="因子明细" type="query" efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<div class="col-xs-10">
				<EF:EFInput ename="inqu_status-0-oprationType" type="hidden" enable="false" />
				<EF:EFInput ename="inqu_status-0-monitorname" type="hidden" enable="false" />
				<EF:EFInput ename="inqu_status-0-factorname" type="hidden" enable="false" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
				<EF:EFSelect ename="inqu_status-0-monitorid" cname="监测类型" colWidth="10" required="true">
					<EF:EFOption value="" label="--请选择--" />
					<EF:EFOption value="01" label="废气" />
					<EF:EFOption value="02" label="废水" />
					<EF:EFOption value="03" label="噪声" />
					<EF:EFOption value="04" label="空气质量" />
					<EF:EFOption value="06" label="无组织" />
                    <EF:EFOption value="08" label="降尘" />
					<EF:EFOption value="00" label="其他" />
				</EF:EFSelect>
			</div>
			
		</div>
		<br>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
				<EF:EFSelect ename="inqu_status-0-factorid" cname="监测因子" colWidth="10" required="true" >
					<EF:EFOption label="--请选择--" value="" />
					<EF:EFOptions blockId="factor" textField="factorname" valueField="factorid" />
				</EF:EFSelect>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
				<EF:EFSelect ename="inqu_status-0-unit" cname="因子单位" colWidth="10" required="false" >
					<EF:EFOption label="--请选择--" value="" />
					<EF:EFOption label="mg/m3" value="mg/m3" />
					<EF:EFOption label="mg/L" value="mg/L" />
					<EF:EFOption label="t/h" value="t/h" />
					<EF:EFOption label="μg/L" value="μg/L" />
					<EF:EFOption label="dB" value="dB" />
					<EF:EFOption label="级" value="级" />
					<EF:EFOption label="℃" value="℃" />
					<EF:EFOption label="KPa" value="KPa" />
					<EF:EFOption label="m/s" value="m/s" />
					<EF:EFOption label="%" value="m/s" />
					<EF:EFOption label="t/(km2·月)" value="t/(km2·月)" />
				</EF:EFSelect>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
				<EF:EFSelect ename="inqu_status-0-isplaninner" cname="汇总页面计划内显示" colWidth="10" required="false" >
					<EF:EFOption value="" label="" />
					<EF:EFOption value="1" label="是" />
					<EF:EFOption value="0" label="否" />
				</EF:EFSelect>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
				<EF:EFSelect ename="inqu_status-0-isplanout" cname="汇总页面计划外显示" colWidth="10" required="false" >
					<EF:EFOption value="" label="" />
					<EF:EFOption value="1" label="是" />
					<EF:EFOption value="0" label="否" />
				</EF:EFSelect>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
				<EF:EFSelect ename="inqu_status-0-israte" cname="兑现率页面显示" colWidth="10" required="false" >
					<EF:EFOption value="" label="" />
					<EF:EFOption value="1" label="是" />
					<EF:EFOption value="0" label="否" />
				</EF:EFSelect>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
				<EF:EFSelect ename="inqu_status-0-iscompare" cname="比对页面显示" colWidth="10" required="false" >
					<EF:EFOption value="" label="" />
					<EF:EFOption value="1" label="是" />
					<EF:EFOption value="0" label="否" />
				</EF:EFSelect>
			</div>
		</div>
		<br>
	</EF:EFRegion>
</EF:EFPage>
