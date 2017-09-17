<%@ page contentType="text/html;charset=UTF-8" %>
<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SQL web service</title>
</head>
<body>
<div>
    <hr>
    <form method="post">
        <label>
            Database schema:<input type="text" name="dbschema" value="${dbschema}">
        </label>
        <label>
            Database table:<input type="text" name="dbtable" value="${dbtable}">
        </label>
        <input type="submit" name="create" value="Get CREATE script">
        <input type="submit" name="select" value="Get SELECT script">
        <input type="submit" name="update" value="Get UPDATE script">
        <hr>
        <table border="1" cellpadding="8" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>Database script:</th>
            </tr>
            </thead>
            <tr>
                <td>${dbscript}</td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>