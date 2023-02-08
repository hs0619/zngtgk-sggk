$(function () {
	
	$("#QUERY").on("click", function (e) {
		humanGrid.dataSource.page(1);
        onlineGrid.dataSource.page(1);
	});

	IPLATUI.EFGrid = {
			"human" : {
				pageable: false,
				exportGrid: false,
	        	onSuccess: function (e) {
	        		if(e.type == "create" || e.type == "update"){
	        			var humanblock = e.eiInfo.blocks["human"];
	        			if (humanblock != null) {
	        				humanGrid.setEiBlock(humanblock);
		        		}
	        		}
	        	}
	        },
	        "online" : {
	        	pageable: false,
				exportGrid: false,
	        	onSuccess: function (e) {
	        		if(e.type == "create" || e.type == "update"){
	        			var onlineblock = e.eiInfo.blocks["online"];
	        			if (onlineblock != null) {
	        				onlineGrid.setEiBlock(onlineblock);
		        		}
	        		}
	        	}
	        }
	    };
	
	window.onload = function(){
		humanGrid.dataSource.page(1);
        onlineGrid.dataSource.page(1);
    };
});