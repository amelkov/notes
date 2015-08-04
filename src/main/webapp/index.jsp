<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Last notes</title>
    <jsp:include page="/Notes?command=getnotes"/>
</head>
<body>

<h2>Hello, ${acptLogin}!</h2>

<form action="Notes" method="post">
    <INPUT type="hidden" name="command" value="logout">
    <input type="submit" value="Log Out">
</form>

<table border="1">
    <tr>
        <th>Note</th>
        <th>Date</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${listNote}" var="listNotes">

        <tr>
            <td height="50" width="300" id="${listNotes.id}">${listNotes.text}</td>
            <td>${listNotes.dateCreate}</td>
            <td>
                <form action="Notes?command=editing&id=${listNotes.id}" method="post">
                    <INPUT type="hidden" name="command" value="editing">
                    <input type="submit" value="Edit">
                </form>
                <form action="Notes?command=deletenote&id=${listNotes.id}" method="post">
                    <INPUT type="hidden" name="command" value="deletenote">
                    <input type="submit" value="Delete">
                </form>
        </tr>
    </c:forEach>
</table>

<form action="Notes" method="post">
    <INPUT type="hidden" name="idEdit" value="${editedNote.id}">
    <c:choose>
        <c:when test="${isAdding}">
            <p><b>Enter note:</b></p>
            <INPUT type="hidden" name="command" value="addnote">
            <p><textarea rows="10" cols="60" name="note" required="true" id="note"></textarea></p>
            <p><input type="submit" value="Add"></p>
        </c:when>
        <c:otherwise>
            <p><b>Edit note:</b></p>
            <INPUT type="hidden" name="command" value="editnote">
            <p><textarea rows="10" cols="60" name="note" required="true" id="note">${editedNote.text}</textarea></p>
            <p><input type="submit" value="Edit"></p>
        </c:otherwise>
    </c:choose>
</form>

<a href="allnotes.jsp">All your notes</a>
</body>
</html>
