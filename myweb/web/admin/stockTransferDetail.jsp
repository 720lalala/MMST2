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
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="cn.edu.lingnan.dto.SalesDTO" %>
<%@ page import="cn.edu.lingnan.dto.StockTransferDTO" %>
<%@ page import="cn.edu.lingnan.dto.DepotDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <!-- Required meta tags -->
    <meta charset="GB18030">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MMST</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="../../vendors/iconfonts/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="../../vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="../../css/style.css">
    <!-- endinject -->
    <link rel="shortcut icon" href="../../images/favicon.png"/>
    <style>
        .white_content {
            overflow: scroll;
            height: 300px;
            display: block;
            position: fixed;
            top: 25%;
            left: 35%;
            width: 50%;
            padding: 16px;
            border: 16px solid #f5587b;
            background-color: #FFFDC0;
            z-index: 1002;
            overflow: auto;
        }

        input {
            border: 1px solid #ccc;
            padding: 7px 0px;
            border-radius: 3px; /*css3属性IE不支持*/
            padding-left: 5px;
        }


    </style>
    <script language="JavaScript">
        function dateStart() {
            //月份对应天数
            MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

            //给年下拉框赋内容
            var y = new Date().getFullYear();
            for (var i = y; i > y - 30; i--) //以今年为准，前50年，后50年
                document.date.year.options.add(new Option(" " + i + " 年", i));

            //给月下拉框赋内容
            for (var i = 1; i < 13; i++)
                document.date.month.options.add(new Option(" " + i + " 月", i));

            document.date.year.value = y;
            document.date.month.value = new Date().getMonth() + 1;
            var n = MonHead[new Date().getMonth()];
            if (new Date().getMonth() == 1 && IsPinYear(yearvalue))
                n++;
            writeDay(n); //赋日期下拉框
            document.date.day.value = new Date().getDate();
        }

        if (document.attachEvent)
            window.attachEvent("onload", dateStart);
        else
            window.addEventListener('load', dateStart, false);

        function selectYear(str) //年发生变化时日期发生变化(主要是判断闰平年)
        {
            var monthvalue = document.date.month.options[document.date.month.selectedIndex].value;
            if (monthvalue == "") {
                var e = document.date.day;
                optionsClear(e);
                return;
            }
            var n = MonHead[monthvalue - 1];
            if (monthvalue == 2 && IsPinYear(str))
                n++;
            writeDay(n);
        }

        function selectYear1(str) //年发生变化时日期发生变化(主要是判断闰平年)
        {
            var monthvalue = document.date.month.options[document.date.month.selectedIndex].value;
            if (monthvalue == "") {
                var e = document.date.day;
                optionsClear(e);
                return;
            }
            var n = MonHead[monthvalue - 1];
            if (monthvalue == 2 && IsPinYear(str))
                n++;
            writeDay(n);
        }

        function selectMonth(str)   //月发生变化时日期联动
        {
            var yearvalue = document.date.year.options[document.date.year.selectedIndex].value;
            if (yearvalue == "") {
                var e = document.date.day;
                optionsClear(e);
                return;
            }
            var n = MonHead[str - 1];
            if (str == 2 && IsPinYear(yearvalue))
                n++;
            writeDay(n);
        }

        function writeDay(n)   //据条件写日期的下拉框
        {
            var e = document.date.day;
            optionsClear(e);
            for (var i = 1; i < (n + 1); i++)
                e.options.add(new Option(" " + i + " 日", i));
        }

        function IsPinYear(year)//判断是否闰平年
        {
            return (0 == year % 4 && (year % 100 != 0 || year % 400 == 0));
        }

        function optionsClear(e) {
            e.options.length = 1;
        }

        function optionsClear(e) {
            e.options.length = 1;
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
                                <form action="searchStockTransferDetailServlet"
                                      accept-charset="GB18030"
                                      method="post">

                                    <div class="row">
                                        <div class="col-md-6">
                                            <label class="col-md-4">入库店铺编号</label>
                                            <input type="text" name="in_useridF" id="in_useridF"
                                            >
                                        </div>
                                        <div class="col-md-6">
                                            <label class="col-md-4">出库店铺编号</label>
                                            <input type="text" name="out_useridF" id="out_useridF"
                                            >
                                        </div>
                                    </div>

                                    <br>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label class="col-md-4">单号</label>
                                            <input type="text" name="idF" id="idF"
                                            >
                                        </div>
                                        <div class="col-md-6">

                                            <label class="col-md-4">状态</label>
                                            <select class="selectpicker" id="statusF" name="statusF">
                                                <option value="?"> </option>
                                                <option value="已出库">已出库</option>
                                                <option value="已入库">已入库</option>
                                                <option value="异常">异常</option>
                                            </select>

                                            <%--<div class="col-md-8">--%>
                                                <%--<input style=""--%>
                                                       <%--list="itemlist" class="form-control" type="text" id="statusF"--%>
                                                       <%--name="statusF"/>--%>
                                                <%--<datalist id="itemlist">--%>
                                                    <%--<option>已出库</option>--%>
                                                    <%--<option>已入库</option>--%>
                                                    <%--<option>异常</option>--%>
                                                <%--</datalist>--%>


                                            <%--</div>--%>

                                        </div>
                                    </div>
                                    <br>
                                    <div style="text-align: center">
                                        <button type="submit" class="btn btn-gradient-primary btn-rounded btn-fw">确定</button>
                                    </div>

                                </form>


                                <div style="height:20px; "></div>
                                <table class="table table-bordered" border="5">
                                    <tr class="table-info" style="text-align:center;vertical-align:middle;">
                                        <td>单号</td>
                                        <td>件数</td>
                                        <td>出库时间</td>
                                        <td>出库店铺</td>
                                        <td>出库员工</td>
                                        <td>入库时间</td>
                                        <td>入库店铺</td>
                                        <td>入库员工</td>
                                        <td>状态</td>
                                    </tr>
                                    <%
                                        if (session.getAttribute("authority") != null) {

                                            Vector<StockTransferDTO> v = (Vector<StockTransferDTO>) session.getAttribute("AllStockTransfer");
                                            Iterator<StockTransferDTO> it = v.iterator();
                                            StockTransferDTO u = new StockTransferDTO();
                                            while (it.hasNext()) {
                                                u = it.next();
                                    %>
                                    <tr class="table-danger" style="text-align:center;vertical-align:middle;">
                                        <td>
                                            <a href="detailForStockTransferServlet?id=<%=u.getId()%>"><%=u.getId()%>
                                            </a>
                                        </td>
                                        <td><%=u.getNumbers()%>
                                        </td>
                                        <td><%=u.getOutTime()%>
                                        </td>
                                        <td><%=u.getOutUserid()%>
                                        </td>
                                        <td><%=u.getOutStaffid()%>
                                        </td>
                                        <td><%=u.getInTime()%>
                                        </td>
                                        <td><%=u.getInUserid()%>
                                        </td>
                                        <td><%=u.getInStaffid()%>
                                        </td>
                                        <td><% if (u.getStatus() == 0) {
                                        %>已出库<%
                                        } else if (u.getStatus() == 1) {
                                        %>已入库<%
                                        } else {
                                        %>异常<%
                                            }%>
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
    <%
        Vector<DepotDTO> StockTransferDetail = null;
        try {
            StockTransferDetail = (Vector<DepotDTO>) session.getAttribute("StockTransferDetail");
        } catch (Exception e) {
        }
        if (StockTransferDetail != null) {
    %>
    <div id="light" class="white_content">
        <table class="table table-bordered" style="text-align: center" border="5">
            <tr class="table-info">
                <td>衣服编号</td>
                <td>件数</td>
                <td>状态</td>
            </tr>
            <%
                Iterator<DepotDTO> it = StockTransferDetail.iterator();
                DepotDTO u = new DepotDTO();
                while (it.hasNext()) {
                    u = it.next();
            %>
            <tr class="table-danger">
                <td><%=u.getClothingid()%>
                </td>
                <td><%=u.getNumbers()%>
                </td>
                <td><% if (u.getState() == 0) {
                %>已出库<%
                } else if (u.getState() == 1) {
                %>已入库<%
                } else {
                %>异常<%
                    }%>
                </td>

            </tr>

            <%
                }
            %>
        </table>
        <div class="form-group" style="margin-top:5px; margin-bottom: 5px;" align="center">
            <input type="button" class="btn btn-gradient-dark btn-icon-text btn-sm" onclick="two()" value="取消">
        </div>
    </div>

    <%
        }
    %>
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
<script src="../../js/bootstrap-select.js"></script>
<!-- endinject -->
<!-- Custom js for this page-->
<!-- End custom js for this page-->
</body>
<script language="JavaScript">

    $('#statusF').selectpicker();

    function two() {
        document.getElementById('light').style.display = 'none';
        <%session.removeAttribute("StockTransferDetail");%>

    }
</script>
</html>
