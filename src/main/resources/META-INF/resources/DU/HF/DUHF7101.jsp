<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="放射源管理编辑">
	<EF:EFRegion id="inqu" title="信息录入" type="query" efRegionShowClear="true" efRegionSave="true" >
		<div class="row">
			<div class="col-xs-2">
				<EF:EFInput ename="inqu_status-0-operation" cname="操作" colWidth="12"  type="hidden"/>
				<EF:EFInput ename="inqu_status-0-radiateid" cname="放射源ID" colWidth="12"  type="hidden"/>
				<EF:EFInput ename="inqu_status-0-status" cname="状态" colWidth="12"  type="hidden"/>
			</div>
            <div class="col-xs-4">
                <EF:EFSelect ename="inqu_status-0-radiatetype" cname="类别"
                             serviceName="DUHF7101" methodName="initLoad" resultId="type"
                             textField="typename" valueField="typeid" colWidth="12" required="true">
                </EF:EFSelect>
            </div>
			<div class="col-xs-4">
				<EF:EFSelect ename="inqu_status-0-departmentid" cname="单位" 
					serviceName="DUHF7101" methodName="initLoad" resultId="qdepart"
					textField="departmentName" valueField="departmentId" colWidth="12" required="true">
				</EF:EFSelect>
			</div>


		</div>
        <div class="row">
            <div class="col-xs-2"></div>
            <div class="col-xs-4">
                <EF:EFInput ename="inqu_status-0-radiatename" cname="放射源名称"  colWidth="12" required="true"/>
            </div>

            <div class="col-xs-4">
                <EF:EFSelect ename="inqu_status-0-iswork" cname="是否停用"  colWidth="12" >
                    <EF:EFOption label="否" value="0" />
                    <EF:EFOption label="是" value="1" />
                </EF:EFSelect>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-2"></div>
            <div class="col-xs-4">
                <EF:EFInput ename="inqu_status-0-address" cname="使用位置"  colWidth="12" required="true"/>
            </div>
            <div class="col-xs-4">
                <EF:EFInput ename="inqu_status-0-purpose" cname="装置用途"  colWidth="12" required="true"/>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-2"></div>
            <div class="col-xs-4">
                <EF:EFInput ename="inqu_status-0-nuclide" cname="核素名称"  colWidth="12" required="true"/>
            </div>
            <div class="col-xs-4">
                <EF:EFInput ename="inqu_status-0-number" cname="数量"  colWidth="12" />
            </div>

        </div>
        <div class="row">
            <div class="col-xs-2"></div>
            <div class="col-xs-4">
                <EF:EFInput ename="inqu_status-0-supplier" cname="供应商"  colWidth="12" />
            </div>
            <div class="col-xs-4">
                <EF:EFInput ename="inqu_status-0-prounit" cname="生产单位"  colWidth="12" />
            </div>
        </div>
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<EF:EFDatePicker ename="inqu_status-0-comedate" cname="出厂日期" format="yyyy-MM-dd" colWidth="12"  required="true">
				</EF:EFDatePicker>
			</div>
			<div class="col-xs-4">
				<EF:EFInput ename="inqu_status-0-comeactivity" cname="出厂活度（Bq）"  colWidth="12" required="true"/>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-10">
				<EF:EFInput ename="inqu_status-0-radiatecode" cname="编码"  colWidth="12"  required="true" type="textarea"/>
			</div>
            <div class="col-xs-2"></div>
		</div>
		<div class="row">
			<div class="col-xs-10">	
				<EF:EFInput ename="inqu_status-0-remark" cname="备注" type="textarea" colWidth="12" />
			</div>
			<div class="col-xs-2"></div>
		</div>
        <div class="row">
            <div class="col-xs-2"></div>
            <div class="col-xs-4">
                <EF:EFInput ename="inqu_status-0-recordname" cname="登记人"  colWidth="12" readonly="true" />
            </div>
            <div class="col-xs-4">
                <EF:EFInput ename="inqu_status-0-recorddate" cname="登记日期"  colWidth="12"  readonly="true">
                </EF:EFInput>
            </div>
        </div>
		<div class="row" style="margin-top:10px;">
			<div class="col-md-12">
				<div id="butdiv" style="display: block;text-align:center;">
				</div>
			</div>
		</div>
		<%--<div class="row" style="margin-top:10px;">
			<div class="col-xs-12">
				<div id="remark"   style="float: left;  font-size: 14.6px; color: #b37400;">
					注：提交后可修改“场所”、“去向”、“是否停用”！
				</div>
			</div>
		</div>--%>
		</EF:EFRegion>
	<EF:EFWindow id="showHM" url="${ctx}/web/DUHF7103" lazyload="true"
		width="50%" height="50%" refresh="true">
	</EF:EFWindow>
</EF:EFPage>