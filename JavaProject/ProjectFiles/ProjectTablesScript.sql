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

user_id 		    bigint AUTO_INCREMENT PRIMARY KEY ,

user_name 			varchar(25),

user_password       varchar(25),

company_id          bigint,

user_type           varchar(25) NOT NULL ,

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

Category_ID 	 bigint AUTO_INCREMENT PRIMARY KEY,

Category_NAME 	varchar(25) UNIQUE

);

/* CREATE TABLE coupons*/
USE javaproject;
CREATE TABLE coupons(

Coupon_ID 	    		bigint AUTO_INCREMENT PRIMARY KEY,

company_id			    bigint not null,

Category    			    varchar(25),

Coupon_TITLE        		varchar(25) ,

Coupon_DESCRIPTION      	varchar(50),

Coupon_START_DATE       	date NULL CHECK (coupon_start_date LIKE '--/--/----'),

Coupon_END_DATE        	date NULL CHECK (coupon_end_date LIKE '--/--/----'),

Coupon_AMOUNT				bigint Not null, 

Coupon_PRICE				double Not null, 

Coupon_IMAGE            	varchar(50),


FOREIGN KEY (Category) 	REFERENCES 	categories(Category_NAME),
FOREIGN KEY (company_id) 	REFERENCES 	companies(company_id)

);

/* CREATE TABLE purchases*/
USE javaproject;
CREATE TABLE purchases(

purchase_id 		bigint AUTO_INCREMENT,

customer_id 		bigint ,

coupon_id 			bigint ,

coupon_id 			int ,

PRIMARY KEY (purchase_id),

FOREIGN KEY (coupon_id) REFERENCES coupons(Coupon_ID),

FOREIGN KEY (customer_id) REFERENCES customers(Customer_ID)
);

INSERT INTO `javaproject`.`categories`
(`Category_ID`,
`Category_NAME`)
VALUES
(1,
'food');

INSERT INTO `javaproject`.`users`
(`user_id`,
`user_name`,
`user_password`,
`company_id`,
`user_type`)
VALUES
(1,
'TestUserName',
'TestUserPassword',
null,
'Customer');
