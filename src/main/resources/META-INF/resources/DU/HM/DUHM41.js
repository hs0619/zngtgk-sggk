$(function () {
    $("#QUERY").on("click",function(e){
        resultGrid.dataSource.page(1);
    });


    IPLATUI.EFTree = {
        "tree01": {
            ROOT: {solidCodes: "固废名称", solidName: "固废名称"},
            select: function (e) {
                var nodeData = this.dataItem(e.node);
                console.log(nodeData);
                $("#inqu_status-0-P_ename").val(nodeData.id);
                resultGrid.dataSource.page(1);
            }
        },
    };


    IPLATUI.EFGrid = {
        "result" : {
            pageable: {
                pageSize: 200,
                pageSizes: [ 200, 500, 1000]
            },
            height: function(){
                return ($("#main-container").height() - $("#inqu").height() - 115);
            },

        }
    };
});
