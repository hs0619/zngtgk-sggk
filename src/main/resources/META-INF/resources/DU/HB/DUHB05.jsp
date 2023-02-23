<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<div class="row">
		<div class="col-md-12">
			<EF:EFRegion id="inqu" title="查询条件" type="query"
				efRegionShowClear="true" efRegionSave="true">
				<div class="row">
					<EF:EFSelect ename="inqu_status-0-monitorid" cname="监测类型"
						colWidth="4">
						<EF:EFOption label="全部" value="" />
						<EF:EFOptions blockId="monitorList" textField="monitorname"
							valueField="monitorid" />
					</EF:EFSelect>
					
					<EF:EFSelect ename="inqu_status-0-departid" cname="厂部"
						colWidth="4">
						<EF:EFOptions blockId="departList" textField="departmentName"
							valueField="departmentId" />
					</EF:EFSelect>
					
					<EF:EFSelect ename="inqu_status-0-procid" cname="工序"
						colWidth="4">
						<EF:EFOption label="全部" value="" />
						<EF:EFOptions blockId="procedureList" textField="departmentName"
							valueField="departmentId" />
					</EF:EFSelect>
					
				</div>
				<div class="row">
					<EF:EFInput ename="inqu_status-0-dischargeportcode" cname="排口编号"
						colWidth="4" />
					<EF:EFInput ename="inqu_status-0-dischargeportname" cname="排口名称"
						colWidth="4" />
					<%--<EF:EFSelect ename="inqu_status-0-dischargeClassify" cname="排放口类型"--%>
						<%--colWidth="4">--%>
						<%--<EF:EFOption label="全部" value="" />--%>
						<%--<EF:EFOption label="一般排放口" value="一般排放口" />--%>
						<%--<EF:EFOption label="主要排放口" value="主要排放口" />--%>
						<%--<EF:EFOption label="其它" value="其它" />--%>
					<%--</EF:EFSelect>--%>

					<EF:EFMultiSelect ename="inqu_status-0-dischargeClassify" colWidth="4"  cname="排口类型" ratio="4:8" autoClose="false">
						<EF:EFOption value="一般排放口" label="一般排放口" />
						<EF:EFOption value="主要排放口" label="主要排放口" />
						<EF:EFOption value="其它" label="其它" />
					</EF:EFMultiSelect>
				</div>

				<div class="row">
				<EF:EFSelect ename="inqu_status-0-status" cname="是否使用" colWidth="4" enable="true" >
					<EF:EFOption label="全部" value="" />
					<EF:EFOption label="是" value="1" />
					<EF:EFOption label="否" value="0" />
				</EF:EFSelect>

<%--					<EF:EFSelect ename="inqu_status-0-isformal" cname="是否正式" colWidth="4" enable="true" >--%>
<%--					<EF:EFOption label="全部" value="" />--%>
<%--					<EF:EFOption label="正式" value="1" />--%>
<%--					<EF:EFOption label="虚拟" value="0" />--%>
<%--				</EF:EFSelect>--%>
				<EF:EFSelect ename="inqu_status-0-controlPoint" cname="点位" colWidth="4" enable="true">
					<EF:EFOption label="其他" value=""/>
					<EF:EFOption label="国控点" value="1"/>
					<EF:EFOption label="内控点" value="2"/>
					<EF:EFOption label="市控点" value="3"/>
				</EF:EFSelect>

					<EF:EFSelect ename="inqu_status-0-dischargeStatus" cname="排放口状态" colWidth="4" enable="true">
						<EF:EFOption label="其他" value=""/>
						<EF:EFOption label="正常排放" value="1"/>
						<EF:EFOption label="临时排放" value="2"/>
						<EF:EFOption label="停用" value="3"/>
					</EF:EFSelect>
				</div>


			</EF:EFRegion>
			<EF:EFRegion id="result" title="记录集"  fitHeight="true"  >
				<EF:EFGrid blockId="result" autoDraw="false" sort="setted" rowNo="true">
					<EF:EFColumn ename="dischargeportid" cname="排口ID" hidden="true"
						enable="false" />
					<EF:EFColumn ename="operation" cname="操作" enable="false"
						locked="true" width="170" />
					<EF:EFComboColumn ename="monitorid" cname="监测类型" enable="false"
									  blockName="monitorList" labelProperty="monitorname"
									  valueProperty="monitorid" textField="monitorname"
									  valueField="monitorid" locked="true"/>
					<EF:EFColumn ename="dischargeportcode" cname="排口编号" locked="true" />
					<EF:EFColumn ename="dischargeportname" width="230" cname="排口名称" locked="true" />
					<EF:EFColumn ename="dischargeClassify" cname="排放口类型"  enable="false" /> 
<%--					<EF:EFComboColumn ename="isformal" cname="是否正式" enable="false"--%>
<%--						blockName="formalList" labelProperty="formalname"--%>
<%--						valueProperty="formalid" textField="formalname"--%>
<%--						valueField="formalid" />--%>
					<EF:EFComboColumn ename="departid" cname="厂部" enable="false"
						blockName="departList" labelProperty="departmentName"
						valueProperty="departmentId" textField="departmentName"
						valueField="departmentId" />
					<EF:EFComboColumn ename="procid" cname="工序" enable="false"
						blockName="procedureList" labelProperty="departmentName"
						valueProperty="departmentId" textField="departmentName"
						valueField="departmentId" />
					<EF:EFColumn ename="position" cname="作业区" enable="false"/>
<%--					<EF:EFColumn ename="description" cname="备注" enable="false"/>--%>


					<EF:EFColumn ename="executionstandard" cname="执行标准" enable="false"/>
					<EF:EFColumn ename="longitude" cname="经度" enable="false"/>
					<EF:EFColumn ename="latitude" cname="纬度" enable="false"/>
					<EF:EFComboColumn ename="signform" cname="是否有标志碑" blockName="flagList" enable="false"
						labelProperty="flagname" valueProperty="flagid"
						textField="flagname" valueField="flagid" />


					<EF:EFComboColumn ename="setright" cname="符合要求"
						blockName="flagList" labelProperty="flagname" enable="false"
						valueProperty="flagid" textField="flagname" valueField="flagid" />
<%--					<EF:EFComboColumn ename="ismap" cname="地图展示" blockName="flagList" enable="false"--%>
<%--						labelProperty="flagname" valueProperty="flagid"--%>
<%--						textField="flagname" valueField="flagid" />--%>

					<EF:EFColumn ename="exhaustheight" cname="排气筒高度(m)" enable="false"/>
					<EF:EFColumn ename="exhaustinside" cname="排气筒出口内径(m)" enable="false"/>
<%--					<EF:EFColumn ename="portTemperature" cname="排气筒温度(℃)" enable="false"/>--%>
					<EF:EFColumn ename="outto" cname="排放去向" enable="false"/>
					<EF:EFColumn ename="outtype" cname="排放形式" enable="false"/>
					<EF:EFColumn ename="outrule" cname="排放规律" enable="false"/>
					<EF:EFColumn ename="dischargemode" cname="排放方式" enable="false"/>
<%-- 					<EF:EFComboColumn ename="countrypoint" cname="国控点"--%>
<%--						blockName="flagList" labelProperty="flagname" enable="false"--%>
<%--						valueProperty="flagid" textField="flagname" valueField="flagid" />--%>
<%--					&lt;%&ndash;<EF:EFComboColumn ename="citypoint" cname="市控点"--%>
<%--						blockName="flagList" labelProperty="flagname" enable="false"--%>
<%--						valueProperty="flagid" textField="flagname" valueField="flagid" />&ndash;%&gt;--%>
<%--					<EF:EFComboColumn ename="companypoint" cname="内控点"--%>
<%--						blockName="flagList" labelProperty="flagname" enable="false"--%>
<%--						valueProperty="flagid" textField="flagname" valueField="flagid" />--%>
<%--					<EF:EFComboColumn ename="status" cname="是否使用" blockName="flagList"--%>
<%--						labelProperty="flagname" valueProperty="flagid" enable="false"--%>
<%--						textField="flagname" valueField="flagid" />--%>

					<EF:EFComboColumn ename="controlPoint" cname="点位"
									  blockName="blockPoint" labelProperty="pointname" enable="false"
									  valueProperty="pointid" textField="pointname" valueField="pointid" />
					<EF:EFComboColumn ename="dischargeStatus" cname="排放口状态"
									  blockName="blockStatus" labelProperty="statusname" enable="false"
									  valueProperty="statusid" textField="statusname" valueField="statusid" />


					<EF:EFColumn ename="description" cname="备注" enable="false"/>
				</EF:EFGrid>
				<EF:EFWindow id="showDCP" width="91%" height="90%" refresh="true">
				</EF:EFWindow>
				<EF:EFWindow id="showSF" width="90%" height="90%" refresh="true">
				</EF:EFWindow>
			</EF:EFRegion>

		</div>
	</div>

</EF:EFPage>