<#macro page>
<!DOCTYPE HTML>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Verum Application</title> 
    <link rel="stylesheet" href="../../static/style.css">
    
    <!--
    Сообщаем браузеру, что он должен считывать плотность пикселей на экране устройства
    и делать крупнее шрифт и элементы интерфейса для мобилных устрйоств планшетов и т.д.
    и нормально отображать на устройствах с небольшой плотностью, например ноутбуки и ПК
    -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <!-- Подключаем стили Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src='https://www.google.com/recaptcha/api.js'></script>
</head>

<body>

<#include "navbar.ftl"/>
<div class="container mt-5">
<#nested>
</div>
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <!-- Подключает jQuery -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <!-- Указывает зависимость popper -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <!-- Добавляет разные jQuery плагины, обеспечивающие функциональность -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>
</#macro>