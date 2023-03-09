<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/js/common/common.js"></script>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="计划明细" type="query" efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<div class="col-xs-10">
				<EF:EFInput ename="inqu_status-0-oprationType" type="hidden" enable="false" />
				<EF:EFInput ename="inqu_status-0-planid" type="hidden" enable="false" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<EF:EFDatePicker ename="inqu_status-0-clockyear" cname="年份"
					format="yyyy" colWidth="14" required="true" />
			</div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-departmentid" cname="所属厂部" colWidth="14" required="true"></EF:EFSelect>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-monitorid" cname="监控类别" colWidth="14" required="true">
				</EF:EFSelect>
			</div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-siteid" cname="测点名称" colWidth="14" required="true" filter="contains"></EF:EFSelect>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-licenserate" cname="监测频率" required="true" colWidth="14">
                    <EF:EFOptions blockId="timeDime" textField="textfield" valueField="valuefield"/>
				</EF:EFSelect>
			</div>
			<div class="col-xs-4">
				<EF:EFInput ename="inqu_status-0-licensecount" cname="监测次数" required="true"
					colWidth="14" />
			</div>
			<div class="col-xs-2"></div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-plantype" cname="计入类型" colWidth="14" required="true">
					<EF:EFOption label="计划内" value="1" />
					<EF:EFOption label="计划外" value="0" />
				</EF:EFSelect>
			</div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-status"  cname="状态" colWidth="14" required="false">
					<EF:EFOption label="在用" value="1" />
					<EF:EFOption label="停用 " value="0" />
				</EF:EFSelect>
			</div>
			<div class="col-xs-2"></div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-10">
				<EF:EFInput ename="inqu_status-0-planname" cname="计划名称" disabled="true"
					colWidth="14" required="true" />
			</div>
			<div class="col-xs-2"></div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-iscompare" cname="是否比对"
					colWidth="14" required="false">
					<EF:EFOption label="没有比对" value="0" />
					<EF:EFOption label="有比对" value="1" />
				</EF:EFSelect>
			</div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-isentrust" cname="是否外委"
					colWidth="14" required="false">
                    <EF:EFOption label="非外委" value="0" />
					<EF:EFOption label="外委 " value="1" />
				</EF:EFSelect>
			</div>
			<div class="col-xs-2"></div>
		</div>
		
		<br>
		<div class="row" style="display:none;">
			<div class="col-xs-2"></div>
			<div class="col-xs-4" >
				<EF:EFSelect ename="inqu_status-0-checkrate" cname="比对频率" colWidth="14" >
					<EF:EFOption label="" value="" />
					<EF:EFOption label="周" value="WEEK" />
					<EF:EFOption label="月 " value="MONTH" />
					<EF:EFOption label="季度" value="SEASON" />
					<EF:EFOption label="半年" value="HALFYEAR" />
					<EF:EFOption label="年" value="YEAR" />
				</EF:EFSelect>
			</div>
			<div class="col-xs-4">
				<EF:EFInput ename="inqu_status-0-checkcount" cname="比对次数"
					colWidth="14" />
			</div>
			<div class="col-xs-2"></div>
		</div>

		<br>
		<div class="row">
			<div class="col-xs-10">
				<EF:EFInput ename="inqu_status-0-mark" colWidth="14" value=""
					type="textarea" cname="备注"  />
			</div>
			<div class="col-xs-2"></div>
		</div>
		<br>
        <div class="row" style="margin-top:10px;">
            <div class="col-md-12">
                <div id="butdiv" style="display: block;text-align:center;">
                </div>
            </div>
        </div>

		<div id="copy" style="display:none;">
			<div class="row" >
				<div class="col-xs-2"></div>
				<div class="col-xs-4">
					<EF:EFDatePicker ename="inqu_status-0-copyyear" cname="年份"
						format="yyyy" colWidth="14" required="true" />
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-xs-10">
					<EF:EFInput ename="inqu_status-0-copyname" cname="复制名称" disabled="true"
						colWidth="14" required="true" />
				</div>
				<div class="col-xs-2"></div>
			</div>
		</div>
		
	</EF:EFRegion>
</EF:EFPage>
