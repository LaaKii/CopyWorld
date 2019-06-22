create schema copyworld;
use copyworld;

create table Coordinate(
coordinateId int not null auto_increment,
xCoord int,
yCoord int,
zCoord int,
Primary Key (CoordinateId)
);

create table Region(
regionId int not null auto_increment,
regionName varchar(255),
startCoord int,
endCoord int,
Primary Key (regionId),
Foreign Key (startCoord) REFERENCES Coordinate(coordinateId),
Foreign Key (endCoord) REFERENCES Coordinate(coordinateId)
);

create table McBlock(
blockId int not null auto_increment,
blockCoord int,
blockMat varchar(255),
Primary Key (blockId),
Foreign Key (blockCoord) references Coordinate(coordinateId)
);

create table RegionBlock(
regionBlockId int not null auto_increment,
regionId int,
blockId int,
Primary Key (regionBlockId),
Foreign Key (regionId) references Region(regionId),
Foreign Key (blockId) references McBlock(blockId)
);




