<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <EF:EFPage>
        <div class="row">
            <div class="col-md-3">
                <EF:EFRegion id="tree" title="多次加载树 JS 配置首节点">
                    <EF:EFTree bindId="tree01" textField="solidName" valueField="solidCodes" hasChildren="leaf"
                               dataSpriteCssClassField="icon"
                               serviceName="DUHM41" methodName="query" style="height:100%">
                    </EF:EFTree>
                </EF:EFRegion>
            </div>
            <div class="col-md-9">
                <EF:EFRegion title="查询区" id="inqu">
                    <EF:EFInput ename="inqu_status-0-Sname" cname="固废名称"/>
                    <EF:EFSelect ename="inqu_status-0-industrialDisposal" cname="是否使用" colWidth="4" enable="true" >
                        <EF:EFOption label="全部" value="%" />
                        <EF:EFOption label="是" value="是" />
                        <EF:EFOption label="否" value="否" />
                    </EF:EFSelect>
                    <%--<EF:EFSelect ename="inqu_status-0-solidProduct" cname="委外产品化" colWidth="4" enable="true" >
                        <EF:EFOption label="全部" value="%" />
                        <EF:EFOption label="是" value="是" />
                        <EF:EFOption label="否" value="否" />
                    </EF:EFSelect>

                    <EF:EFSelect ename="inqu_status-0-ironMud" cname="含铁尘泥" colWidth="4" enable="true" >
                        <EF:EFOption label="全部" value="%" />
                        <EF:EFOption label="是" value="是" />
                        <EF:EFOption label="否" value="否" />
                    </EF:EFSelect>--%>


                    <EF:EFInput ename="inqu_status-0-P_ename" style="display:none"/>
                </EF:EFRegion>
                <div class="row">
                    <div class="col-md-9">
                        <div id="butdiv" style="float: right;"></div>
                    </div>
                </div>
                <EF:EFRegion title="结果集" id="result" fitHeight="true">
                    <EF:EFGrid blockId="result" serviceName="DUHM41" queryMethod="search"   deleteMethod="delete"   autoDraw="false" autoFit="true">
                        <EF:EFColumn ename="solidId" cname="solidId" hidden="true" defaultValue="" width="100" enable="true" readonly="true" editType="text" displayType="default" />
                        <EF:EFColumn ename="rank" cname="序号" defaultValue="" width="100" />
                        <EF:EFColumn ename="solidName" cname="固废名称" defaultValue="" width="100"  required="true"/>
                        <EF:EFColumn ename="solidCode" cname="固废代码" defaultValue="" width="100"  />
                        <EF:EFComboColumn ename="solidType" cname="父级名称"   required="true" blockName="qsolidType"  width="100"
                                          textField="solidCodes"
                                          valueField="solidName" />
                        <EF:EFComboColumn ename="state" cname="处置情况" columnTemplate="#=valueField#" itemTemplate="#=valueField#" width="100"  hidden="true"
                                          textField="textField" valueField="valueField" >
                            <EF:EFOption label="否" value="否"/>
                            <EF:EFOption label="是" value="是"/>
                        </EF:EFComboColumn>
                        <EF:EFComboColumn ename="industrialDisposal" cname="是否使用" columnTemplate="#=valueField#" itemTemplate="#=valueField#" width="100"
                                          textField="textField" valueField="valueField" >
                            <EF:EFOption label="否" value="否"/>
                            <EF:EFOption label="是" value="是"/>
                        </EF:EFComboColumn>

                        <EF:EFComboColumn ename="solidProduct" cname="委外产品化" columnTemplate="#=valueField#" itemTemplate="#=valueField#" width="100"
                                          textField="textField" valueField="valueField" hidden="true">
                            <EF:EFOption label="否" value="否"/>
                            <EF:EFOption label="是" value="是"/>
                        </EF:EFComboColumn>
                        <EF:EFComboColumn ename="ironMud" cname="含铁尘泥" columnTemplate="#=valueField#" itemTemplate="#=valueField#" width="100"
                                          textField="textField" valueField="valueField"  hidden="true">
                            <EF:EFOption label="否" value="否"/>
                            <EF:EFOption label="是" value="是"/>
                        </EF:EFComboColumn>

                        <EF:EFComboColumn ename="solidState" cname="固废状况" columnTemplate="#=valueField#" itemTemplate="#=valueField#" width="100"  hidden="true"
                                          textField="textField" valueField="valueField">
                            <EF:EFOption label="启用" value="启用"/>
                            <EF:EFOption label="禁用" value="禁用"/>
                        </EF:EFComboColumn>
                        <EF:EFColumn ename="remark" cname="备注" defaultValue="" width="100"  hidden="true"/>

                    </EF:EFGrid>
                </EF:EFRegion>
            </div>
        </div>
    </EF:EFPage>

