<%@ page import="models.Weather" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Weather</title>
</head>
<body>

Size: ${requestScope.size} <br>
Weather <br>
<c:forEach items="${requestScope.weathers}" var="weather">
    <table>
        <tr>
            <td> ${weather.weatherId} </td>
            <td> ${weather.city} </td>
            <td> ${weather.temperature} </td>
        </tr>
    </table>
</c:forEach>

<br>
// для проверки <br>
Weather <br>
<%
    List<Weather> weathers = (List<Weather>) request.getAttribute("weathers");
    for (Weather weather : weathers) {
%>
<%=weather.getWeatherId()%>, <%=weather.getCity()%>, <%=weather.getTemperature()%> <br>
<%
    }
%>

</body>
</html>