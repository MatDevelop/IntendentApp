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
    
    <script>

    
    
    </script>
    
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
                <h1>Karty materiałowe</h1>
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
                <div class="container text-center">
                    <form class="form-inline" method="POST" action="cards">
                        <div class="form-group">
                            <label>Wybierz kartę: </label>
                            <input type="text" class="form-control" name="number" value="${card.number}" min="1" max="5" required="true"/>
                        </div>
                        <input type="submit" class="btn btn-primary" value="Zatwierdź"/>
                    </form>
                </div>


            </c:when>
            
            <c:when test="${mode == 'MODE_CARD'}">
            	<div class="container">
                    <div class="row text-center">
                        <div class="col-lg-2 col-lg-offset-10">
                            <a href="/"><button type="button" class="btn btn-view-product btn-primary">Powrót do menu</button></a>
                        </div>
                    </div>
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