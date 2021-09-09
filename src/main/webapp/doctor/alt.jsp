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
				<label class="layui-form-label">ID</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" name="id" id="id" value="" disabled="disabled" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">医师名</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" name="name" id="name" value="" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">医师ID</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" name="cardno" id="cardno" value="" disabled="disabled"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">职称</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" name="title" id="title" value="" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">所属科室</label>
				<div class="layui-input-block">
					<select name="dept" id="dept" class="layui-select" style="width: 100px;height: 20px;">
						<option value ="0"id="tes">不做变更</option>
					</select>
				</div>
			</div>
			
		</form>	
		<script src="../plugins/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="../plugins/jquery3.6.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
	
			
			$.ajax({
			    url:'/dept',
			    dataType:'json',
			    type:'post',
			    success:function(data){
			    
			    	$.each(data,function(i,item){

			    		$('#dept').append("<option value='"+item.id+"'>"+item.name+"</option>");//往下拉菜单里添加元素
			    	})
			
				
				}
			})
		
		</script>
</body>
</html>