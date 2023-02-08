$(function () {

	var eiInfo = new EiInfo();
	EiCommunicator.send("DUHB0703", "initSelectData", eiInfo, {
		onSuccess: function(ei) {
			var sumber = [];

			//var data = ei.blocks.result.rows;
			// for (var i in data){
			// 	var obj ={
			// 		value:data[i][1],
			// 		name :data[i][0]+'(单位:个)'
			// 	};
			// 	sumber.push(obj)
			// }

			//	f(sumber);
			//var data0 =  ei.get("data0");
			var data1 =  ei.get("data1");
			var data2 =  ei.get("data2");
			var data3 =  ei.get("data3");
			var data4 =  ei.get("data4");
			    // $("#feiqi").text(data1);
				// $("#feishui").text(data2);
			 	// $("#zaixian").text(data3);
			    // $("#rengong").text(data4);
            $("#feiqi").html('<span class="distance">'+data1+'</span><span>个</span>');
            $("#feishui").html('<span class="distance">'+data2+'</span><span>个</span>');
            $("#zaixian").html('<span class="distance">'+data3+'</span><span>个</span>');
            $("#rengong").html('<span class="distance">'+data4+'</span><span>个</span>');
		}, onFail: function(ei) {
			// 发生异常
			NotificationUtil("加载数据出错" + ei.getMsg(), "error");
		}
	});


	IPLATUI.EFGrid = {
		result : {
			pageable: false,
			exportGrid: false,
			loadComplete: function(grid) {
				//	resultGrid.dataSource.page(1);
			},
			onSuccess: function (e) {
				//f();
				if(e.type == "create" || e.type == "update"){
					var resultblock = e.eiInfo.blocks["result"];
					if (resultblock != null) {
						resultGrid.setEiBlock(resultblock);
					}
				}
			}
		}

	};

});

// function f( sumber ) {
//
//
// 	var chart = document.getElementById('olchart');
// 	// 实例化对象
// 	var myChart = echarts.init(chart);
// 	option = {
// 		tooltip: {
// 			trigger: 'item'
// 		},
// 		series: [
// 			{
// 				// name: 'Access From',
// 				type: 'pie',
// 				radius: '50%',
// 				data: [
// 					// { value: 1048, name: 'Search Engine' },
// 					// { value: 735, name: 'Direct' }
// 				],
// 				emphasis: {
// 					itemStyle: {
// 						shadowBlur: 10,
// 						shadowOffsetX: 0,
// 						shadowColor: 'rgba(0, 0, 0, 0.5)'
// 					}
// 				}
// 			}
// 		]
// 	};
//
//     option.series[0].data = sumber;
//
// 	// 把配置给实例对象
// 	myChart.setOption(option);
// }