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
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="cn.edu.lingnan.dto.DepotDetailsDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <%float sumprice = 0;%>
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
            height: 70%;
            border: 16px solid #f5587b;
            background-color: #FFFDC0;
            z-index: 1002;
            padding: 0;
        }

        input {
            border: 1px solid #ccc;
            padding: 7px 0px;
            border-radius: 3px; /*css3属性IE不支持*/
            padding-left: 5px;
        }
    </style>
    <script>
        function checkstaffidF() {

            if (staffInsertForm.staffidI.value == null || staffInsertForm.staffidI.value == "") {

                alert("所输入的员工id为空！");
                staffInsertForm.staffidI.focus();
                return false;
            }
        }

        function checkclothingidF() {

            if (clothingInsertForm.clothingidF.value == null || clothingInsertForm.clothingidF.value == "") {

                alert("所输入的衣服id为空！");
                clothingInsertForm.clothingidF.focus();
                return false;
            }

        }

        function checkvip() {
            if (formVip.searchVip.value == null || formVip.searchVip.value == "") {
                console.log("111");
                alert("所输入的vip号码为空！");
                formVip.searchVip.focus();
                return false;
            }

        }

        function one() {

            var FutureFlowid = "<%=session.getAttribute("FutureFlowid")%>";
            var sumprice = document.getElementById('sumprice').value;
            if (sumprice == "0.0" || sumprice == "0.00"|| sumprice == "0") {
                alert("没有输入数据！");
                document.getElementById('light').style.display = 'none';
                return false;

            }
            document.getElementById("flowidF").value = FutureFlowid;
            document.getElementById("sumPriceF").value = sumprice;
            document.getElementById('light').style.display = 'block';

        }

        function useScoreOnclick() {
            document.getElementById('onclickScore').style.display = 'none';
            document.getElementById('useScoreDiv').style.display = 'block';

        }

        function scoreDivNone() {
            document.getElementById("scoreF").value = 0;
            document.getElementById('onclickScore').style.display = 'block';
            document.getElementById('useScoreDiv').style.display = 'none';
        }

        function two() {
            document.getElementById('light').style.display = 'none';
        }

        function check() {
            if (form1.zhifuF.value == "") {
                alert("支付方式为空...");
                form1.zhifuF.focus();
                return false;
            }
            return true;
        }

        function staffidChange(clothingid, staffidF) {
            window.location.href = "../allCanAccept/changeSalesStaffidServlet?staffid=" + staffidF.toString() + "&&clothingid=" + clothingid;

        }

        var sumprice1 = 0;

        function scoreFChange(scoreF) {
            var regm = /[0-9]+$/;
            if (!scoreF.match(regm)) {
                alert("请勿输入无效字符");
                return false;
            }
            <% int vipScore = 0 ;
            try{
                vipScore = Integer.valueOf(session.getAttribute("vipScore").toString() );
            }catch (Exception e){}
            %>
            var vipscore =<%=vipScore%>;
            if (vipscore < scoreF || scoreF < 0) {
                alert("扣除积分多于原有积分或扣除积分少于0!");
                return false;
            }
            if (sumprice1 == 0) {
                sumprice1 = document.getElementById("sumPriceF").value;
            }
            document.getElementById("sumPriceF").value = sumprice1 - scoreF;

        }

        function checkSalesUserid() {
            var regml = /^(\w){6,20}$/;
            var regm = /[0-9]+$/;
            if (salesuseridForm.salesUserid.value == "") {
                alert("所属店铺编号不能为空...");
                salesuseridForm.salesUserid.focus();
                return false;
            }
            if ((salesuseridForm.salesUserid.value.length != 4) || (!salesuseridForm.salesUserid.value.match(regm))) {
                alert("所属店铺编号为四个数字...");
                salesuseridForm.salesUserid.focus();
                return false;
            }
            return true;
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
                <div class="row">
                    <div class="col-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <%
                                    String FutureFlowid = session.getAttribute("FutureFlowid").toString();
                                    String vipPhone = null;
                                    try {
                                        vipPhone = session.getAttribute("vipPhone").toString();
                                    } catch (Exception e) {
                                    }

                                %>
                                <div class="row">
                                    <div class="col-md-7">
                                        <label class="col-md-3">流水单号</label>
                                        <label><%=FutureFlowid%>
                                        </label>
                                    </div>
                                    <div class="col-md-5">
                                        <form accept-charset="GB18030" name="formVip" action="searchVipByPhoneServlet"
                                              method="get">
                                            <label class="col-md-4">vip</label>
                                            <input class="col-md-5" type="text" id="searchVip" name="searchVip"
                                                <% if(vipPhone != null && vipPhone !="") { %>
                                                   value="<%=vipPhone%>"
                                                <%}%>>
                                            <input type="submit" onclick="return checkvip();"
                                                   class="btn btn-gradient-success btn-sm" value="确定"/>
                                        </form>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-7">
                                        <form name="clothingInsertForm" action="../allCanAccept/SalesOneServlet">
                                            <label class="col-md-3">衣服编号</label>
                                            <input  class="col-md-5" type="text" id="clothingidF" name="clothingidF">
                                            <button type="submit" class="btn btn-outline-success btn-sm"
                                                    onclick="return checkclothingidF();">确定
                                            </button>
                                        </form>
                                    </div>
                                    <div class="col-md-5">
                                        <form name="staffInsertForm" action="../allCanAccept/insertAllSalesServlet">
                                            <label class="col-md-4" >员工编号</label>
                                            <input class="col-md-5" type="text" name="staffidI" id="staffidI">
                                            <button type="submit" class="btn btn-outline-success btn-sm"
                                                    onclick="return checkstaffidF();">确定
                                            </button>

                                        </form>
                                    </div>
                                </div>
                                </br>
                                <div style="float: right">
                                    <button type="button" class="btn btn-gradient-dark btn-fw"
                                            onclick="return one();">提交订单
                                    </button>
                                    <div style="height:20px; "></div>
                                </div>

                                <div style="height:20px; "></div>
                                <table class="table table-bordered" border="5">
                                    <tr class="table-info">
                                        <td>编号</td>
                                        <td>属性</td>
                                        <td>颜色</td>
                                        <td>尺寸</td>
                                        <td>单价</td>
                                        <td>折扣</td>
                                        <td>件数</td>
                                        <td>价格</td>
                                        <td>员工</td>
                                        <td>操作</td>
                                    </tr>
                                    <%
                                        if (session.getAttribute("authority") != null) {
                                            if ((Vector<DepotDetailsDTO>) session.getAttribute("salesDetails") != null) {
                                                Vector<DepotDetailsDTO> v = (Vector<DepotDetailsDTO>) session.getAttribute("salesDetails");
                                                System.out.println(v.size());
                                                Iterator<DepotDetailsDTO> it = v.iterator();
                                                DepotDetailsDTO u = new DepotDetailsDTO();
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
                                        <td>
                                            <a href="../allCanAccept/addOrReduceSalesNumberServlet?numbers=<%=u.getNumbers()%>&&clothingid=<%=u.getClothingid()%>&&action=-">
                                                <i class="mdi mdi-minus"></i>
                                            </a>
                                            <%=u.getNumbers()%>
                                            <a href="../allCanAccept/addOrReduceSalesNumberServlet?numbers=<%=u.getNumbers()%>&&clothingid=<%=u.getClothingid()%>&&action=+">
                                                <i class="mdi mdi-plus"></i>
                                            </a>
                                        </td>
                                        <td><fmt:formatNumber
                                                value="<%=(u.getDiscount()*1.0)*u.getNumbers()*(u.getPrice()*1.0)%>"
                                                type="number" maxFractionDigits="2" groupingUsed="false"/></td>
                                        <%
                                            sumprice += (u.getDiscount() * 1.0) * u.getNumbers() * (u.getPrice() * 1.0); %>
                                        <td><input style="width:100px;" type="text" name="staffidF" id="staffidF"
                                                   value="<%=u.getStaffid()%> "
                                                   onchange='staffidChange("<%=u.getClothingid()%>",this.value)'></td>
                                        <td style="width: 150px;">
                                            <button type="button" class="btn btn-outline-dark  btn-sm">
                                                <a href="../allCanAccept/deleteSalesServlet?clothingid=<%=u.getClothingid()%>">删除</a>
                                            </button>
                                        </td>
                                    </tr>
                                    <%

                                                }
                                            }
                                        }
                                    %>
                                    <tr class="table-info">
                                        <td>总价格</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><input type="hidden"
                                                   value="<fmt:formatNumber value="<%=sumprice%>" groupingUsed="false" type="number" maxFractionDigits="2"/>"
                                                   id="sumprice"/><fmt:formatNumber  value="<%=sumprice%>" type="number"
                                                                                    maxFractionDigits="2" groupingUsed="false"/></td>

                                    </tr>
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


    <div id="light" class="white_content" style="height:50%;">
        <div class="card-body">
            <form class="forms-sample" accept-charset="GB18030" name="form1" action="sumbitSalesServlet" method="post">
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="flowidF">流水单号</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="text" id="flowidF"
                           name="flowidF" readonly="readonly"/>
                </div>
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="sumPriceF">总价格</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="text" id="sumPriceF"
                           name="sumPriceF" readonly="readonly"/>

                </div>
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="zhifuF">支付方式</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" list="itemlist" class="form-control"
                           type="text" id="zhifuF" name="zhifuF"/>
                    <datalist id="itemlist">
                        <option>现金</option>
                        <option>支付宝</option>
                        <option>微信</option>
                        <option>银行卡</option>
                    </datalist>
                </div>
                <div style="height: 10px;"></div>
                <% if (vipPhone != null) {%>
                <div class="form-group" style="margin-top:5px; margin-bottom: 5px;" align="center">
                    <input id="onclickScore" name="onclickScore" type="button"
                           class="btn btn-gradient-dark btn-icon-text btn-sm" onclick="useScoreOnclick()" value="使用积分">
                    <div id="useScoreDiv" style="display: none;">
                        <input type="text" id="scoreF" name="scoreF" value="0"
                               onchange="return scoreFChange(this.value);">
                        <input type="button" class="btn btn-outline-danger btn-icon-text btn-sm"
                               onclick="return scoreDivNone();" value="取消">
                    </div>
                </div>

                <%} %>

                <div class="form-group" style="margin-top:5px; margin-bottom: 5px;" align="center">
                    <input type="submit" class="btn btn-gradient-primary btn-icon-text btn-sm" onclick="return check();"
                           value="确定">
                    <label style="width: 30px;"></label>
                    <input type="button" class="btn btn-gradient-dark btn-icon-text btn-sm" onclick="two()" value="取消">
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

