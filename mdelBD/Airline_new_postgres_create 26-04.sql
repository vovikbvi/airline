CREATE TABLE "flight_catalog" (
	"id" serial NOT NULL,
	"airport_start_id" int,
	"airport_finish_id" int,
	"distance" int,
	"international" BOOLEAN,
	CONSTRAINT flight_catalog_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "airport" (
	"id" serial NOT NULL,
	"name" character varying(20) NOT NULL,
	"code_iata" character varying(3) NOT NULL,
	"code_icao" character varying(4),
	"city_id" int,
	"class_weight" int NOT NULL DEFAULT '0',
	"coordinates_x" DECIMAL,
	"coordinates_y" DECIMAL,
	CONSTRAINT airport_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "city" (
	"id" serial NOT NULL,
	"name" character varying(50),
	"country_id" int,
	"timezone" DECIMAL,
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
	"flight_catalog_id" int,
	"registr_time" TIMESTAMP,
	"departure_time" TIMESTAMP,
	"arrival_time" TIMESTAMP,
	"plane_id" int,
	"start_sale_ticket" TIMESTAMP,
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
	"manufactured_plane_id" int,
	"model" character varying(50),
	"col_passangers_buisnes" int NOT NULL DEFAULT '0',
	"col_passangers_firstclass" int NOT NULL DEFAULT '0',
	"col_passangers_economy" int NOT NULL DEFAULT '0',
	"weight_all_baggage" int NOT NULL,
	"avg_speed" int,
	"class_weight" int NOT NULL DEFAULT '2',
	CONSTRAINT model_plane_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "manufactured_plane" (
	"id" serial NOT NULL,
	"name" character varying(50) NOT NULL,
	CONSTRAINT manufactured_plane_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "ticket" (
	"id" serial NOT NULL,
	"flight_id" int,
	"user_profile_id" int,
	"paid" BOOLEAN DEFAULT '0',
	"number_seats" int,
	"date_bought" TIMESTAMP,
	"baggage" BOOLEAN DEFAULT '0',
	"weight_baggage" DECIMAL NOT NULL DEFAULT '0.0',
	"ticket_tupe" int,
	"ticket_class" int,
	"priority_registration" BOOLEAN DEFAULT '0',
	"priority_seats" BOOLEAN DEFAULT '0',
	"costs" DECIMAL,
	"for_baby" BOOLEAN DEFAULT '0',
	CONSTRAINT ticket_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_profile" (
	"id" serial NOT NULL,
	"login" character varying(50) UNIQUE,
	"password" character varying(50),
	"first_name" character varying(50) NOT NULL,
	"last_name" character varying(50) NOT NULL,
	"email" character varying(100),
	"passport_number" character varying(20),
	"phone_number" character varying(20),
	"count_oder" int DEFAULT '0',
	"vip" BOOLEAN,
	"date_registr" TIMESTAMP,
	"role" int,
	"acept_registr" BOOLEAN DEFAULT '0',
	CONSTRAINT user_profile_pk PRIMARY KEY ("id")
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



ALTER TABLE "flight_catalog" ADD CONSTRAINT "flight_catalog_fk0" FOREIGN KEY ("airport_start_id") REFERENCES "airport"("id");
ALTER TABLE "flight_catalog" ADD CONSTRAINT "flight_catalog_fk1" FOREIGN KEY ("airport_finish_id") REFERENCES "airport"("id");

ALTER TABLE "airport" ADD CONSTRAINT "airport_fk0" FOREIGN KEY ("city_id") REFERENCES "city"("id");

ALTER TABLE "city" ADD CONSTRAINT "city_fk0" FOREIGN KEY ("country_id") REFERENCES "country"("id");


ALTER TABLE "flight" ADD CONSTRAINT "flight_fk0" FOREIGN KEY ("flight_catalog_id") REFERENCES "flight_catalog"("id");
ALTER TABLE "flight" ADD CONSTRAINT "flight_fk1" FOREIGN KEY ("plane_id") REFERENCES "plane"("id");

ALTER TABLE "plane" ADD CONSTRAINT "plane_fk0" FOREIGN KEY ("model_plane_id") REFERENCES "model_plane"("id");

ALTER TABLE "model_plane" ADD CONSTRAINT "model_plane_fk0" FOREIGN KEY ("manufactured_plane_id") REFERENCES "manufactured_plane"("id");


ALTER TABLE "ticket" ADD CONSTRAINT "ticket_fk0" FOREIGN KEY ("flight_id") REFERENCES "flight"("id");
ALTER TABLE "ticket" ADD CONSTRAINT "ticket_fk1" FOREIGN KEY ("user_profile_id") REFERENCES "user_profile"("id");



