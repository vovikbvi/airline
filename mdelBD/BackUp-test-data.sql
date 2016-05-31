--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-06-01 00:35:03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = "public", pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 204 (class 1259 OID 33831)
-- Name: airport; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "airport" (
    "id" integer NOT NULL,
    "name" character varying(20) NOT NULL,
    "code_iata" character varying(3) NOT NULL,
    "code_icao" character varying(4),
    "city_id" integer,
    "class_weight" integer DEFAULT 0 NOT NULL,
    "coordinates_x" numeric,
    "coordinates_y" numeric
);


--
-- TOC entry 205 (class 1259 OID 33838)
-- Name: airport_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "airport_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2303 (class 0 OID 0)
-- Dependencies: 205
-- Name: airport_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "airport_id_seq" OWNED BY "airport"."id";


--
-- TOC entry 206 (class 1259 OID 33840)
-- Name: city; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "city" (
    "id" integer NOT NULL,
    "name" character varying(50),
    "country_id" integer,
    "timezone" numeric
);


--
-- TOC entry 207 (class 1259 OID 33846)
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "city_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2304 (class 0 OID 0)
-- Dependencies: 207
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "city_id_seq" OWNED BY "city"."id";


--
-- TOC entry 208 (class 1259 OID 33848)
-- Name: country; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "country" (
    "id" integer NOT NULL,
    "name" character varying(50)
);


--
-- TOC entry 209 (class 1259 OID 33851)
-- Name: country_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "country_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2305 (class 0 OID 0)
-- Dependencies: 209
-- Name: country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "country_id_seq" OWNED BY "country"."id";


--
-- TOC entry 210 (class 1259 OID 33853)
-- Name: flight; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "flight" (
    "id" integer NOT NULL,
    "flight_catalog_id" integer,
    "registr_time" timestamp without time zone,
    "departure_time" timestamp without time zone,
    "arrival_time" timestamp without time zone,
    "plane_id" integer,
    "start_sale_ticket" timestamp without time zone
);


--
-- TOC entry 211 (class 1259 OID 33856)
-- Name: flight_catalog; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "flight_catalog" (
    "id" integer NOT NULL,
    "airport_start_id" integer,
    "airport_finish_id" integer,
    "distance" integer,
    "international" boolean
);


--
-- TOC entry 212 (class 1259 OID 33859)
-- Name: flight_catalog_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "flight_catalog_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2306 (class 0 OID 0)
-- Dependencies: 212
-- Name: flight_catalog_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "flight_catalog_id_seq" OWNED BY "flight_catalog"."id";


--
-- TOC entry 213 (class 1259 OID 33861)
-- Name: flight_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "flight_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2307 (class 0 OID 0)
-- Dependencies: 213
-- Name: flight_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "flight_id_seq" OWNED BY "flight"."id";


--
-- TOC entry 214 (class 1259 OID 33863)
-- Name: manufactured_plane; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "manufactured_plane" (
    "id" integer NOT NULL,
    "name" character varying(50) NOT NULL
);


--
-- TOC entry 215 (class 1259 OID 33866)
-- Name: manufactured_plane_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "manufactured_plane_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2308 (class 0 OID 0)
-- Dependencies: 215
-- Name: manufactured_plane_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "manufactured_plane_id_seq" OWNED BY "manufactured_plane"."id";


--
-- TOC entry 216 (class 1259 OID 33868)
-- Name: model_plane; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "model_plane" (
    "id" integer NOT NULL,
    "manufactured_plane_id" integer,
    "model" character varying(50),
    "col_passangers_buisnes" integer DEFAULT 0 NOT NULL,
    "col_passangers_firstclass" integer DEFAULT 0 NOT NULL,
    "col_passangers_economy" integer DEFAULT 0 NOT NULL,
    "weight_all_baggage" integer NOT NULL,
    "avg_speed" integer,
    "class_weight" integer DEFAULT 2 NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 33875)
-- Name: model_plane_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "model_plane_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2309 (class 0 OID 0)
-- Dependencies: 217
-- Name: model_plane_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "model_plane_id_seq" OWNED BY "model_plane"."id";


--
-- TOC entry 218 (class 1259 OID 33877)
-- Name: plane; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "plane" (
    "id" integer NOT NULL,
    "bort_number" character varying(20),
    "model_plane_id" integer
);


--
-- TOC entry 219 (class 1259 OID 33880)
-- Name: plane_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "plane_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2310 (class 0 OID 0)
-- Dependencies: 219
-- Name: plane_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "plane_id_seq" OWNED BY "plane"."id";


--
-- TOC entry 220 (class 1259 OID 33882)
-- Name: price; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "price" (
    "id" integer NOT NULL,
    "data_change" timestamp without time zone,
    "basic_price" numeric
);


--
-- TOC entry 221 (class 1259 OID 33888)
-- Name: price_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "price_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2311 (class 0 OID 0)
-- Dependencies: 221
-- Name: price_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "price_id_seq" OWNED BY "price"."id";


--
-- TOC entry 222 (class 1259 OID 33890)
-- Name: ticket; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "ticket" (
    "id" integer NOT NULL,
    "flight_id" integer,
    "user_profile_id" integer,
    "paid" boolean DEFAULT false,
    "number_seats" integer,
    "date_bought" timestamp without time zone,
    "baggage" boolean DEFAULT false,
    "weight_baggage" numeric DEFAULT 0.0 NOT NULL,
    "ticket_tupe" integer,
    "ticket_class" integer,
    "priority_registration" boolean DEFAULT false,
    "priority_seats" boolean DEFAULT false,
    "costs" numeric,
    "for_baby" boolean DEFAULT false
);


--
-- TOC entry 223 (class 1259 OID 33902)
-- Name: ticket_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "ticket_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2312 (class 0 OID 0)
-- Dependencies: 223
-- Name: ticket_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "ticket_id_seq" OWNED BY "ticket"."id";


--
-- TOC entry 224 (class 1259 OID 33904)
-- Name: user_profile; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "user_profile" (
    "id" integer NOT NULL,
    "login" character varying(50),
    "password" character varying(50),
    "first_name" character varying(50) NOT NULL,
    "last_name" character varying(50) NOT NULL,
    "email" character varying(100),
    "passport_number" character varying(20),
    "phone_number" character varying(20),
    "count_oder" integer DEFAULT 0,
    "vip" boolean,
    "date_registr" timestamp without time zone,
    "role" integer,
    "acept_registr" boolean DEFAULT false
);


--
-- TOC entry 225 (class 1259 OID 33909)
-- Name: user_profile_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE "user_profile_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2313 (class 0 OID 0)
-- Dependencies: 225
-- Name: user_profile_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE "user_profile_id_seq" OWNED BY "user_profile"."id";


--
-- TOC entry 2106 (class 2604 OID 33911)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "airport" ALTER COLUMN "id" SET DEFAULT "nextval"('"airport_id_seq"'::"regclass");


--
-- TOC entry 2107 (class 2604 OID 33912)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "city" ALTER COLUMN "id" SET DEFAULT "nextval"('"city_id_seq"'::"regclass");


--
-- TOC entry 2108 (class 2604 OID 33913)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "country" ALTER COLUMN "id" SET DEFAULT "nextval"('"country_id_seq"'::"regclass");


--
-- TOC entry 2109 (class 2604 OID 33914)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "flight" ALTER COLUMN "id" SET DEFAULT "nextval"('"flight_id_seq"'::"regclass");


--
-- TOC entry 2110 (class 2604 OID 33915)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "flight_catalog" ALTER COLUMN "id" SET DEFAULT "nextval"('"flight_catalog_id_seq"'::"regclass");


--
-- TOC entry 2111 (class 2604 OID 33916)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "manufactured_plane" ALTER COLUMN "id" SET DEFAULT "nextval"('"manufactured_plane_id_seq"'::"regclass");


--
-- TOC entry 2116 (class 2604 OID 33917)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "model_plane" ALTER COLUMN "id" SET DEFAULT "nextval"('"model_plane_id_seq"'::"regclass");


--
-- TOC entry 2117 (class 2604 OID 33918)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "plane" ALTER COLUMN "id" SET DEFAULT "nextval"('"plane_id_seq"'::"regclass");


--
-- TOC entry 2118 (class 2604 OID 33919)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "price" ALTER COLUMN "id" SET DEFAULT "nextval"('"price_id_seq"'::"regclass");


--
-- TOC entry 2125 (class 2604 OID 33920)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "ticket" ALTER COLUMN "id" SET DEFAULT "nextval"('"ticket_id_seq"'::"regclass");


--
-- TOC entry 2128 (class 2604 OID 33921)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "user_profile" ALTER COLUMN "id" SET DEFAULT "nextval"('"user_profile_id_seq"'::"regclass");


--
-- TOC entry 2277 (class 0 OID 33831)
-- Dependencies: 204
-- Data for Name: airport; Type: TABLE DATA; Schema: public; Owner: -
--

COPY "airport" ("id", "name", "code_iata", "code_icao", "city_id", "class_weight", "coordinates_x", "coordinates_y") FROM stdin;
1	Minsk	MNS	\N	2	2	\N	\N
2	Grodno	GRN	\N	1	2	\N	\N
3	Bialystok	BLS	\N	3	2	\N	\N
4	Domodedovo	DMD	\N	4	2	\N	\N
\.


--
-- TOC entry 2314 (class 0 OID 0)
-- Dependencies: 205
-- Name: airport_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"airport_id_seq"', 4, true);


--
-- TOC entry 2279 (class 0 OID 33840)
-- Dependencies: 206
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: -
--

COPY "city" ("id", "name", "country_id", "timezone") FROM stdin;
1	Grodno	2	3
2	Minsk	2	3
3	Bialystik	1	2
4	Moskow	3	4
\.


--
-- TOC entry 2315 (class 0 OID 0)
-- Dependencies: 207
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"city_id_seq"', 4, true);


--
-- TOC entry 2281 (class 0 OID 33848)
-- Dependencies: 208
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: -
--

COPY "country" ("id", "name") FROM stdin;
1	Poland
2	Belarussian
3	Russia
4	The UK
\.


--
-- TOC entry 2316 (class 0 OID 0)
-- Dependencies: 209
-- Name: country_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"country_id_seq"', 4, true);


--
-- TOC entry 2283 (class 0 OID 33853)
-- Dependencies: 210
-- Data for Name: flight; Type: TABLE DATA; Schema: public; Owner: -
--

COPY "flight" ("id", "flight_catalog_id", "registr_time", "departure_time", "arrival_time", "plane_id", "start_sale_ticket") FROM stdin;
1	2	2016-05-04 00:00:00	2016-05-04 00:00:00	2016-05-04 00:00:00	1	2016-05-02 00:00:00
2	5	2016-05-04 00:00:00	2016-05-04 00:00:00	2016-05-04 00:00:00	1	2016-05-01 00:00:00
3	7	2016-05-04 00:00:00	2016-05-04 00:00:00	2016-05-04 00:00:00	1	2016-05-30 00:00:00
4	1	2016-05-04 00:00:00	2016-05-04 00:00:00	2016-05-04 00:00:00	1	2016-05-05 00:00:00
\.


--
-- TOC entry 2284 (class 0 OID 33856)
-- Dependencies: 211
-- Data for Name: flight_catalog; Type: TABLE DATA; Schema: public; Owner: -
--

COPY "flight_catalog" ("id", "airport_start_id", "airport_finish_id", "distance", "international") FROM stdin;
1	1	2	300	t
2	2	1	300	t
3	1	4	800	f
4	4	1	800	f
5	2	4	1000	f
6	2	3	100	f
7	3	2	100	f
\.


--
-- TOC entry 2317 (class 0 OID 0)
-- Dependencies: 212
-- Name: flight_catalog_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"flight_catalog_id_seq"', 7, true);


--
-- TOC entry 2318 (class 0 OID 0)
-- Dependencies: 213
-- Name: flight_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"flight_id_seq"', 4, true);


--
-- TOC entry 2287 (class 0 OID 33863)
-- Dependencies: 214
-- Data for Name: manufactured_plane; Type: TABLE DATA; Schema: public; Owner: -
--

COPY "manufactured_plane" ("id", "name") FROM stdin;
1	Boing
\.


--
-- TOC entry 2319 (class 0 OID 0)
-- Dependencies: 215
-- Name: manufactured_plane_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"manufactured_plane_id_seq"', 1, true);


--
-- TOC entry 2289 (class 0 OID 33868)
-- Dependencies: 216
-- Data for Name: model_plane; Type: TABLE DATA; Schema: public; Owner: -
--

COPY "model_plane" ("id", "manufactured_plane_id", "model", "col_passangers_buisnes", "col_passangers_firstclass", "col_passangers_economy", "weight_all_baggage", "avg_speed", "class_weight") FROM stdin;
1	1	Boing-745	30	10	10	500	300	2
\.


--
-- TOC entry 2320 (class 0 OID 0)
-- Dependencies: 217
-- Name: model_plane_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"model_plane_id_seq"', 1, true);


--
-- TOC entry 2291 (class 0 OID 33877)
-- Dependencies: 218
-- Data for Name: plane; Type: TABLE DATA; Schema: public; Owner: -
--

COPY "plane" ("id", "bort_number", "model_plane_id") FROM stdin;
1	WE-3443	1
\.


--
-- TOC entry 2321 (class 0 OID 0)
-- Dependencies: 219
-- Name: plane_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"plane_id_seq"', 1, true);


--
-- TOC entry 2293 (class 0 OID 33882)
-- Dependencies: 220
-- Data for Name: price; Type: TABLE DATA; Schema: public; Owner: -
--

COPY "price" ("id", "data_change", "basic_price") FROM stdin;
1	2016-04-01 00:00:00	0.2
\.


--
-- TOC entry 2322 (class 0 OID 0)
-- Dependencies: 221
-- Name: price_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"price_id_seq"', 1, true);


--
-- TOC entry 2295 (class 0 OID 33890)
-- Dependencies: 222
-- Data for Name: ticket; Type: TABLE DATA; Schema: public; Owner: -
--

COPY "ticket" ("id", "flight_id", "user_profile_id", "paid", "number_seats", "date_bought", "baggage", "weight_baggage", "ticket_tupe", "ticket_class", "priority_registration", "priority_seats", "costs", "for_baby") FROM stdin;
1	1	2	t	4	2016-06-01 00:15:34.331	t	45	0	0	f	f	-21	f
2	4	2	\N	2	2016-06-01 00:30:06.544	t	12	0	0	f	f	463.2	f
\.


--
-- TOC entry 2323 (class 0 OID 0)
-- Dependencies: 223
-- Name: ticket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"ticket_id_seq"', 2, true);


--
-- TOC entry 2297 (class 0 OID 33904)
-- Dependencies: 224
-- Data for Name: user_profile; Type: TABLE DATA; Schema: public; Owner: -
--

COPY "user_profile" ("id", "login", "password", "first_name", "last_name", "email", "passport_number", "phone_number", "count_oder", "vip", "date_registr", "role", "acept_registr") FROM stdin;
2	vovik	1	Bogdevich	Vladimir	vovikbvi@tut.by	HJJG234243RB8	1212121212	0	f	\N	0	f
3	User	1	First	Last	user@qwq.qw	23423423423	23423423423	0	f	2016-05-31 23:05:11.768	2	f
\.


--
-- TOC entry 2324 (class 0 OID 0)
-- Dependencies: 225
-- Name: user_profile_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('"user_profile_id_seq"', 3, true);


--
-- TOC entry 2130 (class 2606 OID 33923)
-- Name: airport_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "airport"
    ADD CONSTRAINT "airport_pk" PRIMARY KEY ("id");


--
-- TOC entry 2132 (class 2606 OID 33925)
-- Name: city_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "city"
    ADD CONSTRAINT "city_pk" PRIMARY KEY ("id");


--
-- TOC entry 2134 (class 2606 OID 33927)
-- Name: country_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "country"
    ADD CONSTRAINT "country_pk" PRIMARY KEY ("id");


--
-- TOC entry 2138 (class 2606 OID 33929)
-- Name: flight_catalog_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "flight_catalog"
    ADD CONSTRAINT "flight_catalog_pk" PRIMARY KEY ("id");


--
-- TOC entry 2136 (class 2606 OID 33931)
-- Name: flight_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "flight"
    ADD CONSTRAINT "flight_pk" PRIMARY KEY ("id");


--
-- TOC entry 2140 (class 2606 OID 33933)
-- Name: manufactured_plane_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "manufactured_plane"
    ADD CONSTRAINT "manufactured_plane_pk" PRIMARY KEY ("id");


--
-- TOC entry 2142 (class 2606 OID 33935)
-- Name: model_plane_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "model_plane"
    ADD CONSTRAINT "model_plane_pk" PRIMARY KEY ("id");


--
-- TOC entry 2144 (class 2606 OID 33937)
-- Name: plane_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "plane"
    ADD CONSTRAINT "plane_pk" PRIMARY KEY ("id");


--
-- TOC entry 2146 (class 2606 OID 33939)
-- Name: price_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "price"
    ADD CONSTRAINT "price_pk" PRIMARY KEY ("id");


--
-- TOC entry 2148 (class 2606 OID 33941)
-- Name: ticket_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "ticket"
    ADD CONSTRAINT "ticket_pk" PRIMARY KEY ("id");


--
-- TOC entry 2150 (class 2606 OID 33943)
-- Name: user_profile_login_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "user_profile"
    ADD CONSTRAINT "user_profile_login_key" UNIQUE ("login");


--
-- TOC entry 2152 (class 2606 OID 33945)
-- Name: user_profile_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "user_profile"
    ADD CONSTRAINT "user_profile_pk" PRIMARY KEY ("id");


--
-- TOC entry 2153 (class 2606 OID 33946)
-- Name: airport_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "airport"
    ADD CONSTRAINT "airport_fk0" FOREIGN KEY ("city_id") REFERENCES "city"("id");


--
-- TOC entry 2154 (class 2606 OID 33951)
-- Name: city_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "city"
    ADD CONSTRAINT "city_fk0" FOREIGN KEY ("country_id") REFERENCES "country"("id");


--
-- TOC entry 2157 (class 2606 OID 33956)
-- Name: flight_catalog_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "flight_catalog"
    ADD CONSTRAINT "flight_catalog_fk0" FOREIGN KEY ("airport_start_id") REFERENCES "airport"("id");


--
-- TOC entry 2158 (class 2606 OID 33961)
-- Name: flight_catalog_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "flight_catalog"
    ADD CONSTRAINT "flight_catalog_fk1" FOREIGN KEY ("airport_finish_id") REFERENCES "airport"("id");


--
-- TOC entry 2155 (class 2606 OID 33966)
-- Name: flight_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "flight"
    ADD CONSTRAINT "flight_fk0" FOREIGN KEY ("flight_catalog_id") REFERENCES "flight_catalog"("id");


--
-- TOC entry 2156 (class 2606 OID 33971)
-- Name: flight_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "flight"
    ADD CONSTRAINT "flight_fk1" FOREIGN KEY ("plane_id") REFERENCES "plane"("id");


--
-- TOC entry 2159 (class 2606 OID 33976)
-- Name: model_plane_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "model_plane"
    ADD CONSTRAINT "model_plane_fk0" FOREIGN KEY ("manufactured_plane_id") REFERENCES "manufactured_plane"("id");


--
-- TOC entry 2160 (class 2606 OID 33981)
-- Name: plane_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "plane"
    ADD CONSTRAINT "plane_fk0" FOREIGN KEY ("model_plane_id") REFERENCES "model_plane"("id");


--
-- TOC entry 2161 (class 2606 OID 33986)
-- Name: ticket_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "ticket"
    ADD CONSTRAINT "ticket_fk0" FOREIGN KEY ("flight_id") REFERENCES "flight"("id");


--
-- TOC entry 2162 (class 2606 OID 33991)
-- Name: ticket_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "ticket"
    ADD CONSTRAINT "ticket_fk1" FOREIGN KEY ("user_profile_id") REFERENCES "user_profile"("id");


-- Completed on 2016-06-01 00:35:04

--
-- PostgreSQL database dump complete
--

