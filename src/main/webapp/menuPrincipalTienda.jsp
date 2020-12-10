<%@page import="rios.demo.bean.EmpleadoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

	<!--ini :css -->
	<jsp:include page="css.jsp"/>
	<!--fin :css -->


<meta charset="ISO-8859-1">
<title>Menu Tienda</title>
</head>
<body data-spy="scroll" data-target="#navbar"  data-offset="57">

<!-- ini:validamos session -->
<%
boolean valido = false;
EmpleadoBean empleado = (EmpleadoBean)session.getAttribute("usuarioSession");

if(empleado == null)valido = false;
else {valido = true;}

if(!valido){ // si valido es false 
	response.sendRedirect("login.jsp");
}
%>
<!-- fin:validamos session -->

	<!--ini :Menu -->
	<jsp:include page="menuTienda.jsp"/>
	<!--fin :Menu -->  	

    <br><br><br>
    <!--ini: inicio -->
	<main id="main">
      <div
        id="carousel"
        class="carousel slide"
        data-ride="carousel"
        data-pause="false"
      >
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img
              class="d-block w-100"
              src="img/img1.jpg"
              alt="First slide"
             
            />
          </div>
          <div class="carousel-item">
            <img
              class="d-block w-100"
              src="img/img2.jpg"
              alt="Second slide"
            />
          </div>
          <div class="carousel-item">
            <img
              class="d-block w-100"
              src="img/img3.jpg"
              alt="Third slide"
            />
          </div>
          <br><br><br>
          <div class="overlay"   >
            <div class="container h-100">
              <div class="row align-items-center h-100">
                <div class="col-md-6 offset-md-6 text-center text-md-right">
                  <h1>Lo Mejor Para Ti</h1>
                  <p class="d-none d-md-block">
                   Vivamus in convallis ligula. Pellentesque in justo pellentesque leo bibendum maximus. 
                   Nulla turpis est, varius et placerat scelerisque, vulputate a est. Mauris blandit ex varius lacus ornare.
                   Vivamus in convallis ligula. Pellentesque in justo pellentesque leo bibendum maximus. Nulla turpis est, varius 
                   et placerat scelerisque, vulputate a est. Mauris blandit ex varius lacus ornare.
                  </p>
                  
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
    <!--fin: inicio -->
    
    <!-- ini:valores -->
    <section id="why-us" class="wow fadeIn">
      <div class="container">
      
        <header class="section-header text-center mt-4 mb-4">
          <h3>¿Porque confiar en nosotros?</h3>
          <p>Laudem latine persequeris id sed, ex fabulas delectus quo. No vel partiendo abhorreant vituperatoribus.</p>
        </header>

        <div class="row row-eq-height justify-content-center">

          <div class="col-lg-4 mb-4">
            <div class="card wow bounceInUp">
                <i class="fa fa-child"></i>
              <div class="card-body">
                <h5 class="card-title">Responsabilidad Social</h5>
                <p class="card-text">Deleniti optio et nisi dolorem debitis. Aliquam nobis est temporibus sunt ab inventore officiis aut voluptatibus.</p>
                <a href="#" class="readmore">Conoce más</a>
              </div>
            </div>
          </div>

          <div class="col-lg-4 mb-4">
            <div class="card wow bounceInUp">
                <i class="fa fa-heart"></i>
              <div class="card-body">
                <h5 class="card-title">Valores</h5>
                <p class="card-text">Voluptates nihil et quis omnis et eaque omnis sint aut. Ducimus dolorum aspernatur.</p>
                <a href="#" class="readmore">Conoce más </a>
              </div>
            </div>
          </div>

          <div class="col-lg-4 mb-4">
            <div class="card wow bounceInUp">
                <i class="fa fa-home"></i>
              <div class="card-body">
                <h5 class="card-title">Comunidad</h5>
                <p class="card-text">Autem quod nesciunt eos ea aut amet laboriosam ab. Eos quis porro in non nemo ex. </p>
                <a href="#" class="readmore">Conoce más </a>
              </div>
            </div>
          </div>

        </div>

        <div class="row counters">

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="clientes">1000000</span>
            <p>Clientes</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="Tiendas">100</span>
            <p>Tiendas</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="colaboradores">1,364</span>
            <p>colaboradores</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="ciudades">18</span>
            <p>paises</p>
          </div>
        </div>
      </div>
    </section>
    <!-- fin:valores -->
    
    <!-- ini: equipo -->
    <section id="equipo" class="mt-4 mb-4">
      <div class="container">
        <div class="row">
          <div class="col text-center text-uppercase">
            <small>Conoce a nuestro</small>
            <h2>Equipo</h2>
          </div>
        </div>
        
        <div class="row">
          <div class="col-md-6 col-lg-3 mb-4">
            <div class="card">
              <img class="rounded" src="img/p1.png" alt="rodolfo"/>
              <div class="card-body">
                <h5 class="card-title mb-0">Rodolfo Caraz</h5>
                <div class="badges mb-2">
                  <span class="badge badge-secondary">Gerente</span>
                </div>
                <p class="card-text">
                  Vivamus in convallis ligula. Pellentesque in justo
                  pellentesque leo bibendum maximus. Nulla turpis est, varius et
                  placerat scelerisque, vulputate a est. Mauris blandit ex
                  varius lacus ornare.
                </p>
              </div>
            </div>
          </div>
          
          <div class="col-md-6 col-lg-3 mb-4">
            <div class="card">
              <img class="rounded" src="img/p2.png" alt="foto de steven"/>
              <div class="card-body">
                <h5 class="card-title mb-0">Sofia</h5>
                <div class="badges mb-2">
                  <span class="badge badge-danger">Marketing</span>
                </div>
	                <p class="card-text">
	                  Vivamus in convallis ligula. Pellentesque in justo
	                  pellentesque leo bibendum maximus. Nulla turpis est, varius et
	                  placerat scelerisque, vulputate a est. Mauris blandit ex
	                  varius lacus ornare.
	                </p>
              </div>
            </div>
          </div>
          
          <div class="col-md-6 col-lg-3 mb-4">
            <div class="card">
              <img class="rounded" src="img/p3.png" alt="foto de juan"/>
              <div class="card-body">
                <h5 class="card-title mb-0">Juan Carlos</h5>
                <div class="badges mb-2">
                  <span class="badge badge-success">Vendedor</span>
                </div>
	                <p class="card-text">
	                  Vivamus in convallis ligula. Pellentesque in justo
	                  pellentesque leo bibendum maximus. Nulla turpis est, varius et
	                  placerat scelerisque, vulputate a est. Mauris blandit ex
	                  varius lacus ornare.
	                </p>
              </div>
            </div>
          </div>
          
          <div class="col-md-6 col-lg-3 mb-4">
            <div class="card">
              <img class="rounded" src="img/p4.png" alt="foto de Christian"/>
              <div class="card-body">
                <h5 class="card-title mb-0">Cristina</h5>
                <div class="badges mb-2">
                  <span class="badge badge-warning"> Operaciones</span>
                </div>
                <p class="card-text">
                  Vivamus in convallis ligula. Pellentesque in justo
                  pellentesque leo bibendum maximus. Nulla turpis est, varius et
                  placerat scelerisque, vulputate a est. Mauris blandit ex
                  varius lacus ornare.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- fin: equipo -->
    <!-- ini: testimonio -->
    <section id="testimonials" class="section-bg">
      <div class="container">

        <header class="section-header">
          <h3>Testimonios</h3>
        </header>

        <div class="row justify-content-center">
          <div class="col-lg-8">

            <div class="owl-carousel testimonials-carousel wow fadeInUp">
    
              <div class="testimonial-item">
                <img src="img/cvander.jpeg" class="testimonial-img" alt="">
                <h3>Luis</h3>
                <h4>Ceo &amp; Founder</h4>
                <p>
                  Proin iaculis purus consequat sem cure digni ssim donec porttitora entum suscipit rhoncus. Accusantium quam, ultricies eget id, aliquam eget nibh et. Maecen aliquam, risus at semper.
                </p>
              </div>
    
              <div class="testimonial-item">
                <img src="img/freddy.jpeg" class="testimonial-img" alt="">
                <h3>Marco </h3>
                <h4>Designer</h4>
                <p>
                  Export tempor illum tamen malis malis eram quae irure esse labore quem cillum quid cillum eram malis quorum velit fore eram velit sunt aliqua noster fugiat irure amet legam anim culpa.
                </p>
              </div>
    
              <div class="testimonial-item">
                <img src="img/leonidas.jpeg" class="testimonial-img" alt="">
                <h3>Jena </h3>
                <h4>Store Owner</h4>
                <p>
                  Enim nisi quem export duis labore cillum quae magna enim sint quorum nulla quem veniam duis minim tempor labore quem eram duis noster aute amet eram fore quis sint minim.
                </p>
              </div>
    
              <div class="testimonial-item">
                <img src="img/sacha.jpg" class="testimonial-img" alt="">
                <h3>Marco</h3>
                <h4>Freelancer</h4>
                <p>
                  Fugiat enim eram quae cillum dolore dolor amet nulla culpa multos export minim fugiat minim velit minim dolor enim duis veniam ipsum anim magna sunt elit fore quem dolore labore illum veniam.
                </p>
              </div>
    
              <div class="testimonial-item">
                <img src="img/leonidas2.jpeg" class="testimonial-img" alt="">
                <h3>Juan</h3>
                <h4>Entrepreneur</h4>
                <p>
                  Quis quorum aliqua sint quem legam fore sunt eram irure aliqua veniam tempor noster veniam enim culpa labore duis sunt culpa nulla illum cillum fugiat legam esse veniam culpa fore nisi cillum quid.
                </p>
              </div>

            </div>

          </div>
        </div>
      </div>
    </section>
    <!-- fin testimonio -->
    
    <!-- ini: contacto -->
    <section id="contact" class="mt-4 mb-4">
      <div class="container-fluid">
        <div class="section-header text-center text-uppercase">
          <h3>Conocenos</h3>
        </div><br>
        <div class="row wow fadeInUp">

          <div class="col-lg-6">
            <div class="map mb-4 mb-lg-0 offset-md-2">
              <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d31218.06163519725!2d-7
            6.92866826278926!3d-12.025771344783092!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0
            x9105c3fa3f3aadf7%3A0x7535b93fe293bb60!2sLos%20Diamantes%2C%20Ate%2015491!5e0!3m2!1ses-419!
            2spe!4v1589644425286!5m2!1ses-419!2spe" width="470" height="400" frameborder="0" style="border:0;" 
            allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
            </div>
          </div>

          <div class="col-lg-6 ">
            <div class="row">
              <div class="col-md-5 info">
                <i class="ion-ios-location-outline"></i>
                <p>Ate Vitarte Lima Perú</p>
              </div>
              <div class="col-md-4 info">
                <i class="ion-ios-email-outline"></i>
                <p>info@example.com</p>
              </div>
              <div class="col-md-3 info">
                <i class="ion-ios-telephone-outline"></i>
                <p>+51 99999</p>
              </div>
           </div>

            <div class="form">
              <div id="sendmessage">Gracias por contactarnos!</div>
              <div id="errormessage"></div>
              <form action="" method="post" role="form" class="contactForm">
                <div class="form-row">
                  <div class="form-group col-lg-6">
                    <input type="text" name="name" class="form-control" id="name" placeholder="Nombre" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                    <div class="validation"></div>
                  </div>
                  <div class="form-group col-lg-6">
                    <input type="email" class="form-control" name="email" id="email" placeholder="Email" data-rule="email" data-msg="Please enter a valid email" />
                    <div class="validation"></div>
                  </div>
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" name="subject" id="subject" placeholder="Asunto" data-rule="minlen:4" data-msg="Please enter at least 8 chars of subject" />
                  <div class="validation"></div>
                </div>
                <div class="form-group">
                  <textarea class="form-control" name="message" rows="5" data-rule="required" data-msg="Please write something for us" placeholder="Mensaje"></textarea>
                  <div class="validation"></div>
                </div>
                <div class="text-center"><button type="submit" title="Send Message">Enviar</button></div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- fin:contacto -->
    
	<!--ini:footer -->
	<jsp:include page="importFooter.jsp"/>	
	<!--fin:footer -->
	
	<!--ini:footer -->
	<jsp:include page="menuFooter.jsp"/>	
	<!--fin:footer -->

</body>
</html>