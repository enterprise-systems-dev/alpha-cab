
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
('Eva Smith', '129 Station Rd, London, N3 2AS', 4),
('Rob Manton', '23 Bow Lane, London, N3', 5),
('Bob Currie', '54 Teignmouth Rd, London, NW2', 6),
('Jim Hunter', '765 High Road, London, N12', 7),
('Phil Johnson', '75 Squires Lane, London, N3', 8),
('Saim Soyler', '2 Rosemary Ave, London, N3', 9),
('Gul Hikmet', '31 Clifton Rd, London, N3 2SG', 10);

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

Alter table Demands add foreign key (userid) references Users;

INSERT INTO Demands (Name, Address, Destination, Date, Time, Status, userid) VALUES
('Eva Smith', 'Finchley, London', 'King''s Cross, London', '2015-11-02', '09:22:18', 'Outstanding', 4);

-- --------------------------------------------------------
--DROP Table Drivers;
CREATE TABLE Drivers (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
  Registration varchar(10) NOT NULL,
  Name varchar(20),
  userid int,
  PRIMARY KEY (Registration)
);

Alter table Drivers add foreign key (userid) references Users;

INSERT INTO Drivers (Registration, Name, userid) VALUES
('AK52VZV', 'John Smith', 11),
('BN60WKA', 'Mehmet Aydin', 12),
('R34AKP', 'Mark Johnson', 13);

-- --------------------------------------------------------

--DROP Table Journey;
CREATE TABLE Journey (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
  customerid int NOT NULL,
  Destination varchar(60),
  Distance integer NOT NULL DEFAULT 1,
  Registration varchar(10) NOT NULL,
  Date date NOT NULL,
  Time time DEFAULT NULL,
  PRIMARY KEY (id)
);

Alter table Journey add foreign key (customerid) references Customer;
Alter table Journey add foreign key (Registration) references Drivers;

INSERT INTO Journey (Destination, Distance, customerid, Registration, Date, Time) VALUES
('King''s Cross Station, London', 5, 1, 'BN60WKA', '2015-10-14', '09:30:00'),
('Heathrow Terminal 3, London', 20, 7, 'BN60WKA', '2015-10-14', '12:00:00'),
('120 Green Lanes, London, N13', 7, 7, 'AK52VZV', '2015-10-15', '06:00:00'),
('131 Stoke Newington High Road, London, N12', 8, 7, 'AK52VZV', '2015-10-15', '12:00:00'),
('Luton Airport, Luton', 30, 1, 'R34AKP', '2015-10-22', '10:00:00');