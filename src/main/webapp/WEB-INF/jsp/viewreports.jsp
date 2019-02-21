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
                    <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Wyloguj</a></li>
                </ul>
            </div>
        </nav>

        <div class="container">
            <div class="page-header text-center">
                <h1>Raporty dzienne</h1>
            </div>
        </div>
        <div class="container">
            <div class="row text-center">
                <div class="col-lg-2 col-lg-offset-10">
                    <a href="/"><button type="button" class="btn btn-view-product btn-primary text-right">Powrót do menu</button></a>
                </div>
            </div>
        </div>
        <br>
        <div class="container text-center">
            <div class="row text-center">
                <div class="col-md-6 col-md-offset-3">
                    <div class="table">
                        <table class="table table-striped table-bordered text-left">
                            <thead>
                                <col width="300">
                                <col width="80">
                                <tr>
                                    <th>Nazwa pliku</th>
                                    <th>Pobierz</th>
                                </tr>
                                <tbody>
                                    <c:forEach var="file" items="${files}">
                                        <tr>
                                            <td>${file.name}</td>
                                            <td class="text-center"><a href="getDayReport?fileName=${file.name}"><span style="font-size:25px;" class="glyphicon glyphicon-download"></span></a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="page-header text-center">
                <h1>Raporty miesięczne</h1>
            </div>
        </div>
        <div class="container text-center">
            <div class="row text-center">
                <div class="col-md-6 col-md-offset-3">
                    <div class="table">
                        <table class="table table-striped table-bordered text-left">
                            <thead>
                                <col width="300">
                                <col width="80">
                                <tr>
                                    <th>Nazwa pliku</th>
                                    <th>Pobierz</th>
                                </tr>
                                <tbody>
                                    <c:forEach var="file" items="${monthfiles}">
                                        <tr>
                                            <td>${file.name}</td>
                                            <td class="text-center"><a href="getMonthReport?fileName=${file.name}"><span style="font-size:25px;" class="glyphicon glyphicon-download"></span></a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>



    </div>
    <!--Copyright-->
        <div class="footersettings">
            © 2019 Copyright: Mateusz Brugier
        </div>
    <!--/.Copyright-->

</body>
</html>