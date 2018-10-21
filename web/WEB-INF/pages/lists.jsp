<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
HEAD!!!
</head>
<body>

<p>
<ul>
    <c:forEach items="${requestScope.listF}" var="listF">
        <li>
            <a>${listF.id}</a>
            <a>${listF.name}</a>
        </li>
    </c:forEach>
</ul>
</p>

</body>
</html>
