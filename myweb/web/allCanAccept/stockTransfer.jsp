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
    <%int sum = 0;%>
    <style>
        .white_content {
            display: none;
            position: fixed;
            top: 25%;
            left: 35%;
            width: 35%;
            height: 45%;
            border: 16px solid #f5587b;
            background-color: #FFFDC0;
            z-index: 1002;
            padding: 0;
        }

        .white_content2 {
            display: none;
            position: fixed;
            top: 35%;
            left: 35%;
            width: 35%;
            height: 30%;
            border: 16px solid #f5587b;
            background-color: #FFFDC0;
            z-index: 1002;
            padding: 0;
        }


    </style>
    <script>
        function stockOutClick() {
            document.getElementById('outDIV').style.display = 'block';
            document.getElementById('inDIV').style.display = 'none';
            document.getElementById('idDIV').style.display = 'none';

        }

        function stockInClick() {
            document.getElementById('idDIV').style.display = 'block';
            document.getElementById('outDIV').style.display = 'none';
            document.getElementById('inDIV').style.display = 'block';
        }

        function endStockOut() {
            var sum = document.getElementById('sum').value;
            if (sum == 0) {
                alert("û�г������ݣ�");
                return 0;
            } else {
                document.getElementById("numbersF").value = sum;
                document.getElementById('light').style.display = 'block';
            }


        }

        function endStockIn() {
            document.getElementById('light2').style.display = 'block';

        }

        function two() {
            document.getElementById('light').style.display = 'none';
            document.getElementById('light2').style.display = 'none';

        }

        function check() {
            if (form1.inUserF.value == "") {
                alert("�������̱�Ų���Ϊ��...");
                form1.inUserF.focus();
                return false;
            }
            if (form1.staffidF.value == "") {
                alert("Ա��id����Ϊ��...");
                form1.staffidF.focus();
                return false;
            }

            return true;
        }

        function check2() {

            var regm = /[0-9]+$/;
            if (form2.staffidI.value == "") {
                alert("Ա��id����Ϊ��...");
                form2.staffidI.focus();
                return false;
            }
            if ((form2.staffidI.value.length != 7) || (!form2.staffidI.value.match(regm))) {
                alert("Ա��idΪ7λ����...");
                form2.staffidI.focus();
                return false;
            }

            return true;
        }

        function checkOutOneStock() {
            var regm = /[0-9]+$/;
            if (OutOneStockForm.OutOneStockTemp.value.length != 15 || !OutOneStockForm.OutOneStockTemp.value.match(regm)) {

                alert("��������ȷ���·����");
                OutOneStockForm.OutOneStockTemp.focus();
                return false;
            }

        }

        function checkStockInid() {
            var regm = /[0-9]+$/;
            if (StockInidForm.idTemp.value.length != 11 || !StockInidForm.idTemp.value.match(regm)) {

                alert("��������ȷ�ĵ���");
                StockInidForm.idTemp.focus();
                return false;
            }

        }

        function checkInOneStock() {
            var regm = /[0-9]+$/;
            if (InOneStockForm.clothingTemp.value.length != 15 || !InOneStockForm.clothingTemp.value.match(regm)) {

                alert("��������ȷ���·����");
                InOneStockForm.clothingTemp.focus();
                return false;
            }

        }

        function OutOneStockSearch() {
            var regm = /[0-9]+$/;
            if (OutOneStockForm.OutOneStockTemp.value.length == 15 && OutOneStockForm.OutOneStockTemp.value.match(regm)) {

                window.location.href = "../allCanAccept/OutOneStockServlet?OutOneStockTemp=" + OutOneStockForm.OutOneStockTemp.value;
            }

        }

        function InOneStockSearch() {
            var regm = /[0-9]+$/;
            if (InOneStockForm.clothingTemp.value.length == 15 && InOneStockForm.clothingTemp.value.match(regm)) {

                window.location.href = "../allCanAccept/InOneStockServlet?clothingTemp=" + InOneStockForm.clothingTemp.value;
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
                            �޸���Ϣ
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
                            <!--Ȩ��-->
                            <span class="text-secondary text-small">
                <%
                    boolean status = true;
                    if (session.getAttribute("authority").toString().equals("su")) {
                %>
                <c:out value="����Ա"></c:out>
                <%
                } else {
                    status = false;
                %>
                <c:out value="��ͨ�û�"></c:out>
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
                        <span class="menu-title">�û�����</span>
                        <i class="mdi mdi-home menu-icon"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../admin/ALLApplyuserServlet">
                        <span class="menu-title">�������</span>
                        <i class="mdi mdi-crosshairs-gps menu-icon"></i>
                    </a>
                </li>
                    <%
                    }
                %>
                <li class="nav-item">
                    <a class="nav-link" href="../allCanAccept/allStaffServlet">
                        <span class="menu-title">Ա������</span>
                        <i class="mdi mdi-account-multiple menu-icon"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../allCanAccept/aUservipServlet">
                        <span class="menu-title">vip����</span>
                        <i class="mdi mdi-account-circle menu-icon"></i>
                    </a>
                </li>
                    <%
                    if(status){
                %>
                <li class="nav-item">
                    <a class="nav-link" href="../admin/AllClothiingServlet">
                        <span class="menu-title">�·�����</span>
                        <i class="mdi mdi-hanger menu-icon"></i>
                    </a>
                </li>
                    <%
                    }
                %>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false"
                       aria-controls="ui-basic">
                        <span class="menu-title">�ֿ�</span>
                        <i class="menu-arrow"></i>
                        <i class="mdi mdi-tshirt-crew menu-icon"></i>
                    </a>
                    <div class="collapse" id="ui-basic">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item"><a class="nav-link " href="../allCanAccept/AllDepotServlet">�ֿ����</a>
                            </li>
                            <li class="nav-item"><a class="nav-link" href="../allCanAccept/countDepotServlet">�̵�</a>
                            </li>
                            <li class="nav-item"><a class="nav-link"
                                                    href="../allCanAccept/stockTransferServlet">��������</a>
                            </li>
                            <%
                                if (status) {
                            %>
                            <li class="nav-item"><a class="nav-link"
                                                    href="../admin/stockTransferDetailServlet">�ֿ�ת����Ϣ</a>
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
                        <span class="menu-title">��ˮ���鿴</span>
                        <i class="mdi mdi-table-large menu-icon"></i>
                    </a>
                </li>
                    <%
                    }
                %>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="collapse" href="#ui-basic3" aria-expanded="false"
                       aria-controls="ui-basic">
                        <span class="menu-title">���۹���</span>
                        <i class="menu-arrow"></i>
                        <i class="mdi mdi-chart-bar menu-icon"></i>
                    </a>
                    <div class="collapse" id="ui-basic3">
                        <ul class="nav flex-column sub-menu">
                            <%
                                if (status) {
                            %>
                            <li class="nav-item"><a class="nav-link " href="../admin/allSalesServlet">����</a></li>

                            <%
                            } else {
                            %>
                            <li class="nav-item"><a class="nav-link " href="../allCanAccept/salesServlet">����</a></li>
                            <%
                                }
                            %>
                            <li class="nav-item"><a class="nav-link " href="../allCanAccept/returnServlet">�˻���</a></li>


                        </ul>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../allCanAccept/checkAllSalesServlet">
                        <span class="menu-title">Ӫҵͳ��</span>
                        <i class="mdi mdi-chart-bar menu-icon"></i>
                    </a>
                </li>

                    <%
                    if(status){
                %>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="collapse" href="#ui-basic2" aria-expanded="false"
                       aria-controls="ui-basic">
                        <span class="menu-title">����ͳ��</span>
                        <i class="menu-arrow"></i>
                        <i class="mdi mdi-chart-bar menu-icon"></i>
                    </a>
                    <div class="collapse" id="ui-basic2">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item"><a class="nav-link " href="../admin/countsuServlet">���ۼ��</a></li>
                            <li class="nav-item"><a class="nav-link " href="../admin/clothingSalesServlet">�·��������</a>
                            </li>
                            <li class="nav-item"><a class="nav-link "
                                                    href="../admin/adminAchievementUserServlet">����ҵ��ͳ��</a></li>
                            <li class="nav-item"><a class="nav-link"
                                                    href="../admin/adminAchievementStaffServlet">��Աҵ��ͳ��</a></li>
                        </ul>
                    </div>
                </li>
                    <%
                    }else{
                %>
                <li class="nav-item">
                    <a class="nav-link" href="countpuServlet">
                        <span class="menu-title">����ͳ��</span>
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
                                <div>
                                    <button type="button" class="btn btn-gradient-primary btn-fw"
                                            onclick="return stockOutClick();">����
                                    </button>
                                    <label style="width: 100px;"></label>
                                    <button type="button" class="btn btn-gradient-success btn-fw"
                                            onclick="return stockInClick();">���
                                    </button>
                                </div>
                                <%
                                    Vector<DepotDTO> v4 = (Vector<DepotDTO>) session.getAttribute("stockInDetails");
                                    if (v4.size() == 0) {
                                %>
                                <script>document.getElementById('inDIV').style.display = 'none';</script>
                                <script>document.getElementById('idDIV').style.display = 'none';</script>
                                <%
                                    }
                                %>
                                <div id="outDIV" style="margin-top: 30px">

                                    <form class="form-inline" accept-charset="GB18030" name="OutOneStockForm"
                                          action="OutOneStockServlet"
                                          method="get">
                                        <div class="form-group col-4">
                                            <div class="input-group">
                                                <input type="text" id="OutOneStockTemp" name="OutOneStockTemp"
                                                       class="form-control"
                                                       placeholder="�·����" aria-label=""
                                                       aria-describedby="basic-addon2" onchange="OutOneStockSearch();">
                                                <div class="input-group-append">
                                                    <button type="submit" class="btn btn-sm btn-outline-dark"
                                                            type="button" onclick="return checkOutOneStock();">ȷ��
                                                    </button>
                                                </div>
                                            </div>
                                        </div>


                                        <label style="width:10px;"> </label>
                                        <button type="button" class="btn btn-outline-dark btn-fw" value=""
                                                onclick="return endStockOut();">��������
                                        </button>
                                    </form>
                                    <div style="height:20px; "></div>
                                    <%
                                        if (session.getAttribute("stockOutDetails") != null) {
                                            try {
                                                Vector<DepotDTO> v = (Vector<DepotDTO>) session.getAttribute("stockOutDetails");
                                                System.out.println(v.size());
                                                Iterator<DepotDTO> it = v.iterator();
                                                DepotDTO u = new DepotDTO();
                                    %>
                                    <table class="table table-bordered" style="text-align: center" border="5">
                                        <tr class="table-info">
                                            <td>�·����</td>
                                            <td>����</td>
                                        </tr>
                                        <%
                                            while (it.hasNext()) {
                                                u = it.next();


                                        %>
                                        <tr class="table-danger">
                                            <td><%=u.getClothingid()%>
                                            </td>
                                            <td><%=u.getNumbers()%>
                                            </td>
                                            <%sum += u.getNumbers();%>
                                        </tr>
                                        <%
                                            }%>
                                        <tr class="table-info">
                                            <td>�ܼ���</td>
                                            <td><input type="hidden" id="sum" value="<%=sum%>"><%=sum%>
                                            </td>
                                        </tr>
                                    </table>
                                    <%
                                        } catch (Exception e) {
                                        }
                                    %><%
                                    }
                                %>
                                </div>
                                <%
                                    Vector<DepotDTO> v = (Vector<DepotDTO>) session.getAttribute("stockOutDetails");
                                    if (v.size() == 0) {
                                %>
                                <script>document.getElementById('outDIV').style.display = 'none';</script>
                                <%
                                    }
                                %>
                                <div id="inDIV" style="margin-top: 30px">
                                    <form class="form-inline" accept-charset="GB18030" name="InOneStockForm"
                                          action="InOneStockServlet"
                                          method="get">
                                        <div class="form-group col-4">
                                            <div class="input-group">
                                                <input type="text" id="clothingTemp" name="clothingTemp"
                                                       class="form-control"
                                                       placeholder="�·����" aria-label=""
                                                       aria-describedby="basic-addon2" onchange="InOneStockSearch();">
                                                <div class="input-group-append">
                                                    <button type="submit" class="btn btn-sm btn-outline-dark"
                                                            type="button" onclick="return checkInOneStock();">ȷ��
                                                    </button>
                                                </div>
                                            </div>
                                        </div>


                                        <label style="width:10px;"> </label>
                                        <button type="button" class="btn btn-outline-dark btn-fw" value=""
                                                onclick="return endStockIn();">�������
                                        </button>
                                    </form>
                                    <%--<form accept-charset="GB18030" name="InOneStockForm" action="InOneStockServlet"--%>
                                          <%--method="get">--%>
                                        <%--<label>�·����</label>--%>
                                        <%--<input type="text" style="border-top-right-radius: 5px;border-top-left-radius: 5px;--%>
                                        <%--border-bottom-right-radius:5px;--%>
                                        <%--border-bottom-left-radius:5px;"--%>
                                               <%--id="clothingTemp" name="clothingTemp" onchange="InOneStockSearch();">--%>
                                        <%--<input type="submit" class="btn btn-outline-success btn-sm" value="ȷ��"--%>
                                               <%--onclick="return checkInOneStock();">--%>
                                        <%--<label style="width:10px;"> </label>--%>
                                        <%--<button type="button" class="btn btn-gradient-info btn-sm" value=""--%>
                                                <%--onclick="endStockIn()">�������--%>
                                        <%--</button>--%>
                                    <%--</form>--%>
                                    <%----%>
                                    <div style="height:20px; "></div>
                                    <%
                                        if (session.getAttribute("stockInDetails") != null) {
                                            try {
                                                Vector<DepotDTO> v3 = (Vector<DepotDTO>) session.getAttribute("stockInDetails");
                                                System.out.println(v3.size());
                                                Iterator<DepotDTO> it = v3.iterator();
                                                DepotDTO u = new DepotDTO();
                                    %>
                                    <table class="table table-bordered" style="text-align: center"   border="5">
                                        <tr class="table-info">
                                            <td>�·����</td>
                                            <td>����</td>
                                        </tr>
                                        <%
                                            while (it.hasNext()) {
                                                u = it.next();


                                        %>
                                        <tr class="table-danger">
                                            <td><%=u.getClothingid()%>
                                            </td>
                                            <td><%=u.getNumbers()%>
                                            </td>
                                            <%sum += u.getNumbers();%>
                                        </tr>
                                        <%
                                            }%>
                                        <tr class="table-info">
                                            <td>�ܼ���</td>
                                            <td><input type="hidden" id="sum" value="<%=sum%>"><%=sum%>
                                            </td>
                                        </tr>
                                    </table>
                                    <%
                                        } catch (Exception e) {
                                        }
                                    %><%
                                    }
                                %>
                                </div>
                                <%
                                    String idTemp = null;
                                    try {
                                        idTemp =session.getAttribute("idTemp").toString();
                                    }catch (Exception e){}
//                                    if (v2.size() == 0 && idTemp == null) {
                                    if (idTemp == null) {
                                %>
                                <script>document.getElementById('inDIV').style.display = 'none';</script>
                                <%
                                    }
                                %>
                            </div>


                        </div>

                    </div>

                </div>

            </div>


            <!-- content-wrapper ends -->
            <!-- partial:../../partials/_footer.html -->

            <!-- partial -->
        </div>

        <!-- main-panel ends -->

    </div>
    <div id="light" class="white_content">
        <div class="card-body">
            <form class="forms-sample" accept-charset="GB18030" name="form1" action="submitStockOutServlet"
                  method="post">
                <div class="form-group" style="margin-bottom: 0px;">
                    <label for="numbersF">����</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="label" id="numbersF"
                           readonly="readonly" name="numbersF"/>
                </div>
                <div class="form-group" style="margin-top:5px; margin-bottom: 5px;">
                    <label for="inUserF">�������̱��</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="label" id="inUserF"
                           name="inUserF"/>
                </div>
                <div class="form-group" style="margin-top:5px; margin-bottom: 5px;">
                    <label for="staffidF">Ա�����</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="label" id="staffidF"
                           name="staffidF"/>
                </div>
                <div style="height: 10px;"></div>
                <div class="form-group" style="margin-top:5px; margin-bottom: 5px;" align="center">
                    <input type="submit" class="btn btn-gradient-primary btn-icon-text btn-sm" onclick="return check();"
                           value="ȷ��">
                    <label style="width: 30px;"></label>
                    <input type="button" class="btn btn-gradient-dark btn-icon-text btn-sm" onclick="two()" value="ȡ��">
                </div>

            </form>
        </div>
    </div>
    <div id="light2" class="white_content2">
        <div class="card-body">
            <form class="forms-sample" accept-charset="GB18030" name="form2" action="submitStockInServlet"
                  method="post">

                <div class="form-group" style="margin-top:5px; margin-bottom: 5px;">
                    <label for="staffidI">Ա�����</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="label" id="staffidI"
                           name="staffidI"/>
                </div>
                <div style="height: 10px;"></div>
                <div class="form-group" style="margin-top:5px; margin-bottom: 5px;" align="center">
                    <input type="submit" class="btn btn-gradient-primary btn-icon-text btn-sm"
                           onclick="return check2();" value="ȷ��">
                    <label style="width: 30px;"></label>
                    <input type="button" class="btn btn-gradient-dark btn-icon-text btn-sm" onclick="two()" value="ȡ��">
                </div>

            </form>
        </div>
    </div>
    <div id="idDIV" class="white_content2">
        <div class="card-body">
            <form accept-charset="GB18030" name="StockInidForm" action="idStockInServlet"
                  method="get">
                <div class="form-group" style="margin-top:5px; margin-bottom: 5px;">
                    <label for="idTemp">����</label>
                    <input style="padding-top: 5px;padding-bottom: 5px;" class="form-control" type="label" id="idTemp"
                           name="idTemp"/>
                </div>
                <div style="height: 10px;"></div>
                <div class="form-group" style="margin-top:5px; margin-bottom: 5px;" align="center">
                    <input type="submit" class="btn btn-gradient-primary btn-icon-text btn-sm"
                           onclick="return checkStockInid();" value="ȷ��">
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
