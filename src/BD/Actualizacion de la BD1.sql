use amazonV3
select * from Product
insert into Product values
	('Folder',2,'Folder amarillo',3.00,'/sample/imagenes/folder.jpg'),
	('Protectores',2,'Protector transparente',3.00,'/sample/imagenes/protector.jpg'),
	('Memoria RAM',1,'DDR4 de 8 GB',1100,'/sample/imagenes/ram.jpg'),
	('Tarjeta madre',1,'AM4 AMD Gaming ATX',2200,'/sample/imagenes/tarjeta.jpg'),
	('Laptop Dell',3,'8GB RAM Intel i7, GTX 1060',18500,'/sample/imagenes/laptop.jpg'),
	('Lapiz',2,'Lapiz HB',4.50,'/sample/imagenes/lapiz.jpg')


create procedure filtro
	@categoria VARCHAR(15)
as
BEGIN
	Select nameProduct,price,description,image
		from Product
		where idCategory IN (SELECT idCategory
								FROM Category
								WHERE name = @categoria)
END


create procedure filtro2
	@categoria1 varchar(15), @categoria2 varchar(15)
as
BEGIN
	Select nameProduct,price,description,image
		from Product
		where idCategory IN (SELECT idCategory
								FROM Category
								WHERE name = @categoria1)
	union
	Select nameProduct,price,description,image
		from Product
		where idCategory IN (SELECT idCategory
								FROM Category
								WHERE name = @categoria2)
END

create procedure filtroPre
	@p1 float,@p2 float
as
BEGIN
	select nameProduct,price,description,image
		from Product
		where price >= @p1 and price <= @p2
END