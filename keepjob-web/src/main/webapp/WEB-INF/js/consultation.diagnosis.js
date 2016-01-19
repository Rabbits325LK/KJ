var fileGrid;
var selectRowData;



function gotoFileView(rowData){
	selectRowData=rowData;
	centerDivHide();
	$("#viewTable").hide();
	$("#fileDiv").show();
	$("#consultaTion").show();
	fileView(selectRowData.uniqueCode);
	loadConsultaTionView(null);
}

function gotoFileViewInfo(rowData){
	selectRowData=rowData;
	if(rowData.sourceType=="2"){
		$.ajax({
	        type: "POST",
	        url: rootPath+"/consultationMedical/ehr/loadTree.json",
	        data: {'idCard':rowData.idCard},
	        dataType: "json",
	        success: function(data){
				if(data!=null){
					loadPatientsTree(data);
				}	
	      	}
	    });
	}else{
		checkConsultationStatus(rowData.uniqueCode, rowData.status);
	}
}


function checkConsultationStatus(uniqueCode, status){
	$.ajax({
		type : "POST",
		url : rootPath + "/consultation/checkConsultationStatus.json",
		data : {
			'uniqueCode' : uniqueCode,
			'status' : status
		},
		success : function(data){
			if(data == true){
				$("#viewTable").hide();
				centerDivHide();
				//$("#fileDiv").show();
				//$("div[name=centerDiv]").hide();
				$("#imgFileDiv").hide();
				$("#fileDiv").show();
				$("#consultaTion").show();
				fileView(selectRowData.uniqueCode);
				loadConsultaTionView(null);
				if(selectRowData.status == '04'){
					loadCompletedConsultation(selectRowData.ehrCode);
				}
			}else{
				alert("该会诊记录状态已更新。请选择其他记录！");
			}
		}
	});
}

function centerDivHide(){
	var d =$("div[name=centerDiv]").hide();
	for(var i=0;i<d.length;i++){
		$(d[i]).hide();
	}
}

function fileView(uniqueCode){
	$.ajax({
        type: "POST",
        url: rootPath+"/consultation/apply/fileList.json",
        data: {'uniqueCode':uniqueCode},
        dataType: "json",
        success: function(data){
			if(data!=null){
				loadTree(data);
			}	
      	}
    });
}

function getOfficeName(code){
	$.ajax({
		type : "POST",
		url : rootPath + "/common/getOffice.json",
		data : { 'code' : code},
		//dataType : "json",
		success : function(data){
			if(data != null) {
				//alert(data.name);
				$("#tab_officeCode").html(data.name);
			}
		}
	});
}


function loadPatientsTree(dataList){
	centerDivHide();
	$("#viewTable").hide();
	$("#fileDiv").show();
	fileGrid = $('#fileGrid').treegrid({
			idField : 'code',
			treeField : 'name',
			width : '100%',
			fit : true,
			fitColumns : true,
			border : false,
		   onDblClickRow :function(treeData){
			   if(treeData.url!=null && treeData.url!=""){
				   loadPage(treeData);
			   }
		   },
			columns : [ [
							{
								field : 'code',
								title : '编号',
								hidden: true
							},
							{
								field : 'name',
								title : '文件名称',
								width : 150
							}] ]
		});
	$('#fileGrid').treegrid("loadData",dataList.rows);
	closeAll();
}


function loadTree(dataList){
	fileGrid = $('#fileGrid').treegrid({
			idField : 'code',
			treeField : 'fileName',
			width : '100%',
			fit : true,
			fitColumns : true,
			border : false,
		   onDblClickRow :function(rowData){
			   dataClick(rowData);
		   },
			columns : [ [
							{
								field : 'code',
								title : '编号',
								hidden: true
							},
							{
								field : 'fileName',
								title : '文件名称',
								width : 150
							},
							{
								field : 'status',
								title : '文件状态',
								width : 80,
								formatter:function(value){
									if(value=='0'){
										return "原始未转化格式";
									}else if(value=='1'){
										return "文件转换中";
									}else if(value=="2"){
										return "文件转换完成";
									}else if(value=="3"){
										return "超时";
									}else if(value=="4"){
										return "文件转换失败";
									}else{
										return "";															
									}
									}
							}] ]
		});
	$('#fileGrid').treegrid("loadData",getTreeList(dataList));
	closeAll();
}

function closeAll(){
	var treeGrid=$('#fileGrid');
	var node = treeGrid.treegrid('getSelected');
	if (node) {
		treeGrid.treegrid('collapseAll', node.code);
	} else {
		treeGrid.treegrid('collapseAll');
	}
}
function openAll(){
	var treeGrid=$('#fileGrid');
	var node = treeGrid.treegrid('getSelected');
	if (node) {
		treeGrid.treegrid('expandAll', node.code);
	} else {
		treeGrid.treegrid('expandAll');
	}
}


function getTreeList(arr){
	var result ={"rows":[]};
	var bl={'code':'01','fileName':'病例文档','path':'','status':'','parentCode':'','isTop':'0','index':'1','isChild':'0','children':[]};
	var xdbg={'code':'02','fileName':'心电报告','path':'','status':'','parentCode':'','isTop':'0','index':'2','isChild':'0','children':[]};
	var bc={'code':'03','fileName':'B超','path':'','status':'','parentCode':'','isTop':'0','index':'3','isChild':'0','children':[]};
	var cs={'code':'04','fileName':'超声','path':'','status':'','parentCode':'','isTop':'0','index':'4','isChild':'0','children':[]};
	var ct={'code':'05','fileName':'CT','path':'','status':'','parentCode':'','isTop':'0','index':'5','isChild':'0','children':[]};
	var dr={'code':'06','fileName':'DR','path':'','status':'','parentCode':'','isTop':'0','index':'6','isChild':'0','children':[]};
	var cgz={'code':'07','fileName':'磁共振','path':'','status':'','parentCode':'','isTop':'0','index':'7','isChild':'0','children':[]};
	var consultaTionNode={'code':'consultaTion','fileName':'基础信息','path':'','status':'','parentCode':'','isTop':'0','index':'0',
			'isChild':'0','children':[],'consultaTionCode':selectRowData.uniqueCode,'type':'consultaTion'};
	var c=getChild(arr,bl.code);
	if(c!=null && c.length>0){
		bl.children=c;
	}
	c=getChild(arr,xdbg.code);
	if(c!=null && c.length>0){
		xdbg.children=c;
	}
	c=getChild(arr,bc.code);
	if(c!=null && c.length>0){
		bc.children=c;
	}
	c=getChild(arr,cs.code);
	if(c!=null && c.length>0){
		cs.children=c;
	}
	c=getChild(arr,ct.code);
	if(c!=null && c.length>0){
		ct.children=c;
	}
	c=getChild(arr,dr.code);
	if(c!=null && c.length>0){
		dr.children=c;
	}
	c=getChild(arr,cgz.code);
	if(c!=null && c.length>0){
		cgz.children=c;
	}
	result.rows.push(consultaTionNode);
	result.rows.push(bl);
	result.rows.push(xdbg);
	result.rows.push(bc);
	result.rows.push(cs);
	result.rows.push(ct);
	result.rows.push(dr);
	result.rows.push(cgz);
	return result;
}


function getChild(arr,parentCode){
	var result ={"rows":[]};
	if(arr!=null){
		for(var i=0;i<arr.length;i++){
			var obj = arr[i];
			if (parentCode==obj.materialType) {
				obj.code=parentCode+i;
				obj.parentCode=parentCode;
				obj.isTop=0;
				obj.index=i;
				obj.children=[];
				result.rows.push(obj);
			}
		}
	}
	return result.rows;
}
var isLoadpage=false;
function loadPage(rowData){
	if(rowData.bussniseCode=="03"||rowData.bussniseCode=="04"||rowData.bussniseCode=="05"||rowData.bussniseCode=="06"||rowData.bussniseCode=="07"){
		if (!isLoadpage) {
			$.ajax({
				type: "GET",
				url: rootPath+"/consultationMedical/ehr/mainCheckUSView.html?serialno=545",
				data: {},
				dataType: "html",
				success: function(responseText){
					$("#blpage").html(responseText);
						$.ajax({
							type: "GET",
							url: rootPath+rowData.url,
							data: {},
							dataType: "html",
							success: function(div1Html){
								$("#div1").html(div1Html);
//								loadDcmFilejc(rowData.filePath);
								$("#blpage").show();
							}
						});
				}
			});
		}else{
			b=false;
			$.ajax({
				type: "GET",
				url: rootPath+rowData.url,
				data: {},
				dataType: "html",
				success: function(div1Html){
					$("#div1").html(div1Html);
//					loadDcmFilejc(rowData.filePath);
				}
			});
		}
		return;
	}
	$.ajax({
		type: "GET",
		url: rootPath+rowData.url,
		data: {},
		dataType: "html",
		success: function(responseText){
			$("#blpage").html(responseText);
			$("#blpage").show();
		}
	});
}



function dataClick(rowData){
	centerDivHide();
//	if(rowData.code=="test"){
//		loadPage();
//		$("#blpage").show();
//	}
	if("consultaTion"==rowData.code){
		$("#consultaTion").show();
		loadConsultaTion(null);
	}else if ("01"==rowData.materialType) {
		showDocView(rowData.swfPath);
		$("#docFileDiv").show();
	}else if("02"==rowData.materialType){
		if(rowData.suffix=="pdf"){
			showDocView(rowData.swfPath);
			$("#docFileDiv").show();
		}else if(isImg(rowData.suffix)){
			$("#centerImgFile").attr("src",rootPath+"/swf/ftp/"+rowData.path);
			$("#imgFileDiv").show();
		}
	}else if("03"==rowData.materialType||"04"==rowData.materialType||"05"==rowData.materialType||"06"==rowData.materialType||"07"==rowData.materialType){
		loadDcm(rowData);
		$("#dcmFileDiv").show();
	}
	
	function isImg(suffix){
		if(suffix=="jpg" || suffix=="JPG"){
			return true;
		}else if(suffix=="png" || suffix=="PNG"){
			return true;
		}else if(suffix=="gif" || suffix=="GIF"){
			return true;
		}else if(suffix=="bmp" || suffix=="BMP"){
			return true;
		}else if(suffix=="jpeg" || suffix=="JPEG"){
			return true;
		}
		return false;
	}
	
}

function showDocView(swf){
		var fp = new FlexPaperViewer(	
				 rootPath+'/swf/FlexPaperViewer',
				 'docView', { config : {
				 SwfFile : escape(rootPath+'/swf/ftp/'+swf),
				 Scale : 1.5, 
				 ZoomTransition : 'easeOut',
				 ZoomTime : 0.5,
				 ZoomInterval : 0.2,
				 FitPageOnLoad : false,
				 FitWidthOnLoad : false,
				 FullScreenAsMaxWindow : false,
				 ProgressiveLoading : false,
				 MinZoomSize : 0.2,
				 MaxZoomSize : 5,
				 SearchMatchAll : false,
				 InitViewMode : 'Portrait',
				 PrintPaperAsBitmap : false,
				 ViewModeToolsVisible : true,
				 ZoomToolsVisible : true,
				 NavToolsVisible : true,
				 CursorToolsVisible : true,
				 SearchToolsVisible : true,
					localeChain: 'en_US'
	 }});
}

function loadConsultaTion(consultaTionCode){
	$("#idCard").val(obj2Str(selectRowData.idCard));
	$("#name").val(obj2Str(selectRowData.name));
	$("#officeCode").val(obj2Str(selectRowData.officeCode));
	$("#age").val(obj2Str(selectRowData.age));
	$("#nation").val(obj2Str(selectRowData.nation));
	$("#address").val(obj2Str(selectRowData.address));
	$("#occupation").val(obj2Str(selectRowData.occupation));
	$("#countyCode").val(obj2Str(selectRowData.countyCode));
	$("#ehrCode").val(obj2Str(selectRowData.ehrCode));
	$("#diseaseName").val(obj2Str(selectRowData.diseaseName));
	$("#diseaseCode").val(obj2Str(selectRowData.diseaseCode));
	$("#visitTabloid").val(obj2Str(selectRowData.visitTabloid));
	$("#purpose").val(obj2Str(selectRowData.purpose));
	$("#expectDateView").val(obj2Str(selectRowData.expectDateView));
	$("#expectTimeView").val(obj2Str(selectRowData.expectTimeView));
	
	$('#sexCode').combobox('setValues',obj2Str(selectRowData.sexCode));  
	$('#emergencyMark').combobox('setValues',obj2Str(selectRowData.emergencyMark));  
	$('#sourceCode').combobox('setValues',obj2Str(selectRowData.sourceCode));  
	$('#visitType').combobox('setValues',obj2Str(selectRowData.visitType));  
	$('#marriage').combobox('setValues',obj2Str(selectRowData.marriage));  
	$('#centerCode').combobox('setValues',obj2Str(selectRowData.centerCode));  
}

function loadConsultaTionView(consultaTionCode){
	$("#tab_idCard").html(obj2Str(selectRowData.idCard));
	$("#tab_name").html(obj2Str(selectRowData.name));
	//$("#tab_officeCode").html(getOfficeName(obj2Str(selectRowData.officeCode)));
	getOfficeName(obj2Str(selectRowData.officeCode));
	$("#tab_age").html(obj2Str(selectRowData.age));
	$("#tab_nation").html(obj2Str(selectRowData.nation));
	$("#tab_address").html(obj2Str(selectRowData.address));
	$("#tab_occupation").html(obj2Str(selectRowData.occupation));
	$("#tab_countyCode").html(obj2Str(selectRowData.countyCode));
	$("#tab_ehrCode").html(obj2Str(selectRowData.ehrCode));
	$("#tab_diseaseName").html(obj2Str(selectRowData.diseaseName));
	$("#tab_diseaseCode").html(obj2Str(selectRowData.diseaseCode));
	$("#tab_visitTabloid").html(obj2Str(selectRowData.visitTabloid));
	$("#tab_purpose").html(obj2Str(selectRowData.purpose));
	$("#tab_expectDateView").html(selectRowData.expectDateView);
	$("#tab_expectTimeView").html(selectRowData.expectTimeView);
	$('#tab_returnReason').html(obj2Str(selectRowData.returnReason));
	$('#tab_sexCode').html(function(){
		if(obj2Str(selectRowData.sexCode) == 1){
			return "男";
		}else{
			return "女";
		}
	});  
	$('#tab_emergencyMark').html(function(){
		if(obj2Str(selectRowData.emergencyMark) == 0){
			return "普诊";
		}else{
			return "急诊";
		}
	});
	$('#tab_sourceCode').html(function(){
		if(obj2Str(selectRowData.sourceCode)== 1){
			return "个人申请";
		}else{
			return "医院申请";
		}
	});  
	$('#tab_visitType').html(function(){
		if(obj2Str(selectRowData.visitType)==1){
			return "离线会诊";
		}else{
			return "视频会诊";
		}
	});  
	$('#tab_marriage').html(function(){
		if(obj2Str(selectRowData.marriage) == 1){
			return "未婚";
		}else{
			return "已婚";
		}
	});  
	$('#tab_centerCode').html(obj2Str(selectRowData.centerCode));  
}

function obj2Str(obj){
	if(obj==null||obj==undefined){
		return "";
	}else{
		return obj;
	}
}

function goBack(){
	$("#viewTable").show();
	centerDivHide();
	$("#context").val("");
	$("#remak").val("");
}

function saveDiagnosis(){
	if($("#context").val()==null || $("#context").val()==undefined || $("#context").val().trim()==""){
		alert("诊断结论不能为空!");
		return;
	}
	parent.$.messager.confirm('询问', '您是否要发送诊断报告？', function(b) {
		if (b) {
			$.ajax({
		        type: "POST",
		        url: rootPath+"/consultation/diagnosis.json",
		        data: {'consultationCode':selectRowData.uniqueCode,'verdict':$("#context").val(),'mavinCode':selectRowData.mavinCode},
		        dataType: "json",
		        success: function(data){
		        	if(data.success){
		        		alert("报告发送成功！");
		        	}else{
		        		alert("你没有诊断权限！")	
		        	}
					goBack();
		      	}
		    }); 
		}
	});
}

//提交申请
function consultationApply(fromPage){
	parent.$.messager.confirm('询问','您是否确定申请会诊？',function(b){
		if(b) {
			$.ajax({
				type : "POST",
				url : rootPath + "/consultation/apply/submitapply.json",
				data : {
					'id' : obj2Str(selectRowData.id),
					'fromPage' : fromPage
				},
				dataType : 'json',
				success : function(data){
					alert("申请提交成功");
					goBack();
					$('#dataGrid').datagrid('reload');
				}
			});
		}
	});
}

//会诊发送会中心
function consultationReturnCenter(){
	parent.$.messager.confirm('询问','您是否确定把当前会诊退回中心？', function(b){
		if(b){
			$.ajax({
				type : "POST",
				url : rootPath + "/consultation/apply/submitapply.json",
				data : {
					'id' : obj2Str(selectRowData.id)
				},
				dataType : 'json',
				success : function(data){
					alert("会诊已发送会中心");
					goBack();
					$('#dataGrid').datagrid('reload');
				}
			});
		}
	});
}

//会诊审核
function consultationAudit(){
	var diagnosisOrganCode = $('#diagnosisOrganCode').val();
	var mavinCode = $('#mavinCode').val();
	if(diagnosisOrganCode==null || diagnosisOrganCode==undefined || diagnosisOrganCode.trim()==""){
		alert("诊断机构不能为空!");
		return;
	}
	if(mavinCode==null || mavinCode==undefined || mavinCode.trim() == ""){
		alert("专家不能为空");
		return;
	}
	parent.$.messager.confirm('询问', '您是否确定会诊审核通过？', function(b) {
		if (b) {
			$.ajax({
		        type: "POST",
		        url: rootPath+"/consultation/audit/auditapprove.json",
		        data: {
		        	'diagnosisOrganCode': diagnosisOrganCode,
		        	'mavinCode' :mavinCode,
		        	'id':obj2Str(selectRowData.id)},
		        dataType: "json",
		        success: function(data){
					alert("审核通过！");
					goBack();
					$('#dataGrid').datagrid('reload');
		      	}
		    }); 
		}
	});
}

//审核退回
function consultationReturn(){
	if($('#returnReason').val()== null || $('#returnReason').val() == undefined || $('#returnReason').val().trim()==""){
		alert("退回理由不能为空!");
		return;
	}
	parent.$.messager.confirm('询问','您是否确定退回审核？',function(b){
		if(b){
			$.ajax({
				type : "POST",
				url : rootPath+"/consultation/audit/invalid.json",
				data : {
					'returnReason' : $('#returnReason').val(),
					'id' : obj2Str(selectRowData.id)
				},
				dataType : 'json',
				success : function(data){
					alert("审核退回成功！");
					goBack();
					$('#dataGrid').datagrid('reload');
				}
			});
		}
	});
}

//专家退回
function consultReturn(){
	if($('#returnReason').val()== null || $('#returnReason').val() == undefined || $('#returnReason').val().trim()==""){
		alert("退回理由不能为空!");
		return;
	}
	parent.$.messager.confirm('询问','您是否确定退回审核？',function(b){
		if(b){
			$.ajax({
				type : "POST",
				url : rootPath+"/consultation/consult/invalid.json",
				data : {
					'returnReason' : $('#returnReason').val(),
					'id' : obj2Str(selectRowData.id)
				},
				dataType : 'json',
				success : function(data){
					alert("审核退回成功！");
					goBack();
					$('#dataGrid').datagrid('reload');
				}
			});
		}
	});
}

//异步加载诊断结果信息
function loadCompletedConsultation(mainFlowCode){
	if(mainFlowCode == null && mainFlowCode == undefined && mainFlowCode == ""){
		return;
	}
	$.ajax({
		type : "POST",
		url : rootPath + "/consultation/completed/loadCompletedConsultation.json",
		data : {
			'mainFlowCode' : mainFlowCode
		},
		dataType : 'json',
		success : function (data){
			$("#tab_verdict").html(data.verdict);
			$("#tab_visitDate").html(new Date(data.visitDate).Format("yyyy-MM-dd").toLocaleString());
		}
	});
}
