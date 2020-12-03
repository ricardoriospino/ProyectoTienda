<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

    <style>
      html,body {
        height: 100%;
        margin: 10;
        padding: 10;
        font-family: sans-serif;
      }
      .negro {
        font-family: sans-serif;
        font-size: 15px;
        font-weight: bold;
        
      }  
    </style>
</head>
  <body>
 
	    <h1>BOLETA DE VENTA</h1>
	    <P class="negro">FECHA Y HORA: </P>
	    <p class="negro">ID BOLETA:</p>
	    <P class="negro">ID EMPLEADO:</P>
	    <p class="negro">CAJERO:</p>
	    <p class="negro">CLIENTE:</p>
	    <P class="negro">DNI O RUC:</P>
	  <table width="100%"  cellspacing="0" cellpadding="0">
	      <thead>
		      <tr>
		          <th>ID</th>
		          <th>PRODUCTO</th>
		          <th>PRECIO</th>
		          <th>CANTIDAD</th>
		          <th>SUBTOTAL</th>    
		      </tr>
	      </thead>
      	<tr>
	          <td >7</td>
	          <td >Tijeras</td>
	          <td >7</td>
	          <td >3</td>
	          <td >21</td>    
     	</tr>  
      	<tr>
          <td class="negro" colspan="4" >PRECIO TOTAL</td>
          <br>
          <td class="espacio">21</td>
      </tr>   
  	</table>
 </body>
</html>