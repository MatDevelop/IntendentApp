<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>IntendentApp</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="static/css/style.css">
</head>
<body>
    <div id="container">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/">IntendentApp</a>
                </div>
                <ul class="nav navbar-nav">
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Wyloguj</a></li>
                </ul>
            </div>
        </nav>


        <c:choose>
            <c:when test="${mode == 'MODE_PRODUCTS'}">
                <div class="container">
                    <div class="page-header text-center">
                        <h1>Produkty</h1>
                    </div>
                </div>
                <div class="container">
                    <div class="row text-center">
                        <div class="col-lg-2 col-lg-offset-8">
                            <a href="add-product"><button type="button" class="btn btn-view-product btn-primary">Dodaj</button></a>
                        </div>
                        <div class="col-lg-2">
                            <a href="/"><button type="button" class="btn btn-view-product btn-primary">Powrót do menu</button></a>
                        </div>
                    </div>
                </div>
                <br>
                <div class="container text-center" id="productsDiv">
                    <div class="table">
                        <table class="table table-striped table-bordered text-left">
                            <thead>
                                <col width="500">
                                <col width="150">
                                <col width="150">
                                <col width="80">
                                <col width="100">
                                <tr>
                                    <th>Nazwa</th>
                                    <th>Numer</th>
                                    <th>Jednostka</th>
                                    <th>Cena jednostkowa</th>
                                    <th>Edytuj</th>
                                    <th>Usuń</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="product" items="${products}">
                                    <tr>
                                        <td>${product.name}</td>
                                        <td>${product.number}</td>
                                        <td>${product.unit}</td>
                                        <c:choose>
                                            <c:when test="${product.unitprice>=0}">
                                                <td><fmt:formatNumber value="${product.unitprice}" minFractionDigits="2" maxFractionDigits="2"/> zł</td>
                                            </c:when>
                                            <c:when test="${empty product.unitprice}">
                                                <td><fmt:formatNumber value="${product.unitprice}" minFractionDigits="2" maxFractionDigits="2"/></td>
                                            </c:when>
                                        </c:choose>
                                        <td><a href="update-product?number=${product.number}"><span class="glyphicon glyphicon-pencil"></span></a></td>
                                        <td><a href="delete-product?number=${product.number}"><span class="glyphicon glyphicon-trash"></span></a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:when>
            <c:when test="${mode == 'MODE_WRONG_NUMBER'}">
                <div class="container">
                    <div class="page-header text-center">
                        <h1>Dodaj produkt</h1>
                    </div>
                </div>
                <div class="container">
                        <div class="row">
                                <div class="alert alert-danger">
                                  <strong>Ups!</strong> Podany numer produktu już istnieje!
                                </div>
                        </div>
                        <button type="button" class="btn btn-danger" name="back" onclick="history.back()">Powtórz</button>
                </div>
            </c:when>
            <c:when test="${mode == 'MODE_ADD'}">
                <div class="container">
                    <div class="page-header text-center">
                        <h1>Dodaj produkt</h1>
                    </div>
                </div>
                <div class="container text-center">
                    <form class="form-horizontal" method="POST" action="save-product">
                        <input type="hidden" name="addorupdate" value="1"/>
                        <div class="row text-center col-lg-offset-2">
                            <div class="col-lg-3">
                                <label>Nazwa</label>
                            </div>
                            <div class="col-lg-3">
                                <label>Numer</label>
                            </div>
                            <div class="col-lg-3">
                                <label>Jednostka</label>
                            </div>
                        </div>
                        <div class="row text-center col-lg-offset-2">
                            <div class="col-lg-3 ">
                                <input type="text" class="form-control" name="name" value="${product.name}" maxlength="23" placeholder="Nazwa" required/>
                            </div>
                            <div class="col-lg-3">
                                <input type="text" class="form-control" name="number" value="${product.number}" maxlength="4" placeholder="Numer" required/>
                            </div>
                            <div class="col-lg-3">
                                <input type="text" class="form-control" name="unit" value="${product.unit}" maxlength="5" placeholder="Jednostka" required/>
                            </div>
                        </div>

                        <br><br>
                        <div class="row text-center">
                            <input type="submit" class="btn btn-primary" value="Zapisz"/>
                            <a href="/viewproducts"><button type="button" class="btn btn-update-product btn-primary">Cofnij</button></a>
                        </div>
                    </form>
                </div>
            </c:when>
            <c:when test="${mode == 'MODE_UPDATE'}">
                <div class="container">
                    <div class="page-header text-center">
                        <h1>Modyfikuj produkt</h1>
                    </div>
                </div>
                <div class="container text-center">
                    <form class="form-horizontal" method="POST" action="save-product">
                        <input type="hidden" name="addorupdate" value="2"/>
                        <input type="hidden" name="number" value="${product.number}"/>
                        <div class="row text-center">
                            <div class="col-lg-4 col-lg-offset-2">
                                <label>Nazwa</label>
                            </div>
                            <div class="col-lg-2">
                                <label>Jednostka</label>
                            </div>
                            <div class="col-lg-2">
                                <label>Cena jednostkowa</label>
                            </div>
                        </div>
                        <div class="row text-center">
                            <div class="col-lg-4 col-lg-offset-2">
                                <input type="text" class="form-control" name="name" value="${product.name}" maxlength="23" placeholder="Nazwa" required/>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" class="form-control" name="unit" value="${product.unit}" maxlength="5" placeholder="Jednostka" required/>
                            </div>
                            <div class="col-lg-2">
                                <input type="number" class="form-control" name="unitprice" value="${product.unitprice}" maxlength="5" step="0.01" pattern="\d+(\.\d{1,2})?" placeholder="0.00" required/>
                            </div>
                        </div>

                        <br><br>
                        <div class="row text-center">
                            <input type="submit" class="btn btn-update-product btn-primary" value="Zapisz"/>
                            <a href="/viewproducts"><button type="button" class="btn btn-update-product btn-primary">Cofnij</button></a>
                        </div>
                    </form>
                </div>
            </c:when>
        </c:choose>




    </div>
    <br><br>
    <!--Copyright-->
        <div class="footersettings">
            © 2019 Copyright: Mateusz Brugier
        </div>
    <!--/.Copyright-->

</body>
</html>