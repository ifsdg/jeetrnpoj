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
	<div class=" layui-form layui-inline">
	
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
	<div class="layui-btn-container layui-inline" style="margin-top: 11px;">
		<a class="layui-btn"  id="addbtn">添加新医师</a>
	</div>
		<table border="" class="layui-table" id="rtab" lay-filter="try">

		<tfoot>
			
		</tfoot>
		</table>
		<div id="tfootbar">
				
			</div>

		<script type="text/javascript" src="../plugins/layui/layui.js"></script>


	

		<script type="text/html" id="sidebar">
			<div class="layui-btn-container">
				<button class="layui-btn layui-btn-xs" lay-event="edit">编辑</button>
				<button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</button>
			</div>
		</script>
		<script type="text/javascript">
			var data;
			layui.use(["table", "laypage", "form"], function() {
				var table = layui.table,laypage=layui.laypage,$=layui.jquery;

				table.render({
					elem: '#rtab',
					url: '/Doc',
					page: true,
					limits: [5, 10, 15, 20],
					limit: 5,
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
								field: 'cardno',
								title: '医师id',
								width: 200
							}, {
								field: 'dept',
								title: '所属科室',
								width: 100
							}, {
								field: 'title',
								title: '职称',
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
									action: 'serch',
									target:serchtab,
									data: reloadtab.val()
								},
								page: {
									curr: 1 //重新从第 1 页开始
								},done:function(){this.where={action:'list'};}
							});
						}, 800);
					

				})
				$("#addbtn").on('click',function(){
					layer.open({
						type: 2,
						area: ['500px', '600px'],
						title: 'AltUser',
						content: './add.jsp',
						shade: 0.5,
						shadeClose: true,
						btn: ['提交', '取消'],
						btn1: function(index, layero) {
							var dat = layer.getChildFrame('input', index);
							var dep=layer.getChildFrame('select',index);
							
							table.reload('rtab', {
					
								where: {
									action: 'add',
									id:dat[0].value,
									name: dat[1].value,
									cardno: dat[2].value,
									title: dat[3].value,
									dept: dep[0].value
								},done:function(){this.where={action:'list'};}
							});
							layer.closeAll();
						},
						btn2: function(index, layero) {
					
						},
						cancel: function(layero, index) {
					
					
							layer.closeAll();
						}
					
					});
					
				});



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
									var dep=layer.getChildFrame('select',index);
									
									table.reload('rtab', {

										where: {
											action: 'alt',
											id:dat[0].value,
											name: dat[1].value,
											cardno: dat[2].value,
											title: dat[3].value,
											dept: dep[0].value
										},done:function(){this.where={action:'list'};}
									});
									layer.closeAll();
								},
								btn2: function(index, layero) {

								},
								success: function(layero, index) {
									tmpIndex = index;
									var body = layer.getChildFrame('body', index);
									body.find('#name').val(data.name)
									body.find('#cardno').val(data.cardno)
									body.find('#title').val(data.title)
									body.find('#id').val(data.id)
									body.find('#tes').val(data.dept_id)
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
									},done:function(){this.where={action:'list'};}
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
