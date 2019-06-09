use master
use amazonV3

	select * from PaymentMethod
insert into PaymentMethod values
	('TarjetaR','transaccion-digital'),
    ('Trueque','dinero-fisico'),
	('efectivo','dinero-fisico'),
    ('TarjetasCR','transaccion-digital'),
    ('Cupones','transaccion-digital')

	select * from Promotion
insert into Promotion values
	('2x1','2018-12-25'),
    ('3x2','2018-12-28'),
	('5descuento','2018-12-15'),
    ('10descuento','2018-12-20'),
    ('20descuento','2018-12-25')

insert into Customers values
	('Paulina','Cupa','F','PC'),
    ('Juan luis','Montero','M','JL'),
	('Erick','Camacho','M','EC'),
    ('Sarai','Jimenes','F','SJ'),
    ('Francisco','Tierrablnca','M','FT')

insert into Category values
	('Componentes PC','Accesorios',null),
    ('Papeleria','plumas, lapiz',null),
	('Electronicos','cables etc',null)

insert into Country values
	('España'),
    ('Inglaterra'),
	('Japon'),
    ('Mexico'),
    ('EUA')

	select * from TypeSale
insert into TypeSale values
	('Normal','Caja'),
    ('Regalo','envoltura')

insert into Product values
	('Lapiz',2,'Lapiz HB',4.50,'/sample/imagenes/lapiz.jpg'),
	('Folder',2,'Folder amarillo',3.00,'/sample/imagenes/folder.jpg'),
	('Protectores',2,'Protector transparente',3.00,'/sample/imagenes/protector.jpg'),
	('Memoria RAM',1,'DDR4 de 8 GB',1100,'/sample/imagenes/ram.jpg'),
	('Tarjeta madre',1,'AM4 AMD Gaming ATX',2200,'/sample/imagenes/tarjeta.jpg'),
	('Laptop Dell',3,'8GB RAM Intel i7, GTX 1060',18500,'/sample/imagenes/laptop.jpg'),
	('print Brother',3,'DCP-L2540DW multifuncional',2999,'/sample/imagenes/brother.jpg'),
	('Cooler Liquid',1,'Enfriamento Cooler Master',4347.88,'/sample/imagenes/coler.jpg')

insert into State values
	('Cataluña',1),
    ('Greater London',2),
	('Guanajuato',4),
    ('California',5),
    ('Tokyo',3)

	select * from city

insert into City values
	('Londres',2,2),
    ('Barcelona',1,1),
	('Celaya',4,3),
    ('L.A',5,4),
    ('kioto',3,5)

	select * from Address

insert into Address values
    ('insurgenes','erick@gmail.com','4612565465','pokemon',1,2,2,38050),
	('seiso foruya','cupa@gmail.com','4613215487','chinos',2,1,1,38030),
	('valle del pirul','Juan@gmail.com','4616543252','ArtiDep',3,3,4,38010),
	('agua marina','fran@gmail.com','4611789567','Forex',4,4,5,38020),
	('piracanto','Sarai@gmail.com','4611685599','Celutec',5,5,3,38040)

	select * from store
insert into Store values
	(4,'Machitos'),
    (5,'Leon'),
	(6,'Tigres'),
    (7,'Stadium'),
    (8,'miniso')

	select * from store
	select * from Customers
	select * from ShoppingCart
	select * from Product

insert into ShoppingCart values
	(4,1,7,2100.00,1),
    (5,2,8,16500.00,1),
	(2,3,8,12999.99,1),
    (1,4,5,24999.99,1),
    (3,5,2,1599.99,1)

	select * from AddressCustomer
insert into AddressCustomer values
	(5,4),
    (4,5),
	(2,6),
    (1,7),
    (3,8)

	select * from Store
	select * from Promotion
	select * from Customers
	select * from ShoppingCart
	select * from PaymentMethod
	select * from sale

insert into Sale values
	(1,4,12599.99,1,2,2,1,5,'2018-12-25'),
    (2,3,16500.00,5,2,2,2,8,'2018-12-26'),
    (3,5,12599.99,4,1,3,3,2,'2018-12-11'),
    (4,1,12599.99,3,1,4,2,7,'2018-12-11'),
    (5,2,24999.99,2,1,5,2,8,'2018-12-10')

select * from sale
select * from invoice


insert into invoice values
	(3,'2018-12-05',1),
    (4,'2018-12-01',2),
    (5,'2018-12-03',3),
    (6,'2018-12-05',4)

	select * from Product
	select * from Store
	select * from Stock
insert into Stock values
	(2,2,15),
    (3,3,10),
	(4,4,10),
    (5,5,5),
    (1,6,50)
