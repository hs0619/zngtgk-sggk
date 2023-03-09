<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="因子配置管理" >
	<EF:EFRegion id="inqu" title="查询条件" type="query" efRegionShowClear="true" efRegionSave="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-planid" cname="计划名称" colWidth="6" type="hidden" disabled="true" />
			<EF:EFInput ename="inqu_status-0-planname" cname="计划名称" colWidth="7"
				disabled="true" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="human" title="人工监测因子">
		<EF:EFGrid blockId="human" autoDraw="false" sort="setted"  queryMethod="queryHuman"
					updateMethod="updateHuman">
			<EF:EFColumn ename="planid" cname="计划ID" enable="false" hidden="true" />
			<EF:EFComboColumn ename="factorid" cname="污染因子" blockName="factors"
				textField="factorname" valueField="factorid" readonly="true" required="true" />
            <EF:EFColumn ename="unit" cname="单位" enable="false"  />
			<EF:EFColumn ename="highlimit" cname="高限值" enable="true"  />
			<EF:EFColumn ename="lowlimit" cname="低限值" enable="true"  />
			<EF:EFComboColumn ename="licenserate" cname="监测频率" blockName="timeDime"
				textField="textfield" valueField="valuefield" enable="true" />
			<EF:EFColumn ename="licensecount" cname="监测次数" enable="true"  />
			<EF:EFComboColumn ename="iscompare" cname="比对" enable="true" blockName="iscompare" width="95"
				textField="iscomparename" valueField="iscompareid"/>
			<EF:EFComboColumn ename="status" cname="使用" blockName="flagList"
				textField="flagname" valueField="flagid" defaultValue="1" />
			<EF:EFComboColumn ename="usezs" cname="使用折算值" blockName="flagList" hidden="true"
				textField="flagname" valueField="flagid" defaultValue="0" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
