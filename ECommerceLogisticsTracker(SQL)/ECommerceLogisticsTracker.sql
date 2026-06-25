CREATE DATABASE IF NOT EXISTS SwiftShip;

USE SwiftShip;

CREATE TABLE Partners
(
    PartnerID INT PRIMARY KEY,
    PartnerName VARCHAR(50),
    ContactNumber VARCHAR(15)
);

CREATE TABLE Shipments
(
    ShipmentID INT PRIMARY KEY,
    PartnerID INT,
    CustomerName VARCHAR(50),
    DestinationCity VARCHAR(50),
    OrderDate DATE,
    PromisedDate DATE,
    ActualDeliveryDate DATE,

    FOREIGN KEY (PartnerID)
    REFERENCES Partners(PartnerID)
);

CREATE TABLE DeliveryLogs
(
    LogID INT PRIMARY KEY,
    ShipmentID INT,
    DeliveryStatus VARCHAR(20),

    FOREIGN KEY (ShipmentID)
    REFERENCES Shipments(ShipmentID)
);



INSERT INTO Partners VALUES
(1,'BlueDart','9876543210'),
(2,'DTDC','9876543211'),
(3,'Delhivery','9876543212'),
(4,'Ekart','9876543213');

INSERT INTO Shipments VALUES
(101,1,'Rahul','Hyderabad','2026-06-01','2026-06-05','2026-06-04'),
(102,2,'Sneha','Chennai','2026-06-03','2026-06-08','2026-06-10'),
(103,1,'Amit','Bangalore','2026-06-05','2026-06-09','2026-06-09'),
(104,3,'Priya','Hyderabad','2026-06-06','2026-06-10','2026-06-13'),
(105,4,'Kiran','Mumbai','2026-06-07','2026-06-11','2026-06-11'),
(106,2,'Neha','Chennai','2026-06-09','2026-06-14','2026-06-16'),
(107,3,'Rohit','Hyderabad','2026-06-10','2026-06-15','2026-06-15'),
(108,4,'Anjali','Pune','2026-06-12','2026-06-17','2026-06-18');

INSERT INTO DeliveryLogs VALUES
(1,101,'Successful'),
(2,102,'Returned'),
(3,103,'Successful'),
(4,104,'Returned'),
(5,105,'Successful'),
(6,106,'Returned'),
(7,107,'Successful'),
(8,108,'Successful');

SELECT * FROM Partners;

SELECT * FROM Shipments;

SELECT * FROM DeliveryLogs;

SELECT
ShipmentID,
CustomerName,
DestinationCity,
PromisedDate,
ActualDeliveryDate
FROM Shipments
WHERE ActualDeliveryDate > PromisedDate;

SELECT
P.PartnerName,

COUNT(CASE
WHEN D.DeliveryStatus='Successful'
THEN 1
END) AS SuccessfulDeliveries,

COUNT(CASE
WHEN D.DeliveryStatus='Returned'
THEN 1
END) AS ReturnedDeliveries

FROM Partners P

JOIN Shipments S
ON P.PartnerID=S.PartnerID

JOIN DeliveryLogs D
ON S.ShipmentID=D.ShipmentID

GROUP BY P.PartnerName;

SELECT
DestinationCity,
COUNT(*) AS TotalOrders

FROM Shipments

WHERE OrderDate >= CURDATE() - INTERVAL 30 DAY

GROUP BY DestinationCity

ORDER BY TotalOrders DESC

LIMIT 1;

SELECT

P.PartnerName,

COUNT(*) AS TotalDeliveries,

SUM(
CASE
WHEN S.ActualDeliveryDate>S.PromisedDate
THEN 1
ELSE 0
END
) AS DelayedDeliveries,

SUM(
CASE
WHEN D.DeliveryStatus='Successful'
THEN 1
ELSE 0
END
) AS SuccessfulDeliveries,

SUM(
CASE
WHEN D.DeliveryStatus='Returned'
THEN 1
ELSE 0
END
) AS ReturnedDeliveries

FROM Partners P

JOIN Shipments S
ON P.PartnerID=S.PartnerID

JOIN DeliveryLogs D
ON S.ShipmentID=D.ShipmentID

GROUP BY P.PartnerName

ORDER BY DelayedDeliveries ASC;