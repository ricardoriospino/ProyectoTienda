  CREATE DATABASE tienda;
 USE tienda;
 
 -- -------------------------------------------------------------
drop table tb_distrito;
CREATE TABLE `tb_distrito` (
  `codigo_distrito` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255),
  PRIMARY KEY (`codigo_distrito`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `tb_distrito` VALUES 
(1,'ate'),
(2,'molina'),
(3,'comas'),
(4,'surquillo'),
(5,'miraflores');

-- ----------------------------------------------------------------

 CREATE TABLE `tb_empleado` (
  `id_empleado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombres`varchar(100)NOT NULL,
  `apellido_ma` varchar(100) NOT NULL,
  `apellido_pa` varchar(200) NOT NULL,
  `fecha_nacimiento` DATE  DEFAULT NULL,
  `direccion_emp` varchar(255) ,
  `telefono` varchar(7),
  `codigo_distrito`int(10) unsigned NOT NULL,
  `correo` varchar (100) NOT NULL,
  `fecha_ingreso` date, 
  `codigo_supervisor` int unsigned ,
  PRIMARY KEY (`id_empleado`),
  UNIQUE KEY `correo`(`correo`),
  FOREIGN KEY (`codigo_supervisor`) REFERENCES tb_empleado (id_empleado),
  FOREIGN KEY (`codigo_distrito`) references tb_distrito (codigo_distrito)
) ;


-- CAMBIE LA COLUMNA DE EMPLEADO Y AGREGUE
ALTER TABLE tb_empleado modify COLUMN codigo_supervisor  INT(10) unsigned;
ALTER TABLE tb_empleado modify COLUMN fecha_nacimiento DATE;
-- agregado
ALTER TABLE tb_empleado ADD COLUMN sub_acargo INT AFTER codigo_supervisor;
UPDATE tb_empleado SET sub_acargo= 1 WHERE id_empleado IN (1,2,3) ;


-- agregando usuario y clave
ALTER TABLE tb_empleado ADD COLUMN usuario VARCHAR(100) AFTER sub_acargo ;
ALTER TABLE tb_empleado ADD COLUMN clave VARCHAR(100) AFTER usuario;

UPDATE tb_empleado SET usuario= '@ronal' WHERE id_empleado IN (1) ;
UPDATE tb_empleado SET usuario= '@joel' WHERE id_empleado IN (2) ;
UPDATE tb_empleado SET usuario= '@juan' WHERE id_empleado IN (3) ;
UPDATE tb_empleado SET usuario= '@ricardo' WHERE id_empleado IN (4) ;
UPDATE tb_empleado SET usuario= '@carlos' WHERE id_empleado IN (5) ;

UPDATE tb_empleado SET clave= '123' WHERE id_empleado IN (1) ;
UPDATE tb_empleado SET clave= '124' WHERE id_empleado IN (2) ;
UPDATE tb_empleado SET clave= '125' WHERE id_empleado IN (3) ;
UPDATE tb_empleado SET clave= '126' WHERE id_empleado IN (4) ;
UPDATE tb_empleado SET clave= '127' WHERE id_empleado IN (5) ;

-- --------------------------------------------------------

SELECT * FROM tb_empleado WHERE usuario='@ronal' AND clave='123';

-- ------------------------------------------------------------

-- introduciendo data tb_empleado 
INSERT INTO tb_empleado(nombres,apellido_ma,apellido_pa,fecha_nacimiento,direccion_emp,telefono,codigo_distrito,correo,fecha_ingreso)
 VALUE ('carlos','mora','nuñez','1980-02-10','angeles','342323',1,'angeles@hotail.com','2020-03-15');

INSERT INTO tb_empleado(nombres,apellido_ma,apellido_pa,fecha_nacimiento,direccion_emp,telefono,codigo_distrito,correo,fecha_ingreso)
		 VALUE ('juan','mora','nuñez','1980-02-10','angeles','342323',1,'asdas@hotail.com','2020-03-15');
         
         
INSERT INTO `tb_empleado` VALUES 
(1,'ronaldo','corsa','manrique','1954-08-28','casuarinas','7654765',2,'ronaldo@gmail.com','2018-10-10',null),
(2,'joel','hinojosa','rios','1995-12-05','girasoles','3333333',1,'joelh@hotmail.com','2019-11-30',null),
(3,'juan','mayta','taipe','1987-03-14','san andres','2222222',4,'juanca@hotmail.com','2017-01-24',null),
(4,'ricardo','rios','pino','1997-02-15','planicie','6666666',5,'ricriospino@gmail.com','2013-06-16',1);

-- -----------------------------------------------------------------
CREATE TABLE `tb_cliente` (
`id_cliente`int(10) unsigned NOT NULL AUTO_INCREMENT,
`direccion_cliente` varchar(255) ,
`telefono_cliente` varchar(7),
`codigo_distrito`int(10) unsigned NOT NULL,
`correo_cliente` varchar (100) NOT NULL,
`web` varchar (255),
UNIQUE KEY `correo_cliente`(`correo_cliente`),
FOREIGN KEY (`codigo_distrito`) references tb_distrito (codigo_distrito),
PRIMARY KEY (id_cliente)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `tb_cliente` VALUES 
(1,'girasoles mz e','7473737',1,'ronalc@gmail.com','no'),
(2,'ferrocaril ','2325533',3,'face@gmail.com','no'),
(3,'raul ferrero','222322',5,'face134@gmail.com','no'),
(4,'mayorazgo','1234567',1,'marycielo@gmail.com','no'),
(5,'av primavera','7362722',3,'moda@gmail.com','no'),
(6,'ceres','234234',1,'atedemoda@gmail.com','no'),
(7,'av peru','736434',3,'desntistaroanl@gmail.com','si'),
(8,'malecon checa','7364342',3,'modazaoficial@gmail.com','si'),
(9,'caja de agua','231123',4,'mismascotas@gmail.com','no'),
(10,'calle trabajo','7363636',1,'productotv@gmail.com','no');


-- ---------------Cliente Juridico------------------
CREATE TABLE `tb_cliente_per_juridica` (
`id_per_juridica`int(10) unsigned NOT NULL AUTO_INCREMENT,
`id_cliente` INTEGER UNSIGNED NOT NULL,
`razon_social` varchar(100) NOT NULL,
`ruc` varchar(10) NOT NULL,
`contacto_cliente` varchar(100),
`codigo_cargo` varchar(100),
INDEX id_per_juridica(id_cliente),
FOREIGN KEY (`id_cliente`) references tb_cliente (id_cliente),
PRIMARY KEY (`id_per_juridica`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `tb_cliente_per_juridica` VALUES 
(1,1,'asociados SA','1010291817','celular','212CA'),
(2,4,'Dentistas EIRL','1010290101','telefono','101CA'),
(3,7,'Ana y claudio SA','1010291850','celular','111CA'),
(4,8,'El 10 SAC','1010294333','celular','133CA'),
(5,10,'Youtube SA','1010290000','telefono','104CA');

-- ------------cliente natural-------------------

CREATE TABLE `tb_cliente_per_natural` (
`id_per_natural`int(10) unsigned NOT NULL AUTO_INCREMENT,
`id_cliente` int(10) unsigned NOT NULL,
`nombre` varchar(100) NOT NULL,
`apellido_paterno` varchar(10) NOT NULL,
`apellido_materno` varchar(100) NOT NULL,
INDEX tb_cliente_per_natural(id_cliente),
FOREIGN KEY (`id_cliente`) references tb_cliente (id_cliente),
PRIMARY KEY (`id_per_natural`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `tb_cliente_per_natural` VALUES 
(1,2,'carlo','magno','rojas'),
(2,3,'claudi','tideman','mangnota'),
(3,5,'claudio','herrera','gonzales'),
(4,6,'dulce','quispe','roco'),
(5,9,'luana','del aguila','perez');

-- agregando columna dni
SELECT * FROM tb_cliente_per_natural;
ALTER TABLE tb_cliente_per_natural ADD COLUMN dni varchar(100) UNIQUE   AFTER id_cliente;

-- AGREGANDO data dni 
UPDATE tb_cliente_per_natural SET dni= '12348638' WHERE id_per_natural IN(12);
UPDATE tb_cliente_per_natural SET dni= '32468274' WHERE id_per_natural IN(19);
UPDATE tb_cliente_per_natural SET dni= '23237182' WHERE id_per_natural IN(20);
UPDATE tb_cliente_per_natural SET dni= '12121212' WHERE id_per_natural IN(21);

-- -------------tipo producto------------------------

CREATE TABLE  `tb_tipo_producto` (
`id_tipo_producto`int(10) unsigned NOT NULL AUTO_INCREMENT,
`descripcion_tipo_producto` varchar(255) NOT NULL,
PRIMARY KEY (`id_tipo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `tb_tipo_producto` VALUES 
(1,'medicina'),
(2,'abarrotes'),
(3,'limpieza'),
(4,'juguetes'),
(5,'oficina');

-- ----------------tabla producto------------------------
SELECT * FROM tb_producto;
CREATE TABLE `tb_producto` (
`id_producto`int(10) unsigned NOT NULL AUTO_INCREMENT,
`nombre_producto` varchar(255) NOT NULL,
`precio` int(100) NOT NULL,
`stock_actual` int(100) NOT NULL,
`id_tipo_producto`int(10) unsigned NOT NULL,
FOREIGN KEY (`id_tipo_producto`) references tb_tipo_producto  (id_tipo_producto),
PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `tb_producto` VALUES 
(1,'kn95',6,10030,1),
(2,'leche',2,103,2),
(3,'mascarilla quirurgica',1,23123,1),
(4,'tractor de juguete',13,31,4),
(5,'paracetamol',4,232,1),
(6,'jabon',2,323,3),
(7,'papel a4',1,1331,5),
(8,'azucar',3,1313,2),
(9,'fideos',2,210,2),
(10,'arroz',1,233,2);

INSERT INTO tb_producto(id_producto,nombre_producto,precio,stock_actual,id_tipo_producto)
VALUES (11,'galletas',0,10,2);

INSERT INTO tb_producto(id_producto,nombre_producto,precio,stock_actual,id_tipo_producto)
VALUES (12,'caramelos',0,3,2);

-- -------------tabla boleta---------------------
CREATE TABLE `tb_boleta` (
`id_boleta`int(10) unsigned NOT NULL AUTO_INCREMENT,
`id_empleado`int(10) unsigned NOT NULL,
`fecha_boleta` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`id_cliente` int(10) unsigned NOT NULL,
`estado_boleta` tinyint(1) NOT NULL DEFAULT '1',
FOREIGN KEY (`id_empleado`) references tb_empleado (id_empleado),
FOREIGN KEY (`id_cliente`) references tb_cliente (id_cliente),
PRIMARY KEY (`id_boleta`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
select * from tb_boleta;

-- agregar columna total_venta 
ALTER TABLE tb_boleta ADD COLUMN total_venta  int(100) AFTER estado_boleta ;

INSERT INTO tb_boleta(id_empleado,id_cliente,estado_boleta) VALUE (2,10,0);
INSERT INTO tb_boleta(id_empleado,id_cliente) VALUE (3,3);
INSERT INTO tb_boleta(id_empleado,id_cliente) VALUE (1,7);
INSERT INTO tb_boleta(id_empleado,id_cliente) VALUE (1,8);
INSERT INTO tb_boleta(id_empleado,id_cliente) VALUE (1,2);
INSERT INTO tb_boleta(id_empleado,id_cliente) VALUE (1,9);
INSERT INTO tb_boleta(id_empleado,id_cliente) VALUE (2,10);
INSERT INTO tb_boleta(id_empleado,id_cliente) VALUE (3,5);
INSERT INTO tb_boleta(id_empleado,id_cliente) VALUE (2,6);
INSERT INTO tb_boleta(id_empleado,id_cliente) VALUE (1,1);
INSERT INTO tb_boleta(id_empleado,id_cliente) VALUE (1,5);
INSERT INTO tb_boleta(id_empleado,id_cliente) VALUE (1,50);
INSERT INTO tb_boleta(id_empleado,id_cliente) VALUE (1,44);

-- ----------------detalle boleta----------------------------

select *from tb_detalle_boleta;

CREATE TABLE  `tb_detalle_boleta` (
`id_detalle_boleta`int(10) unsigned NOT NULL AUTO_INCREMENT,
`id_boleta`int(10) unsigned NOT NULL,
`id_producto`int(10) unsigned NOT NULL,
`cantidad` int(100) NOT NULL,
`precio_venta` int(100) NOT NULL,
FOREIGN KEY (`id_boleta`) references tb_boleta (id_boleta),
FOREIGN KEY (`id_producto`) references tb_producto (id_producto),
PRIMARY KEY (`id_detalle_boleta`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- cambiando el nombre de la columna 
ALTER TABLE tb_detalle_boleta CHANGE precio_venta  sub_total int(100)  ;

INSERT INTO tb_detalle_boleta (id_boleta,id_producto,cantidad,precio_venta) VALUE (5,1,2,20);
INSERT INTO tb_detalle_boleta(id_boleta,id_producto,cantidad,precio_venta) VALUE (6,2,4,13);
INSERT INTO tb_detalle_boleta(id_boleta,id_producto,cantidad,precio_venta) VALUE (7,3,4,50);
INSERT INTO tb_detalle_boleta(id_boleta,id_producto,cantidad,precio_venta) VALUE (8,4,1,100);
INSERT INTO tb_detalle_boleta(id_boleta,id_producto,cantidad,precio_venta) VALUE (9,5,1,30);
INSERT INTO tb_detalle_boleta(id_boleta,id_producto,cantidad,precio_venta) VALUE (10,6,2,10);
INSERT INTO tb_detalle_boleta(id_boleta,id_producto,cantidad,precio_venta) VALUE (11,2,1,5);
INSERT INTO tb_detalle_boleta(id_boleta,id_producto,cantidad,precio_venta) VALUE (12,8,5,48);
INSERT INTO tb_detalle_boleta(id_boleta,id_producto,cantidad,precio_venta) VALUE (13,1,10,23);
INSERT INTO tb_detalle_boleta(id_boleta,id_producto,cantidad,precio_venta) VALUE (14,10,4,59);
INSERT INTO tb_detalle_boleta(id_boleta,id_producto,cantidad,precio_venta) VALUE (15,11,1,32);


-- ----------------queys-------------------------

SELECT * FROM tb_boleta;
SELECT * FROM tb_cliente;
SELECT * FROM tb_cliente_per_juridica;
SELECT * FROM tb_cliente_per_natural;
SELECT * FROM tb_detalle_boleta;
SELECT * FROM tb_distrito;
SELECT * FROM tb_empleado;
SELECT * FROM tb_producto;
SELECT * FROM tb_tipo_producto;

-- ----------Procedure cliente Natural/cliente insertar--------------------------------
DELIMITER $$
CREATE PROCEDURE stp_cliente_natural (IN pdni varchar(100),
									  IN Pdireccion_cliente varchar(255),
									  IN Ptelefono_cliente varchar(7),
									  IN Pcodigo_distrito int(10),
                                      IN Pcorreo_cliente varchar (100),
									  IN Pnombre varchar(100),
									  IN Papellido_paterno  varchar(10),
									  IN Papellido_materno varchar(100))
BEGIN
DECLARE vid_cliente INT;

INSERT INTO tb_cliente (direccion_cliente,telefono_cliente,codigo_distrito,correo_cliente)
VALUES (Pdireccion_cliente,Ptelefono_cliente,Pcodigo_distrito,Pcorreo_cliente);

SET vid_cliente  = (SELECT id_cliente FROM tb_cliente a 
					WHERE 
                    a.direccion_cliente = Pdireccion_cliente AND
					a.telefono_cliente = Ptelefono_cliente  AND
					a.codigo_distrito = Pcodigo_distrito AND 
					a.correo_cliente = Pcorreo_cliente);
                          
INSERT INTO tb_cliente_per_natural(id_cliente,dni,nombre,apellido_paterno,apellido_materno)
VALUES (vid_cliente,Pdni,Pnombre,Papellido_paterno,Papellido_materno);

end$$;
DELIMITER $$

drop procedure stp_cliente_natural;
select * from tb_cliente;
select*from tb_cliente_per_natural;
CALL stp_cliente_natural ('angeles ate','24323',2,'angelesaf@hotmail.com','mmmonolo','carrazco','diaz');
CALL stp_cliente_natural('manylsa','24332',1,'manylsa@hotmail.com','beto','quezada','diaz');


-- ---------------Procedure cliente Juridico/cliente insertar------------------------
DELIMITER $$
CREATE PROCEDURE stp_cliente_juridico (IN Pdireccion_cliente varchar(255),
									  IN Ptelefono_cliente varchar(7),
									  IN Pcodigo_distrito int(10),
                                      IN Pcorreo_cliente varchar (100),
									  IN Prazon_social varchar(100),
									  IN Pruc  varchar(10),
									  IN Pcontacto_cliente varchar(100))
BEGIN
DECLARE vid_cliente_juridico INT;

INSERT INTO tb_cliente (direccion_cliente,telefono_cliente,codigo_distrito,correo_cliente)
VALUES (Pdireccion_cliente,Ptelefono_cliente,Pcodigo_distrito,Pcorreo_cliente);

SET vid_cliente_juridico  = (SELECT id_cliente FROM tb_cliente a 
							WHERE a.direccion_cliente = Pdireccion_cliente AND
							a.telefono_cliente = Ptelefono_cliente  AND
							a.codigo_distrito = Pcodigo_distrito AND 
							a.correo_cliente = Pcorreo_cliente);
                          
INSERT INTO tb_cliente_per_juridica(id_cliente,razon_social,ruc,contacto_cliente)
VALUES (vid_cliente_juridico,Prazon_social,Pruc,Pcontacto_cliente);

end$$;
DELIMITER $$

select * from tb_cliente;
select* from tb_cliente_per_juridica;
CALL stp_cliente_juridico ('grifo tokio','234256',1,'tokiohotel@gmail.com','tokio sac','20434325','telefono');

                         
-- -------------DELETE ---------------------------

DELETE FROM tb_producto WHERE id_producto = 30;
DELETE FROM tb_empleado WHERE id_empleado = 15;


DELIMITER $$
CREATE PROCEDURE stp_borra_cliente_natural ( pid_cliente INT)
BEGIN
	DELETE FROM tb_cliente_per_natural WHERE id_cliente = pid_cliente;
    DELETE FROM tb_cliente WHERE id_cliente = pid_cliente;
    
END$$
DELIMITER ;

CALL stp_borra_cliente_natural(34);
-- -----------Procedure con return cliente natural -------------------

DELIMITER $$
CREATE PROCEDURE stp_borra_cliente_natural ( IN pid_cliente INT,
											OUT bandera INT)
BEGIN
	DECLARE numeros_venta INT;
    
    SET numeros_venta = (SELECT COUNT(*)  FROM tb_boleta WHERE id_cliente = pid_cliente);
   
	IF  (numeros_venta > 0)THEN 
       
		SET  bandera = 0;
		SELECT 'NO PODEMOS BORRAR DATA';
        
	ELSE IF (numeros_venta = 0)THEN
		
		DELETE FROM tb_cliente_per_natural WHERE id_cliente = pid_cliente;
		DELETE FROM tb_cliente WHERE id_cliente = pid_cliente;
        SET bandera =1;
	END IF;
    END IF;
END $$
DELIMITER ;
SET @bandera = 0;
CALL stp_borra_cliente_natural(53,@bandera);
SELECT @bandera ;

-- -------------Procedure con return cliente Juridico------------------------
DELIMITER $$
CREATE PROCEDURE stp_borra_cliente_juridico ( IN pid_cliente INT,
											OUT bandera INT)
BEGIN
	DECLARE numeros_venta INT;
    
    SET numeros_venta = (SELECT COUNT(*)  FROM tb_boleta WHERE id_cliente = pid_cliente);
   
	IF  (numeros_venta > 0)THEN 
       
		SET  bandera = 0;
		SELECT 'NO PODEMOS BORRAR DATA';
        
	ELSE IF (numeros_venta = 0)THEN
		
		DELETE FROM tb_cliente_per_juridica WHERE id_cliente = pid_cliente;
		DELETE FROM tb_cliente WHERE id_cliente = pid_cliente;
        SET bandera =1;
	END IF;
    END IF;
END $$
DELIMITER ;
SET @bandera = 0;
CALL stp_borra_cliente_juridico(44,@bandera);
SELECT @bandera ;

-- --------------Procedure con ruturn Empleado --------------

DELIMITER $$
CREATE PROCEDURE stp_borra_Empleado ( IN pid_empleado INT,
											OUT bandera INT)
BEGIN
	DECLARE numeros_venta INT;
    
    SET numeros_venta = (SELECT COUNT(*)  FROM tb_boleta WHERE id_empleado = pid_empleado);
   
	IF  (numeros_venta > 0)THEN 
       
		SET  bandera = 0;
		SELECT 'NO PODEMOS BORRAR DATA';
        
	ELSE IF (numeros_venta = 0)THEN
		
		DELETE FROM tb_empleado WHERE id_empleado = pid_empleado;
		
        SET bandera =1;
	END IF;
    END IF;
END $$
DELIMITER ;
SET @bandera = 0;
CALL stp_borra_Empleado(1,@bandera);
SELECT @bandera ;
-- --------------Procedure con ruturn Producto --------------
SELECT * FROM tb_detalle_boleta;
DELIMITER $$
CREATE PROCEDURE stp_borra_Producto ( IN pid_producto INT,
									  OUT bandera INT)
BEGIN
	DECLARE producto_vendido INT;
    
    SET producto_vendido = (SELECT COUNT(*)  FROM tb_detalle_boleta WHERE id_producto = pid_producto);
   
	IF  (producto_vendido > 0)THEN 
       
		SET  bandera = 0;
		SELECT 'NO PODEMOS BORRAR DATA';
        
	ELSE IF (producto_vendido = 0)THEN
		
		DELETE FROM tb_producto WHERE id_producto = pid_producto;
		
        SET bandera =1;
	END IF;
    END IF;
END $$
DELIMITER ;
SET @bandera = 0;
CALL stp_borra_Producto(10,@bandera);
SELECT @bandera ;


-- -------------Update ----------------------
update tb_producto set nombre_producto = 'kn96' , precio = 7, stock_actual=100, id_tipo_producto = 2 WHERE  id_producto= 1 ;

UPDATE tb_empleado SET  nombres = 'elpuma', apellido_ma ='perez',
apellido_pa ='gomez',fecha_nacimiento='2020-12-20', direccion_emp='la molina vieja',
telefono='22222', codigo_distrito= 1, correo= 'ronaldi@gmail.com',fecha_ingreso ='2020-01-10' WHERE id_empleado = 15; 

 -- -----------	Update Procedure cliente natural ---------------------
DELIMITER $$
CREATE PROCEDURE stp_cliente_natural_update (
											IN Pdireccion_cliente varchar(255),
											IN Ptelefono_cliente varchar(7),
											IN Pcodigo_distrito int(10),
											IN Pcorreo_cliente varchar (100),
                                            IN Pdni varchar (100),
											IN Pnombre varchar(100),
											IN Papellido_paterno  varchar(10),
											IN Papellido_materno varchar(100),
                                            IN Pid_cliente INT )
BEGIN
DECLARE vid_cliente INT;

UPDATE tb_cliente SET  direccion_cliente = Pdireccion_cliente, telefono_cliente = Ptelefono_cliente,
					 codigo_distrito = Pcodigo_distrito , correo_cliente = Pcorreo_cliente 
                      WHERE id_cliente = Pid_cliente ;
                    
SET vid_cliente  = (SELECT id_cliente FROM tb_cliente a 
					WHERE 
                    a.direccion_cliente = Pdireccion_cliente AND
					a.telefono_cliente = Ptelefono_cliente  AND
					a.codigo_distrito = Pcodigo_distrito AND 
					a.correo_cliente = Pcorreo_cliente);
                    
UPDATE tb_cliente_per_natural SET dni = Pdni ,nombre = Pnombre , apellido_paterno = Papellido_paterno ,
								  apellido_materno = Papellido_materno 
                                  WHERE id_cliente = vid_cliente;						
end$$;
DELIMITER $$

drop procedure stp_cliente_natural_update;
CALL stp_cliente_natural_update ('canta obrajillo','11111',1,'canta@gmail.com','marcelino','pino','mayta', 59);

-- -------- Update procedure cliente Juridico ---------------
DELIMITER $$
CREATE PROCEDURE stp_cliente_juridico_update (IN Pdireccion_cliente varchar(255),
											  IN Ptelefono_cliente varchar(7),
											  IN Pcodigo_distrito int(10),
											  IN Pcorreo_cliente varchar (100),
											  IN Prazon_social varchar(100),
											  IN Pruc  varchar(10),
											  IN Pcontacto_cliente varchar(100),
											  IN Pid_cliente INT)
BEGIN
DECLARE vid_cliente_juridico INT;

UPDATE tb_cliente SET direccion_cliente = Pdireccion_cliente, telefono_cliente = Ptelefono_cliente,
					 codigo_distrito = Pcodigo_distrito , correo_cliente = Pcorreo_cliente 
                      WHERE id_cliente = Pid_cliente ;

SET vid_cliente_juridico  = (SELECT id_cliente FROM tb_cliente a 
							WHERE a.direccion_cliente = Pdireccion_cliente AND
							a.telefono_cliente = Ptelefono_cliente  AND
							a.codigo_distrito = Pcodigo_distrito AND 
							a.correo_cliente = Pcorreo_cliente);
                            
 UPDATE tb_cliente_per_juridica SET razon_social = Prazon_social, ruc = Pruc ,
								  contacto_cliente =  Pcontacto_cliente
                                  WHERE id_cliente = vid_cliente_juridico;                         
end$$;
DELIMITER $$


CALL stp_cliente_juridico_update ('catacao','22222',3,'catacaosa@gmail.com','catacaos sac','9999999999','telefono', 44);

------  obtenerClieneById -------------------

-- query para cliente juridico 
SELECT a.id_cliente, a.direccion_cliente, a.telefono_cliente, a.codigo_distrito,
a.correo_cliente, b.id_per_juridica, b.razon_social, b.ruc, b.contacto_cliente 
FROM tb_cliente AS a JOIN tb_cliente_per_juridica AS b
ON a.id_cliente = b.id_cliente WHERE a.id_cliente = 1;

-- query para cliente natural
SELECT a.id_cliente, a.direccion_cliente, a.telefono_cliente, a.codigo_distrito,
a.correo_cliente,b.id_per_natural,b.nombre, b.apellido_paterno, b.apellido_materno
FROM tb_cliente AS a JOIN tb_cliente_per_natural AS b
ON a.id_cliente = b.id_cliente WHERE a.id_cliente = 50;


-- ---------Crear vista de cliente juridico y natural--------------

CREATE VIEW cliente_vista AS
	(SELECT DISTINCT a.id_cliente AS id,
     CONCAT(a.nombre,' ',a.apellido_paterno,' ',a.apellido_materno)  AS nombre_o_razon_social,
    a.dni AS dni_o_ruc
    FROM tb_cliente_per_natural AS a  INNER JOIN  tb_cliente AS b ON a.id_cliente = b.id_cliente)
    UNION 
    (SELECT DISTINCT c.id_cliente AS id_juridico,
    c.razon_social AS razon_social,
    c.ruc AS ruc
    FROM tb_cliente_per_juridica AS c INNER JOIN tb_cliente AS d ON c.id_cliente = d.id_Cliente);
    
    SELECT * FROM cliente_vista;
    DROP VIEW cliente_vista;
    
-- -----------obtenerclienteByIdVista-----------------
SELECT id , nombre_o_razon_social , dni_o_ruc FROM cliente_vista WHERE id = 50;

-- modificar precio de int a decimal 
 ALTER TABLE tb_producto MODIFY precio DECIMAL(10,2);
 
 -- modificar total_venta de int a double 
ALTER TABLE tb_boleta MODIFY total_venta DECIMAL(30,2);

-- modificar sub_total de int a decimal 
 ALTER TABLE tb_detalle_boleta MODIFY sub_total DECIMAL(30,2);