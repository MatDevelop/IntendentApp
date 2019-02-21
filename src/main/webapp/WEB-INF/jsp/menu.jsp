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
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Wyloguj</a></li>
                </ul>
            </div>
        </nav>

        <div class="container">
            <div class="page-header text-center">
                <h1>Tworzenie jadłospisu</h1>
            </div>
        </div>


        <c:choose>
            <c:when test="${mode == 'MODE_DAYSAMOUNT'}">
                <div class="container">
                    <div class="row text-center">
                        <div class="col-lg-2 col-lg-offset-10">
                            <a href="/"><button type="button" class="btn btn-view-product btn-primary">Powrót do menu</button></a>
                        </div>
                    </div>
                </div>
                <div class="container text-center">
                    <form class="form-inline" method="POST" action="menu">
                        <div class="form-group">
                            <label>Liczba dni [0-5] </label>
                            <input type="number" class="form-control" name="amount" value="${menu.amount}" min="1" max="5" required="true"/>
                        </div>
                        <input type="submit" class="btn btn-primary" value="Zatwierdź"/>
                    </form>
                </div>
            </c:when>

            <c:when test="${mode == 'MODE_FORMS'}">
                <div class="container">
                    <div class="row text-center">
                        <div class="col-lg-2 col-lg-offset-10">
                            <a href="/"><button type="button" class="btn btn-view-product btn-primary">Powrót do menu</button></a>
                        </div>
                    </div>
                </div>
                <div class="container">

                    <form method="POST" action="addmenu">
                    <div class="row text-center">
                        <div class="col-lg-2 col-lg-offset-1">
                            <label>Nazwa pliku</label>
                            <input type="text" class="form-control" name="filename" value="${menu.filename}" required="true"/>
                            <br>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-1 text-right">
                            <label>Lp</label>
                        </div>
                        <div class="col-lg-3">
                            <label>Data</label>
                        </div>
                        <div class="col-lg-8">
                            <label>Posiłek</label>
                        </div>

                    </div>
                        <c:forEach begin="1" end="${formamounts}" varStatus="loop">
                            <div class="row">
                                    <div class="col-lg-1 text-right">
                                        <label>${loop.index}</label>
                                    </div>
                                    <div class="col-lg-3">
                                        <input type="date" class="form-control" name="date" value="${menu.date}" required="true"/>
                                    </div>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control" name="meal" value="${menu.meal}" required="true"/>
                                    </div>
                            </div>
                            <br>
                        </c:forEach>
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
                                          <strong>Sukces!</strong> Utworzono/zmodyfikowano jadłospis!
                                        </div>
                                </div>
                                <div class="row">
                                    <input type="submit" class="btn btn-success" value="Powrót do menu!"/>
                                </div>
                        </div>
                    </c:when>
                    <c:when test="${message == '0'}">
                        <div class="container">
                            <form method="GET" action="menu">
                                <div class="row">
                                        <div class="alert alert-danger">
                                          <strong>Ups!</strong> Coś poszło nie tak!
                                        </div>
                                </div>
                                <div class="row">
                                    <input type="submit" class="btn btn-danger" value="Powtórz!"/>
                                </div>
                        </div>
                    </c:when>
                </c:choose>

            </c:when>

        </c:choose>
    </div>
        <!--Copyright-->
            <div class="footersettings">
                © 2018 Copyright: Mateusz Brugier
            </div>
        <!--/.Copyright-->
</body>
</html>