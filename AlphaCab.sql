
--DROP Table "Users";
CREATE TABLE Users (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
  username varchar(20) NOT NULL,
  password varchar(20),
  role varchar(20),
  PRIMARY KEY (id) 
) ;

INSERT INTO Users (username, password, role) VALUES('admin', 'admin', 'admin');
INSERT INTO Users (username, password, role) VALUES('driver', 'driver', 'driver');
INSERT INTO Users (username, password, role) VALUES('customer', 'customer', 'customer');
INSERT INTO Users (username, password, role) VALUES('esmith', 'esmith', 'customer');
INSERT INTO Users (username, password, role) VALUES('rmanton', 'rmanton', 'customer');
INSERT INTO Users (username, password, role) VALUES('bcurrie', 'bcurrie', 'customer');
INSERT INTO Users (username, password, role) VALUES('jhunter', 'jhunter', 'customer');
INSERT INTO Users (username, password, role) VALUES('pjohnson', 'pjohnson', 'customer');
INSERT INTO Users (username, password, role) VALUES('ssoyler', 'ssoyler', 'customer');
INSERT INTO Users (username, password, role) VALUES('ghikmet', 'ghikmet', 'customer');
INSERT INTO Users (username, password, role) VALUES('jsmith', 'jsmith', 'driver');
INSERT INTO Users (username, password, role) VALUES('maydin', 'maydin', 'driver');
INSERT INTO Users (username, password, role) VALUES('mjohnson', 'mjohnson', 'driver');
INSERT INTO Users (username, password, role) VALUES('ebrown', 'ebrown', 'customer');

-- --------------------------------------------------------

--DROP Table Customer;
CREATE TABLE Customer (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
  Name varchar(20),
  Address varchar(60),
  userid int not null,
  PRIMARY KEY (id)
);

Alter table Customer add foreign key (userid) references Users;

INSERT INTO Customer (Name, Address, userid) VALUES
('Mr Test Customer', '123 Test Lane', 3),
('Eva Smith', '129 Station Rd, London, N3 2AS', 4),
('Rob Manton', '23 Bow Lane, London, N3', 5),
('Bob Currie', '54 Teignmouth Rd, London, NW2', 6),
('Jim Hunter', '765 High Road, London, N12', 7),
('Phil Johnson', '75 Squires Lane, London, N3', 8),
('Saim Soyler', '2 Rosemary Ave, London, N3', 9),
('Gul Hikmet', '31 Clifton Rd, London, N3 2SG', 10),
('Ed Brown', '3 Cecilia Close, London, N2 0UT', 14);

-- --------------------------------------------------------
--DROP Table Demands;
CREATE TABLE Demands (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
  Name varchar(20),
  Address varchar(60),
  Destination varchar(60),
  Date date DEFAULT NULL,
  Time time DEFAULT NULL,
  Status varchar(15) NOT NULL,
  customerid int,
  PRIMARY KEY (id)
);

Alter table Demands add foreign key (customerid) references Users;

INSERT INTO Demands (Name, Address, Destination, Date, Time, Status, customerid) VALUES
('Eva Smith', 'Finchley, London', 'King''s Cross, London', '2015-11-02', '09:22:18', 'Outstanding', 4);

-- --------------------------------------------------------
--DROP Table Drivers;
CREATE TABLE Drivers (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
  Registration varchar(10) NOT NULL,
  Name varchar(20),
  userid int,
  status varchar(20),
  PRIMARY KEY (Registration)
);

Alter table Drivers add foreign key (userid) references Users;

INSERT INTO Drivers (Registration, Name, userid) VALUES
('123T35T', 'Test Smith', 2),
('AK52VZV', 'John Smith', 11),
('BN60WKA', 'Mehmet Aydin', 12),
('R34AKP', 'Mark Johnson', 13);

-- --------------------------------------------------------

--DROP Table Journey;
CREATE TABLE Journey (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
  Name varchar(20),
  Address varchar(60),
  customerid int NOT NULL,
  Destination varchar(60),
  Distance double NOT NULL DEFAULT 1.0,
  Registration varchar(10) NOT NULL,
  Cost float,
  Date date NOT NULL,
  Time time DEFAULT NULL,
  PRIMARY KEY (id)
);

Alter table Journey add foreign key (customerid) references Customer;
Alter table Journey add foreign key (Registration) references Drivers;

INSERT INTO Journey (Name, Address, Destination, Distance, customerid, Registration, Date, Time) VALUES
('Eva Smith', 'Finchley, London', 'King''s Cross Station, London', 5, 4, 'BN60WKA', '2015-10-14', '09:30:00'),
('Jim Hunter', '765 High Road, London, N12', 'Heathrow Terminal 3, London', 20, 7, 'BN60WKA', '2015-10-14', '12:00:00'),
('Jim Hunter', '765 High Road, London, N12', '120 Green Lanes, London, N13', 7, 7, 'AK52VZV', '2015-10-15', '06:00:00'),
('Jim Hunter', '765 High Road, London, N12', '131 Stoke Newington High Road, London, N12', 8, 7, 'AK52VZV', '2015-10-15', '12:00:00'),
('Eva Smith', 'Finchley, London', 'Luton Airport, Luton', 30, 4, 'R34AKP', '2015-10-22', '10:00:00');

-- --------------------------------------------------------

--DROP Table Rates;
CREATE TABLE Rates (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
  Base_Rate double NOT NULL DEFAULT 10.0,
  Rate_per_mile double NOT NULL DEFAULT 1.0,
  PRIMARY KEY (id)
);

INSERT INTO Rates (Base_Rate, Rate_per_mile) VALUES
(10.0, 1.0);
