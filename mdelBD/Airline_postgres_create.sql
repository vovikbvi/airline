CREATE TABLE "flights_catalog" (
	"id" serial NOT NULL,
	"airport_start" int,
	"airport_finish" int,
	"distance" int,
	"international" BOOLEAN,
	CONSTRAINT flights_catalog_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "airport" (
	"id" serial NOT NULL,
	"code_iata" character varying(3) NOT NULL,
	"code_icao" character varying(4) NOT NULL,
	"city" int,
	"class_weight" int,
	"coordinates_X" DECIMAL NOT NULL,
	"coordinates_Y" DECIMAL NOT NULL,
	CONSTRAINT airport_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "city" (
	"id" serial NOT NULL,
	"name" character varying(50),
	"country_id" int,
	CONSTRAINT city_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "country" (
	"id" serial NOT NULL,
	"name" character varying(50),
	CONSTRAINT country_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "flight" (
	"id" serial NOT NULL,
	"flights_catalog_id" int,
	"registr_time" TIMESTAMP,
	"departure_time" TIMESTAMP,
	"arrival_time" TIMESTAMP,
	"plane_id" int,
	"start_sale_ticket" DATE,
	CONSTRAINT flight_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "plane" (
	"id" serial NOT NULL,
	"bort_number" character varying(20),
	"model_plane_id" int,
	CONSTRAINT plane_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "model_plane" (
	"id" serial NOT NULL,
	"manufacturad_id" int,
	"model" character varying(50),
	"col_passangers_buisnes" int NOT NULL DEFAULT '0',
	"col_passangers_firstclass" int NOT NULL DEFAULT '0',
	"col_passangers_economy" int NOT NULL DEFAULT '0',
	"wieight_all_baggage" int NOT NULL,
	"avg_speed" int NOT NULL,
	"class_weight" int,
	CONSTRAINT model_plane_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "manufactured_plain" (
	"id" serial NOT NULL,
	"name" character varying(50) NOT NULL,
	CONSTRAINT manufactured_plain_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "ticket" (
	"id" serial NOT NULL,
	"flight_id" int,
	"passanger_id" int,
	"paid" BOOLEAN,
	"nember_seats" int,
	"date_bouhgt" TIMESTAMP,
	"baggage" BOOLEAN,
	"weight_baggage" DECIMAL NOT NULL,
	"ticket_tupe" int,
	"ticket_class" int,
	"priority_registration" BOOLEAN,
	"priority_seats" BOOLEAN,
	"costs" DECIMAL,
	"for_baby" BOOLEAN,
	CONSTRAINT ticket_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user" (
	"id" serial NOT NULL,
	"login" character varying(50) UNIQUE,
	"password" character varying(50),
	"first_name" character varying(50) NOT NULL,
	"last_name" character varying(50) NOT NULL,
	"email" character varying(100),
	"pasport_number" character varying(20),
	"phone_number" character varying(20) NOT NULL,
	"count_oder" int NOT NULL,
	"vip" BOOLEAN,
	"role" int,
	CONSTRAINT user_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "price" (
	"id" serial NOT NULL,
	"data_change" TIMESTAMP,
	"basic_price" DECIMAL,
	CONSTRAINT price_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "flights_catalog" ADD CONSTRAINT "flights_catalog_fk0" FOREIGN KEY ("airport_start") REFERENCES "airport"("id");
ALTER TABLE "flights_catalog" ADD CONSTRAINT "flights_catalog_fk1" FOREIGN KEY ("airport_finish") REFERENCES "airport"("id");

ALTER TABLE "airport" ADD CONSTRAINT "airport_fk0" FOREIGN KEY ("city") REFERENCES "city"("id");

ALTER TABLE "city" ADD CONSTRAINT "city_fk0" FOREIGN KEY ("country_id") REFERENCES "country"("id");


ALTER TABLE "flight" ADD CONSTRAINT "flight_fk0" FOREIGN KEY ("flights_catalog_id") REFERENCES "flights_catalog"("id");
ALTER TABLE "flight" ADD CONSTRAINT "flight_fk1" FOREIGN KEY ("plane_id") REFERENCES "plane"("id");

ALTER TABLE "plane" ADD CONSTRAINT "plane_fk0" FOREIGN KEY ("model_plane_id") REFERENCES "model_plane"("id");

ALTER TABLE "model_plane" ADD CONSTRAINT "model_plane_fk0" FOREIGN KEY ("manufacturad_id") REFERENCES "manufactured_plain"("id");


ALTER TABLE "ticket" ADD CONSTRAINT "ticket_fk0" FOREIGN KEY ("flight_id") REFERENCES "flight"("id");
ALTER TABLE "ticket" ADD CONSTRAINT "ticket_fk1" FOREIGN KEY ("passanger_id") REFERENCES "user"("id");



