<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/12/18
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="en">

<%@ page contentType="text/html;charset=GB18030" language="java" import="cn.edu.lingnan.servlet.*"
         pageEncoding="GB18030" %>
<%@ page import="cn.edu.lingnan.dto.ClothingDTO" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <!-- Required meta tags -->
    <meta charset="GB18030">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MMST</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="../../vendors/iconfonts/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="../../vendors/css/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="../../css/style.css">
    <!-- endinject -->
    <link rel="shortcut icon" href="../../images/favicon.png"/>
    <style>
        .white_content {
            display: none;
            position: fixed;
            top: 20%;
            left: 35%;
            width: 35%;
            height: 72%;
            border: 16px solid #f5587b;
            background-color: #FFFDC0;
            z-index: 1002;
            padding: 0;
            overflow: auto;
        }


    </style>
    <script>
        function one(clothingid,property,price,discount) {
            console.log(clothingid);
            document.getElementById("clothingidFF").value = clothingid;
            document.getElementById("propertyFF").value = property;
            document.getElementById("priceFF").value = price;
            document.getElementById("discountFF").value = discount;
            formF.clothingidFF.readOnly = true;
            document.getElementById('light').style.display = 'block';


        }

        function two() {
            document.getElementById('Insertdiv').style.display = 'none';

        }

        function three() {
            document.getElementById("light").style.display = 'none';
        }

        function four() {
            document.getElementById('Insertdiv').style.display = 'block';
        }

        function check() {

            var regml = /^(\w){6,20}$/;
            var regm = /[0-9]+$/;


            if (formF.propertyFF.value == "") {
                alert("属性还没有选择...");
                formF.propertyFF.focus();
                return false;
            }
            if (formF.priceFF.value == "") {
                alert("价格为空...");
                formF.priceFF.focus();
                return false;
            }
            if(!formF.priceFF.value.match(regm) && !formF.priceFF.value.match(regml)){
                alert("价格栏输入非法字符");
                formF.priceFF.focus();
                return false;
            }
            if (formF.priceFF.value.length > 6) {
                alert("价格不能大于10000");
                formF.priceFF.focus();
                return false;
            }
            if (formF.discountFF.value == "") {
                alert("折扣为空...");
                formF.discountFF.focus();
                return false;
            }
            if (parseFloat(formF.discountFF.value) < 0 || parseFloat(formF.discountFF.value) > 1 || (!formF.discountFF.value.match(regm))) {
                alert("折扣设置错误...");
                formF.discountFF.focus();
                return false;
            }
            return true;


        }

        function check2() {

            var regml = /^(\w){6,20}$/;
            var regm = /[0-9]+$/;

            if (formInsert.startF.value == "") {
                alert("生产日期为空...");
                formInsert.startF.focus();
                return false;
            }
            if ((formInsert.startF.value.length != 6) || (!formInsert.startF.value.match(regm))) {
                alert("生产日期为六位数字...");
                formInsert.startF.focus();
                return false;
            }
            if (formInsert.bianhaoF.value == "") {
                alert("编号为空...");
                formInsert.bianhaoF.focus();
                return false;
            }
            if ((formInsert.bianhaoF.value.length != 4) || (!formInsert.bianhaoF.value.match(regm))) {
                alert("编号为四位数字...");
                formInsert.bianhaoF.focus();
                return false;
            }
            if (formInsert.propertyF.value == "") {
                alert("属性还没有选择...");
                formInsert.colorF.focus();
                return false;
            }
            if (formInsert.colorF.value == "") {
                alert("颜色还没有选择...");
                formInsert.colorF.focus();
                return false;
            }
            if (formInsert.sizeF.value == "") {
                alert("尺寸还没有选择...");
                formInsert.sizeF.focus();
                return false;
            }
            if (formInsert.priceF.value == "") {
                alert("价格为空...");
                formInsert.priceF.focus();
                return false;
            }
            if ((formInsert.priceF.value.length > 4) || (!formInsert.priceF.value.match(regm))) {
                alert("价格不能大于10000");
                formInsert.priceF.focus();
                return false;
            }
            if (formInsert.discountF.value == "") {
                alert("折扣为空...");
                formInsert.discountF.focus();
                return false;
            }
            if (parseFloat(formInsert.discountF.value) < 0 || parseFloat(formInsert.discountF.value) > 1 || (!formInsert.discountF.value.match(regm))) {
                alert("折扣设置错误...");
                formInsert.discountF.focus();
                return false;
            }


        }

        function checkSearch() {
            if (formS.SearchTemp.value == null || formS.SearchTemp.value == "") {
                alert("请输入有效字符");
                formS.SearchTemp.focus();
                return false;
            }

        }
    </script>
</head>

<body>
<div class="container-scroller">
    <!-- partial:../../partials/_navbar.html -->
    <nav class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
        <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
            <a class="navbar-brand brand-logo"><img src="../images/MMST.png" alt="logo"/></a>
        </div>
        <div class="navbar-menu-wrapper d-flex align-items-stretch">

            <ul class="navbar-nav navbar-nav-right">
                <li class="nav-item nav-profile dropdown">
                    <a class="nav-link dropdown-toggle" id="profileDropdown" href="#" data-toggle="dropdown"
                       aria-expanded="false">
                        <div class="nav-profile-img">
                            <img src="../../images/faces/face1.jpg" alt="image">
                            <span class="availability-status online"></span>
                        </div>
                        <div class="nav-profile-text">
                            <p class="mb-1 text-black"><%=session.getAttribute("userid").toString()%>
                            </p>
                        </div>
                    </a>
                    <div class="dropdown-menu navbar-dropdown" aria-labelledby="profileDropdown">
                        <a class="dropdown-item" href="../allCanAccept/changeIseridMessage">
                            <i class="mdi mdi-cached mr-2 text-success"></i>
                            修改信息
                        </a>

                    </div>
                </li>
                <li class="nav-item d-none d-lg-block full-screen-link">
                    <a class="nav-link">
                        <i class="mdi mdi-fullscreen" id="fullscreen-button"></i>
                    </a>
                </li>
                <li class="nav-item nav-logout d-none d-lg-block">
                    <a class="nav-link" href="../exitServlet">
                        <i class="mdi mdi-power"></i>
                    </a>
                </li>
                <li class="nav-item nav-settings d-none d-lg-block">
                    <a class="nav-link" href="#">
                        <i class="mdi mdi-format-line-spacing"></i>
                    </a>
                </li>
            </ul>
            <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button"
                    data-toggle="offcanvas">
                <span class="mdi mdi-menu"></span>
            </button>
        </div>
    </nav>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
        <!-- partial:../../partials/_sidebar.html -->
        <nav class="sidebar sidebar-offcanvas" id="sidebar">
            <ul class="nav">
                <li class="nav-item nav-profile">
                    <a href="#" class="nav-link">
                        <div class="nav-profile-image">
                            <img src="../../images/faces/face1.jpg" alt="profile">
                            <span class="login-status online"></span> <!--change to offline or busy as needed-->
                        </div>
                        <div class="nav-profile-text d-flex flex-column">
                            <!--userid-->
                            <span class="font-weight-bold mb-2"><%=session.getAttribute("userid").toString()%></span>
                            <!--权限-->
                            <span class="text-secondary text-small">
                <%
                    boolean status = true;
                    if (session.getAttribute("authority").toString().equals("su")) {
                %>
                <c:out value="管理员"></c:out>
                <%
                } else {
                    status = false;
                %>
                <c:out value="普通用户"></c:out>
                <%
                    }
                %>
              </span>
                        </div>
                        <i class="mdi mdi-bookmark-check text-success nav-profile-badge"></i>
                    </a>
                </li>
                    <%
                    if(status){
                %>
                <li class="nav-item">
                    <a class="nav-link" href="../admin/AllUserServlet">
                        <span class="menu-title">用户管理</span>
                        <i class="mdi mdi-home menu-icon"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../admin/ALLApplyuserServlet">
                        <span class="menu-title">申请管理</span>
                        <i class="mdi mdi-crosshairs-gps menu-icon"></i>
                    </a>
                </li>
                    <%
                    }
                %>
                <li class="nav-item">
                    <a class="nav-link" href="../allCanAccept/allStaffServlet">
                        <span class="menu-title">员工管理</span>
                        <i class="mdi mdi-account-multiple menu-icon"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../allCanAccept/aUservipServlet">
                        <span class="menu-title">vip管理</span>
                        <i class="mdi mdi-account-circle menu-icon"></i>
                    </a>
                </li>
                    <%
                    if(status){
                %>
                <li class="nav-item">
                    <a class="nav-link" href="../admin/AllClothiingServlet">
                        <span class="menu-title">衣服管理</span>
                        <i class="mdi mdi-hanger menu-icon"></i>
                    </a>
                </li>
                    <%
                    }
                %>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false"
                       aria-controls="ui-basic">
                        <span class="menu-title">仓库</span>
                        <i class="menu-arrow"></i>
                        <i class="mdi mdi-tshirt-crew menu-icon"></i>
                    </a>
                    <div class="collapse" id="ui-basic">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item"><a class="nav-link " href="../allCanAccept/AllDepotServlet">仓库管理</a>
                            </li>
                            <li class="nav-item"><a class="nav-link" href="../allCanAccept/countDepotServlet">盘点</a>
                            </li>
                            <li class="nav-item"><a class="nav-link"
                                                    href="../allCanAccept/stockTransferServlet">出库和入库</a>
                            </li>
                            <%
                                if (status) {
                            %>
                            <li class="nav-item"><a class="nav-link"
                                                    href="../admin/stockTransferDetailServlet">仓库转储信息</a>
                            </li>
                            <%
                                }
                            %>
                        </ul>
                    </div>
                </li>
                    <%
                    if(status){
                %>
                <li class="nav-item">
                    <a class="nav-link" href="../admin/AllFlowsheetServlet">
                        <span class="menu-title">流水单查看</span>
                        <i class="mdi mdi-table-large menu-icon"></i>
                    </a>
                </li>
                    <%
                    }
                %>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="collapse" href="#ui-basic3" aria-expanded="false"
                       aria-controls="ui-basic">
                        <span class="menu-title">销售管理</span>
                        <i class="menu-arrow"></i>
                        <i class="mdi mdi-chart-bar menu-icon"></i>
                    </a>
                    <div class="collapse" id="ui-basic3">
                        <ul class="nav flex-column sub-menu">
                            <%
                                if (status) {
                            %>
                            <li class="nav-item"><a class="nav-link " href="../admin/allSalesServlet">销售</a></li>

                            <%
                            } else {
                            %>
                            <li class="nav-item"><a class="nav-link " href="../allCanAccept/salesServlet">销售</a></li>
                            <%
                                }
                            %>
                            <li class="nav-item"><a class="nav-link " href="../allCanAccept/returnServlet">退换货</a></li>


                        </ul>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../allCanAccept/checkAllSalesServlet">
                        <span class="menu-title">营业统计</span>
                        <i class="mdi mdi-chart-bar menu-icon"></i>
                    </a>
                </li>

                    <%
                    if(status){
                %>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="collapse" href="#ui-basic2" aria-expanded="false"
                       aria-controls="ui-basic">
                        <span class="menu-title">数据统计</span>
                        <i class="menu-arrow"></i>
                        <i class="mdi mdi-chart-bar menu-icon"></i>
                    </a>
                    <div class="collapse" id="ui-basic2">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item"><a class="nav-link " href="../admin/countsuServlet">销售简况</a></li>
                            <li class="nav-item"><a class="nav-link " href="../admin/clothingSalesServlet">衣服销售情况</a>
                            </li>
                            <li class="nav-item"><a class="nav-link "
                                                    href="../admin/adminAchievementUserServlet">各店业绩统计</a></li>
                            <li class="nav-item"><a class="nav-link"
                                                    href="../admin/adminAchievementStaffServlet">店员业绩统计</a></li>
                        </ul>
                    </div>
                </li>
                    <%
                    }else{
                %>
                <li class="nav-item">
                    <a class="nav-link" href="countpuServlet">
                        <span class="menu-title">数据统计</span>
                        <i class="mdi mdi-chart-bar menu-icon"></i>
                    </a>
                </li>
                    <%
                    }
                %>
        </nav>
        </nav>
        <!-- partial -->
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="page-header">
                    <h3 class="page-title">
                        衣服管理
                    </h3>
                </div>
                <div class="row">
                    <div class="col-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <form class="form-inline" accept-charset="GB18030" name="formS"
                                      action="searchClothingServlet"
                                      method="post">
                                    <div class="form-group col-4">
                                        <div class="input-group">
                                            <input type="text" id="SearchTemp" name="SearchTemp" class="form-control"
                                                   placeholder="衣服编号或属性或颜色" aria-label=""
                                                   aria-describedby="basic-addon2">
                                            <div class="input-group-append">
                                                <button type="submit" class="btn btn-sm btn-gradient-primary"
                                                        type="button" onclick="return checkSearch();">寻找
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group col-4">
                                        <button type="button" class="btn btn-outline-primary btn-md"
                                                onclick="window.location.href='AllClothiingServlet'">全部寻找
                                        </button>
                                    </div>
                                    <div class="form-group col-4">
                                        <div style="float:right">
                                            <input type="button" onclick="four()"
                                                   class="btn btn-gradient-primary btn-md"
                                                   value="添加衣服"/>
                                        </div>
                                    </div>

                                </form>
                                <div style="height:20px; "></div>
                                <table class="table table-bordered" style="text-align: center" border="5">
                                    <tr class="table-info">
                                        <td>衣服编号</td>
                                        <td>属性</td>
                                        <td>颜色</td>
                                        <td>码数</td>
                                        <td>价钱</td>
                                        <td>折扣</td>
                                        <td>操作</td>
                                    </tr>
                                    <%
                                        if (session.getAttribute("authority") != null) {

                                            Vector<ClothingDTO> v = (Vector<ClothingDTO>) session.getAttribute("AllClothing");
                                            System.out.println(v.size());
                                            Iterator<ClothingDTO> it = v.iterator();
                                            ClothingDTO u = new ClothingDTO();
                                            while (it.hasNext()) {
                                                u = it.next();
                                    %>
                                    <tr class="table-danger">
                                        <td><%=u.getClothingid()%>
                                        </td>
                                        <td><%=u.getProperty()%>
                                        </td>
                                        <td><%=u.getColor()%>
                                        </td>
                                        <td><%=u.getSize()%>
                                        </td>
                                        <td><%=u.getPrice()%>
                                        </td>
                                        <td><%=u.getDiscount()%>
                                        </td>
                                        <td style="width: 204px;">
                                            <input type="button" class="btn btn-outline-primary btn-sm"
                                                   onclick='one("<%=u.getClothingid()%>","<%=u.getProperty()%>","<%=u.getPrice()%>","<%=u.getDiscount()%>") ' value="修改">
                                            <a href="javascript:void(0)"></a>
                                            </input>
                                            <label style="width: 10px;"></label>
                                            <button class="btn btn-outline-dark  btn-sm"
                                                    onclick='window.location.href="deleteClothingServlet?clothingid=<%=u.getClothingid()%>" '>
                                                删除
                                            </button>


                                        </td>
                                    </tr>
                                    <%
                                            }
                                        }
                                    %>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- content-wrapper ends -->
            <!-- partial:../../partials/_footer.html -->
            <footer class="footer">
                <div class="d-sm-flex justify-content-center justify-content-sm-between">
          <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">
            Copyright © 2018 <a href="#" target="_blank">Bootstrap Dash</a> MMST </span>

                </div>
            </footer>
            <!-- partial -->
        </div>

        <!-- main-panel ends -->
    </div>


    <div id="Insertdiv" class="white_content">
        <div class="card-body">
            <form class="forms-sample" accept-charset="GB18030" name="formInsert" action="insertClothingServlet"
                  method="post">
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="startF">生产日期</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="label" id="startF"
                           name="startF"/>
                </div>
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="bianhaoF">编号</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="label" id="bianhaoF"
                           name="bianhaoF"/>
                </div>
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="propertyF">属性</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" list="itemlist4" type="label"
                           id="propertyF" name="propertyF"/>
                    <datalist id="itemlist4">
                        <option>外套</option>
                        <option>上衣</option>
                        <option>裤子</option>
                        <option>连衣裙</option>
                        <option>打底衣</option>
                        <option>打底裤</option>
                        <option>睡衣</option>
                        <option>袜子</option>
                        <option>内衣裤</option>
                    </datalist>
                </div>
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="colorF">颜色</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" list="itemlist"
                           type="label" id="colorF" name="colorF"/>
                    <datalist id="itemlist">
                        <option>011:红色</option>
                        <option>012:橙色</option>
                        <option>013:灰色</option>
                        <option>014:花色</option>
                        <option>015:绿色</option>
                        <option>016:蓝色</option>
                        <option>017:黄色</option>
                        <option>999:其他</option>
                    </datalist>
                </div>
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="sizeFF">尺寸</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" list="sizeFF" type="label"
                           id="sizeF" name="sizeF"/>
                    <datalist id="sizeFF">
                        <option>01:XS</option>
                        <option>02:S</option>
                        <option>03:M</option>
                        <option>04:L</option>
                        <option>05:XL</option>
                        <option>06:XXL</option>
                        <option>09</option>
                        <option>10</option>
                        <option>11</option>
                        <option>12</option>
                        <option>13</option>
                        <option>14</option>
                        <option>15</option>
                        <option>16</option>
                        <option>17</option>
                    </datalist>
                </div>
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="priceF">价格</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="label" id="priceF"
                           name="priceF" />
                </div>
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="discountF">折扣</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="label"
                           id="discountF" name="discountF" value="1.0"/>
                </div>
                <div style="height: 10px;"></div>
                <div class="form-group" style="margin-top:5px; margin-bottom: 5px;" align="center">
                    <input type="submit" class="btn btn-gradient-primary btn-icon-text btn-sm"
                           onclick="return check2();" value="确定">
                    <label style="width: 30px;"></label>
                    <input type="button" class="btn btn-gradient-dark btn-icon-text btn-sm" onclick="two()" value="取消">
                </div>

            </form>
        </div>
    </div>

    <div id="light" class="white_content" style="height:50%;">
        <div class="card-body">
            <form class="forms-sample" accept-charset="GB18030" name="formF" action="modifyClothingServlet"
                  method="post">
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="clothingidFF">衣服编号</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="label"
                           id="clothingidFF" name="clothingidFF"/>
                </div>
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="propertyFF">属性</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="label"
                           id="propertyFF" name="propertyFF"/>
                </div>
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="priceFF">价格</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="label" id="priceFF"
                           name="priceFF"/>
                </div>
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="discountFF">折扣</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="label"
                           id="discountFF" name="discountFF"/>
                </div>
                <div style="height: 10px;"></div>
                <div class="form-group" style="margin-top:5px; margin-bottom: 5px;" align="center">
                    <input type="submit" class="btn btn-gradient-primary btn-icon-text btn-sm" onclick="return check();"
                           value="确定">
                    <label style="width: 30px;"></label>
                    <input type="button" class="btn btn-gradient-dark btn-icon-text btn-sm" onclick="three()"
                           value="取消">
                </div>

            </form>
        </div>
    </div>

    <!-- page-body-wrapper ends -->
</div>
<!-- container-scroller -->
<!-- plugins:js -->
<script src="../../vendors/js/vendor.bundle.base.js"></script>
<script src="../../vendors/js/vendor.bundle.addons.js"></script>
<!-- endinject -->
<!-- Plugin js for this page-->
<!-- End plugin js for this page-->
<!-- inject:js -->
<script src="../../js/off-canvas.js"></script>
<script src="../../js/misc.js"></script>
<!-- endinject -->
<!-- Custom js for this page-->
<!-- End custom js for this page-->
</body>

</html>
