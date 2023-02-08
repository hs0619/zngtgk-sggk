<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="排口监测因子信息">
	<EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-departmentid" cname="厂部ID" colWidth="4" type="hidden" />
			<EF:EFInput ename="inqu_status-0-dischargeportid" cname="排口ID"
				colWidth="4" disabled="true" />
			<EF:EFInput ename="inqu_status-0-dischargeportname" cname="排口名称"
				colWidth="4" disabled="true" />
			<EF:EFInput ename="inqu_status-0-dischargeportcode" cname="排口编号"
				colWidth="4" disabled="true" />
		</div>
	</EF:EFRegion>

	<EF:EFTab id="info">
		<div title="污染防治设施信息">
			<EF:EFRegion id="ssfs" title="记录集">
				<EF:EFGrid blockId="ssfs" autoDraw="false" sort="setted" queryMethod="querySSFS" >
					<EF:EFColumn ename="facilityid" cname="设施ID" hidden="true" enable="false" />
					<EF:EFColumn ename="dischargeportid" cname="排口id" hidden="true" enable="false" />
					<EF:EFColumn ename="operation" locked="true" cname="操作"  width="90" enable="false" />
					<EF:EFComboColumn ename="departmentid" cname="厂部" locked="true"
						blockName="departList" labelProperty="departmentName"
						valueProperty="departmentId" textField="departmentName"
						valueField="departmentId" />
					<EF:EFComboColumn ename="procedureid" cname="工序" locked="true"
						blockName="procedureList" labelProperty="departmentName"
						valueProperty="departmentId" textField="departmentName"
						valueField="departmentId" />
					<EF:EFColumn ename="facilitycode" locked="true"  cname="污染防治设施编号"  width="150" enable="false" />
					<EF:EFColumn ename="facilityname" locked="true" cname="污染防治设施名称"  width="150" enable="false" sort="true"/>
					<EF:EFColumn ename="mark" locked="true" cname="备注" enable="false" />
					<EF:EFColumn ename="numbers" cname="台（套）" enable="false" />
					<EF:EFColumn ename="runtime" cname="投运时间" enable="false" />
					<EF:EFColumn ename="devicecode" cname="产污设施编码" enable="false" />
					<EF:EFColumn ename="devicemodel" cname="风机/水泵 型号" enable="false" />
					<EF:EFColumn ename="motormodel" cname="电机型号/容量" enable="false" />
					<EF:EFColumn ename="controllermodel" cname="变频器型号/容量"
						enable="false" />
					<EF:EFColumn ename="situation" cname="控制系统情况" enable="false" />
					<EF:EFColumn ename="portid" cname="排口ID" enable="false" />
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="showFQ" width="90%" height="90%" refresh="true">
			</EF:EFWindow>
			<EF:EFWindow id="showFS" width="90%" height="90%" refresh="true">
			</EF:EFWindow>
			<EF:EFWindow id="showFI" width="90%" height="90%" refresh="true">
			</EF:EFWindow>
		</div>
		<div title="监测点信息">
			<EF:EFRegion id="sitefs" title="记录集">
				<EF:EFGrid blockId="sitefs" autoDraw="false" sort="setted"
					queryMethod="querySITEFS" deleteMethod="deleteSITEFS">
					<EF:EFColumn ename="siteid" cname="监测点ID" hidden="true"
						enable="false" />
					<EF:EFColumn ename="operation" cname="操作" enable="false"
						locked="true" />
					<EF:EFColumn ename="sitename" cname="监测点名称" locked="true" sort="true"/>
					<EF:EFColumn ename="departname" cname="厂部名称" />
					<EF:EFColumn ename="procname" cname="工序名称" />
					<EF:EFColumn ename="portname" cname="排口名称" />
					<EF:EFComboColumn ename="isartificial" cname="是否人工"
						blockName="flagList" labelProperty="flagname"
						valueProperty="flagid" textField="flagname" valueField="flagid" sort="true"/>
					<EF:EFComboColumn ename="isonline" cname="是否在线"
						blockName="flagList" labelProperty="flagname"
						valueProperty="flagid" textField="flagname" valueField="flagid" sort="true"/>
					<EF:EFComboColumn ename="classifyid" cname="人工监测类型"
						blockName="monitorList" labelProperty="monitorname"
						valueProperty="monitorid" textField="monitorname"
						valueField="monitorid" sort="true"/>
					<EF:EFComboColumn ename="monitorid" cname="在线监测类型"
						blockName="monitorList" labelProperty="monitorname"
						valueProperty="monitorid" textField="monitorname"
						valueField="monitorid" />
					<EF:EFColumn ename="mnid" cname="MN编号" />
					<EF:EFColumn ename="description" cname="描述"/>
					<EF:EFColumn ename="sort" cname="排序" />
					<EF:EFComboColumn ename="status" cname="是否启用" blockName="flagList"
						labelProperty="flagname" valueProperty="flagid"
						textField="flagname" valueField="flagid" />
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="showSI" width="90%" height="90%" refresh="true">
			</EF:EFWindow>
			<EF:EFWindow id="showSF" width="90%" height="90%" refresh="true">
			</EF:EFWindow>
		</div>
		<div title="排口监测因子信息">
			<EF:EFRegion id="portfs" title="记录集">
				<EF:EFGrid blockId="portfs" autoDraw="false" showCount="false" sort="setted" checkMode="multiple, row"  serviceName="DUHB06"
					queryMethod="queryPORTFS" deleteMethod="deletePORTFS" updateMethod="updatePORTFS"  insertMethod="insertPORTFS" >
					<EF:EFColumn ename="factorid" cname="监测因子ID" enable="false"
						hidden="true" />
					<EF:EFColumn ename="portid" cname="排口ID" enable="false"
						hidden="true" />
                    <EF:EFColumn ename="monitorname" cname="监测类型" enable="false"  />
					<EF:EFColumn ename="factorname" cname="监测因子" enable="false"  />
					<EF:EFColumn ename="highlimit" cname="限值上限" enable="true" />
					<EF:EFColumn ename="lowlimit" cname="限值下限" enable="true" />
					<EF:EFColumn ename="nkhighlimit" cname="内控上限" enable="true" />
					<EF:EFColumn ename="nklowlimit" cname="内控下限" enable="true" />
					<EF:EFColumn ename="detectionLimit" cname="检出限" enable="true" />
					<EF:EFColumn ename="description" width="120" cname="描述" enable="true" />
					<EF:EFComboColumn ename="status" cname="是否启用" blockName="flagList"
						labelProperty="flagname" valueProperty="flagid"
						textField="flagname" valueField="flagid" required="true"
						defaultValue="1" width="60" />
				</EF:EFGrid>
			</EF:EFRegion>
			<EF:EFWindow id="showPF" width="90%" height="90%" refresh="true">
			</EF:EFWindow>
		</div>
	</EF:EFTab>
</EF:EFPage>
