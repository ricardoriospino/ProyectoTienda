<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" 
integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" 
crossorigin="anonymous">

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Montserrat:300,400,500,700" rel="stylesheet">

<!-- librerias -->
<link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="lib/animate/animate.min.css" rel="stylesheet">
<link href="lib/ionicons/css/ionicons.min.css" rel="stylesheet">
<link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
<link href="css/bootnavbar.css" rel="stylesheet">



<style >
#login {
  margin: 0;
  padding: 0;
  background-color: #17a2b8;
  height: 100vh;
}
#login .container #login-row #login-column #login-box {
  margin-top: 120px;
  max-width: 600px;
  height: 320px;
  border: 1px solid #9C9C9C;
  background-color: #EAEAEA;
}
#login .container #login-row #login-column #login-box #login-form {
  padding: 20px;
}
#login .container #login-row #login-column #login-box #login-form #register-link {
  margin-top: -85px;
}
#header img {
  width: 80px;
}
.btn-tienda {
  background-color: #DBDBDC ;
  color: black;
}
.btn-tienda:hover {
  background-color: #DBDBDC ;
  
}

.navbar-nav li:hover > ul.dropdown-menu {
    display: block;
}

.dropdown-submenu {
    position: relative;
}

.dropdown-submenu > .dropdown-menu {
        top: 0;
        left: 100%;
        margin-top: -6px;
 }
 
 
 
/* ----------------ini: footer----------------------- */
#footer {
  background: linear-gradient(90deg, #1E8AF9, #037AF3 40%, #1e5472);
}
#footer a {
  color: white;
}

/* ---------------fin: footer----------------------- */

/* ----------------ini: main----------------------- */
#main .carousel-inner img {
  max-height: 70vh;
  object-fit: cover;
  filter: grayscale(40%);
}
/* ----------------fin: main----------------------- */

/* ----------------ini: carousel----------------------- */

#carousel {
  position: relative;
}
#carousel .overlay {
  z-index: 1;
  position: relative;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
 justify-content: center;
 aling-items: center;
}
#place-time img {
  max-width: 100%;
}
#equipo img {
  height: 100px;
  object-fit: cover;
}

/* ----------------fin: carousel----------------------- */

/* ----------------ini: testimonios----------------------- */
#testimonials {
  padding: 60px 0;
  box-shadow: inset 0px 0px 12px 0px rgba(0, 0, 0, 0.1);
}

#testimonials .section-header {
  margin-bottom: 40px;
}

#testimonials .testimonial-item .testimonial-img {
  width: 120px;
  border-radius: 50%;
  border: 4px solid #fff;
  float: left;
}

#testimonials .testimonial-item h3 {
  font-size: 20px;
  font-weight: bold;
  margin: 10px 0 5px 0;
  color: #111;
  margin-left: 140px;
}

#testimonials .testimonial-item h4 {
  font-size: 14px;
  color: #999;
  margin: 0 0 15px 0;
  margin-left: 140px;
}

#testimonials .testimonial-item p {
  font-style: italic;
  margin: 0 0 15px 140px;
}

#testimonials .owl-nav,
 #testimonials .owl-dots {
  margin-top: 5px;
  text-align: center;
}

#testimonials .owl-dot {
  display: inline-block;
  margin: 0 5px;
  width: 12px;
  height: 12px;
  border-radius: 50%;
  background-color: #ddd;
}

#testimonials .owl-dot.active {
  background-color: #007bff;
}
/* ----------------fin: testimonios----------------------- */
/* ----------------ini: contacto----------------------- */

#contact .form button[type="submit"] {
  background: #007bff;
  border: 0;
  border-radius: 20px;
  padding: 8px 30px;
  color: #fff;
  transition: 0.3s;
}

#contact .form button[type="submit"]:hover {
  background: #0067d5;
  cursor: pointer;
}

/* ----------------fin: contacto----------------------- */
/* ----------------ini: beneficios----------------------- */

#why-us {
  padding: 60px 0;
  background: white;
}

#why-us .section-header h3,
#why-us .section-header p {
  color: black;
}

#why-us .card {
  background: #1E8AF9;
  border-color: #00458f;
  border-radius: 10px;
  margin: 0 10px;
  padding: 15px 0;
  text-align: center;
  color: #fff;
  transition: 0.3s ease-in-out;
  height: 100%;
}

#why-us .card:hover {
  background: #003b7a;
  border-color: #003b7a;
}

#why-us .card i {
  font-size: 48px;
  padding-top: 15px;
  color: #bfddfe;
}

#why-us .card h5 {
  font-size: 22px;
  font-weight: 600;
}

#why-us .card p {
  font-size: 15px;
  color: #d8eafe;
}

#why-us .card .readmore {
  color: #fff;
  font-weight: 600;
  display: inline-block;
  transition: 0.3s ease-in-out;
  border-bottom: #00458f solid 2px;
}

#why-us .card .readmore:hover {
  border-bottom: #fff solid 2px;
}

 #why-us .counters {
  padding-top: 40px;
}

#why-us .counters span {
  font-family: "Montserrat", sans-serif;
  font-weight: bold;
  font-size: 48px;
  display: block;
  color: black;
}

#why-us .counters p {
  padding: 0;
  margin: 0 0 20px 0;
  font-family: "Montserrat", sans-serif;
  font-size: 14px;
  color: black;
}
/* ----------------fin: beneficios----------------------- */

/* ----------------ini: boton lista----------------------- */

 .btn-success  {
  background: #007bff;
  border: 0;
  border-radius: 20px;
  padding: 8px 30px;
  color: #fff;
  transition: 0.3s;
}
.btn-dataTable {
background: #007bff;
  border: 0;  
  padding: 1px 5px;
  color: #fff;
  transition: 0.3s;

}

.btn-listar{
background-color: #007bff ;
 color: white;
 justify-content: center;
 align-items:center;
}

#botonAgregar :hover {
  background: #0067d5;
  cursor: pointer;
}


/* ----------------fin: boton lista----------------------- */


/* -----centrar  data table ------------*/

}

</style>



 