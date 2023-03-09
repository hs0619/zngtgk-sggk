$(function() {
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});


	IPLATUI.EFGrid = {
		"result": {
			columns: [
				{
					field: "edit",
					title: "编辑",
					encoded: false,
					template: function(dateItem) {
						return '<div style="text-align: center"><input value="编辑" class="k-button k-button-details" type="button" align="center" onclick="edit(\'' + dateItem.monitorid + '\',\'' + dateItem.factorid + '\')"/></div>';
					}
				},

			],
			beforeAdd: function(e) {
				e.preventDefault();
				var popWindow = $("#showHM");
				var url = IPLATUI.CONTEXT_PATH + "/web/DUHC230501";
				popWindow.data("kendoWindow").setOptions({
					open: function() {
						popWindow.data("kendoWindow").refresh({
							url: url
						});
					},
					lazyload: true,
					minWidth: 1000,
					minHeight: 500,
					iframe: true
				});
				popWindow.data("kendoWindow").open().center();
			},
			pageable: {
				pageSize: 200,
				pageSizes: [ 200, 500, 1000]
			},
			
			height: function(){
				return ($("#main-container").height() - $("#inqu").height() - 115);
			}
		}
	};

	IPLATUI.EFWindow = {
		"showHM": {
			close: function(e) {
				resultGrid.dataSource.page(1);
			}
		}
	};

	edit = function(monitorid, factorid) {
		var popWindow = $("#showHM");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHC230501?monitorid=" + monitorid + "&factorid=" + factorid;
		popWindow.data("kendoWindow").setOptions({
			open: function() {
				popWindow.data("kendoWindow").refresh({
					url: url
				});
			},
			lazyload: true,
			minWidth: 1000,
			minHeight: 500,
			iframe: true
		});
		popWindow.data("kendoWindow").open().center();

	};
});