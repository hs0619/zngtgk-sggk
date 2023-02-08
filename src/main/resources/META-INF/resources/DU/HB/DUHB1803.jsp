<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx}/js/css/infostyle.css">
<EF:EFPage>
	<EF:EFRegion id="result" title="记录集" rowNo="true">
		<EF:EFGrid blockId="result" autoDraw="false" sort="single" rowNo="false" checkMode="single,row">
			<EF:EFColumn ename="dischargeportid" cname="排口id" enable="false" hidden="true" />
			<EF:EFColumn ename="dischargeportcode" cname="排口编号" enable="false" locked="true" />
			<EF:EFColumn ename="dischargeportname" cname="排口名称" enable="false" />
			<EF:EFColumn ename="description" cname="备注" enable="false" />

		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFRegion id="inqu" title="排口信息" type="query" efRegionShowClear="true" efRegionSave="true">
		<div class="row divtop">
			<div class="divtitle">
				<b>主要信息</b>
			</div>
		</div>
		<div class="row divtop">
			<div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-dischargeportcode" cname="排口编号"
					colWidth="12" />
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-dischargeportname" cname="排口名称"
					colWidth="12" required="true" />
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFSelect ename="inqu_status-0-dischargeClassify" cname="排放口类型"
					colWidth="12"  required="true">
					<EF:EFOption label="一般排放口" value="一般排放口" />
					<EF:EFOption label="主要排放口" value="主要排放口" />
					<EF:EFOption label="其它" value="其它" />
				</EF:EFSelect>
			</div>
		</div>
		<!-- 		<div class="row divhr"></div> -->
				<div class="row divtop">
			<div class="col-xs-4 divcol">
				<EF:EFSelect ename="inqu_status-0-isformal" cname="是否正式"
					colWidth="12" required="true">
				</EF:EFSelect>
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFSelect ename="inqu_status-0-status" cname="使用" colWidth="12"
					required="true">
				</EF:EFSelect>
			</div>
		</div>
		<div class="row divtop">
			<div class="col-xs-4 divcol">
				<EF:EFSelect ename="inqu_status-0-monitorid" cname="监测类型"	
					colWidth="12" required="true">
				</EF:EFSelect>
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFSelect ename="inqu_status-0-departid" cname="厂部" colWidth="12"
					required="true">
				</EF:EFSelect>
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFSelect ename="inqu_status-0-procid" cname="工序" colWidth="12"
					required="true">
				</EF:EFSelect>
			</div>
		</div>
		<div class="row divhr"></div>
		<div class="row divtop">
			<div class="divtitle"><b>位置信息</b></div>
        </div>
		<div class="row divtop">
			<div class="col-xs-4  divcol">
				<EF:EFInput ename="inqu_status-0-position" cname="位置" colWidth="12" />
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-longitude" cname="经度" colWidth="12" />
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-latitude" cname="纬度" colWidth="12" />
			</div>
		</div>
		<div class="row divhr"></div>
		<div class="row divtop"> <div class="divtitle"><b>排放信息</b></div></div>
		<div class="row divtop">
			<div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-dischargemode" cname="排放方式" colWidth="12" />
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-outto" cname="排放去向" colWidth="12" />
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-outtype" cname="排放形式" colWidth="12" />
			</div>
		</div>
		<div class="row divtop">
			<div class="col-xs-4 divcol">
				<EF:EFInput type="textarea" ename="inqu_status-0-outrule" cname="排放规律" colWidth="12" />
			</div>
		</div>
		<div class="row divtop">
			<div class="divtitle"><b>其它信息</b></div>
        </div>
		<div class="row divtop">
			<div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-exhaustheight" cname="排气筒高度(m)"
					colWidth="12" />
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-exhaustinside" cname="排气筒出口内径(m)"
					colWidth="12" />
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-portTemperature" cname="排气筒温度(℃)"  colWidth="12" />
			</div>
		</div>
		<div class="row divtop">
			<%-- <div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-importantport" cname="级别"
					colWidth="12" />
			</div> --%>
			<div class="col-xs-4 divcol">
				<EF:EFSelect ename="inqu_status-0-setright" cname="符合要求 "
					colWidth="12">
				</EF:EFSelect>
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFSelect ename="inqu_status-0-ismap" cname="地图展示" colWidth="12">
				</EF:EFSelect>
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFSelect ename="inqu_status-0-signform" cname="是否有标志牌"
					type="textarea" colWidth="12" />
			</div>
		</div>
		<div class="row divtop">
			<div class="col-xs-4 divcol">
				<EF:EFSelect ename="inqu_status-0-countrypoint" cname="国控点 "
					colWidth="12">
				</EF:EFSelect>
			</div>
			<%-- <div class="col-xs-4 divcol">
				<EF:EFSelect ename="inqu_status-0-citypoint" cname="市控点"
					colWidth="12">
				</EF:EFSelect>
			</div> --%>
			<div class="col-xs-4 divcol">
				<EF:EFSelect ename="inqu_status-0-companypoint" cname="内控点"
					colWidth="12">
				</EF:EFSelect>
			</div>
		</div>
		<div class="row divbottom">
			<%-- <div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-signform" cname="标志牌形式"
					type="textarea" colWidth="12" />
			</div> --%>
			<div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-executionstandard" cname="执行标准"
					type="textarea" colWidth="12" />
			</div>
			<div class="col-xs-4 divcol">
				<EF:EFInput ename="inqu_status-0-description" cname="备注"
					type="textarea" colWidth="12" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-10">
				<EF:EFInput ename="inqu_status-0-oprationType" type="hidden"
					enable="false" />
				<EF:EFInput ename="inqu_status-0-dischargeportid" type="hidden"
					enable="false" />
			</div>
			<!-- <div class="col-xs-2">
				<input value="保存" style="float: right; margin-top: 8px;"
					class="k-button k-button-icontext" type="button" id="submitButton" />
			</div> -->
		</div>
	</EF:EFRegion>
</EF:EFPage>
