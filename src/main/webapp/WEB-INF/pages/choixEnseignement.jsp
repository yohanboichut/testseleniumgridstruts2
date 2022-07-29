<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: yoh
  Date: 08/06/2022
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="choixEnseignement.titre"/></title>
</head>
<body>

<h1><s:text name="choixEnseignement.titre"/></h1>
<s:form action="selectionnerEnseignement">
    <s:select list="%{enseignements}" listKey="libelle" listValue="libelle"
              name="enseignement" key="choixEnseignement"/>
    <s:submit name="valider"/>

</s:form>


<h2><s:a action="deconnexion"><s:text name="deconnexion"/></s:a> </h2>


</body>
</html>
