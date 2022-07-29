<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: yoh
  Date: 08/06/2022
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="saisieNotes.titre"/></title>
</head>
<body>

    <h1><s:text name="login.titre"/></h1>


    <h2> <s:text name="saisieNotes.saisie"/> </h2>
    <s:form action="saisirNote">
        <s:textfield name="numeroEtudiant" key="saisieNotes.numeroEtudiant"/>
        <s:textfield name="note" key="saisieNotes.note"/>
        <s:submit name="valider"/>
    </s:form>

    <h2><s:text name="saisieNotes.liste"/></h2>
    <ul>
    <s:iterator value="%{etudiantsModule}" var="x">
        <li><s:property value="#x.numeroEtudiant"/> : <s:property value="#x.nomEtudiant"/> <s:property value="#x.prenomEtudiant"/>
        </li>
    </s:iterator>
    </ul>

    <h2><s:text name="saisieNotes.notessaisies"/></h2>
    <ul>
    <s:iterator value="%{etudiantsNotes}" var="x">
        <li><s:property value="#x.etudiant.numeroEtudiant"/> (<s:property value="#x.etudiant.nomEtudiant"/> <s:property value="#x.etudiant.prenomEtudiant"/>) :
            <s:property value="#x.note"/>
        </li>
    </s:iterator>
    </ul>

    <h2><s:a action="deconnexion"><s:text name="deconnexion"/></s:a> </h2>

    </body>
</html>
