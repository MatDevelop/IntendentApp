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
	<link rel="stylesheet" href="static/css/formstyle.css">
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
                    <!--  <li><a href="stats"><span class="glyphicon glyphicon-stats"></span> Statystyki</a></li> -->
                    <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Wyloguj</a></li>
                </ul>
            </div>
        </nav>

        <c:choose>
            <c:when test="${mode == 'MODE_HOME'}">
                <div class="container-fluid top100">
                    <div class="row top100 center-block">
                        <div class="col-lg-2 col-lg-offset-2">
                            <a href="menu" class="btn btn-sq-lg btn-primary center-text-button">
                                Utwórz<br>jadłospis
                            </a>
                        </div>
                        <div class="col-lg-2">
                            <a href="dayreport" class="btn btn-sq-lg btn-success center-text-button">
                                 Utwórz<br>raport
                            </a>
                        </div>
                        <div class="col-lg-2">
                            <a href="sales" class="btn btn-sq-lg btn-warning center-text-button">
                                 Utwórz zestawienie<br>sprzedaży
                            </a>
                        </div>
                        <div class="col-lg-2">
                             <a href="cards" class="btn btn-sq-lg btn-danger center-text-button">
                                Przeglądaj<br>karty materiałowe
                             </a>
                        </div>
                    </div>
                    <div class="row top100 center-block">
                        <div class="col-lg-2 col-lg-offset-2">
                            <a href="viewreports" class="btn btn-sq-lg btn-info center-text-button">
                                Przeglądaj<br>raporty
                            </a>
                        </div>
                        <div class="col-lg-2">
                             <a href="viewsales" class="btn btn-sq-lg btn-danger center-text-button">
                                Przeglądaj<br>zestawienia<br>sprzedaży
                             </a>
                        </div>
                        <div class="col-lg-2">
                             <a href="viewconsumers" class="btn btn-sq-lg btn-primary center-text-button">
                                 Przeglądaj<br>konsumentów
                             </a>
                        </div>
                        <div class="col-lg-2">
                            <a href="viewproducts" class="btn btn-sq-lg btn-success center-text-button">
                                 Przeglądaj<br>produkty
                            </a>
                        </div>

                    </div>
                </div>

            </c:when>

        </c:choose>
    </div>
    <!--Copyright-->
        <div class="footersettings">
            © 2019 Copyright: Mateusz Brugier
        </div>
    <!--/.Copyright-->

</body>
</html>