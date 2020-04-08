<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List,com.group.project.natalife.model.User,com.group.project.natalife.model.Product,com.group.project.natalife.model.Cart,java.lang.*" %>
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
	
	</style>
	
  </head>
  <body>
    
    <header role="banner">
      <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container">
          <a class="navbar-brand" href="../../index">Natalife</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarsExample05">
            <ul class="navbar-nav ml-auto pl-lg-5 pl-0">
              <li class="nav-item">
                <a class="nav-link active" href="#">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="products">Products</a>
              </li>
			<%
				User user = (User) session.getAttribute("user");
				if(user != null && user.isIsadmin()){
			%>
			  <li class="nav-item">
                <a class="nav-link" href="admin">Admin Panel</a>
              </li>
			<%
				}
			%>
              <li class="nav-item">
                <a class="nav-link" href="blogs">Blogs</a>
              </li>
            </ul>

            <ul class="navbar-nav ml-auto" style ="margin-right:50px;">
				<ul class="navbar-nav ml-auto" style ="margin-right:50px;">
              <li class="nav-item cta-btn-other-cart" >
				<a class="nav-link" href="cart" style="width:130px;height:42px;">CART 
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
              <li class="nav-item cta-btn">
				<% 
					if(user == null) {%>
					<a class="nav-link" href="login">Login / Sign Up</a>
				<% } else { %>
					<a class="nav-link" href="#">Hi ${user.fname} ${user.lname}</a>
					<a class="nav-link nav-link-logout" href="logout" color = "red">Logout</a>
				<% } %>
              </li>
            </ul>
            
          </div>
        </div>
      </nav>
    </header>
    <!-- END header -->
    
    <div class="slider-wrap">
      <section class="home-slider owl-carousel">


        <div class="slider-item" style="background-image: url('/resources/img/hero_1.jpg');">
          
          <div class="container">
            <div class="row slider-text align-items-center justify-content-center">
              <div class="col-md-8 text-center col-sm-12 ">
                <h1 data-aos="fade-up">Eat Healthy, Stay Healthy</h1>
                <p class="mb-5" data-aos="fade-up" data-aos-delay="100">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sapiente et sed quasi.</p>
                <p data-aos="fade-up" data-aos-delay="200"><a href="products" class="btn btn-white btn-outline-white">Get Started</a></p>
              </div>
            </div>
          </div>

        </div>

        <div class="slider-item" style="background-image: url('/resources/img/hero_2.jpg');">
          <div class="container">
            <div class="row slider-text align-items-center justify-content-center">
              <div class="col-md-8 text-center col-sm-12 ">
                <h1 data-aos="fade-up">Find Healthy Recipes</h1>
                <p class="mb-5" data-aos="fade-up" data-aos-delay="100">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sapiente et sed quasi.</p>
                <p data-aos="fade-up" data-aos-delay="200"><a href="products" class="btn btn-white btn-outline-white">Get Started</a></p>
              </div>
            </div>
          </div>
          
        </div>

      </section>
    <!-- END slider -->
    </div> 
    

    <section class="section bg-light py-5  bottom-slant-gray">
      <div class="container">
        <div class="row">
          <div class="col-md-6 mb-4 mb-lg-0 col-lg-3 text-left service-block" data-aos="fade-up" data-aos-delay="">
            <span class="wrap-icon"><span class="flaticon-dinner d-block mb-4"></span></span>
            <h3 class="mb-2 text-primary">Healthy Food</h3>
            <p>A small river named Duden flows by their place and supplies it with the necessary regelialia.</p>
          </div>
          <div class="col-md-6 mb-4 mb-lg-0 col-lg-3 text-left service-block" data-aos="fade-up" data-aos-delay="100">
            <span class="wrap-icon"><span class="flaticon-dinner d-block mb-4"></span></span>
            <h3 class="mb-2 text-primary">Delecious Food</h3>
            <p>A small river named Duden flows by their place and supplies it with the necessary regelialia.</p>
          </div>
          <div class="col-md-6 mb-4 mb-lg-0 col-lg-3 text-left service-block" data-aos="fade-up" data-aos-delay="200">
            <span class="wrap-icon"><span class="flaticon-dinner d-block mb-4"></span></span>
            <h3 class="mb-2 text-primary">Fresh Food</h3>
            <p>A small river named Duden flows by their place and supplies it with the necessary regelialia.</p>
          </div>
          <div class="col-md-6 mb-4 mb-lg-0 col-lg-3 text-left service-block" data-aos="fade-up" data-aos-delay="300">
            <span class="wrap-icon"><span class="flaticon-dinner d-block mb-4"></span></span>
            <h3 class="mb-2 text-primary">Organic Food</h3>
            <p>A small river named Duden flows by their place and supplies it with the necessary regelialia.</p>
          </div>
        </div>
      </div>
    </section>

    <section class="section bg-light ">

      <div class="clearfix mb-5 pb-5">
        <div class="container-fluid">
          <div class="row" data-aos="fade">
            <div class="col-md-12 text-center heading-wrap">
              <h2>Our Menu</h2>
            </div>
          </div>
        </div>
      </div>

    <div class="container">
	    <div class="row no-gutters">

        <% 
			List<Product> products = (List<Product>) request.getAttribute("products");
			if(products != null) {
				for(Product product: products){
					
		%>
			<div class="col-md-6">
				<a href="product-details?id=<% out.println(product.getId()); %>">
				<div class="sched d-block d-lg-flex product-block" style="margin:5px;height:370px;">
					<div class="bg-image order-2" <% out.println("style='background-image: url(/resources/img/"+product.getImage()+")'");  %> data-aos="fade"></div>
					<div class="text order-1">
						<h3>  <% out.println(product.getName()); %> </h3>
						<p><% out.println(product.getDescription());%></p>
						<p class="text-primary h3">$<% out.println(product.getPrice()); %></p>
					</div>
				</div>
				</a> 
			</div>	
		<%
				}
			} else {
				out.println("Sold Out");
			}
		%>
		</div>
	</div>
</section> <!-- .section -->


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