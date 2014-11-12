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
