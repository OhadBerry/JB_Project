Drop database javaproject;
CREATE DATABASE javaproject;


/*CREATE TABLE Companies*/
USE javaproject;
CREATE TABLE Companies(

ID 	    			int(3) AUTO_INCREMENT PRIMARY KEY,

NAME 	    varchar(25) NOT NULL , 

EMAIL  		varchar(50)  NOT NULL , 

PASSWORD	varchar(25) NOT NULL 

);

/*CREATE TABLE Customers*/
USE javaproject;
CREATE TABLE  Customers(

ID 	    					int(3) AUTO_INCREMENT PRIMARY KEY,

FIRSTNAME 			varchar(25) NOT NULL ,

LASTNAME 	    	varchar(25) NOT NULL ,

EMAIL				varchar(50)  NOT NULL ,

PASSWORD 			varchar(25) NOT NULL

-- FOREIGN KEY (city_id) REFERENCES cities(city_id)

);

/*CREATE TABLE categories*/
USE javaproject;
CREATE TABLE categories(

ID 	    		int(3) AUTO_INCREMENT PRIMARY KEY,

NAME 	varchar(25)

);

/* CREATE TABLE coupons*/
USE javaproject;
CREATE TABLE coupons(

ID 	    			int(3) AUTO_INCREMENT PRIMARY KEY,

company_id			int(3) not null,

category_id			int(3) not null,

TITLE        		varchar(25),

DESCRIPTION      	varchar(50),

START_DATE       	date NULL CHECK (coupon_start_date LIKE '--/--/----'),

END_DATE        	date NULL CHECK (coupon_end_date LIKE '--/--/----'),

AMOUNT				int(6) Not null, 

PRICE				double Not null, 

IMAGE            	varchar(50),


FOREIGN KEY (category_id) 	REFERENCES 	categories(id),
FOREIGN KEY (company_id) 	REFERENCES 	companies(id)

);

/* CREATE TABLE customers_vs_couponscategories*/
USE javaproject;
CREATE TABLE customers_vs_coupons(

customer_id 		int(3) ,

coupon_id 			int(3) ,

PRIMARY KEY (customer_id,coupon_id),

FOREIGN KEY (coupon_id) REFERENCES coupons(id),

FOREIGN KEY (customer_id) REFERENCES customers(id)
);

