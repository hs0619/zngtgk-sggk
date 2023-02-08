<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<EF:EFPage>
	<EF:EFRegion id="result" title="污染源信息"   fitHeight="true" style="width: 100%;height:100%;">
		<div class="row case-wrapper clearfix"  style="text-align:center;padding-top: 1em;">
			<div class="col-xs-5 chart"   onclick="feiqi()"   style="text-align:center;padding-top: 1.5em;">
				<%--<div class="case-item-bg" ></div>--%>
				<img src="${ctx}/img/feiqi.png" style="width: 2em;height: 2em;margin: auto;">
					<div id="feiqi" class="topdistance" ></div>
				废气排口
			</div>
			<div  class="col-xs-2"></div>
			<div class="col-xs-5 chart"   onclick="feishui()"    style="text-align:center;padding-top: 1.5em;">
				<img src="${ctx}/img/shui.png" style="width: 2em;height: 2em;margin: auto;">
				<div id="feishui" class="topdistance" ></div>
				废水排口
			</div>
		</div>
		<div class="row" style="padding-top: 1.1em;">
			<div class="col-xs-5 chart"   onclick="zaixian()"   style="text-align:center;">
				<img src="${ctx}/img/zaixian.png" style="width: 2em;height: 2em;">
				<div id="zaixian" class="topdistance"></div>
				在线监测点
			</div>
			<div  class="col-xs-2"></div>
			<div class="col-xs-5 chart"  onclick="rengong()"    style="text-align:center;">
				<img src="${ctx}/img/rengong.png" style="width: 2em;height: 2em;">
				<div id="rengong" class="topdistance"></div>
				人工监测点
			</div>
		</div>

	</EF:EFRegion>

</EF:EFPage>

<style>
	 .topdistance{
		padding: 0.6em;
	}
	 .chart{
		 padding: 0.6em;
		 box-shadow: 5px 5px 5px #ccc;
		 border-radius:5px;
	 }
	 /* 伪类选择器加盒子阴影 */
	 .chart:hover{
		 box-shadow: 0 0 10px #3f474e;
		 rgba(0, 0,0,.3);
	 }
	.distance{
		padding-top: 0.5em;
		font-weight: bold;
		font-size: 1.2em;
	}
</style>
<script>
	feiqi =	function (){
		window.open("../web/DUHB05?monitorid=01");
	}

	 	function feishui(){
		window.open("../web/DUHB05?monitorid=02");
	}

	zaixian =	function (){
		window.open("../web/DUHB07?isonline=01");
	}

	rengong =	function (){
		window.open("../web/DUHB07?isonline=02");
	}

</script>
<%--<EF:EFPage>--%>
	<%--<script src="${ctx}/js/echarts/dist/echarts.min.js"></script>--%>
	<%--<link rel="stylesheet" href="${ctx}/js/css/style1.css">--%>

	<%--<EF:EFRegion id="result" title="污染源信息"   fitHeight="true" style="width: 100%;height:100%;">--%>
		<%--<div class="container"   fitHeight="true" style="width: 100%;height:100%;">--%>
			<%--<button class="button button--one">废气排口：<span id="feiqi"></span></button>--%>
			<%--<button class="button button--two">废水排口：<span id="feishui"></span></button>--%>
			<%--<button class="button button--three">在线监测点：<span id="zaixian"></span></button>--%>
			<%--<button class="button button--four">人工监测点：<span id="rengong"></span></button>--%>
			<%--&lt;%&ndash;<h5>废气排口：<span id="feiqi"></span></h5>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<h5>废水排口：<span id="feishui"></span></h5>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<h5>在线监测点：<span id="zaixian"></span></h5>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<h5>人工监测点：<span id="rengong"></span></h5>&ndash;%&gt;--%>
		<%--</div>--%>
	<%--</EF:EFRegion>--%>

<%--</EF:EFPage>--%>

