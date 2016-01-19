//是否加载增量数据
var loadNewData = true;
/**
 * 获取新数据
 * @param tableId
 */
function pullConsulltation(tableId,status){
	if (loadNewData) {
		$.ajax({
	        type: "POST",
	        url: rootPath+"/consultation/apply/pullData.json",
	        data: {'status':status},
	        dataType: "json",
	        success: function(data){
				if(data!=null){
					console.info(data);
					if(loadNewData){
						var page={"rows":[]};
						page.rows.push(data);
						$('#'+tableId).datagrid("load",page);
					}
				}else{
				}
				pullConsulltation(tableId);
	      	}
	    });
	}
}