$(function () {
	$("#QUERY").on("click", function (e) {
	    resultGrid.dataSource.page(1);
	});
	IPLATUI.EFGrid = {
		"result": {
			pageable: {
				pageSize: 200,
				pageSizes: [200, 500, 1000]
			},
			height: function () {
				return ($("#main-container").height() - $("#inqu").height() - 115);
			}
		}
	}
});