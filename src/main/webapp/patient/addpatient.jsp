<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="../plugins/layui/css/layui.css" />
		<title></title>
	</head>
	<body>

		<div style="width: -webkit-fit-content; margin: auto;" class="layui-fluid layui-form">


			<h1 align="center">住院登录</h1>

			<form action="User.do" method="post" class="layui-form layui-form-pane" enctype="multipart/form-data"
				lay-filter="#formtes">
				<div class="layui-form-item">
					<label class="layui-form-label">姓名：</label>
					<div class="layui-input-block ">
						<input type="text" class="layui-input" lay-verify="required|username" name="username"
							autocomplete="off" required="required" placeholder="请输入用户名" />*
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">性别：</label>
						<div class="layui-input-block">
							<input type="radio" name="gender" value="M" title="男"  autocomplete="off"
								checked="checked" />
							<input type="radio" name="gender" value="F" title="女" autocomplete="off" />
						</div>

					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">年龄：</label>
					<div class="layui-input-block">
						<input type="text" name="age" class="layui-input" />
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">住院编号：</label>
					<div class="layui-input-block">
						<input type="text" name="patino" required  class="layui-input"
							autocomplete="off" placeholder="请输入编号" />
					</div>

				</div>
			

				<div class="layui-form-item">
					<label class="layui-form-label">科室：</label>
					<div class="layui-input-block">
						<select name="dept" id="dept" lay-filter="#dept" lay-search>
							
						</select>
					</div>

				</div>

				<div class="layui-form-item" >
					<label class="layui-form-label">病房</label>

					<div class="layui-input-inline">
						<select  lay-filter="#room" name="room" id="room" lay-search>
							<option value="none" selected="selected"></option>
						</select>
					</div>

				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">主治医生：</label>
					<div class="layui-input-block">
						<select id="doc" name="=doc" lay-filter="doc">
							
						</select>
					</div>

				</div>
				
				<div align="middle">
					<div class="layui-form-item">
						<div class="layui-input-block">
							<input name="ope" value="add" hidden="hidden" />
							<input type="reset" class="layui-btn" value="重置" />
							<input type="submit" class="layui-btn" value="提交" lay-filter="sub" />
						</div>
					</div>
				</div>


			</form>
		</div>
	
		<script type="application/javascript" src="../plugins/jquery3.6.0.js"></script>
	<script src="../plugins/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			layui.use('form', function() {
				var form = layui.form;
				$.ajax({
				    url:'/dept',
				    dataType:'json',
				    type:'post',
				    success:function(data){
				    	$.each(data,function(i,item){
				
				    		$('#dept').append("<option value='"+item.id+"'>"+item.name+"</option>");//往下拉菜单里添加元素
							form.render(); 
				    	})	
					}
				});
				form.on('select(#dept)',function(data){
					$.ajax({
						url:'/Doc',
						dataType:'json',
						type:'post',
						data:{action:'find',id:data.value},
						success:function(data){
							$('#doc').empty();	
							$.each(data,function(i,item){
									
								$('#doc').append("<option value='"+item.id+"'>"+item.name+"</option>");//往下拉菜单里添加元素
								form.render(); 
							})	
						}
					});
					$.ajax({
						url:'/Ward',
						dataType:'json',
						type:'post',
						data:{action:'find',id:data.value},
						success:function(data){
							$('#room').empty();	
							$.each(data,function(i,item){
									
								$('#room').append("<option value='"+item.wardno+"'>第"+item.wardno+"病房</option>");//往下拉菜单里添加元素
								form.render(); 
							})	
						}
					});
				})
			
			
			});
		
		</script>
	</body>
</html>
