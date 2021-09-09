<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="../css/global.css" />
		<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css" />
		<title></title>
	</head>
	<body>
	
		<table border="" class="layui-table" id="rtab" lay-filter="try">


		</table>
		<table border="" class="layui-table" id="ltab">


		</table>

		<script type="text/javascript" src="../plugins/layui/layui.js"></script>


		<script type="text/html" id="toolbar">
		<div class="demoTable layui-form layui-inline">
		
			<div class="layui-inline">
				<select id="tserch" class="layui-select">
					<option value="id"> 搜索ID</option>
					<option value="name"> 搜索姓名</option>
				</select>
			</div>
			<div class="layui-inline">
				<input class="layui-input"  id="Reload" autocomplete="off">
			</div>
			<button class="layui-btn" data-type="reload" id="serBtn">搜索</button>
		
		</div>
			<div class="layui-btn-container layui-inline">
				<!--<a class="layui-btn" href="" id="adduser">添加新hz</a>-->
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
					url: '/Pati',
					page: true,
					limits: [5, 10, 15, 20],
					limit: 5,
					toolbar: '#toolbar',
					id: 'rtab',
					where: {
						action:'list'
					},
					cols: [
						[{
								checkbox: true
							}, {
								field: 'id',
								title: 'ID',
								width: 100
							}, {
								field: 'name',
								title: '姓名',
								width: 100
							}, {
								field: 'gender',
								title: '性别',
								width: 90
							}, {
								field: 'age',
								title: '年龄',
								width: 100
							},{
								field: 'dep',
								title: '科室',
								width: 200
							},{
								field: 'ward_id',
								title: '病房号',
								width: 200
							}, {
								field: 'inpatientno',
								title: '住院号',
								width: 200
							},{
								field: 'doctor',
								title: '主治医师',
								width: 100
							},{
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
								content: './alt.jsp',
								shade: 0.5,
								shadeClose: true,
								btn: ['提交', '取消'],
								btn1: function(index, layero) {
									var dat = layer.getChildFrame('input', index);
									table.reload('rtab', {

										where: {
											action: 'alt',
											id:dat[0].value,
											name: dat[1].value,
											tel: dat[2].value,
											adderss: dat[3].value
										},
									});
									layer.closeAll();
								},
								btn2: function(index, layero) {

								},
								success: function(layero, index) {
									tmpIndex = index;
									var body = layer.getChildFrame('body', index);
									body.find('#name').val(data.name)
									body.find('#tel').val(data.tel)
									body.find('#address').val(data.address)
									body.find('#id').val(data.id)
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
										action: 'del',
										id: data.id
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
