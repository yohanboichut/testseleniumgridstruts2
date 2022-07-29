<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: yoh
  Date: 08/06/2022
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="login.titre"/></title>
</head>
<body>


<h1><s:text name="login.titre"/></h1>

<s:form action="login">
    <s:textfield key="login.email" name="email"/>
    <s:password key="login.password" name="motDePasse"/>
    <s:submit name="valider"/>
</s:form>

</body>
</html>
