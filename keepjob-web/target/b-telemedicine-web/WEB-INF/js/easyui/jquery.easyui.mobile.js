(function(a){a.fn.navpanel=function(d,c){if(typeof d=="string"){var b=a.fn.navpanel.methods[d];return b?b(this,c):this.panel(d,c)}else{d=d||{};return this.each(function(){var e=a.data(this,"navpanel");if(e){a.extend(e.options,d)}else{e=a.data(this,"navpanel",{options:a.extend({},a.fn.navpanel.defaults,a.fn.navpanel.parseOptions(this,d))})}a(this).panel(e.options)})}};a.fn.navpanel.methods={options:function(b){return a.data(b[0],"navpanel").options}};a.fn.navpanel.parseOptions=function(b){return a.extend({},a.fn.panel.parseOptions(b),a.parser.parseOptions(b,[]))};a.fn.navpanel.defaults=a.extend({},a.fn.panel.defaults,{fit:true,border:false,cls:"navpanel"});a.parser.plugins.push("navpanel")})(jQuery);(function(a){a(function(){a.mobile.init()});a.mobile={defaults:{animation:"slide",direction:"left",reverseDirections:{up:"down",down:"up",left:"right",right:"left"}},panels:[],init:function(c){a.mobile.panels=[];var b=a(c||"body").children(".navpanel:visible");if(b.length){b.not(":first").children(".panel-body").navpanel("close");var d=b.eq(0).children(".panel-body");a.mobile.panels.push({panel:d,animation:a.mobile.defaults.animation,direction:a.mobile.defaults.direction})}a(document).unbind(".mobile").bind("click.mobile",function(i){var h=a(i.target).closest("a");if(h.length){var g=a.parser.parseOptions(h[0],["animation","direction",{back:"boolean"}]);if(g.back){a.mobile.back();i.preventDefault()}else{var f=a.trim(h.attr("href"));if(/^#/.test(f)){var j=a(f);if(j.length&&j.hasClass("panel-body")){a.mobile.go(j,g.animation,g.direction);i.preventDefault()}}}}});a(window).unbind(".mobile").bind("hashchange.mobile",function(){var f=a.mobile.panels.length;if(f>1){var e=location.hash;var g=a.mobile.panels[f-2];if(!e||e=="#&"+g.panel.attr("id")){a.mobile._back()}}})},nav:function(d,h,c,b){if(window.WebKitAnimationEvent){c=c!=undefined?c:a.mobile.defaults.animation;b=b!=undefined?b:a.mobile.defaults.direction;var g="m-"+c+(b?"-"+b:"");var f=a(d).panel("open").panel("resize").panel("panel");var e=a(h).panel("open").panel("resize").panel("panel");f.add(e).bind("webkitAnimationEnd",function(){a(this).unbind("webkitAnimationEnd");var i=a(this).children(".panel-body");if(a(this).hasClass("m-in")){i.panel("open").panel("resize")}else{i.panel("close")}a(this).removeClass(g+" m-in m-out")});e.addClass(g+" m-in");f.addClass(g+" m-out")}else{a(h).panel("open").panel("resize");a(d).panel("close")}},_go:function(f,d,c){d=d!=undefined?d:a.mobile.defaults.animation;c=c!=undefined?c:a.mobile.defaults.direction;var b=a.mobile.panels[a.mobile.panels.length-1].panel;var e=a(f);if(b[0]!=e[0]){a.mobile.nav(b,e,d,c);a.mobile.panels.push({panel:e,animation:d,direction:c})}},_back:function(){if(a.mobile.panels.length<2){return}var e=a.mobile.panels.pop();var d=a.mobile.panels[a.mobile.panels.length-1];var c=e.animation;var b=a.mobile.defaults.reverseDirections[e.direction]||"";a.mobile.nav(e.panel,d.panel,c,b)},go:function(d,c,b){c=c!=undefined?c:a.mobile.defaults.animation;b=b!=undefined?b:a.mobile.defaults.direction;location.hash="#&"+a(d).attr("id");a.mobile._go(d,c,b)},back:function(){history.go(-1)}};a.map(["validatebox","textbox","filebox","searchbox","combo","combobox","combogrid","combotree","datebox","datetimebox","numberbox","spinner","numberspinner","timespinner","datetimespinner"],function(b){if(a.fn[b]){a.extend(a.fn[b].defaults,{height:32,iconWidth:28,tipPosition:"bottom"})}});a.map(["spinner","numberspinner","timespinner","datetimespinner"],function(b){a.extend(a.fn[b].defaults,{height:32,iconWidth:56})});a.extend(a.fn.menu.defaults,{itemHeight:30,noline:true})})(jQuery);