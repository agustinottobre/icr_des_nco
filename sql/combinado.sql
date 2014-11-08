create table Articulos
(
	idArticulo int not null primary key,
	descripcion nvarchar(50),
--	marca nvarchar(50),
--	modelo nvarchar(50),
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
	idSolicitudArticulo int not null  references SolicitudesArticulo(idSolicitudArticulo),
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
	estadoOrden nvarchar(50),
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
--'Adidas', 'Air',
3);
insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1351953, 
			'Aire Acondicionado Split', 
			--'Whirlpool', 
			--' WBC 12B-13B 2645 F/C', 
			3);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1739701, 
			'Freezer Horizontal Eternity', 
			--'Gafa', 
			--'M210', 
			1);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1755725, 
			'Centrifugador', 
			--'Dream', 
			--'QV 5.5', 
			1);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1754721, 
			'Afeitadora', 
			--'Philips', 
			--'PQ222/17', 
			2);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1648970, 
			'Mini Masajeador Glow', 
			--'Gama', 
			--'X4 MM-100F', 
			3);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(3122, 
			'Musculosa', 
			--'Basement', 
			--'Pale', 
			3);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1721678, 
			'Cartera', 
			--'Basement', 
			--'FIO2003', 
			1);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1741046, 
			'Reloj Negro Hombre', 
			--'Montreal', 
			--'MZ-24', 
			2);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1456328, 
			'Joggin Deportivo', 
			--'Nike', 
			--'Deporte', 
			3);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1672839, 
			'Zapatos', 
			--'Harris & Frank', 
			--'Zapato de vestir', 
			1);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1857363, 
			'Mesa para TV 21', 
			--'Mica', 
			--'wengue', 
			2);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1660104, 
			'Sillon Nahuel 1', 
			--'Mica', 
			--'', 
			3);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1849382, 
			'Mesa de PC Básica', 
			--'Mica', 
			--'Wengue 82 x 60 x 39', 
			1);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1607594, 
			'Juego de Jardín', 
			--'Mica', 
			--'Humbert Chocolate', 
			2);

insert into Articulos (idArticulo, descripcion, marca, modelo, idDeposito) 
	values	(1802975, 
			'Banqueta Baja', 
			--'Ambienta', 
			--'Thonet -c/NG-770', 
			3);

