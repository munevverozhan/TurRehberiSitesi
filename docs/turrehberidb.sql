create table admins
(
admins_id integer primary key not null,
	mail varchar,
    passwords varchar
);


create table holidays
(
holidays_id integer primary key not null,
	type_id integer,
	holiday_date date,
	
	admins_id integer references admins(admins_id)
);


create table users
(
users_id integer primary key not null,
	mail varchar not null,
	passwords varchar,
	first_name varchar,
	last_name varchar
	

);

create table tours_car
(
tours_car_id integer primary key not null,
	car_type varchar ,
	driver_name varchar 
);

create table hotels
(
hotels_id integer primary key not null,
	hotel_date date,
	hotel_area  varchar ,
	users_id integer references users(users_id)
);

create table agreement
(
agrement_id integer primary key not null,
	agreement_date date,
	
	users_id integer references users(users_id)
);

create table proposal
(
proposal_id integer primary key not null,
	type_id integer
);

create table consideration 
(
consideration_id integer primary key not null
    
);

create table tour_guide
(
tour_guide_id integer primary key not null,
	guide_name varchar ,
	guide_language varchar,
	holidays_id integer references holidays(holidays_id)
);
create table kullanici_tatil
(
	kullanici_tatil_id integer primary key not null,
    holidays_id integer references holidays(holidays_id),
	users_id integer references users(users_id)
	
	
);

create table tur_oneri
(
tur_oneri_id integer primary key not null,
	tour_guide_id integer references tour_guide(tour_guide_id),
	proposal_id integer references proposal(proposal_id)
);

create table kullanici_deger
(
kullanici_deger_id integer primary key not null,
	users_id integer references users(users_id),
	consideration_id integer references consideration(consideration_id) 
);
