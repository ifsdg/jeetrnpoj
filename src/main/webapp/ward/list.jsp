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
		<div class="demoTable layui-form layui-inline">
		
			<div class="layui-inline">
				<label class="layui-form-label">搜索ID</label>
			</div>
			<div class="layui-inline">
				<input class="layui-input"  id="Reload" autocomplete="off">
			</div>
			<button class="layui-btn" data-type="reload" id="serBtn">搜索</button>
		
		</div>
			<div class="layui-btn-container layui-inline" style="margin-top: 12px;">
				<a class="layui-btn" id="addbtn">添加新病房</a>
			</div>
	
		<table border="" class="layui-table" id="rtab" lay-filter="try">


		</table>
		<table border="" class="layui-table" id="ltab">


		</table>

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
				var table = layui.table;

				table.render({
					elem: '#rtab',
					url: '/Ward',
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
								title: '病房id',
								width: 100
							}, {
								field: 'dep',
								title: '科室名',
								width: 200
							}, {
								field: 'wardno',
								title: '病房编号',
								width: 100
							}, {
								field: 'address',
								title: '病房地址',
								width: 200
							}, {
								field: 'count',
								title: '现有人数',
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
				
				
				$("#serBtn").on('click', function() {

					var reloadtab = $('#Reload');
						setTimeout(function() {
							table.reload('rtab', {

								where: {
									action: 'serch',
									target:'id',
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
												wardno: dat[1].value,
												address: dat[2].value,
												deptno: dep[0].value
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
											wardno: dat[1].value,
											address: dat[2].value,
											deptno: dep[0].value
										},done:function(){this.where={action:'list'};}
									});
									layer.closeAll();
								},
								btn2: function(index, layero) {

								},
								success: function(layero, index) {
									tmpIndex = index;
									var body = layer.getChildFrame('body', index);
									body.find('#wardno').val(data.wardno)
							
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
