CREATE TABLE products (
                      id int primary key,
                      product_name varchar(500) not null unique,
                      product_brand varchar(500) not null,
                      available_quantity int not null check (available_quantity >= 0),
                      weight varchar(100) not null ,
                      rating int not null,
                      category varchar(100) not null,
                      description varchar(2000),
                      color varchar(100),
                      price double not null,
                      photo_title varchar(100),
                      photo varchar(1000),
                      properties varchar(2000)
);

CREATE TABLE products_photo (
                          id int primary key,
                          photo_product int,
                          photo varchar(200)
);
