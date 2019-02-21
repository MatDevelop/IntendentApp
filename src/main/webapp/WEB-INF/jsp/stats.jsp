<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html lang="pl">
<head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['bar']});
        google.charts.setOnLoadCallback(drawConsumers);
        google.charts.setOnLoadCallback(drawDinners);
        google.charts.setOnLoadCallback(drawUnpaid);

        function drawConsumers() {
            var data = google.visualization.arrayToDataTable([
                ['Miesiąc i rok', 'Konsumenci'],
                <c:forEach var="stat" items="${stats}">
                    ['${stat.month_year}', ${stat.consumers}],
                </c:forEach>
            ]);

            var options = {
                legend: { position: 'none' },
                chart: {
                    title: 'Ilość konsumentów jedzących obiady',
                    subtitle: 'Konsumenci w danym miesiącu',
                }
            };

            var chart = new google.charts.Bar(document.getElementById('consumersColumnChart'));

            chart.draw(data, google.charts.Bar.convertOptions(options));
        }
        function drawDinners() {
            var data = google.visualization.arrayToDataTable([
                ['Miesiąc i rok', 'Obiady'],
                <c:forEach var="stat" items="${stats}">
                    ['${stat.month_year}', ${stat.reports}],
                </c:forEach>
            ]);

            var options = {
                legend: { position: 'none' },
                colors: ['#e2431e'],
                chart: {
                    title: 'Ilość obiadów',
                    subtitle: 'Odbyte obiady w danym miesiącu',
                }
            };

            var chart = new google.charts.Bar(document.getElementById('dinnersColumnChart'));

            chart.draw(data, google.charts.Bar.convertOptions(options));
        }
        function drawUnpaid() {
            var data = google.visualization.arrayToDataTable([
                ['Miesiąc i rok', 'Niezapłacone'],
                <c:forEach var="stat" items="${stats}">
                    ['${stat.month_year}', ${stat.unpaid}],
                </c:forEach>
            ]);

            var options = {
                legend: { position: 'none' },
                colors: ['gold'],
                chart: {
                    title: 'Ilość niezapłaconych osób',
                    subtitle: 'Niezapłacone osoby w danym miesiącu',
                }
            };

            var chart = new google.charts.Bar(document.getElementById('unpaidColumnChart'));

            chart.draw(data, google.charts.Bar.convertOptions(options));
        }
    </script>
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
                <h1>Statystyki</h1>
            </div>
        </div>
        <div class="container">
            <div class="row text-center">
                <div class="col-lg-2 col-lg-offset-8">
                    <a href="/"><button type="button" class="btn btn-view-product btn-primary">Powrót do menu</button></a>
                </div>
                <div class="col-lg-2">
                    <a href="resetstats"><button type="button" class="btn btn-view-product btn-danger">Reset statystyk</button></a>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row text-center">
                <br>
                <div id="consumersColumnChart" style="height: 500px;"></div>
                <div id="dinnersColumnChart" style="height: 500px;"></div>
                <div id="unpaidColumnChart" style="height: 500px;"></div>
            </div>
        </div>


    </div>
    <!--Copyright-->
        <br><br><br>
        <div class="footersettings">
            © 2019 Copyright: Mateusz Brugier
        </div>
    <!--/.Copyright-->
</body>
</html>