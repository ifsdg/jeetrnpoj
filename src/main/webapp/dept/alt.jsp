<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form class="layui-form layui-form-pane" id="altuser"  action="user.do" >
			<div class="layui-form-item">
				<label class="layui-form-label">新科室名</label>
				<div class="layui-input-block">
				<input type="hidden" class="layui-input" name="id" id="id" value="" />
					<input type="text" class="layui-input" name="name" id="name" value="" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">新联系电话</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" name="tel" id="tel" value="" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">科室地址</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" name="address" id="address" value="" />
				</div>
			</div>
			
		</form>	
		<script src="../plugins/layui/layui.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>