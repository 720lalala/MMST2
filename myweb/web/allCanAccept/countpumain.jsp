<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/12/21
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>

<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/12/16
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<html lang="en">
<%@ page contentType="text/html;charset=GB18030" language="java" import="cn.edu.lingnan.servlet.*"
         pageEncoding="GB18030" %>
<%@ page import="cn.edu.lingnan.dto.DepotDTO" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="cn.edu.lingnan.dto.DepotDetailsDTO" %>
<%@ page import="cn.edu.lingnan.dto.Count.achievementStaffDTO" %>
<%@ page import="cn.edu.lingnan.dto.Count.salesKingDTO" %>
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
            top: 25%;
            left: 35%;
            width: 35%;
            height: 50%;
            border: 16px solid #bfdef7;
            background-color: #a8b2b9;
            z-index: 1002;
            padding: 0;
        }


    </style>


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
                    <div class="col-lg-6 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="page-title">
                                    业绩
                                </h4>
                                <label style="height: 20px;"></label>
                                <table style="text-align: center">
                                    <tr>
                                        <td style="width: 90px;">
                                            <div class="form-check">
                                                <label class="form-check-label">
                                                    <input type="radio" class="form-check-input" name="optionsRadios"
                                                           id="optionsRadios1" value="0"
                                                           onclick="window.location.href='achievementStaffServlet?value=0'">
                                                    今日
                                                </label>
                                            </div>
                                        </td>
                                        <td style="width: 90px;">
                                            <div class="form-check">
                                                <label class="form-check-label">
                                                    <input type="radio" class="form-check-input" name="optionsRadios"
                                                           id="optionsRadios2" value="1"
                                                           onclick="window.location.href='achievementStaffServlet?value=1'">
                                                    今月
                                                </label>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-check">
                                                <label class="form-check-label">
                                                    <input type="radio" class="form-check-input" name="optionsRadios"
                                                           id="optionsRadios3" value="2"
                                                           onclick="window.location.href='achievementStaffServlet?value=2'">
                                                    今年
                                                </label>
                                            </div>
                                        </td>

                                    </tr>
                                </table>
                                <table class="table table-bordered" style="text-align: center" border="5">
                                    <tr class="table-info">
                                        <td>员工编号</td>
                                        <td>业绩</td>
                                    </tr>
                                    <%
                                        if (session.getAttribute("achievementStaff") != null) {

                                            Vector<achievementStaffDTO> v = (Vector<achievementStaffDTO>) session.getAttribute("achievementStaff");
                                            System.out.println(v.size());
                                            Iterator<achievementStaffDTO> it = v.iterator();
                                            achievementStaffDTO u = new achievementStaffDTO();
                                            while (it.hasNext()) {
                                                u = it.next();
                                    %>
                                    <tr class="table-danger">
                                        <td><%=u.getStaffid()%>
                                        </td>
                                        <td><fmt:formatNumber value="<%=u.getAchievement()%>" type="number"
                                                              maxFractionDigits="2"/></td>
                                    </tr>
                                    <%
                                            }
                                        }
                                    %>
                                </table>


                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="page-title">
                                    营业额
                                </h4>
                                <label style="height: 20px;"></label>
                                <table style="text-align: center">
                                    <tr>
                                        <td style="width: 90px;">
                                            <div class="form-check">
                                                <label class="form-check-label">
                                                    <input type="radio" class="form-check-input" name="optionsRadios1"
                                                           id="optionsRadios11" value="0"
                                                           onclick="window.location.href='achievementPaywayServlet?value=0'">
                                                    今日
                                                </label>
                                            </div>
                                        </td>
                                        <td style="width: 90px;">
                                            <div class="form-check">
                                                <label class="form-check-label">
                                                    <input type="radio" class="form-check-input" name="optionsRadios1"
                                                           id="optionsRadios12" value="1"
                                                           onclick="window.location.href='achievementPaywayServlet?value=1'">
                                                    今月
                                                </label>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-check">
                                                <label class="form-check-label">
                                                    <input type="radio" class="form-check-input" name="optionsRadios1"
                                                           id="optionsRadios13" value="2"
                                                           onclick="window.location.href='achievementPaywayServlet?value=2'">
                                                    今年
                                                </label>
                                            </div>
                                        </td>

                                    </tr>
                                </table>
                                <table class="table table-bordered" style="text-align: center" border="5">
                                    <tr class="table-info">
                                        <td>支付方式</td>
                                        <td>金额</td>
                                    </tr>
                                    <%
                                        if (session.getAttribute("paywayAchievement") != null) {
                                            float[] a = (float[]) session.getAttribute("paywayAchievement");


                                    %>
                                    <tr class="table-danger">
                                        <td>现金</td>
                                        <td><fmt:formatNumber value="<%=a[0]%>" type="number"
                                                              maxFractionDigits="2"/></td>
                                    </tr>
                                    <tr class="table-danger">
                                        <td>微信</td>
                                        <td><fmt:formatNumber value="<%=a[1]%>" type="number"
                                                              maxFractionDigits="2"/></td>
                                    </tr>
                                    <tr class="table-danger">
                                        <td>支付宝</td>
                                        <td><fmt:formatNumber value="<%=a[2]%>" type="number"
                                                              maxFractionDigits="2"/></td>
                                    </tr>
                                    <tr class="table-danger">
                                        <td>银行卡</td>
                                        <td><fmt:formatNumber value="<%=a[3]%>" type="number"
                                                              maxFractionDigits="2"/></td>
                                    </tr>
                                    <tr class="table-info">
                                        <td>总金额</td>
                                        <td><fmt:formatNumber value="<%=a[0]+a[1]+a[2]+a[3]%>" type="number"
                                                              maxFractionDigits="2"/></td>
                                    </tr>

                                    <%
                                        }
                                    %>
                                </table>


                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="page-title">
                                    今日衣服销量
                                </h4>
                                <label style="height: 20px;"></label>

                                <table class="table table-dark" style="text-align: center" border="5">
                                    <tr>
                                        <td>衣服编号</td>
                                        <td>卖出件数</td>
                                    </tr>
                                    <%
                                        if (session.getAttribute("salesKing") != null) {

                                            Vector<salesKingDTO> v = (Vector<salesKingDTO>) session.getAttribute("salesKing");
                                            System.out.println(v.size());
                                            Iterator<salesKingDTO> it = v.iterator();
                                            salesKingDTO u = new salesKingDTO();
                                            while (it.hasNext()) {
                                                u = it.next();
                                    %>
                                    <tr>
                                        <td><%=u.getClothingid()%>
                                        </td>
                                        <td><%=u.getMunber()%>
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
