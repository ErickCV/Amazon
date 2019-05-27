use amazon

insert into role values
	(1,'Administrador','encargado'),
    (2,'Usuario','comprador')
    
insert into PaymentMethod values
	(4,'TarjetaR','transaccion-digital'),
    (5,'Trueque','dinero-fisico'),
	(1,'efectivo','dinero-fisico'),
    (2,'TarjetasCR','transaccion-digital'),
    (3,'Cupones','transaccion-digital')
    
insert into Promotion values
	(4,'2x1','2018-12-25'),
    (5,'3x2','2018-12-28'),
	(1,'5descuento','2018-12-15'),
    (2,'10descuento','2018-12-20'),
    (3,'20descuento','2018-12-25')
    
insert into Customers values
	(4,'Paulina','Cupa','F'),
    (5,'Juan luis','Montero','M'),
	(1,'Erick','Camacho','M'),
    (2,'Sarai','Jimenes','F'),
    (3,'Francisco','Tierrablnca','M')
    
insert into Category values
	(4,'Mascotas','Accesorios',null),
    (5,'Libros','Libros',null),
	(1,'Electronicos','Celulares,Computadoras',null),
    (2,'Linea Blanca','Refrigeradores,Lavadoras',null),
    (3,'Deportes','Tenis,Sudaderas',null)
    
insert into Package values
	(4,'Libros','Habitos de millonarios',250.00,10,null),
    (5,'Accesorios','Sueter para perro',199.99,10,null),
	(1,'Celulares','Note 9',24999.99,10,null),
    (2,'Lavadora','lG',12599.99,20,null),
    (3,'Tenis','nike',1599.99,30,null)
    
insert into Country values
	(4,'España'),
    (5,'Inglaterra'),
	(1,'Japon'),
    (2,'Mexico'),
    (3,'EUA')
    
insert into Users values
	(4,'JM','12345',2),
    (5,'PC','12345',1),
	(1,'EC','12345',2),
    (2,'SJ','12345',1),
    (3,'FT','12345',2)
    
insert into TypeSale values
	(1,'Normal','Caja'),
    (2,'Regalo','envoltura')
    
insert into Product values
	(9,'Xbox one',1,'Xbox de 1T',7500.00,'/sample/imagenes/xbox.jpg')
	(6,'Lavadora Smart',2,'Lavadora Samsung',16500.00,'/sample/imagenes/lavadora2.jpg'),
    (7,'Tenis modernos',3,'Tenis Nike',2100.00,'/sample/imagenes/tenis2.jpg'),
    (8,'iPhone x',1,'Celular Apple',20999.99,'/sample/imagenes/iphonex.png')
	(1,'note 9',1,'Celular Samsung',24999.99,'/sample/imagenes/note9.jpg'),
    (2,'Lavadora',2,'Lavador LG',12599.99,'/sample/imagenes/lavadora.jpg'),
    (3,'Tenis',3,'Tenis nike',1599.99,'/sample/imagenes/tenis.jpg'),
    (4,'Refrigerador',2,'Refrigerador 16 pies',21000.00,'/sample/imagenes/refri.jpg'),
	(5,'Google pixel',1,'Celular google',18999.99,'/sample/imagenes/pixel.jpg')

insert into State values
	('Merseyside',5)
	(4,'Cataluña',4),
    (5,'Greater London',5),
	(1,'Guanajuato',2),
    (2,'California',3),
    (3,'Tokyo',1)
    
insert into City values
	('Liverpool',
	('Londres',5,5),
    ('Barcelona',4,4),
	('Celaya',2,1),
    ('L.A',3,2),
    ('kioto',1,3)
    
insert into Address values
	(4,'seiso foruya','cupa@gmail.com','4613215487','chinos',5,4,4,38030),
    (5,'valle del pirul','Juan@gmail.com','4616543252','ArtiDep',4,5,5,38010),
	(1,'agua marina','fran@gmail.com','4611789567','Forex',1,1,2,38020),
    (2,'piracanto','Sarai@gmail.com','4611685599','Celutec',2,2,3,38040),
    (3,'insurgenes','erick@gmail.com','4612565465','pokemon',3,3,1,38050)
    
insert into Store values
	(4,5),
    (5,4),
	(1,3),
    (2,1),
    (3,2)
    
insert into ShoppingCart values
	(4,5,2100.00),
    (5,4,16500.00),
	(2,1,12999.99),
    (1,3,24999.99),
    (3,2,1599.99)
    
insert into AddressCustomer values
	(5,5),
    (4,4),
	(2,3),
    (1,2),
    (3,1)
    
insert into Sale values
	(4,2,1,1,12599.99,2,1,1,2,'2018-12-25'),
    (5,5,4,1,16500.00,1,2,1,2,'2018-12-26'),
    (2,2,1,1,12599.99,2,2,2,1,'2018-12-11'),
    (3,3,2,1,12599.99,2,2,2,1,'2018-12-11'),
    (1,1,3,1,24999.99,1,1,1,1,'2018-12-10')
    
insert into invoice values
	(1,1,'2018-12-05',1),
    (2,3,'2018-12-01',2),
    (3,2,'2018-12-03',3),
    (1,3,'2018-12-05',1)
    
insert into ListShoppingCart values
	(3,1,'Celular',1,2,2,2),
    (1,2,'Tenis',1,2,2,2),
	(3,1,'Celular',1,1,1,1),
    (1,2,'Lavadora',2,1,2,1),
    (2,3,'Tenis',3,1,3,1)
    
insert into Stock values
	(4,2,15),
    (5,1,10),
	(1,2,10),
    (2,1,5),
    (3,3,50)
    
insert into PackageContent values
	(4,'Celular',8,1),
    (5,'Tenis',7,1),
	(1,'celular',1,1),
    (2,'Lavadora',2,1),
    (3,'tenis',3,1)