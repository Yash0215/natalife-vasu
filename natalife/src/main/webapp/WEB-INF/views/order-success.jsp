<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<a href="../../index"><img src="/resources/img/logo.png" ></a>
<h1 style="text-align:center;">Thank you for placig your order at Natalife!</h1>
<h1 style="text-align:center;font-size:25px;">Your Order ID is <% out.print((String) request.getAttribute("orderid")); %> and order will be delivered within 30 minutes.</h1>
<br>

<style>
img {
  margin: 0 auto;
  display: block;
  margin-top: 10%;
}
</style>
