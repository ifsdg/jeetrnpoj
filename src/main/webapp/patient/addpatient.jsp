

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="./plugins/layui/css/layui.css"/>
		<title></title>
	</head>
	<body>
		
		<div style="width: -webkit-fit-content; margin: auto;" class="layui-fluid layui-form">


			<h1 align="center">用户注册</h1>

			<form action="User.do"  method="post" class="layui-form layui-form-pane" enctype="multipart/form-data" lay-filter="#formtes">
				<div class="layui-form-item">
					<label class="layui-form-label">用户名：</label>
					<div class="layui-input-block ">
						<input type="text" class="layui-input" lay-verify="required|username" name="username"
							autocomplete="off" required="required" placeholder="请输入用户名"/>*
					</div>

				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">密码：</label>
					<div class="layui-input-block">
						<input type="password" name="password" id="pass" lay-verify="required|pass" class="layui-input"
							required="required" autocomplete="off" placeholder="请输入密码"/>*
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">确认密码：</label>
					<div class="layui-input-block">
						<input type="password" name="cpassword" id="cpass" lay-verify="required|pass" class="layui-input"
							required="required" autocomplete="off" placeholder="请确认密码" />*
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">邮箱：</label>
					<div class="layui-input-block">
						<input type="text" name="mail" required lay-verify="email" class="layui-input" autocomplete="off"
							  placeholder="请输入邮箱"/>
					</div>

				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">生日：</label>
					<div class="layui-input-block">
						<input type="date" name="birthday" lay-verify="required|date" class="layui-input" autocomplete="off"
							required="required" />
					</div>

				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">性别：</label>
					<div class="layui-input-block">
						<input type="radio" name="gender" value="M" title="男" autocomplete="off" checked="checked" />
						<input type="radio" name="gender" value="F" title="女" autocomplete="off" />
					</div>

				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">爱好：</label>
					<div class="layui-input-block">
						<input type="checkbox" name="hobby" value="" title="跑步:" autocomplete="off" />
						<input type="checkbox" name="hobby" value="" title="音乐:" autocomplete="off" />
						<input type="checkbox" name="hobby" value="" title="读书:" autocomplete="off" />
						<input type="checkbox" name="hobby" value="" title="游泳:" autocomplete="off" />
					</div>

				</div>
			<!--	<div class="layui-form-item">
					<label class="layui-form-label">年龄：</label>
					<div class="layui-input-block">
						<input type="text" name="age" class="layui-input" />
					</div>

				</div>-->
			<div class="layui-form-item" id="bioarea-wrap">
			    <label class="layui-form-label">所属地域</label>
			   
			    <div class="layui-input-inline">
			        <select style="width: 100px;" class="level-1-select" lay-filter="#bioarea-wrap1level"
			            name="province" lay-search>
			            <option value="none" selected="selected" >请选择省</option>
			        </select>
			    </div>
			 
			    <div class="layui-input-inline">
			        <select class="level-2-select" lay-filter="#bioarea-wrap2level" name="city">
			            <option value="none" selected="selected">请选择市</option>
			        </select>
			    </div>
			   
			    <div class="layui-input-inline">
			        <select class="level-3-select" lay-filter="#bioarea-wrap3level" name="county">
			            <option value="none" selected="selected">请选择区</option>
			        </select>
			    </div>
			</div>
			<div class="layui-form-item">
					<label class="layui-form-label">用户角色：</label>
					<div class="layui-input-block">
						<select id="role">
						<c:forEach items="${roles }" var="role">
						<c:if test="${role.id==3 }">
						<option value="${role.id }" selected="selected" >${role.name }</option>
						</c:if>
						<c:if test="${!(role.id eq '3') }">
						<option value="${role.id }"  >${role.name }</option>
						</c:if>
						</c:forEach>
						</select>
					</div>

				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">头像：</label>
					<div class="layui-input-block">
						<input type="file" accept="image/*" name="icon" class="layui-input layui-upload-choose" />
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
	<script src="./js/city.js" type="application/javascript" ></script>
		<script type="application/javascript" src="./plugins/layui/layui.js"></script>
	  <script>
	      // 组件路径
	        layui.config({
	            base: './js/'
	        }).extend({
	            bioarea: 'bioarea'
	        });
	    </script>
		<script type="text/javascript">
			layui.use(['form', 'bioarea'], function() {
			var form = layui.form
			    , bioarea = layui.bioarea;
			form.verify({
				username: function(value, item) { //value：表单的值、item：表单的DOM对象
					if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
						return '用户名不能有特殊字符';
					}
					if (/(^\_)|(\__)|(\_+$)/.test(value)) {
						return '用户名首尾不能出现下划线\'_\'';
					}
					if (/^\d+\d+\d$/.test(value)) {
						return '用户名不能全为数字';
					}
				}  ,pass: [
							/^[\S]{6,12}$/
							,'密码必须6到12位，且不能出现空格'
						]							
			});
			
			bioarea.render({
			    elem: '#bioarea-wrap',
			    defaultData: {
			        provinceCode: '110000',
			        cityCode: '110100',
			        countyCode: '110113'
			    },
			    form
			});
			//表单取值
			layui.$('#LAY-component-form-getval').on('click', function() {
			    var data = form.val('example');
			    console.log(data);
			    alert(JSON.stringify(data));
			});
				
			var $=layui.jquery;
		
				
				
				
				});
								
				
			
	
		</script>
	</body>
</html>
