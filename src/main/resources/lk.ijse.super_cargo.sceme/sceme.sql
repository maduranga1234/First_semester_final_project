DROP DATABASE if exists super_cargo;

CREATE DATABASE super_cargo;

USE super_cargo;

CREATE TABLE employee(
                         employeeId VARCHAR(10)PRIMARY KEY ,
                         employeeName VARCHAR(30),
                         nic VARCHAR(15)NOT NULL ,
                         dateOfBirth VARCHAR(15)NOT NULL ,
                         address VARCHAR(40)NOT NULL ,
                         contactNumber VARCHAR(15)NOT NULL ,
                         jobTitel VARCHAR(25)
);

CREATE TABLE salary(
                       salaryId VARCHAR(10)PRIMARY KEY ,
                       employeeId VARCHAR(10),
                       ot DECIMAL (8,2)NOT NULL ,
                       amount DECIMAL(8,2)NOT NULL ,
                       paymentMethord VARCHAR(20)NOT NULL ,
                       CONSTRAINT FOREIGN KEY(employeeId)REFERENCES employee(employeeId)

                           ON UPDATE CASCADE
                           ON DELETE CASCADE

);

CREATE TABLE user(
                     userName VARCHAR(30)PRIMARY KEY ,
                     employeeId VARCHAR(10),
                     password VARCHAR(20)NOT NULL ,
                     email VARCHAR(50)NOT NULL ,
                     jobTitel VARCHAR(25)NOT NULL ,
                     CONSTRAINT FOREIGN KEY(employeeId)REFERENCES employee(employeeId)

                         ON UPDATE CASCADE
                         ON DELETE CASCADE
);

CREATE TABLE buyer(
                      buyerId VARCHAR(10)PRIMARY KEY ,
                      userName VARCHAR(10),
                      buyerName VARCHAR(25),
                      country VARCHAR(20)NOT NULL ,
                      contactNumber VARCHAR(15)NOT NULL ,
                      email VARCHAR(50)NOT NULL ,
                      CONSTRAINT FOREIGN KEY(userName)REFERENCES user(userName)

                          ON UPDATE CASCADE
                          ON DELETE CASCADE

);

CREATE TABLE orders(
                       orderId VARCHAR(10)PRIMARY KEY ,
                       buyerId VARCHAR(10),
                       time VARCHAR(8),
                       orderDate DATE NOT NULL ,
                       payment DECIMAL(10,2)NOT NULL ,
                       CONSTRAINT FOREIGN KEY(buyerId)REFERENCES buyer(buyerId)

                           ON UPDATE CASCADE
                           ON DELETE CASCADE

);


CREATE TABLE item(
                     itemId VARCHAR(10)PRIMARY KEY ,
                     itemName VARCHAR(20),
                     weight    VARCHAR(10)NOT NULL ,
                     unitPrice VARCHAR(20)NOT NULL,
                     quality VARCHAR(20)

);

CREATE TABLE ordersDetail(
                             orderId VARCHAR(10),
                             itemId VARCHAR(10) ,
                             weight VARCHAR(10)NOT NULL ,
                             CONSTRAINT PRIMARY KEY (orderId,itemId),
                             CONSTRAINT FOREIGN KEY(orderId)REFERENCES orders(orderId)
                                 ON UPDATE CASCADE
                                 ON DELETE CASCADE,

                             CONSTRAINT FOREIGN KEY(itemId)REFERENCES item(itemId)
                                 ON UPDATE CASCADE
                                 ON DELETE CASCADE

);

CREATE TABLE supplier(
                         supplierId VARCHAR(10)PRIMARY KEY ,
                         supplierName VARCHAR(30),
                         address VARCHAR(40),
                         contactNumber VARCHAR(15)NOT NULL ,
                         email VARCHAR(50)
);

CREATE TABLE supplierItemDetail(
                                   itemId VARCHAR(10),
                                   supplierId VARCHAR(10),
                                   qnt int NOT NULL ,
                                   CONSTRAINT PRIMARY KEY (itemId,supplierId),
                                   CONSTRAINT FOREIGN KEY(itemId)REFERENCES item(itemId)
                                       ON UPDATE CASCADE
                                       ON DELETE CASCADE,

                                   CONSTRAINT FOREIGN KEY(supplierId)REFERENCES supplier(supplierId)
                                       ON UPDATE CASCADE
                                       ON DELETE CASCADE


);

CREATE TABLE matirial(
                         matirialId VARCHAR(10)PRIMARY KEY ,
                         matirialName VARCHAR(30)

);

CREATE TABLE supplierMatirialDetail(
                                       supplierId VARCHAR(10),
                                       matirialId VARCHAR(10),
                                       qty int NOT NULL ,
                                       date DATE NOT NULL ,
                                       price VARCHAR(15) NOT NULL ,

                                       CONSTRAINT PRIMARY KEY (supplierId, matirialId),

                                       CONSTRAINT FOREIGN KEY(supplierId)REFERENCES supplier(supplierId)
                                           ON UPDATE CASCADE
                                           ON DELETE CASCADE,

                                       CONSTRAINT FOREIGN KEY(matirialId)REFERENCES matirial(matirialId)
                                           ON UPDATE CASCADE
                                           ON DELETE CASCADE


);


INSERT INTO employee(employeeId,employeeName,nic,dateOfBirth,address,contactNumber,jobTitel) VALUES ('C001','Maduranga','20023409856','2002','galle','091875097','laber');

DELETE from item where itemId='I002';
