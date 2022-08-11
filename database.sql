-- Creating Tables.
create table customer_details
(
    name varchar(200),
    contact int primary key
);

create table orders
(
    order_id varchar(200) primary key,
    particulars varchar(1000),
    seats int,
    total_bill int,
    status varchar(50)
);

create table order_maps
(
    order_id varchar(200),
    contact int,
    foreign key(order_id) references orders(order_id),
    foreign key(contact) references customer_details(contact)
);