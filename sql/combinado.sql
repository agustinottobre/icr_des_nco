create table Articulos
(
	idArticulo int not null primary key,
	descripcion nvarchar(50),
	idDeposito int
);

create table SolicitudesArticulo
(
	idSolicitudArticulo int identity(1,1) primary key,
	idOrdenDespacho int,
	estadoSolicitud nvarchar(50),
	idDeposito int
);

create table ItemsSolicitudArticulo
(
	idItemSA int identity(1,1) primary key,
	idArticulo int not null  references Articulos(idArticulo),
	idSolicitudArticulo int not null,
	cantidad int
);

create table Portales
(
	idPortal int not null primary key,
	nombrePortal nvarchar(50)
);

create table OrdenesVenta
(
	idOrdenVenta int not null primary key,
	idPortal int not null  references Portales(idPortal)
);

create table OrdenesDespacho
(
	idOrdenDespacho int not null primary key,
	idOrdenVenta int not null  references OrdenesVenta(idOrdenVenta),
	idLogistica int,
	estadoOrden   nvarchar(50),
	fechaRecepcion datetime,
);

create table ItemsOrdenDespacho
(
	idItemOD int identity(1,1) primary key,
	idOrdenDespacho int not null,
	idArticulo int not null  references Articulos(idArticulo),
	cantidad int,
	estadoItems nvarchar(50)
);

create table HistorialOrdenesDespacho
(
	fechaHora datetime not null primary key,
	idOrdenDespacho int not null  references OrdenesDespacho(idOrdenDespacho),
	evento nvarchar(100)
);
insert into Articulos (idArticulo, descripcion, idDeposito) values (1, 'Zapatillas', 
11);
insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1351953, 
			'Aire Acondicionado Split', 
			3);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1739701, 
			'Freezer Horizontal Eternity', 
			1);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1755725, 
			'Centrifugador', 
			1);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1754721, 
			'Afeitadora', 
			2);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1648970, 
			'Mini Masajeador Glow',  
			3);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(3122, 
			'Musculosa', 
			3);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1721678, 
			'Cartera', 
			1);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1741046, 
			'Reloj Negro Hombre', 
			2);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1456328, 
			'Joggin Deportivo',  
			3);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1672839, 
			'Zapatos',  
			1);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1857363, 
			'Mesa para TV 21', 
			2);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1660104, 
			'Sillon Nahuel 1', 
			3);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1849382, 
			'Mesa de PC B�sica',  
			1);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1607594, 
			'Juego de Jard�n',  
			2);

insert into Articulos (idArticulo, descripcion, idDeposito) 
	values	(1802975, 
			'Banqueta Baja',  
			3);

