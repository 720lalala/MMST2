<%@ page contentType="text/html;charset=GB18030" language="java" pageEncoding="GB18030" %>
<html>
<head>
	<title>MMST</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="css/templatemo_style.css" rel="stylesheet" type="text/css">
	<script language="JavaScript">
        function check() {

            var regml=/^(\w){6,20}$/;
            var regm=/[0-9]+$/;

            if(form.provinces.value==""){
                alert("省份不能为空...");
                form.provinces.focus();
                return false;
            }
            if(form.remarksInformation.value==""){
                alert("备注信息不能为空...");
                form.remarksInformation.focus();
                return false;
            }
            if((form.userid.value.length!=4)||(!form.userid.value.match(regm))){
                alert("用户编号为四个数字...");
                form.userid.focus();
                return false;
            }
            if(form.optionsRadios.value=="")
			{
			    alert("权限选择不能为空")
                return false;
			}
            if(form.password.value==""){
                alert("密码不能为空...");
                form.password.focus();
                return false;
            }

            if(!form.password.value.match(regml))
			{
                alert("密码只能输入6-20个字母、数字、下划线");
                form.password.value="";
                form.password.focus();
                return false;
			}
            if(form.password_confirm.value==""){
                alert("确认密码不能为空...");
                form.password_confirm.focus();
                return false;
            }
            if(form.password_confirm.value!=form.password.value){
                alert("两个密码不一致，请重新输入...");
                form.password_confirm.value="";
                form.password_confirm.focus();
                return false;
            }
        }
	</script>
	<style>
		.radio_type {
			width: 15px;
			height: 15px;
			appearance: none;
			position: relative;
		}

		.radio_type:before {
			content: '';
			width: 15px;
			height: 15px;
			border: 1px solid #7d7d7d;
			display: inline-block;
			border-radius: 50%;
			vertical-align: middle;
		}

		.radio_type:checked:before {
			content: '';
			width: 15px;
			height: 15px;
			border: 1px solid #1b8dbf;
			background: #1b8dbf;
			display: inline-block;
			border-radius: 50%;
			vertical-align: middle;
		}

		.radio_type:checked:after {
			content: '';
			width: 8px;
			height: 5px;
			border: 2px solid white;
			border-top: transparent;
			border-right: transparent;
			text-align: center;
			display: block;
			position: absolute;
			top: 4px;
			left: 4px;
			vertical-align: middle;
			transform: rotate(-45deg);
		}

		.radio_type:checked + label {
			color: #1b8dbf;
		}
	</style>
</head>
<body class="templatemo-bg-gray" style="background:url(images/background1.jpg) no-repeat center;background-size:100% 100%";>
	<h1 class="margin-bottom-15">申请账户</h1>
	<div class="container">
		<div class="col-md-12">			
			<form class="form-horizontal templatemo-create-account templatemo-container" role="form" action="registerServlet" name="form" method="post" id="form">
				<div class="form-inner">
					<div class="form-group">
			          <div class="col-md-6">		          	
			            <label for="first_name" class="control-label" >省份</label>
			            <input type="text" class="form-control" id="first_name" name="provinces" placeholder="">
			          </div>  
			          <div class="col-md-6">		          	
			            <label for="last_name" class="control-label" >备注信息</label>
			            <input type="text" class="form-control" id="last_name" name="remarksInformation" placeholder="地区">
			          </div>             
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">		          	
			            <label for="email" class="control-label">用户编号</label>
			            <input type="text" class="form-control" id="email" name="userid" placeholder="四位数字">
			          </div>              
			        </div>			
			        <div class="form-group col-md-12"  style="text-align: left">
						<label for="optionsRadios1" class="control-label">权限</label>
			          <div class="col-md-12" >
			          	<label class="radio-inline " style="text-align: center">
		          			<input type="radio" class="radio_type" name="optionsRadios" id="optionsRadios1" value="pu"> 普通用户
		          		</label>
		          		<label class="radio-inline">
		          			<input type="radio" class="radio_type" name="optionsRadios" id="optionsRadios2" value="su"> 管理员用户
		          		</label>
			          </div>             
			        </div>
			        <div class="form-group">
			          <div class="col-md-6">
			            <label for="password" class="control-label">密码</label>
			            <input type="password" class="form-control" id="password" name="password" placeholder="">
			          </div>
			          <div class="col-md-6">
			            <label for="password" class="control-label">确认密码</label>
			            <input type="password" class="form-control" id="password_confirm" name="password_confirm" placeholder="">
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			            <input type="submit" value="申请" class="btn btn-info" onclick="return check();">
			            <a href="login.jsp" class="pull-right">Login</a>
			          </div>
			        </div>	
				</div>				    	
		      </form>		      
		</div>
	</div>
	<!-- Modal -->
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>