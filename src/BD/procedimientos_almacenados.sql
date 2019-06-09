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