# BigdataPOC

## Flow Diagram

![image](https://user-images.githubusercontent.com/25721698/46984197-c6932400-d12f-11e8-9969-ad62c1855ce7.png)

## Steps to install Cloudera VM:-

a.) Download the Oracle VirtualBox and then download the Cloudera VM.

b.)Import the downloaded cloudera VM OVA file in the oracle virtual box.

c.)Start the Virtual Box by right clicking on the imported OVA file.

d.)Also please allocate more than 8GB to image imported by going in the settings.

e.)Open a command prompt and enter the following command as this run all the service required to run the POC.

sudo /home/cloudera/cloudera-manager --express --force

## Steps to run the POC:-

1.) Open the command prompt and enter the following commands to run the MySQL database and create the database/tables:-

mysql -uroot -pcloudera -hlocalhost;  

create db transaction;

use transaction;

CREATE TABLE `transaction` (
  `transactionid` int(10)   NOT NULL,
  `accountnumber` int(10) NOT NULL,
  `referenceamount` varchar(32) NOT NULL,
  `status` varchar(64) DEFAULT NULL,
  `bookingdatainfo` varchar(64) DEFAULT NULL,
  `transactiontype` varchar(32)   NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`transactionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `transaction` (`accountnumber`, `referenceamount`, `status`, `bookingdatainfo`, `transactiontype`, `transactionid`,`created_at`) VALUES
(12313256, '200', 'Transferred', 'crissy@xyz.com', 'Credit','132321231', '2017-07-10 12:30:32'),
(12313332, '50', 'Transferred', 'john@xyz.com', 'Credit','121767331', '2017-07-10 12:30:32'),
(34212342, '33', 'Error', 'kyla@xyz.com', 'Debit','1323453431', '2017-07-10 12:30:32'),
(89483834, '1000', 'Transferred', 'evelin@xyz.com', 'Credit','1329995431', '2017-07-10 12:30:32'),
(6535355, '4000', 'Error', 'elwood@xyz.com', 'Debit','1833883231', '2017-07-10 12:30:32');

2.) Open the Hbase shell using the following command and create a table:-
 
hbase shell

3.) Run the below mentioned command in Hbase shell:-

create ‘transaction’,’transaction_details’

4.) Open a separate terminal and use this import command to insert data in Hbase in a separate command window:-

sqoop import --connect jdbc:mysql://localhost/transaction --username root --password cloudera --table transaction --hbase-table transaction --column-family transaction_details --hbase-row-key transactionid -m 1  

5.) Run the below mentioned command in Hbase Shell to check whether the data has been imported in the Hbase:-

scan 'transaction'

6.) Start the Hbase Rest Server:-

hbase rest start

7.) Clone the Java code and Build the Micro Service code using the below command:-

mvn clean install

8.) After that run the following command:-

mvn springboot:run

9.) Once the application has started , open the browser and hit the following URL:-

http://localhost:8089/accounts/12313332
