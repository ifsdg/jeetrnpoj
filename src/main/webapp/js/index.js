/** index.js By Beginner Emain:zheng_jinfan@126.com HomePage:http://www.zhengjinfan.cn */
layui.config({
	base: 'js/'
}).use(['element', 'layer', 'navbar', 'tab'], function() {
	var $ = layui.jquery,
		navbar = layui.navbar(),
		tab = layui.tab({
			elem: '.admin-nav-card' //设置选项卡容器
		});
	//iframe自适应
	$(window).on('resize', function() {
		var $content = $('.admin-nav-card .layui-tab-content');
		$content.height($(this).height() - 147);
		$content.find('iframe').each(function() {
			$(this).height($content.height());
		});
	}).resize();

	//设置navbar
	navbar.set({
		spreadOne: true,
		elem: '#admin-navbar-side',
		cached: true,
		data: [{
				"title": "基本信息管理",
				"icon": "fa-cubes",
				"spread": true,
				"children": [{
					"title": "新增病号",
					"icon": "&#xe641;",
					"href": "patient/add.jsp"
				},{
					"title": "住院列表",
					"icon": "&#xe641;",
					"href": "patient/list.jsp"
				}]
			}, {
				"title": "系统管理",
				"icon": "fa-cogs",
				"spread": false,
				"children": [{
					"title": "科室管理",
					"icon": "fa-table",
					"href":"dept/list.jsp"
				},{
					"title": "医师管理",
					"icon": "fa-table",
					"href":"doctor/list.jsp"
				},{
					"title": "病房管理",
					"icon": "fa-table",
					"href":"ward/list.jsp"
				}
				
				]
			}]
	});
	//渲染navbar
	navbar.render();
	//监听点击事件
	navbar.on('click(side)', function(data) {
		tab.tabAdd(data.field);
	});
	
	
});