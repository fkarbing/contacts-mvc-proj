<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="UTF-8"/>
	<base href="${pageContext.request.contextPath}/">
	<title>Contacts - Home</title>
</head>
<body>
<h1>Welcome to Contacts.</h1>

<h4><span style="font-style: italic;"> The time is ${serverTime}.</span></h4>

<h2><a href="resources/contacts/crm.html">Go to Application</a></h2>

<h3>Services</h3>

<h4><a href="consultants">Consultants</a></h4>

<h4><a href="companys">Company</a></h4>

<h4><a href="locations">Locations</a></h4>

<h4><a href="consultantskills">Skills</a></h4>


<!-- localhost:7778/contacts-spring-mvc/resources/contacts/contacts/test/data/locations.json -->


</body>
</html>
