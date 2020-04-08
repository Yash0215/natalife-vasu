<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List,com.group.project.natalife.model.Product,com.group.project.natalife.model.Cart,com.group.project.natalife.model.CartEntry,java.util.*" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Natalife: Healthy Food Delivered</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/animate.css">
    <link rel="stylesheet" href="/resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/resources/css/aos.css">

    <link rel="stylesheet" href="/resources/css/magnific-popup.css">


    <link rel="stylesheet" href="/resources/fonts/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="/resources/fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/fonts/flaticon/font/flaticon.css">

    <!-- Theme Style -->
    <link rel="stylesheet" href="/resources/css/style.css">
	    <link rel="stylesheet" href="/resources/css/style-cart.css">

	<%@ page isELIgnored="false"%>
	<style>

		.product-block{
			color:#8a8986;
		}

		.product-block:hover{
			background-color:#ff7404;
			cursor:pointer;
			color:white;
		}
		
	.product-block  p:hover{
			color:white;
		}

		.signup-button{
			margin-top:15px;
			width:100%;padding:10px;
			border-radius: 10px;
			border:none;
			background-color:black;
			color:white;
			text-transform: uppercase;
			font-size: .8rem;
			padding: 20px!important;
			line-height: 1;
			font-weight: bold;
		}
		.signup-button:hover{
			background-color:#ff7404;
			cursor:pointer;
		}
	</style>
  </head>
  <body>
    
      <nav class="navbar navbar-expand-md navbar-dark bg-dark navbar-other2">
        <div class="container">
          <a class="navbar-brand-other" href="../../index">Natalife</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

		  <ul class="navbar-nav ml-auto">
			<ul class="navbar-nav ml-auto" style ="margin-right:50px;">
              <li class="nav-item cta-btn-other-cart">
				<a class="nav-link" href="login">CART 
					<% if(session.getAttribute("cart") == null) {%>
						( 0 )
					<% } else { 
						Cart cart = (Cart) session.getAttribute("cart");
						out.print("  ( " + cart.getTotalElements() + " ) ");	
					}
					%>
				</a>
              </li>
            </ul>
            <ul class="navbar-nav ml-auto">
              <li class="nav-item cta-btn-other">
				<% if(session.getAttribute("user") == null || session.getAttribute("user").equals("logout")) {%>
					<a class="nav-link" href="login">Login / Sign Up</a>
				<% } else { %>
					<a class="nav-link" href="#">Hi ${user.fname} ${user.lname}</a>
					<a class="nav-link nav-link-logout" href="logout" color = "red">Logout</a>
				<% } %>
              </li>
            </ul>
          </ul>
		  
        </div>
      </nav>
    
    <!-- END header -->
    <div class="col-md-12 text-center heading-wrap-other" style ="margin-top:20px;">
        <h2>
		<% 
			Cart cart = (Cart) session.getAttribute("cart");
			if(cart == null) {%> 
				CART IS EMPTY 
		<% 	} else { %> 
				CART HAS 
		<% 
				out.print("  ( " + cart.getTotalElements() + " ) ");	
			}
		%> 		ITEMS
		</h2>
    </div>
	<%
		Collection<CartEntry> entries = cart.getEntries().values();
	%>
	<div class="container" >
	    
	<section class="cart-section spad">
		<div class="container">
			<div class="row" >
				<div class="col-lg-8">
					<div class="cart-table" >
						<div class="cart-table-warp" >
						<table>
							<thead>
								<tr>
									<th class="product-th">Product</th>
									<th class="quy-th">Quantity</th>
									<th class="size-th">Price</th>
								</tr>
							</thead>
							<tbody>
								<%
									for(CartEntry entry : entries) {
								%>
								<tr>
									<td class="product-col">
										<img src="/resources/img/<% out.print(entry.getProduct().getImage()); %>" alt="">
										<div class="pc-title">
											<h4><% out.print(entry.getProduct().getName()); %> </h4>
										</div>
									</td>
									<td class="quy-col">
										<div class="quantity">
											<h6 style="color:#8c8c89"><% out.print(entry.getQuantity()); %> x</h6>
                    					</div>
									</td>
									<td class="size-col"><h4>$<% out.print(entry.getProduct().getPrice()); %></h4></td>
									<td class="total-col"><h4>REMOVE</h4></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						</div>
						<div class="total-cost">
							<h6>Total <span>$<% out.print(cart.getTotalAmount()); %></span></h6>
						</div>
					</div>
				</div>
				<div class="col-lg-4 card-right">
					<a href="order" class="site-btn">Proceed to checkout</a>
					<a href="products" class="site-btn sb-dark">Continue shopping</a>
				</div>
			</div>
		</div>
	</section>
		
	</div>


    <footer class="site-footer" role="contentinfo">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md-4 mb-5">
            <h3>About Us</h3>
            <p class="mb-5">Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatibus et dolor blanditiis consequuntur ex voluptates perspiciatis omnis unde minima expedita.</p>
            <ul class="list-unstyled footer-link d-flex footer-social">
              <li><a href="#" class="p-2"><span class="fa fa-twitter"></span></a></li>
              <li><a href="#" class="p-2"><span class="fa fa-facebook"></span></a></li>
              <li><a href="#" class="p-2"><span class="fa fa-linkedin"></span></a></li>
              <li><a href="#" class="p-2"><span class="fa fa-instagram"></span></a></li>
            </ul>

          </div>
          <div class="col-md-5 mb-5">
            <div class="mb-5">
              <h3>Opening Hours</h3>
              <p><strong class="d-block font-weight-normal text-black">Sunday-Thursday</strong> 5AM - 10PM</p>
            </div>
          </div>
          <div class="col-md-3 mb-5">
            <h3>Quick Links</h3>
            <ul class="list-unstyled footer-link">
              <li><a href="#">Top</a></li>
              <li><a href="products">Products</a></li>
              <li><a href="#">Recipes</a></li>
              <% if(session.getAttribute("user") == null) {%>
              <li><a href="login">Login/Sign Up</a></li>
			  <% } %>
            </ul>
          </div>
        </div>
      </div>
    </footer>
    <!-- END footer -->

    <script src="/resources/js/jquery-3.2.1.min.js"></script>
    <script src="/resources/js/popper.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/owl.carousel.min.js"></script>
    <script src="/resources/js/jquery.waypoints.min.js"></script>
    <script src="/resources/js/aos.js"></script>

    <script src="/resources/js/jquery.magnific-popup.min.js"></script>
    <script src="/resources/js/magnific-popup-options.js"></script>
    

    <script src="/resources/js/main.js"></script>
    
  </body>
</html>