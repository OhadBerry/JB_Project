Drop database javaproject;
CREATE DATABASE javaproject;


/*CREATE TABLE Companies*/
USE javaproject;
CREATE TABLE Companies(

Company_ID 	    	bigint AUTO_INCREMENT PRIMARY KEY,

Company_NAME 	    varchar(25) NOT NULL 

);

/* CREATE TABLE users*/

USE javaproject;
CREATE TABLE Users(

user_id 		    bigint,

user_name 			varchar(25),

user_password       varchar(25),

company_id          bigint,

user_type           int(3) ,

PRIMARY KEY (user_id),

FOREIGN KEY (company_id) REFERENCES companies(company_id)

);

/*CREATE TABLE Customers*/
USE javaproject;
CREATE TABLE  Customers(

Customer_ID                 bigint,

Customer_FIRSTNAME 			varchar(25) NOT NULL ,

Customer_LASTNAME 	    	varchar(25) NOT NULL ,

PRIMARY KEY (customer_id),

FOREIGN KEY (Customer_ID) REFERENCES Users(User_ID)

);

/*CREATE TABLE categories*/
USE javaproject;
CREATE TABLE categories(

Catagory_ID 	 bigint AUTO_INCREMENT PRIMARY KEY,

Catagory_NAME 	varchar(25)

);

/* CREATE TABLE coupons*/
USE javaproject;
CREATE TABLE coupons(

Coupon_ID 	    		bigint AUTO_INCREMENT PRIMARY KEY,

company_id			    bigint not null,

category_id			    bigint not null,

Coupon_TITLE        		varchar(25),

Coupon_DESCRIPTION      	varchar(50),

Coupon_START_DATE       	date NULL CHECK (coupon_start_date LIKE '--/--/----'),

Coupon_END_DATE        	date NULL CHECK (coupon_end_date LIKE '--/--/----'),

Coupon_AMOUNT				bigint Not null, 

Coupon_PRICE				double Not null, 

Coupon_IMAGE            	varchar(50),


FOREIGN KEY (category_id) 	REFERENCES 	categories(Catagory_ID),
FOREIGN KEY (company_id) 	REFERENCES 	companies(company_id)

);

/* CREATE TABLE purchases*/
USE javaproject;
CREATE TABLE purchases(

customer_id 		bigint ,

coupon_id 			bigint ,

PRIMARY KEY (customer_id,coupon_id),

FOREIGN KEY (coupon_id) REFERENCES coupons(Coupon_ID),

FOREIGN KEY (customer_id) REFERENCES customers(Customer_ID)
);
