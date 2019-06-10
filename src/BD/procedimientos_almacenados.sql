use amazonV3;
delimiter //
create procedure insertventa(in carrito int,cliente int,pago int)
begin
	declare total float;
    set total=( select sum(subTotal) from ShoppingCart where idCustomer=carrito and idCart=cliente);
    insert into sale
    set idCustomer=cliente,
    idCart=carrito,
    sale.total=total,
    idPayment=pago,
    idTypeSale=1,
    idStore=1,
    nUser=1,
    idProduct=1,
    date=sysdate();
end
//
delimiter //
create procedure vistaCarrito(in carrito int)
begin
select * from ShoppingCart 
where idCart not in (select idCart from sale where idCart=carrito);
end
//

-----------------------------------------------------------------Creacion de usuario al regsitrar cliente-------------------------------------------------------------------------
use AmazonV3

drop procedure creaUser
create procedure creaUser
@name varchar(30), @pass varchar(15)
as
begin

	insert into Users values(@name, @pass)
	print 'se creo el Usuario'
end

select * from Customers
select * from sale
select * from Users


drop trigger createUser
create trigger createUser
on Customers
for insert
as
begin
	declare @name varchar(30), @pass varchar(15)
	set @name = (select name from inserted)
	set @pass = (select clave from inserted)
	exec creaUser @name, @pass
end

-------------------------------------el de juan luis-------------
use AmazonV3
use master

drop trigger insertcarrito
create trigger insertcarrito
on shoppingcart
instead of insert
as
begin
declare @Customer int;
declare @Cart int;
declare @Producto int;
declare @cantidad int;
declare @subtotal money;
select @Customer=idCustomer, @Cart=idCart, @Producto=idProduct, @Cantidad=Cantidad, @subtotal = subTotal from inserted

	if(exists(Select idCustomer,idCart,idProduct from shoppingcart
			where idCustomer=@Customer and idCart=@Cart and idProduct=@Producto))
            begin
				update  shoppingcart
				set cantidad=(select cantidad from shoppingcart
				where idCustomer=@Customer and idCart=@Cart and idProduct=@Producto)+@cantidad
				where idCustomer=@Customer and idCart=@Cart and idProduct=@Producto;
                update shoppingcart
                set subTotal = ((select price from Product where idProduct=@Producto)*(select cantidad from inserted)
								+(select subTotal from ShoppingCart where idCustomer=@Customer and idCart=@Cart and idProduct=@Producto))
				where idCustomer=@Customer and idCart=@Cart and idProduct=@Producto;
			end
            else
			begin
				insert into shoppingcart
				values(@Customer,@Cart,@Producto,(select price from Product where idProduct=@Producto)*@cantidad,@Cantidad);
			end
		end

/*----------------+ PROCEDIMIENTOS FRAN +-----------------*/
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

/*----------------+ PROCEDIMIENTOS FRAN +-----------------*/