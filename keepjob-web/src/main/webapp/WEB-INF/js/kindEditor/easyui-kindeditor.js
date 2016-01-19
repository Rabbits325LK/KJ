/**
 * 02 Author : ____′↘夏悸 03 Easyui KindEditor的简单扩展. 04
 * 有了这个之后,你就可以像使用Easyui组件的方式使用KindEditor了 05 前提是你需要导入KindEditor的核心js和相关样式.
 * 本插件也需要在Easyui.min和KindEditor之后导入. 06
 * 呵呵..没做深入扩展了,简单实现了一下功能,架子已经搭好.有需要的筒子可以在这基础上扩展. 07
 */
//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
(function($, K) {

	if (!K)

		throw "KindEditor未定义!";

	function create(target) {

		var opts = $.data(target, 'kindeditor').options;

		var editor = K.create(target, opts);

		$.data(target, 'kindeditor').options.editor = editor;

	}

	$.fn.kindeditor = function(options, param) {

		if (typeof options == 'string') {

			var method = $.fn.kindeditor.methods[options];

			if (method) {

				return method(this, param);

			}

		}

		options = options || {};

		return this.each(function() {

			var state = $.data(this, 'kindeditor');

			if (state) {

				$.extend(state.options, options);

			} else {

				state = $.data(this, 'kindeditor', {

					options : $.extend({}, $.fn.kindeditor.defaults,
							$.fn.kindeditor.parseOptions(this), options)

				});

			}

			create(this);

		});

	}

	$.fn.kindeditor.parseOptions = function(target) {

		return $.extend({}, $.parser.parseOptions(target, []));

	};

	$.fn.kindeditor.methods = {

		editor : function(jq) {

			return $.data(jq[0], 'kindeditor').options.editor;

		}

	};

	$.fn.kindeditor.defaults = {

		resizeType : 1,

		allowPreviewEmoticons : false,

		allowImageUpload : true,
		
		allowFileManager : true,
		
		uploadJson : getRootPath()+'/kindeditor/upload_json.jsp',
		
		fileManagerJson : getRootPath()+'/kindeditor/file_manager_json.jsp',
		
		items : [

		'source', '|', 'undo', 'redo', '|', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline','strikethrough ', 'hr',

				'removeformat', '|', 'justifyleft', 'justifycenter',
				'justifyright', 'insertorderedlist',  'removeforma',

				'insertunorderedlist', '|', 'selectall', 'cut', 'copy','paste', 'plainpaste', 'wordpaste',  '|', 'emoticons', 'image', 'link', 'print', 'table' ],

		afterChange : function() {

			this.sync();// 这个是必须的,如果你要覆盖afterChange事件的话,请记得最好把这句加上.
		},
		afterBlur: function () { this.sync(); }

	};

	$.parser.plugins.push("kindeditor");

})(jQuery, KindEditor);
