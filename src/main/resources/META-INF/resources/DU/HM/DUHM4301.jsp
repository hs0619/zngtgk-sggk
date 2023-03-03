<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <EF:EFPage>
        <div class="row">
            <div class="col-md-3">
                <EF:EFRegion id="tree" title="">
                    <EF:EFTree bindId="tree01" textField="departmentname" valueField="departmentid"
                               hasChildren="leaf" dataSpriteCssClassField="icon"
                               serviceName="DUHM4301" methodName="query" style="height:100%">
                    </EF:EFTree>
                </EF:EFRegion>
            </div>
            <div class="col-md-9">
                <EF:EFRegion title="查询区" id="inqu">
                    <EF:EFInput ename="inqu_status-0-departmentid" cname="所属厂部ID" colWidth="4" type="hidden"/>

                    <EF:EFInput ename="inqu_status-0-departmentname" cname="产生单位" colWidth="4"/>
                    <EF:EFInput ename="inqu_status-0-dangername" cname="危废名称" colWidth="4"/>
                </EF:EFRegion>
                <div class="row">
                    <div class="col-md-9">
                        <div id="butdiv" style="float: right;"></div>
                    </div>
                </div>
                <EF:EFRegion title="结果集" id="result" fitHeight="true">
                    <EF:EFGrid blockId="result" autoDraw="false" autoFit="true" editHelper="true"
                               serviceName="DUHM4301" queryMethod="queryInfo">
                        <EF:EFComboColumn ename="departmentid" cname="产生单位" required="true" width="130" readonly="true"
                                          blockName="depart" textField="department_name"  valueField="department_id" />
                        <EF:EFColumn ename="dangertype" cname="危废类别"  required="true" readonly="true"/>
                        <EF:EFColumn ename="dangercode" cname="危废代码"    required="true" readonly="true" />
                        <EF:EFColumn ename="dangername" cname="危废名称"   required="true" readonly="true"/>
                        <EF:EFColumn ename="prodresource" cname="产生源"    />
                        <EF:EFComboColumn ename="dangerform" cname="危废形态"
                                          blockName="form" textField="textfield"  valueField="valuefield" />
                        <EF:EFComboColumn ename="promethod" cname="产生方式" >
                            <EF:EFOption label="生产" value="1"/>
                            <EF:EFOption label="清理" value="2"/>
                        </EF:EFComboColumn>
                        <EF:EFColumn ename="disposeUnit" cname="处置单位" />
                        <EF:EFColumn ename="disposeMethod" cname="处置方式"   />
                    </EF:EFGrid>
                </EF:EFRegion>
            </div>
        </div>
    </EF:EFPage>

