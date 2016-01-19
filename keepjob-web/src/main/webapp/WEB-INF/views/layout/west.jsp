<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
	.easyui-accordion ul{list-style-type:none;margin:0px; padding:10px;}
	.easyui-accordion ul li{ padding:0px;}
	.easyui-accordion ul li a{line-height:24px;}
	.easyui-accordion ul li div{margin:2px 0px;padding-left:10px;padding-top:2px;border:1px solid white;}
	.easyui-accordion ul li div.hover{border:1px dashed #99BBE8; background:#E0ECFF;cursor:pointer;}
	.easyui-accordion ul li div.hover a{color:#416AA3;}
	.easyui-accordion ul li div.selected{border:1px solid #99BBE8; background:#E0ECFF;cursor:default;}
	.easyui-accordion ul li div a{color:black; font-weight:bold;}
	.easyui-accordion ul li div.selected a{color:#416AA3;}
	.icon{width:30px; line-height:18px; display:inline-block;}
	a{ color:Black; text-decoration:none;}
	
</style>
<script type="text/javascript" charset="utf-8">
	$(function() {
		InitLeftMenu();
	});
	
	//初始化左侧
	function InitLeftMenu() {
		$("#nav").accordion({animate:false});
		$.get("${pageContext.request.contextPath}/main/loadleftmenu.json",{isSyn:true},function(result){
			$(result).each(function(){
				var html ='<ul class="easyui-tree">';
				html += getChildren(this.children);
				html += '</ul>';
				$('#nav').accordion("add", {
		            title: this.text,
		            content: html
		        });
				$(".easyui-accordion li a").click(function(){
					var tabTitle = "&nbsp;" + $(this).children(".nav").text();
					var url = $(this).attr("rel");
					var menuid = $(this).attr("ref");
					var icon = $(this).children("span:first-child").attr("class");//"icon icon-seach";
					addTab(tabTitle,url,icon);
					$(".easyui-accordion li a").removeClass("selected");
					$(this).parent().addClass("selected");
				}).hover(function(){
					$(this).parent().addClass("hover");
				},function(){
					$(this).parent().removeClass("hover");
				});
				//选中第一个
				var panels = $("#nav").accordion('panels');
				var t = panels[0].panel('options').title;
			    $("#nav").accordion('select', t);
			});
		},"json");
	}
	
	function getChildren(children){
		var html='';
		$(children).each(function(){
			if(this.state == 'open'){
				html += '<li data-options="state:\'closed\'">';
				html += '<span>' + this.text + '</span>';
				html +='<ul>';
				html +=getChildren(this.children);
				html +='</ul>';
				html += '</li>';
			}else{
				html += '<li>';
				html += '<a ref="'+this.id+'" href="#" rel="${pageContext.request.contextPath}' + this.attributes.url + '"><span class="nav">' + this.text + '</span></a>';
				html += '</li>';
			}
		});
		return html;
	}
</script>
<div id="nav" class="easyui-accordion" fit="true" border="false"></div>
