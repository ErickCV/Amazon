use amazonV3

insert into Role values
	('Administrador','encargado'),
    ('Usuario','comprador')
    
insert into PaymentMethod values
	('TarjetaR','transaccion-digital'),
    ('Trueque','dinero-fisico'),
	('efectivo','dinero-fisico'),
    ('TarjetasCR','transaccion-digital'),
    ('Cupones','transaccion-digital')
    
insert into Promotion values
	('2x1','2018-12-25'),
    ('3x2','2018-12-28'),
	('5descuento','2018-12-15'),
    ('10descuento','2018-12-20'),
    ('20descuento','2018-12-25')
    
insert into Customers values
	('Paulina','Cupa','F','Cupa'),
    ('Juan luis','Montero','M','Montero'),
	('Erick','Camacho','M','Montero'),
    ('Sarai','Jimenes','F','Jimenes'),
    ('Francisco','Tierrablnca','M','Tierrablanca')
    
insert into Category values
	('Mascotas','Accesorios',null),
    ('Libros','Libros',null),
	('Electronicos','Celulares,Computadoras',null),
    ('Linea Blanca','Refrigeradores,Lavadoras',null),
    ('Deportes','Tenis,Sudaderas',null)
    
insert into Package values
	('Libros','Habitos de millonarios',250.00,10,null),
    ('Accesorios','Sueter para perro',199.99,10,null),
	('Celulares','Note 9',24999.99,10,null),
    ('Lavadora','lG',12599.99,20,null),
    ('Tenis','nike',1599.99,30,null)
    
insert into Country values
	('España'),
    ('Inglaterra'),
	('Japon'),
    ('Mexico'),
    ('EUA')
    
insert into Users values
	('JM','12345',2),
    ('PC','12345',1),
	('EC','12345',2),
    ('SJ','12345',1),
    ('FT','12345',2)
    
insert into TypeSale values
	('Normal','Caja'),
    ('Regalo','envoltura')
    
insert into Product values
	('Xbox one',1,'Xbox de 1T',7500.00,'/sample/imagenes/xbox.jpg'),
	('Lavadora Smart',2,'Lavadora Samsung',16500.00,'/sample/imagenes/lavadora2.jpg'),
    ('Tenis modernos',3,'Tenis Nike',2100.00,'/sample/imagenes/tenis2.jpg'),
    ('iPhone x',1,'Celular Apple',20999.99,'/sample/imagenes/iphonex.png'),
	('note 9',1,'Celular Samsung',24999.99,'/sample/imagenes/note9.jpg'),
    ('Lavadora',2,'Lavador LG',12599.99,'/sample/imagenes/lavadora.jpg'),
    ('Tenis',3,'Tenis nike',1599.99,'/sample/imagenes/tenis.jpg'),
    ('Refrigerador',2,'Refrigerador 16 pies',21000.00,'/sample/imagenes/refri.jpg'),
	('Google pixel',1,'Celular google',18999.99,'/sample/imagenes/pixel.jpg')

insert into State values
	('Cataluña',1),
    ('Greater London',2),
	('Guanajuato',4),
    ('California',5),
    ('Tokyo',3)

insert into City values
	('Londres',2,13),
    ('Barcelona',1,12),
	('Celaya',4,14),
    ('L.A',5,15),
    ('kioto',3,16)
    
insert into Address values
    ('insurgenes','erick@gmail.com','4612565465','pokemon',10,13,2,38050),
	('seiso foruya','cupa@gmail.com','4613215487','chinos',12,14,4,38030),
	('valle del pirul','Juan@gmail.com','4616543252','ArtiDep',14,16,3,38010),
	('agua marina','fran@gmail.com','4611789567','Forex',11,12,1,38020),
	('piracanto','Sarai@gmail.com','4611685599','Celutec',13,15,5,38040)

insert into Store values
	(5,'Machitos'),
    (4,'Leon'),
	(3,'Tigres'),
    (6,'Stadium'),
    (7,'miniso')

insert into ShoppingCart values
	(4,1,2100.00),
    (5,2,16500.00),
	(2,3,12999.99),
    (6,4,24999.99),
    (3,5,1599.99)
    
insert into AddressCustomer values
	(5,5),
    (4,4),
	(2,3),
    (6,6),
    (3,7)

insert into Sale values
	(2,3,1,12599.99,2,1,8,2,'2018-12-25'),
    (5,2,2,16500.00,1,2,9,2,'2018-12-26'),
    (4,1,3,12599.99,3,2,10,1,'2018-12-11'),
    (3,5,4,12599.99,4,2,9,1,'2018-12-11'),
    (6,4,5,24999.99,1,1,11,1,'2018-12-10')

insert into invoice values
	(11,'2018-12-05',1),
    (13,'2018-12-01',2),
    (12,'2018-12-03',3),
    (14,'2018-12-05',1)

insert into ListShoppingCart values
	(4,6,'Celular',1,2,2,2),
    (3,2,'Tenis',1,2,2,2),
	(3,2,'Celular',1,1,1,1),
    (5,3,'Lavadora',2,1,2,1),
    (2,5,'Tenis',3,1,3,1)

insert into Stock values
	(4,8,15),
    (5,9,10),
	(1,11,10),
    (2,10,5),
    (3,12,50)
    
insert into PackageContent values
	(4,'Celular',8,1),
    (5,'Tenis',7,1),
	(1,'celular',1,1),
    (2,'Lavadora',2,1),
    (3,'tenis',3,1)