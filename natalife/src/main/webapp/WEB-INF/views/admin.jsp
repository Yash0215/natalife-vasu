<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		.admin-link: hover{
			cursor:pointer;
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
    
      <nav class="navbar navbar-expand-md navbar-dark bg-dark navbar-other">
        <div class="container">
          <a class="navbar-brand-other" href="../../index">Natalife</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
        </div>
      </nav>
    
    <!-- END header -->
 


      <div class="clearfix mb-5 pb-5">
        <div class="container-fluid">
          <div class="row" data-aos="fade">
            <div class="col-md-12 text-center heading-wrap-other">
              <h2>ADMIN PANEL</h2>
            </div>
          </div>
        </div>
      </div>

    <div class="container" style="text-align: center">	
		
		<section class="section bg-light py-5  bottom-slant-gray">
		  <div class="container">
			<div class="row">
			  <div class="col-md-6 mb-4 mb-lg-0 col-lg-3 text-left service-block" data-aos="fade-up" data-aos-delay="">
				<span class="wrap-icon"><span class="flaticon-dinner d-block mb-4"></span></span>
				<h3 class="mb-2 text-primary"><a href="insertProduct" class="admin-link">Insert New Products</a></h3>
				<p>Insert product details to be shown to customers i.e. image, name, desciption, price rtc.</p>
			  </div>
			  <div class="col-md-6 mb-4 mb-lg-0 col-lg-3 text-left service-block" data-aos="fade-up" data-aos-delay="100">
				<span class="wrap-icon"><span class="flaticon-dinner d-block mb-4"></span></span>
				<h3 class="mb-2 text-primary"><a href="insertRecipe" class="admin-link">Insert New Recipes<a></h3>
				<p>Insert recipes details for products i.e. image, name, desciption, price rtc.</p>
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