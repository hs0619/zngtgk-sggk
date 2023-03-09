$(function() {

	$("#QUERY").on("click", function(e) {
		humanGrid.dataSource.page(1);
	});

	window.onload = function() {
		humanGrid.dataSource.page(1);
	};
	
	IPLATUI.EFGrid = {
			"human" : {
				pageable: false,
				/*pageable: {
					pageSize: 100,
					pageSizes: [10, 20, 50, 100]
	            },*/
				onSuccess: function (e) {
	        		if(e.type == "create" || e.type == "update"){
	        			var humanblock = e.eiInfo.blocks["human"];
	        			if (humanblock != null) {
	        				humanGrid.setEiBlock(humanblock);
		        		}
	        		}
	        	}
	        }
	    };
});