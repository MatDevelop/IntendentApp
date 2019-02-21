<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

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
                    <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Wyloguj</a></li>
                </ul>
            </div>
        </nav>

        <div class="container">
            <div class="page-header text-center">
                <h1>Tworzenie zestawienia sprzedaży</h1>
            </div>
        </div>

        <c:choose>
            <c:when test="${mode == 'MODE_FORM'}">
                <div class="container">
                    <div class="row text-center">
                        <div class="col-lg-2 col-lg-offset-10">
                            <a href="/"><button type="button" class="btn btn-view-product btn-primary">Powrót do menu</button></a>
                        </div>
                    </div>
                </div>
                <div class="container">

                    <form method="POST" action="addsales">
                        <div class="row text-center">
                            <div class="col-lg-2 col-lg-offset-5">
                                <label>Miesiąc i rok</label>
                                <input type="month" class="form-control" name="filename" value="${saleReport.filename}" required/>
                                <br>
                            </div>

                        </div>
                        <div class="row text-center">

                            <div class="col-lg-3">
                                <label>Imię i nazwisko</label>
                            </div>
                            <div class="col-lg-2">
                                <label>Adres</label>
                            </div>
                            <div class="col-lg-2">
                                <label>Data</label>
                            </div>
                            <div class="col-lg-1">
                                <label>Kwota</label>
                            </div>
                            <div class="col-lg-1">
                                <label>Odpis</label>
                            </div>
                            <div class="col-lg-1">
                                <label>Wpłata</label>
                            </div>
                            <div class="col-lg-1">
                                <label>Nadpłata</label>
                            </div>
                            <div class="col-lg-1">
                                <label>Zaległa</label>
                            </div>

                        </div>
                        <hr>
                        <c:forEach var="consumer" items="${consumers}">
                            <div class="row text-center">

                                <div class="col-lg-3">
                                    <label>${consumer.name} ${consumer.surname}</label>
                                    <input type="hidden" class="form-control" name="id" value="${consumer.id}"/>
                                    <input type="hidden" class="form-control" name="name" value="${consumer.name}"/>
                                    <input type="hidden" class="form-control" name="surname" value="${consumer.surname}"/>
                                </div>
                                <div class="col-lg-2">
                                    <label>${consumer.address}</label>
                                    <input type="hidden" class="form-control" name="address" value="${consumer.address}"/>
                                </div>
                                <div class="col-lg-2">
                                    <input type="date" class="form-control" name="date" value="${saleReport.date}"/>
                                </div>
                                <div class="col-lg-1">
                                    <input type="number" class="form-control" name="dueamount" min="0" max="1000" step="0.01" pattern="\d+(\.\d{1,2})?" placeholder="0.00" value="${saleReport.dueamount}" required/>
                                </div>
                                <div class="col-lg-1">
                                    <input type="number" class="form-control" name="writeoff" min="0" max="1000" step="0.01" pattern="\d+(\.\d{1,2})?" placeholder="0.00" value="${saleReport.writeoff}"/>
                                </div>
                                <div class="col-lg-1">
                                    <input type="number" class="form-control" name="payment" min="0" max="1000" step="0.01" pattern="\d+(\.\d{1,2})?" placeholder="0.00" value="${saleReport.payment}"/>
                                </div>
                                <div class="col-lg-1">
                                    <input type="number" class="form-control" name="excess" min="0" max="1000" step="0.01" pattern="\d+(\.\d{1,2})?" placeholder="0.00" value="${saleReport.excess}"/>
                                </div>
                                <div class="col-lg-1">
                                    <input type="number" class="form-control" name="overdue" min="0" max="1000" step="0.01" pattern="\d+(\.\d{1,2})?" placeholder="0.00" value="${saleReport.overdue}"/>
                                </div>
                            </div>
                            <br>
                        </c:forEach>
                        <br><br>
                        <div class="row text-center">
                            <input type="submit" class="btn btn-primary" value="Zatwierdź"/>
                        </div>

                    </form>


                </div>
            </c:when>
            <c:when test="${mode == 'MODE_MESSAGE'}">
                <c:choose>
                    <c:when test="${message == '1'}">
                        <div class="container">
                            <form method="GET" action="/">
                                <div class="row">
                                        <div class="alert alert-success">
                                          <strong>Sukces!</strong> Utworzono/zmodyfikowano zestawienie sprzedaży!
                                        </div>
                                </div>
                                <div class="row">
                                    <input type="submit" class="btn btn-view-product btn-success" value="Powrót do menu"/>
                                    <button type="button" class="btn btn-view-product btn-danger" name="back" onclick="history.back()">Popraw</button>
                                </div>
                            </form>
                        </div>
                    </c:when>
                    <c:when test="${message == '0'}">
                        <div class="container">
                                <div class="row">
                                        <div class="alert alert-danger">
                                          <strong>Ups!</strong> Prawdopodobnie próbujesz nadpisać plik, który jest otwarty!
                                        </div>
                                </div>
                                <button type="button" class="btn btn-danger" name="back" onclick="history.back()">Powtórz</button>
                        </div>
                    </c:when>
                </c:choose>
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