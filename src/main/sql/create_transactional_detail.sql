
CREATE TABLE `transaction_data_table` (
  `TXN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MERCHANT_ID` int(11) NOT NULL,
  `TXN_AMOUNT` float(11,2) DEFAULT '0.00',
  `TXN_PG_ID` int(11) DEFAULT NULL,
  `CURRENCY` varchar(100) DEFAULT 'INR',
  `PAYMENT_GATEWAY_ID` varchar(100) DEFAULT NULL,
  `UNIQUE_ORDER_ID` varchar(100) DEFAULT NULL,
  `TXN_STATUS` varchar(100) DEFAULT NULL,
  `TXN_TIMESTAMP` datetime DEFAULT NULL,
  `USER_NAME` varchar(100) DEFAULT NULL,
  `USER_EMAIL` varchar(100) DEFAULT NULL,
  `USER_PHONE_NUMBER` varchar(100) DEFAULT NULL,
  `TXN_SOURCE` varchar(100) DEFAULT 'STANDARD',
  	PRIMARY KEY (`TXN_ID`)
) 