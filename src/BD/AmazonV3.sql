use master
drop database AmazonV3
create database AmazonV3; 
use AmazonV3;


create table PaymentMethod(
							idPayment int not null identity(1,1),
                            typePayment varchar (10) not null,
                            decription varchar(30),
                            constraint PKPaymentMethod primary key(idPayment)
);
create table Promotion(
						idPromo int not null identity(1,1),
                        name varchar(15) not null,
                        datePromo date not null,
                        constraint PKPromotion primary key (idPromo)
);
create table Customers(
						idCustomer int not null identity(1,1),
                        name varchar(15) not null,
                        lastName varchar(15) not null,
                        gender varchar(1) not null,
						clave varchar (15) not null,
                        constraint PKCustomer primary key (idCustomer),
                        constraint CTGender check (gender='M' or gender='F')
);
create table Category(
						idCategory int not null identity(1,1),
                        name varchar(15) not null,
                        description varchar(30),
                        image varchar (50),
                        constraint PKCategory primary key (idCategory)
);

create table Country(
						idCountry smallint not null identity(1,1),
                        name varchar (15) not null,
                        constraint PKCountry primary key (idCountry)
);

create table TypeSale(
						idTypeSale int not null identity(1,1),
                        name varchar(15) not null,
                        description varchar(30) not null,
                        constraint PKTypeSale primary key (idTypeSale));
create table Product (
						idProduct int not null identity(1,1),
                        nameProduct varchar(15) not null,
                        idCategory int,
                        description varchar(30),
                        price float not null,
                        image varchar(50),
                        constraint PKProduct primary key (idProduct),
                        constraint FK1Product foreign key (idCategory) references Category(idCategory),
                        constraint CTProductPrice check(price >=0)
);
create table State(
						idState smallint not null identity(1,1),
                        name varchar(15)not null,
                        idCountry smallint not null,
                        constraint PKState primary key (idState,idCountry),
                        constraint FKState foreign key (idCountry) references Country(idCountry)
);
create table City(
						idCity smallint not null identity(1,1),
                        name varchar(15) not null,
                        idCountry smallint not null,
                        idState smallint not null,
                        constraint PKCity primary key(idCity,idState,idCountry),
                        constraint FKCity1 foreign key (idState,idCountry) references State(idState,idCountry)
);
create table Address(
						idAddress smallint not null identity(1,1),
                        street varchar(15) not null,
                        email varchar(15),
                        phone varchar(10),
                        nameCommerce varchar(15),
                        idCity smallint not null,
                        idState smallint not null,
                        idCountry smallint not null,
                        cp int not null,
                        constraint PKAddress primary key (idAddress),
                        constraint FKAddress foreign key (idCity,idState,idCountry) references City(idCity,idState,idCountry)                        
);
create table Store(
						idStore int not null identity(1,1),
                        idAddress smallint,
						name varchar(20),
                        constraint PKStore primary key (idStore),
                        constraint FKStore foreign key (idAddress)references Address (idAddress));

create table ShoppingCart(
						idCustomer int not null,
                        idCart int not null,
						idProduct int not null,
                        subTotal float not null,
						cantidad int,
                        constraint PKShopping primary key (idCustomer,idCart,idProduct),
                        constraint FKShopping foreign key (idCustomer)references Customers(idCustomer),
						constraint FK2Shopping foreign key (idProduct)references Product(idProduct),
                        constraint CTShoppingSubTotal check (subTotal >=0));

create table AddressCustomer(
						idCustomer int not null,
                        idAddress smallint not null,
                        constraint PKAddressCustomer primary key (idCustomer,idAddress),
                        constraint FK1AdresCustomer foreign key (idCustomer) references Customers (idCustomer),
                        constraint FK2AdresCustomer foreign key (idAddress) references Address (idAddress));

create table Sale(
						idSale int not null identity(1,1),
                        idCustomer int not null,
                        idCart int,
                        total float not null,
                        idPayment int not null,
                        idTypeSale int not null,
                        idStore int not null,
                        idPromo int,
						nUser int not null,
						idProduct int not null,
                        date date not null,
                        constraint PKSale primary key (idSale),
                        constraint FK1Sale foreign key (idCustomer,idCart,idProduct) references ShoppingCart (idCustomer,idCart,idProduct),
                        constraint FK3Sale foreign key (idPayment)references PaymentMethod (idPayment),
                        constraint FK4Sale foreign key (idTypeSale)references TypeSale (idTypeSale),
                        constraint FK5Sale foreign key (idStore)references Store (idStore),
                        constraint FK6Sale foreign key (idPromo)references Promotion (idPromo),
                        constraint CTSaleTotal check(total>=0));

create table invoice(
						idInvoice int not null identity(1,1),
                        idSale int not null,
                        dateInvoice date not null,
                        nUser int,
                        constraint PKInvoice primary key (idInvoice,idSale),
                        constraint FK1Invoice foreign key (idSale) references Sale(idSale));


create table Stock(
						idProduct int not null,
                        idStore int not null,
                        quantity int not null,
                        constraint PKStock primary key (idProduct,idStore),
                        constraint FK1Stock foreign key(idProduct) references Product(idProduct),
                        constraint FK2Stock foreign key(idStore)references Store(idStore),
                        constraint CTStock check (quantity>=0)
);
