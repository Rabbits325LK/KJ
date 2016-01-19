(function(h,d){h.fn.validatebox.extensions={};var o={engNum:{validator:function(r){return/^[0-9a-zA-Z]*$/.test(r)},message:"请输入英文字母或数字"},chsEngNum:{validator:function(r,s){return/^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9])*$/.test(r)},message:"只允许汉字、英文字母或数字。"},code:{validator:function(r,s){return/^[\u0391-\uFFE5\w]+$/.test(r)},message:"只允许汉字、英文字母、数字及下划线."},name:{validator:function(r){return r.isUserName()},message:"用户名不合法(字母开头，允许6-16字节，允许字母数字下划线)"},minLength:{validator:function(r,s){return h.string.trim(r).length>=s[0]},message:"最少输入 {0} 个字符."},maxLength:{validator:function(r,s){return h.string.trim(r).length<=s[0]},message:"最多输入 {0} 个字符."},contains:{validator:function(r,s){return h.string.contains(r,s[0])},message:"输入的内容必须包含 {0}."},startsWith:{validator:function(r,s){return h.string.startsWith(r,s[0])},message:"输入的内容必须以 {0} 作为起始字符."},endsWith:{validator:function(r,s){return h.string.endsWith(r,s[0])},message:"输入的内容必须以 {0} 作为起始字符."},longDate:{validator:function(r){return h.string.isLongDate(r)},message:"输入的内容必须是长日期时间(yyyy-MM-dd hh:mm:ss)格式."},shortDate:{validator:function(r){return h.string.isShortDate(r)},message:"输入的内容必须是短日期(yyyy-MM-dd)格式."},date:{validator:function(r){return h.string.isDate(r)},message:"输入的内容必须是长日期时间(yyyy-MM-dd hh:mm:ss)或短日期(yyyy-MM-dd)格式."},tel:{validator:function(r){return h.string.isTel(r)},message:"输入的内容必须是电话号码(中国)格式."},mobile:{validator:function(r){return h.string.isMobile(r)},message:"输入的内容必须是移动电话号码(中国)格式."},telOrMobile:{validator:function(r){return h.string.isTelOrMobile(r)},message:"输入的内容必须是电话号码(中国)或移动电话号码(中国)格式."},fax:{validator:function(r){return h.string.isFax(r)},message:"输入的内容必须是传真号码(中国)格式."},zipCode:{validator:function(r){return h.string.isZipCode(r)},message:"输入的内容必须是邮政编码(中国)格式."},existChinese:{validator:function(r){return h.string.existChinese(r)},message:"输入的内容必须是包含中文汉字."},chinese:{validator:function(r){return h.string.isChinese(r)},message:"输入的内容必须是纯中文汉字."},english:{validator:function(r){return h.string.isEnglish(r)},message:"输入的内容必须是纯英文字母."},fileName:{validator:function(r){return h.string.isFileName(r)},message:'输入的内容必须是合法的文件名(不能包含字符 \\/:*?"<>|).'},ip:{validator:function(r){return h.string.isIPv4(r)},message:"输入的内容必须是正确的 IP地址v4 格式."},url:{validator:function(r){return h.string.isUrl(r)},message:"输入的内容必须是正确的 url 格式."},ipurl:{validator:function(r){return h.string.isUrlOrIPv4(r)},message:"输入的内容必须是正确的 IP地址v4 或 url 格式."},currency:{validator:function(r){return h.string.isCurrency(r)},message:"输入的内容必须是正确的货币金额(阿拉伯数字表示法)格式."},qq:{validator:function(r){return h.string.isQQ(r)},message:"输入的内容必须是正确 QQ 号码格式."},msn:{validator:function(r){return h.string.isMSN(r)},message:"输入的内容必须是正确 MSN 账户名格式."},unNormal:{validator:function(r){return h.string.isUnNormal(r)},message:"输入的内容必须是不包含空格和非法字符Z."},carNo:{validator:function(r){return h.string.isCarNo(r)},message:"输入的内容必须是合法的汽车车牌号码格式."},carEngineNo:{validator:function(r){return h.string.isCarEngineNo(r)},message:"输入的内容必须是合法的汽车发动机序列号格式."},idCard:{validator:function(r){return h.string.isIDCard(r)},message:"输入的内容必须是合法的身份证号码(中国)格式."},integer:{validator:function(r){return h.string.isInteger(r)},message:"输入的内容必须是合法的整数格式."},integerRange:{validator:function(r,s){return h.string.isInteger(r)&&((s[0]||r>=s[0])&&(s[1]||r<=s[1]))},message:"输入的内容必须是合法的整数格式且值介于 {0} 与 {1} 之间."},numeric:{validator:function(r,s){return h.string.isNumeric(r,s?s[0]:d)},message:"输入的内容必须是指定类型的数字格式."},numericRange:{validator:function(r,s){return h.string.isNumeric(r,s?s[2]:d)&&((s[0]||r>=s[0])&&(s[1]||r<=s[1]))},message:"输入的内容必须是指定类型的数字格式且介于 {0} 与 {1} 之间."},color:{validator:function(r){return h.string.isColor(r)},message:"输入的内容必须是正确的 颜色(#FFFFFF形式) 格式."},password:{validator:function(r){return h.string.isSafePassword(r)},message:"输入的内容必须是安全的密码字符(由字符和数字组成，至少 6 位)格式."},equals:{validator:function(s,u){var t=u[0],r=u[1];if(r){switch(String(r).toLowerCase()){case"jquery":case"dom":t=h(t).val();break;case"id":t=h("#"+t).val();break;case"string":default:break}}return s===t},message:"输入的内容不匹配."}};h.extend(h.fn.validatebox.defaults.rules,o);function i(u){var r=h(u);var s=r.validatebox("options");if(!s._initialized){r.addClass("validatebox-f").change(function(){s.value=h(this).val();if(h.isFunction(s.onChange)){s.onChange.call(u,s.value)}});s.originalValue=s.value;if(s.value){e(u,s.value)}if(s.width&&!r.parent().is("span.combo,span.spinner,span.searchbox")){b(u,s.width)}p(u,s.prompt,s);if(s.autoFocus){h.util.exec(function(){r.focus()})}if(!s.autovalidate){r.validatebox("disableValidation").validatebox("enableValidation")}if(s.defaultClass){r.addClass(s.defaultClass)}l(u,s.disabled);s._initialized=true}}function p(v,r,u){var s=h(v);u=u||s.validatebox("options");u.prompt=r;if(h.html5.testProp("placeholder",s[0].nodeName)){s.attr("placeholder",r)}else{if(!h.isFunction(u.promptFocus)){u.promptFocus=function(){if(s.hasClass("validatebox-prompt")){s.removeClass("validatebox-prompt");if(s.val()==u.prompt){s.val("")}}};s.focus(u.promptFocus)}if(!h.isFunction(u.promptBlur)){u.promptBlur=function(){if(h.string.isNullOrEmpty(s.val())){s.addClass("validatebox-prompt").val(u.prompt)}};s.blur(u.promptBlur)}if(h.string.isNullOrEmpty(s.val())&&!h.string.isNullOrEmpty(u.prompt)){h.util.exec(function(){s.addClass("validatebox-prompt").val(u.prompt)})}}}var n=h.fn.validatebox.methods.isValid;function m(s){var r=h(s);if(r.hasClass("validatebox-prompt")){r.removeClass("validatebox-prompt").val("")}return n.call(r,r)}function e(v,u){var r=h(v),s=r.validatebox("options"),w=r.val();if(w!=u){r.val(s.value=(u?u:""))}m(v)}function g(r){return h(r).val()}function j(u){var r=h(u),s=r.validatebox("options");r.validatebox("setValue","")}function k(u){var r=h(u),s=r.validatebox("options");r.validatebox("setValue",s.originalValue?s.originalValue:"")}function b(v,s){var r=h(v),u=r.validatebox("options");r._outerWidth(u.width=s)}function l(v,s){var r=h(v),u=h.data(v,"validatebox");if(s){if(u&&u.options){u.options.disabled=true}r.attr("disabled",true)}else{if(u&&u.options){u.options.disabled=false}r.removeAttr("disabled")}}var a=h.fn.validatebox;h.fn.validatebox=function(r,s){if(typeof r=="string"){return a.apply(this,arguments)}r=r||{};return this.each(function(){var v=h(this),t=h.data(this,"validatebox")?true:false,u=t?r:h.extend({},h.fn.validatebox.parseOptions(this),h.parser.parseOptions(this,["prompt",{autoFocus:"boolean"}]),r);u.value=u.value||v.val();a.call(v,u);i(this)})};h.union(h.fn.validatebox,a);var c=h.fn.validatebox.extensions.methods={setPrompt:function(s,r){return s.each(function(){p(this,r)})},validate:function(r){return r.each(function(){m(this)})},isValid:function(r){return m(r[0])},setValue:function(s,r){return s.each(function(){e(this,r)})},getValue:function(r){return g(r[0])},clear:function(r){return r.each(function(){j(this)})},reset:function(r){return r.each(function(){k(this)})},resize:function(s,r){return s.each(function(){b(this,r)})},enable:function(r){return r.each(function(){l(this,false)})},disable:function(r){return r.each(function(){l(this,true)})}};var f=h.fn.validatebox.extensions.defaults={prompt:null,autoFocus:false,value:null,width:null,autovalidate:true,disabled:false,defaultClass:"textbox",onChange:function(r){}};h.extend(h.fn.validatebox.defaults,f);h.extend(h.fn.validatebox.methods,c);if(h.fn.form&&h.isArray(h.fn.form.otherList)){h.fn.form.otherList.push("validatebox")}var q=h.fn.val;h.fn.val=function(s){if(this.length>0&&this.is(".validatebox-text.validatebox-prompt")&&!h.html5.testProp("placeholder",this[0].nodeName)){var t,r=this.validatebox("options");if(arguments.length==0){t=q.apply(this,arguments);return t==r.prompt?"":t}if(s&&s!=r.prompt){this.removeClass("validatebox-prompt")}}return q.apply(this,arguments)}})(jQuery);