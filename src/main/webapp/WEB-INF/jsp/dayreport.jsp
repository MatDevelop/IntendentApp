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
        function setProductNumber(selectid){
            var selectBox = document.getElementById(selectid);
            var userInput = selectBox.options[selectBox.selectedIndex].value;

            if(selectBox.selectedIndex==0){
                document.getElementById('numberID'+selectid).value="";
            }else{
                <c:forEach var="product" items="${productlist}">
                    if(userInput=='${product.name}'){
                        document.getElementById('numberID'+selectid).value='${product.number}';
                        document.getElementById('unitpriceID'+selectid).value='${product.unitprice}';
                    }
                </c:forEach>
            }
        }

        function calculateValue(id){
			var unitpriceInput = document.getElementById('unitpriceID'+id);
			var amountInput = document.getElementById('amountID'+id);
			var	positionValueInput = document.getElementById('positionValueID'+id);
			if(unitpriceInput.value != "" && amountInput.value != ""){
				positionValueInput.value=parseFloat(unitpriceInput.value*amountInput.value).toFixed(2);;
			}else{
				positionValueInput.value="";
			}
        }
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
                <h1>Tworzenie raportu dziennego</h1>
            </div>
        </div>

        <c:choose>
            <c:when test="${mode == 'MODE_FORM'}">
                <div class="container">
                    <div class="row text-center">
                        <div class="col-lg-2 col-lg-offset-8">
                            <a href="/dane-testowe"><button type="button" class="btn btn-view-product btn-success">Dane testowe</button></a>         
                        </div>
                        <div class="col-lg-2">
                            <a href="/"><button type="button" class="btn btn-view-product btn-primary">Powrót do menu</button></a>         
                        </div>
                    </div>
                </div>
                <div class="container">

                    <form method="POST" action="adddayreport">
                        <div class="row text-center">
                            <label>Obiad</label>
                        </div>
                        <div class="row text-center">
                            <div class="col-lg-2 col-lg-offset-4">
                                <input type="text" class="form-control" name="dinner1" value="${dayReport.dinner1}" maxlength="18" placeholder="1" required="true" autofocus/>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" class="form-control" name="dinner2" value="${dayReport.dinner2}" maxlength="18" placeholder="2"/>
                            </div>
                        </div>
                        <br>
                        <div class="row text-center">
                            <div class="col-lg-2 col-lg-offset-4">
                                <input type="text" class="form-control" name="dinner3" value="${dayReport.dinner3}" maxlength="18" placeholder="3"/>
                            </div>
                            <div class="col-lg-2">
                                <input type="text" class="form-control" name="dinner4" value="${dayReport.dinner4}" maxlength="18" placeholder="4"/>
                            </div>
                        </div>
                        <br>
                        <div class="row text-center">
                            <div class="col-lg-2 col-lg-offset-4">
                                <label>Data</label>
                                <input type="date" class="form-control" name="date" value="${dayReport.date}" required="true"/>
                            </div>
                            <div class="col-lg-2">
                                <label>Numer raportu</label>
                                <input type="number" class="form-control" name="reportNumber" min="1" max="365" value="${dayReport.reportNumber}" required="true"/>
                            </div>
                        </div>
                        <br>
                        <div class="row text-center">
                            <div class="col-lg-2 col-md-offset-2">
                                <label>Podstawówka</label>
                                <input type="number" class="form-control" name="podstawowa" min="0" max="1000" value="${dayReport.podstawowa}" required="true"/>
                            </div>
                            <div class="col-lg-2">
                                <label>Nauczyciele</label>
                                <input type="number" class="form-control" name="nauczyciele" min="0" max="200" value="${dayReport.nauczyciele}"/>
                            </div>
                            <div class="col-lg-2">
                                <label>Przedszkole</label>
                                <input type="number" class="form-control" name="przedszkole" min="0" max="100" value="${dayReport.przedszkole}"/>
                            </div>
                            <div class="col-lg-2">
                                <label>Zerówka</label>
                                <input type="number" class="form-control" name="zerowka" min="0" max="100" value="${dayReport.zerowka}"/>
                            </div>
                        </div>
                        <br>
                        <div class="row text-center">
                            <div class="col-lg-2 col-md-offset-3">
                                <label>Podstawówka</label>
                                <input type="text" class="form-control" name="podzialPodstawowa" value="${dayReport.podzialPodstawowa}" placeholder="GOPS + PŁATNE"/>
                            </div>                         
                            <div class="col-lg-2">
                                <label>Przedszkole</label>
                                <input type="text" class="form-control" name="podzialPrzedszkole" value="${dayReport.podzialPrzedszkole}" placeholder="GOPS + PŁATNE"/>
                            </div>
                            <div class="col-lg-2">
                                <label>Zerówka</label>
                                <input type="text" class="form-control" name="podzialZerowka" value="${dayReport.podzialZerowka}" placeholder="GOPS + PŁATNE"/>
                            </div>
                        </div>
                        <br>
                        <div class="page-header text-center">
                            <h1>Wprowadź produkty</h1>
                        </div>
                        <div class="row text-center">
                            <div class="col-lg-1">
                                <label>Lp.</label>
                            </div>
                            <div class="col-lg-4">
                                <label>Nazwa produktu</label>
                            </div>
                            <div class="col-lg-2">
                                <label>Cena jednostkowa</label>
                            </div>
                            <div class="col-lg-2">
                                <label>Ilość</label>
                            </div>
                            <div class="col-lg-2">
                                <label>Wartość</label>
                            </div>
                            <div class="col-lg-1">
                                <label>Numer</label>
                            </div>
                        </div>

                        <c:forEach begin="1" end="25" varStatus="loop">
                            <div class="row text-center">
                                <div class="col-lg-1">
                                    <label>${loop.index}</label>
                                </div>
                                <div class="col-lg-4">
                                    <select class="form-control" name="product" id="${loop.index}" value="${dayReport.product}" onchange="return setProductNumber(this.id);">
                                        <option>...</option>
                                        <c:forEach var="product" items="${productlist}">
                                            <option>${product.name}</option>
                                         </c:forEach>
                                    </select>
                                </div>
                                <div class="col-lg-2">
                                    <input type="number" class="form-control" name="unitprice" id="unitpriceID${loop.index}" min="0" max="10000" step="0.01" pattern="\d+(\.\d{1,2})?" placeholder="0.00" value="${dayReport.unitprice}" onchange="return calculateValue(${loop.index});"/>
                                </div>
                                <div class="col-lg-2">
                                    <input type="number" class="form-control" name="amount" id="amountID${loop.index}" min="0" max="10000" step="0.01" pattern="\d+(\.\d{1,2})?" placeholder="0.00" value="${dayReport.amount}" onchange="return calculateValue(${loop.index});"/>
                                </div>
                                <div class="col-lg-2">
                                    <input type="number" class="form-control" name="positionValue" id="positionValueID${loop.index}" min="0" max="10000" step="0.01" pattern="\d+(\.\d{1,2})?" placeholder="0.00" value="${dayReport.positionValue}" tabindex="-1"/>
                                </div>
                                <div class="col-lg-1">
                                    <input type="text" class="form-control" name="number" id="numberID${loop.index}" readonly="readonly" value="${dayReport.number}" tabindex="-1"/>
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
                                <div class="row">
                                        <div class="alert alert-success">
                                          <strong>Sukces!</strong> Utworzono/zmodyfikowano raport dzienny!
                                        </div>
                                </div>
                        </div>
                    </c:when>
                    <c:when test="${message == '2'}">
                        <div class="container">
                            <div class="row">
                                    <div class="alert alert-danger">
                                      <strong>Ups!</strong> Pominięto wiersz lub wiersz nie został w pełni uzupełniony!
                                    </div>
                            </div>
                            <button type="button" class="btn btn-danger" name="back" onclick="history.back()">Powtórz</button>
                        </div>
                    </c:when>
                    <c:when test="${message == '0'}">
                        <div class="container">
                                <div class="row">
                                        <div class="alert alert-danger">
                                          <strong>Ups!</strong> Prawdopodobnie próbujesz nadpisać plik, który jest otwarty!
                                        </div>
                                </div>
                        </div>
                    </c:when>
                </c:choose>
                <br>
                <c:choose>
                    <c:when test="${message2 == '3'}">
                        <div class="container">
                            <div class="row">
                                    <div class="alert alert-danger">
                                      <strong>Ups!</strong> Skończyły się wiersze do wprowadzania w raporcie miesięcznym!
                                    </div>
                            </div>
                            <button type="button" class="btn btn-danger" name="back" onclick="history.back()">Powtórz</button>
                        </div>
                    </c:when>
                    <c:when test="${message2 == '1'}">
                        <div class="container">
                            <form method="GET" action="/">
                                <div class="row">
                                        <div class="alert alert-success">
                                          <strong>Sukces!</strong> Utworzono/zmodyfikowano raport miesięczny!
                                        </div>
                                </div>
                                <div class="row">
                                    <input type="submit" class="btn btn-view-product btn-success" value="Powrót do menu"/>
                                    <button type="button" class="btn btn-view-product btn-danger" name="back" onclick="history.back()">Popraw</button>
                                </div>
                            </form>
                        </div>
                    </c:when>
                    <c:when test="${message2 == '0'}">
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