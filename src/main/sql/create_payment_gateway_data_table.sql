
CREATE TABLE `payment_gateway_data_table` (
  `PG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GATEWAY_NAME` varchar(50) DEFAULT NULL,
  `URL` varchar(100) DEFAULT NULL,
  `SALT` varchar(100) DEFAULT NULL,
  `API_KEY` varchar(100) DEFAULT NULL,
 PRIMARY KEY (`PG_ID`)
)