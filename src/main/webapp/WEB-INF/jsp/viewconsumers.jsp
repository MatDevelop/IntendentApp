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

        <c:choose>
            <c:when test="${mode == 'MODE_CONSUMERS'}">
                <div class="container">
                    <div class="page-header text-center">
                        <h1>Konsumenci</h1>
                    </div>
                </div>
                <div class="container">
                    <div class="row text-center">
                        <div class="col-lg-2 col-lg-offset-8">
                            <a href="add-consumer"><button type="button" class="btn btn-view-product btn-primary">Dodaj</button></a>
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
                                <col width="300">
                                <col width="300">
                                <col width="300">
                                <col width="80">
                                <col width="80">
                                <tr>
                                    <th>Nazwisko</th>
                                    <th>Imię</th>
                                    <th>Adres</th>
                                    <th>Edytuj</th>
                                    <th>Usuń</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="consumer" items="${consumers}">
                                    <tr>
                                        <td>${consumer.surname}</td>
                                        <td>${consumer.name}</td>
                                        <td>${consumer.address}</td>
                                        <td><a href="update-consumer?id=${consumer.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>
                                        <td><a href="delete-consumer?id=${consumer.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:when>

            <c:when test="${mode == 'MODE_ADD'}">
                <div class="container">
                    <div class="page-header text-center">
                        <h1>Dodaj konsumenta</h1>
                    </div>
                </div>
                <div class="container text-center">
                    <form class="form-horizontal" method="POST" action="save-consumer">
                        <input type="hidden" name="addorupdate" value="1"/>
                        <div class="row text-center col-lg-offset-2">
                            <div class="col-lg-3">
                                <label>Imię</label>
                            </div>
                            <div class="col-lg-3">
                                <label>Nazwisko</label>
                            </div>
                            <div class="col-lg-3">
                                <label>Adres</label>
                            </div>
                        </div>
                        <div class="row text-center col-lg-offset-2">
                            <div class="col-lg-3 ">
                                <input type="text" class="form-control" name="name" value="${consumer.name}" maxlength="25" placeholder="Imię" required/>
                            </div>
                            <div class="col-lg-3">
                                <input type="text" class="form-control" name="surname" value="${consumer.surname}" maxlength="25" placeholder="Nazwisko" required/>
                            </div>
                            <div class="col-lg-3">
                                <input type="text" class="form-control" name="address" value="${consumer.address}" maxlength="25" placeholder="Adres" required/>
                            </div>
                        </div>

                        <br><br>
                        <div class="row text-center">
                            <input type="submit" class="btn btn-primary" value="Zapisz"/>
                            <a href="/viewconsumers"><button type="button" class="btn btn-update-product btn-primary">Cofnij</button></a>
                        </div>
                    </form>
                </div>
            </c:when>
            <c:when test="${mode == 'MODE_UPDATE'}">
                <div class="container">
                    <div class="page-header text-center">
                        <h1>Modyfikuj konsumenta</h1>
                    </div>
                </div>
                <div class="container text-center">
                    <form class="form-horizontal" method="POST" action="save-consumer">
                        <input type="hidden" name="addorupdate" value="2"/>
                        <input type="hidden" name="id" value="${consumer.id}"/>
                        <div class="row text-center">
                            <div class="col-lg-4">
                                <label>Imię</label>
                            </div>
                            <div class="col-lg-4">
                                <label>Nazwisko</label>
                            </div>
                            <div class="col-lg-4">
                                <label>Adres</label>
                            </div>
                        </div>
                        <div class="row text-center">
                            <div class="col-lg-4">
                                <input type="text" class="form-control" name="name" value="${consumer.name}" maxlength="25" placeholder="Imię" required/>
                            </div>
                            <div class="col-lg-4">
                                <input type="text" class="form-control" name="surname" value="${consumer.surname}" maxlength="25" placeholder="Nazwisko" required/>
                            </div>
                            <div class="col-lg-4">
                                <input type="text" class="form-control" name="address" value="${consumer.address}" maxlength="25" placeholder="Adres" required/>
                            </div>
                        </div>

                        <br><br>
                        <div class="row text-center">
                            <input type="submit" class="btn btn-update-product btn-primary" value="Zapisz"/>
                            <a href="/viewconsumers"><button type="button" class="btn btn-update-product btn-primary">Cofnij</button></a>
                        </div>
                    </form>
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