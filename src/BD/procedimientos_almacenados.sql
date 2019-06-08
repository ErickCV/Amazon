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