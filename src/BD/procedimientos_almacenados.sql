use AmazonV3
create user Erick for login Erick
create login Erick with password = 'itgd'

create role Administrador
alter role Administrador add member Erick

create role Usuarios

grant select, update, delete, insert on PaymentMethod to Administrador
grant select, update, delete, insert on Promotion to Administrador
grant select, update, delete, insert on Customers to Administrador
grant select, update, delete, insert on Category to Administrador
grant select, update, delete, insert on Country to Administrador
grant select, update, delete, insert on TypeSale to Administrador
grant select, update, delete, insert on Product to Administrador
grant select, update, delete, insert on State to Administrador
grant select, update, delete, insert on City to Administrador
grant select, update, delete, insert on Address to Administrador
grant select, update, delete, insert on Store to Administrador
grant select, update, delete, insert on ShoppingCart to Administrador
grant select, update, delete, insert on AddressCustomer to Administrador
grant select, update, delete, insert on Sale to Administrador
grant select, update, delete, insert on Invoice to Administrador
grant select, update, delete, insert on Stock to Administrador

grant insert on Customers to public


----------------------------------------------------------------verificacion de usuario---------------------------------------------------------
drop procedure verificaUsuario
create procedure verificaUsuario
@name varchar(30), @pass varchar(15)
as begin try
declare @salida int
	if exists(select * from Users where name = @name)
	begin
		if ((select pass from Users where name = @name) = @pass)
		begin
			print 'existe'
			select * from Users where name = @name and pass = @pass

		end
		else
		begin
			print 'clave incorrecta'

		end
	end
	else
	begin
		print 'no existe'
	end
end try
		begin catch
			print dbo.MensajeError()
		end catch

	exec verificaUsuario 'Erick','itgd'


-----------------------------------------------------------------Creacion de usuario al regsitrar cliente-------------------------------------------------------------------------
use AmazonV3

drop procedure creaUser
create procedure creaUser
@name varchar(30), @pass varchar(15)
as
begin try

	insert into Users values(@name, @pass)
	print 'se creo el Usuario'
end try
		begin catch
			print dbo.MensajeError()
		end catch


drop trigger createUser
create trigger createUser
on Customers
for insert
as
begin try
	declare @name varchar(30), @pass varchar(15)
	set @name = (select name from inserted)
	set @pass = (select clave from inserted)
	exec creaUser @name, @pass
end try
		begin catch
			print dbo.MensajeError()
		end catch

-------------------------------------los de juan luis-------------
use AmazonV3
use master

drop trigger insertcarrito
create trigger insertcarrito
on shoppingcart
instead of insert
as
begin try
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
		end try
		begin catch
			print dbo.MensajeError()
		end catch


drop procedure insertventa
create procedure insertventa
@carrito int,@cliente int,@pago int
as begin try
	declare @total float;
    set @total=( select sum(subTotal) from ShoppingCart where idCustomer=@carrito and idCart=@cliente);
    insert into sale values (@cliente,@carrito,@total,@pago,1,1,1,1,GETDATE())
end try
		begin catch
			print dbo.MensajeError()
		end catch

drop procedure vistaCarrito
create procedure vistaCarrito
@carrito int
as begin try
select * from ShoppingCart
where idCart not in (select idCart from sale where idCart=@carrito) and idCart = @carrito;

end try
		begin catch
			print dbo.MensajeError()
		end catch



-----------------------------------------------------------------------------------------------------------------------------------

/*----------------+ PROCEDIMIENTOS FRAN +-----------------*/
create procedure ShowSales
	@IdCustomer int
as
BEGIN
Select nameProduct,price,description,image
from Product
where idProduct in (select idProduct
										from Sale
										where idCustomer = @IdCustomer)
	END


use AmazonV3
drop procedure filtro
create procedure filtro
  @categoria VARCHAR(15)
                    as
BEGIN try
Select nameProduct,price,description,image
from Product
where idCategory IN (SELECT idCategory
                     FROM Category
                     WHERE name = @categoria)
  end try
		begin catch
			print dbo.MensajeError()
		end catch

drop procedure filtro2
create procedure filtro2
  @categoria1 varchar(15), @categoria2 varchar(15)
as
BEGIN try
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
  end try
		begin catch
			print dbo.MensajeError()
		end catch

drop procedure filtroPre
create procedure filtroPre
  @p1 float,@p2 float
as
BEGIN try
select nameProduct,price,description,image
from Product
where price >= @p1 and price <= @p2
end try
		begin catch
			print dbo.MensajeError()
		end catch

/*----------------+ PROCEDIMIENTOS FRAN +-----------------*/

----------------------------------------------------------consulta del semestre pasado para ver el resumen del cliente-------------------------------------------------
use master
use AmazonV3


select  c.name customerName, p.nameProduct productName, s.date saleDate, s.total saleTotal
                                              from Sale s inner join Customers c on s.idCustomer = c.idCustomer
                                                            inner join ShoppingCart sc on s.idCustomer = sc.idCustomer and s.idCart = sc.idCart and c.idCustomer = sc.idCustomer
                                                           inner join Product p on p.idProduct = sc.idProduct
                                              where c.name = SUSER_SNAME()

-----------------------------------------TABLA DE ERRORES--------------------------------------------------------
create table Mensajes(restriccion varchar(20) not null,
                      mensaje varchar(60),
					  constraint mensajesPK primary key (restriccion))

  insert into Mensajes
   Values('PKPaymentMethod','Ese metodo de pago ya esta registrado'),
		 ('PKPromotion','Esa Promocion ya esta registrada'),
		 ('PKCustomer','Ese cliente ya esta registrado'),
		 ('CTGender','El genero solo puede ser F o M'),
		 ('PKCategory','Esa categoria ya esta registrada'),
		 ('PKCountry','Ese pais ya esta registrado'),
		 ('PKUsers','Usuario ya registrado'),
		 ('FK1User','No existe la referencia a role'),
		 ('PKTypeSale','Esa venta ya esta registrada'),
		 ('PKProduct','Ese producto ya esta registrado'),
		 ('FK1Product','No existe la referencia foranea a categoria'),
		 ('CTProductPrice','El precio del producto no puede ser menor a 0'),
		 ('PKState','Ese estado ya esta registrado'),
		 ('FKState','No existe la referencia foranea a  pais'),
		 ('PKCity','Cuidad ya registrada'),
		 ('FKCity1','No existe la referencia foranea a estadoypais'),
		 ('PKAddress','Direccion registrada'),
		 ('FKAddress','No existe la referencia foranea edo,pais,cuidad'),
		 ('PKStore','Tienda ya registrada'),
		 ('FKStore','No existe la referencia foranea a direccion'),
		 ('PKShopping','Carrito ya registrado'),
		 ('FKShopping','No existe la referencia foranea a cliente'),
		 ('CTShoppingSubTotal','El subtotal debe ser mayor a 0'),
		 ('PKAddressCustomer','direccion de cliente ya resgistrada'),
		 ('FK1AdresCustomer','referencia duplicada al id cliente'),
		 ('FK2AdresCustomer','referencia duplicada al id address'),
		 ('PKSale','Venta ya registrada'),
		 ('FK1Sale','No existe la referencia foranea a carritodecompra'),
		 ('FK2Sale','No existe la referencia foranea a user'),
		 ('FK3Sale','No existe la referencia foranea a metodo de pago'),
		 ('FK4Sale','No existe la referencia foranea a Tipo de venta '),
		 ('FK5Sale','No existe la referencia foranea a tienda'),
		 ('FK6Sale','No existe la referencia foranea a Promocion'),
		 ('CTSaleTotal','El total de venta debe ser mayor a 0'),
		 ('PKInvoice','Factura ya regitrada'),
		 ('FK1Invoice','No existe la referencia a  Venta'),
		 ('FK2Invoice','No existe la referencia a  user'),
		 ('PKStock','Stock ya registrado'),
		 ('FK1Stock','No existe la referencia producto'),
		 ('FK2Stock','No existe la referencia a tienda'),
		 ('CTStock','La cantidad en stock debe ser mayor a 0')

drop Function MensajeError
create Function MensajeError()
   returns varchar(60)
as
begin
  declare @Derror varchar(300);
  declare @Mensaje varchar(60);
  declare @inicio smallint;
  declare @fin smallint;
  set @Derror = ERROR_MESSAGE();
  set @inicio = case
	               when CHARINDEX('PRIMARY KEY',@Derror) > 0 then CHARINDEX('PRIMARY KEY',@Derror) + 13
	               when CHARINDEX('FOREIGN KEY',@Derror) > 0 then CHARINDEX('FOREIGN KEY',@Derror) + 13
		 		   when CHARINDEX('CHECK',@Derror) > 0 then CHARINDEX('CHECK',@Derror)+7
				   else 0
				end
	 set @fin = CHARINDEX('.',@Derror,@inicio) - @inicio - 1
	 set @Mensaje = (select mensaje
	                  from mensajes
					 where restriccion = substring(@Derror,@inicio,@fin))

	 return @mensaje;
end
/*---------------------------------------eliminaritem del carrito---------------------------------------------------*/

create procedure borraritem
  @cliente int, @carrito int, @producto int
as
begin
	declare @nuevototal float;
	declare @nuevacantidad int;
	if(select cantidad from shoppingcart where idCustomer=@cliente and idCart=@carrito and idProduct=@producto>1)
		begin
			set @nuevacantidad=(select cantidad from shoppingcart where idCustomer=@cliente and idCart=@carrito and idProduct=@producto)-1;

			update shoppingcart
			set cantidad=@nuevacantidad
			where idCustomer=@cliente and idCart=@carrito and idProduct=@producto

			set @nuevototal=((select price from product where idProduct=@producto)*(select ShoppingCart.cantidad from ShoppingCart
												where idCustomer=@cliente and idCart=@carrito and idProduct=@producto))
			update shoppingcart
			set subTotal=@nuevototal
			where idCustomer=@cliente and idCart=@carrito and idProduct=@producto
    end
	else
		begin
			delete from shoppingcart
    	where idCustomer=@cliente and idCart=@carrito and idProduct=@producto;
    end
end