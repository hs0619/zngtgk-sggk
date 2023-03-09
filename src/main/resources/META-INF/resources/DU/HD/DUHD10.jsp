<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage>
    <EF:EFRegion id="inqu" title="查询" type="query" efRegionShowClear="true"
                 efRegionSave="true">
        <div class="row">
            <EF:EFSelect ename="inqu_status-0-departmentid" cname="厂部"
                         colWidth="4">
                <EF:EFOption label="全部" value=""/>
                <EF:EFOptions blockId="deptblock" textField="departmentName"
                              valueField="departmentId"/>
            </EF:EFSelect>
            <EF:EFSelect ename="inqu_status-0-procedureid" cname="工序"
                         colWidth="4">
                <EF:EFOption label="全部" value=""/>
                <EF:EFOptions blockId="procedureblock" textField="departmentName"
                              valueField="departmentId"/>
            </EF:EFSelect>
            <EF:EFSelect ename="inqu_status-0-facilitytype" cname="设施类型"
                         colWidth="4">
                <EF:EFOption label="全部" value=""/>
                <EF:EFOption label="废气治理设施" value="01"/>
                <EF:EFOption label="废水治理设施" value="02"/>
            </EF:EFSelect>
        </div>
    </EF:EFRegion>
    <EF:EFTab id="info">
        <div title="环保设施信息">
            <EF:EFRegion id="result">
                <EF:EFGrid blockId="result" autoDraw="false" sort="setted" rowNo="true" isFloat="true">
                    <EF:EFColumn ename="facilityid" cname="facilityid" hidden="true"  width="100"/>
                    <EF:EFColumn ename="facilitycode" cname="设施编号" width="50" sort="true" readonly="true"/>
                    <EF:EFColumn ename="facilityname" cname="设施名称" width="80" sort="true" readonly="true"/>
                    <EF:EFComboColumn ename="facilitytype" cname="设施类型" defaultValue="" readonly="true" width="50"
                                      enable="true" editType="select" displayType="default">
                        <EF:EFOption label="废气治理设施" value="01"/>
                        <EF:EFOption label="废水治理设施" value="02"/>
                    </EF:EFComboColumn>
                    <EF:EFComboColumn ename="departmentid" cname="厂部" width="40"
                                      blockName="deptblock" labelProperty="departmentName"
                                      valueProperty="departmentId" textField="departmentName"
                                      valueField="departmentId" sort="true" readonly="true"/>
                    <EF:EFComboColumn ename="procedureid" cname="工序" width="40"
                                      blockName="procedureblock" labelProperty="departmentName"
                                      valueProperty="departmentId" textField="departmentName"
                                      valueField="departmentId" sort="true" readonly="true"/>
                    <EF:EFColumn ename="djRatedpower" cname="电机额定功率（kw）" defaultValue="" width="80" editType="text"
                                 displayType="default" maxLength="50"/>
                    <EF:EFColumn ename="ratedpower" cname="额度功率（kw）" defaultValue="" width="70" editType="text"
                                 displayType="default" maxLength="50"/>
                    <EF:EFColumn ename="frequency" cname="变频(hz)" defaultValue="" width="50" editType="text"
                                 displayType="default" maxLength="50"/>
                    <EF:EFColumn ename="capacity" cname="脱除能力(吨/年)、处理能力(万立或万吨)、处理量(万立/时)" defaultValue="" width="70"
                                 editType="text" displayType="default" maxLength="50"/>
                    <EF:EFColumn ename="technique" cname="治理方式" defaultValue="" width="100" editType="text"
                                 displayType="default" maxLength="50"/>
                    <EF:EFColumn ename="consumption" cname="使用量" defaultValue="" width="100" editType="text"
                                 displayType="default" maxLength="50"/>
                    <EF:EFComboColumn ename="indexName" cname="指标" width="50"
                                      blockName="indexblock"  textField="indexName"
                                      valueField="indexId" sort="true" >
                        <EF:EFOption label=" "  value="  "/>
                    </EF:EFComboColumn>
                    <EF:EFColumn ename="cost" cname="费用(元)" defaultValue="" width="100" editType="text"
                                 displayType="default" maxLength="50"/>
<%--                    <EF:EFComboColumn ename="istl" cname="是否脱硫" defaultValue=""  width="50"--%>
<%--                                      enable="true" editType="select" displayType="default">--%>
<%--                        <EF:EFOption label="是" value="01"/>--%>
<%--                        <EF:EFOption label="否" value="02"/>--%>
<%--                    </EF:EFComboColumn>--%>
<%--                    <EF:EFComboColumn ename="istx" cname="是否脱硝" defaultValue="" width="50"--%>
<%--                                      enable="true" editType="select" displayType="default">--%>
<%--                        <EF:EFOption label="是" value="01"/>--%>
<%--                        <EF:EFOption label="否" value="02"/>--%>
<%--                    </EF:EFComboColumn>--%>
                    <EF:EFColumn ename="isupdate" cname="是否修改"  hidden="true" />
                </EF:EFGrid>
            </EF:EFRegion>
        </div>
        <div title="生产设施信息">
            <EF:EFRegion id="result2">
                <EF:EFGrid blockId="result2" autoDraw="false" autoFit="true"  sort="setted" serviceName="DUHD10"
                           rowNo="true" queryMethod="query2"  updateMethod="update2" deleteMethod="delete2"
                           isFloat="true">
                    <EF:EFColumn ename="facilityid" cname="facilityid" hidden="true" defaultValue="" width="100"
                                 enable="true" editType="text" displayType="default"/>
                    <EF:EFColumn ename="facilitycode" cname="设施编号" width="50" sort="true" readonly="true"/>
                    <EF:EFColumn ename="facilityname" cname="设施名称" width="80" sort="true" readonly="true"/>
                    <EF:EFComboColumn ename="facilitytype" cname="设施类型" defaultValue="" readonly="true" width="80"
                                      enable="true" editType="select" displayType="default">
                        <EF:EFOption label="废气产污设施" value="01"/>
                        <EF:EFOption label="废水产污设施" value="02"/>
                    </EF:EFComboColumn>
                    <EF:EFComboColumn ename="departmentid" cname="厂部" width="40"
                                      blockName="deptblock" labelProperty="departmentName"
                                      valueProperty="departmentId" textField="departmentName"
                                      valueField="departmentId" sort="true" readonly="true"/>
                    <EF:EFComboColumn ename="procedureid" cname="工序" width="40"
                                      blockName="procedureblock" labelProperty="departmentName"
                                      valueProperty="departmentId" textField="departmentName"
                                      valueField="departmentId" sort="true" readonly="true"/>
                    <EF:EFColumn ename="spec" cname="烧结机规格（m²）" defaultValue="" width="100" editType="text"
                                 displayType="default" maxLength="50"/>
                    <EF:EFColumn ename="height" cname="焦炉炭化室高度" defaultValue="" width="100" editType="text"
                                 displayType="default" maxLength="50"/>
                    <EF:EFColumn ename="hole" cname="焦炉孔数" defaultValue="" width="50" editType="text"
                                 displayType="default" maxLength="50"/>
                    <EF:EFColumn ename="volume" cname="高炉有效容积(m³)" defaultValue="" width="100"
                                 editType="text" displayType="default" maxLength="50"/>
                    <EF:EFColumn ename="weight" cname="转炉公称容量(吨)" defaultValue="" width="100" editType="text"
                                 displayType="default" maxLength="50"/>
                    <EF:EFColumn ename="variety" cname="轧机品种及规格" defaultValue="" width="100" editType="text"
                                 displayType="default" maxLength="50"/>
                    <EF:EFColumn ename="consumption" cname="使用量" defaultValue="" width="100" editType="text"
                                 displayType="default" maxLength="50"/>
                    <EF:EFComboColumn ename="indexName" cname="指标" width="50"
                                      blockName="indexblock"  textField="indexName"
                                      valueField="indexId" sort="true" >
                        <EF:EFOption label=" "  value="  "/>
                    </EF:EFComboColumn>
                    <EF:EFColumn ename="cost" cname="费用(元)" defaultValue="" width="100" editType="text"
                                 displayType="default" maxLength="50"/>
<%--                    <EF:EFComboColumn ename="istl" cname="是否脱硫" defaultValue=""  width="50"--%>
<%--                                      enable="true" editType="select" displayType="default">--%>
<%--                        <EF:EFOption label="是" value="01"/>--%>
<%--                        <EF:EFOption label="否" value="02"/>--%>
<%--                    </EF:EFComboColumn>--%>
<%--                    <EF:EFComboColumn ename="istx" cname="是否脱硝" defaultValue="" width="50"--%>
<%--                                      enable="true" editType="select" displayType="default">--%>
<%--                        <EF:EFOption label="是" value="01"/>--%>
<%--                        <EF:EFOption label="否" value="02"/>--%>
<%--                    </EF:EFComboColumn>--%>
                    <EF:EFColumn ename="isupdate" cname="是否修改"  hidden="true" />
                </EF:EFGrid>
            </EF:EFRegion>
        </div>
    </EF:EFTab>
</EF:EFPage>
