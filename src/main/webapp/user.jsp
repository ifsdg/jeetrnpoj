<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="css/global.css" />
		<link rel="stylesheet" type="text/css" href="plugins/layui/css/layui.css" />
		<title></title>
	</head>
	<body>
	
		<table border="" class="layui-table" id="rtab" lay-filter="try">


		</table>
		<table border="" class="layui-table" id="ltab">


		</table>

		<script type="text/javascript" src="./plugins/layui/layui.js"></script>


		<script type="text/html" id="toolbar">
		<div class="demoTable layui-form layui-inline">
		
			<div class="layui-inline">
				<select id="tserch" class="layui-select">
					<option value="id"> 搜索ID</option>
					<option value="name"> 搜索用户名</option>
				</select>
			</div>
			<div class="layui-inline">
				<input class="layui-input"  id="Reload" autocomplete="off">
			</div>
			<button class="layui-btn" data-type="reload" id="serBtn">搜索</button>
		
		</div>
			<div class="layui-btn-container layui-inline">
				<a class="layui-btn" href="/Roel.do?action=serch" id="adduser">添加新用户</a>
			</div>
		</script>

		<script type="text/html" id="sidebar">
			<div class="layui-btn-container">
				<button class="layui-btn layui-btn-xs" lay-event="edit">编辑</button>
				<button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</button>
			</div>
		</script>
		<script type="text/javascript">
			var data;
			layui.use(["table", "laypage", "form"], function() {
				var table = layui.table;

				table.render({
					elem: '#rtab',
					url: 'User.do',
					page: true,
					limits: [5, 10, 15, 20],
					limit: 5,
					toolbar: '#toolbar',
					id: 'rtab',
					where: {
						ope: 'read'
					},
					cols: [
						[{
								checkbox: true
							}, {
								field: 'userid',
								title: 'ID',
								width: 200
							}, {
								field: 'username',
								title: '用户名',
								width: 200
							}, {
								field: 'password',
								title: '密码',
								width: 100
							}, {
								field: 'role',
								title: '用户权限',
								width: 200
							}, {
								field: 'name',
								title: '姓名',
								width: 200
							}, {
								field: 'gender',
								title: '性别',
								width: 200
							}, {
								field: 'birthday',
								title: '生日',
								width: 200
							}, {
								field: 'creattime',
								title: '注册时间',
								width: 200
							}, {
								field: 'age',
								title: '年龄',
								width: 200
							}, {
								field: 'satute',
								title: '状态',
								width: 200
							}, {
								toolbar: '#sidebar',
								width: 150,
								fixed: 'right'
							}


						]
					]

				});

				var form = layui.form;
				var $ = layui.jquery;
				var serchtab = 'id';
				form.on('select', function(data) {
					serchtab = data.value;

				});
				$("#serBtn").on('click', function() {

					var reloadtab = $('#Reload');
						setTimeout(function() {
							table.reload('rtab', {

								where: {
									ope: 'serch',
									target:serchtab,
									data: reloadtab.val()
								},
								page: {
									curr: 1 //重新从第 1 页开始
								}
							});
						}, 800);
					

				})




				table.on('tool(try)', function(obj) {
					data = obj.data;
					var layEvent = obj.event;
					var tr = obj.tr;
					switch (obj.event) {
						case 'edit':
							var tmpIndex = '';
							layer.open({
								type: 2,
								area: ['500px', '600px'],
								title: 'AltUser',
								content: '<%=request.getContextPath() %>/userjsp/alt.jsp',
								shade: 0.3,
								shadeClose: true,
								btn: ['提交', '取消'],
								btn1: function(index, layero) {
									var dat = layer.getChildFrame('input', index);
									table.reload('rtab', {

										where: {
											ope: 'alt',
											oname: data.username,
											nname: dat[0].value,
											npass: dat[1].value
										},
									});
									layer.closeAll();
								},
								btn2: function(index, layero) {

								},
								success: function(layero, index) {
									tmpIndex = index;
									var body = layer.getChildFrame('body', index);
									body.find('#name').val(data.username)
								},
								cancel: function(layero, index) {


									layer.closeAll();
								}

							});
							break;
						case 'del':
							layer.confirm('真的删除行么', function(index) {
								table.reload('rtab', {

									where: {
										ope: 'del',
										name: data.username
									},
								});
								layer.close(index);
							});
							break;

							action = obj.event;
							console.log(layui.input)


					}
				});




				//转换静态表格
				table.init('rtab', {
					height: 315 //设置高度
						,
					limit: 10 //注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
					//支持所有基础参数
				});
			});
		</script>
	</body>


</html>
