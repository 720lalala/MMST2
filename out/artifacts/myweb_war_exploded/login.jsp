<%@ page contentType="text/html;charset=GB18030" language="java" %>
<%@ taglib prefix="aa" uri="http://lingnan.edu.cn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <title>MMST</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
    <link href="css/templatemo_style.css" rel="stylesheet" type="text/css">
    <script language="JavaScript">
        function check() {
            var regm = /[0-9]+$/;

            if (form.userid.value.value = "") {
                alert("用户编号不能为空...");
                form.userid.focus();
                return false;
            }
            if (form.optionsRadios.value == "") {
                alert("权限选择不能为空")
                return false;
            }
            if (form.password.value == "") {
                alert("密码不能为空...");
                form.password.focus();
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
<body class="templatemo-bg-gray"
      style="background:url(images/background1.jpg) no-repeat center;background-size:100% 100%;">
<div class="container">
    <div class="col-md-12">
        <h1 class="margin-bottom-15">MMST</h1>
        <form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form"
              action="loginServlet" method="get" name="form">
            <div class="form-group">
                <div class="col-xs-12">
                    <div class="control-wrapper">
                        <label for="username" class="control-label fa-label"><i
                                class="fa fa-user fa-medium"></i></label>
                        <input type="text" class="form-control" name="userid" id="username" placeholder="用户编号">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-12">
                    <div class="control-wrapper">
                        <label for="password" class="control-label fa-label"><i
                                class="fa fa-lock fa-medium"></i></label>
                        <input type="password" class="form-control" name="password" id="password" placeholder="密码">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-12">
                    <div class="control-wrapper">
                        <div class="col-md-6">
                            <label class="radio-inline" style="text-align: center">
                                <input class="radio_type" type="radio" name="optionsRadios" id="optionsRadios1"
                                       value="pu"> <aa:puUser/>
                            </label>
                        </div>
                        <div class="col-md-6">
                            <label class="radio-inline">
                                <input class="radio_type" type="radio" name="optionsRadios" id="optionsRadios2"
                                       value="su"><aa:suUser/>
                            </label>
                        </div>


                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-12">

                </div>
            </div>

            <div class="form-group">
                <div class="col-md-12" style="text-align: center">

                        <input type="submit" value="登录" class="btn btn-info" onclick="return check();">

                </div>
            </div>
            <hr>

        </form>
        <div class="text-center">
            <a href="register.jsp" class="templatemo-create-new">申请账户 <i class="fa fa-arrow-circle-o-right"></i></a>
        </div>
    </div>
</div>
</body>
</html>