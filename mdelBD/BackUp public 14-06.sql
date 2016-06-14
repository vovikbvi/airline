--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-06-14 15:55:53

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 19832)
-- Name: test; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA test;


--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2395 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 204 (class 1259 OID 20184)
-- Name: airport; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE airport (
    id integer NOT NULL,
    name character varying(20) NOT NULL,
    code_iata character varying(3) NOT NULL,
    code_icao character varying(4),
    city_id integer,
    class_weight integer DEFAULT 0 NOT NULL,
    coordinates_x numeric,
    coordinates_y numeric
);


--
-- TOC entry 205 (class 1259 OID 20191)
-- Name: airport_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE airport_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2396 (class 0 OID 0)
-- Dependencies: 205
-- Name: airport_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE airport_id_seq OWNED BY airport.id;


--
-- TOC entry 206 (class 1259 OID 20193)
-- Name: city; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE city (
    id integer NOT NULL,
    name character varying(50),
    country_id integer,
    timezone numeric
);


--
-- TOC entry 207 (class 1259 OID 20199)
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2397 (class 0 OID 0)
-- Dependencies: 207
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE city_id_seq OWNED BY city.id;


--
-- TOC entry 208 (class 1259 OID 20201)
-- Name: country; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE country (
    id integer NOT NULL,
    name character varying(50)
);


--
-- TOC entry 209 (class 1259 OID 20204)
-- Name: country_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2398 (class 0 OID 0)
-- Dependencies: 209
-- Name: country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE country_id_seq OWNED BY country.id;


--
-- TOC entry 210 (class 1259 OID 20206)
-- Name: flight; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE flight (
    id integer NOT NULL,
    flight_catalog_id integer,
    registr_time timestamp without time zone,
    departure_time timestamp without time zone,
    arrival_time timestamp without time zone,
    plane_id integer,
    start_sale_ticket timestamp without time zone
);


--
-- TOC entry 211 (class 1259 OID 20209)
-- Name: flight_catalog; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE flight_catalog (
    id integer NOT NULL,
    airport_start_id integer,
    airport_finish_id integer,
    distance integer,
    international boolean
);


--
-- TOC entry 212 (class 1259 OID 20212)
-- Name: flight_catalog_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE flight_catalog_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2399 (class 0 OID 0)
-- Dependencies: 212
-- Name: flight_catalog_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE flight_catalog_id_seq OWNED BY flight_catalog.id;


--
-- TOC entry 213 (class 1259 OID 20214)
-- Name: flight_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE flight_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2400 (class 0 OID 0)
-- Dependencies: 213
-- Name: flight_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE flight_id_seq OWNED BY flight.id;


--
-- TOC entry 214 (class 1259 OID 20216)
-- Name: manufactured_plane; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE manufactured_plane (
    id integer NOT NULL,
    name character varying(50) NOT NULL
);


--
-- TOC entry 215 (class 1259 OID 20219)
-- Name: manufactured_plane_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE manufactured_plane_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2401 (class 0 OID 0)
-- Dependencies: 215
-- Name: manufactured_plane_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE manufactured_plane_id_seq OWNED BY manufactured_plane.id;


--
-- TOC entry 216 (class 1259 OID 20221)
-- Name: model_plane; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE model_plane (
    id integer NOT NULL,
    manufactured_plane_id integer,
    model character varying(50),
    col_passangers_buisnes integer DEFAULT 0 NOT NULL,
    col_passangers_firstclass integer DEFAULT 0 NOT NULL,
    col_passangers_economy integer DEFAULT 0 NOT NULL,
    weight_all_baggage integer NOT NULL,
    avg_speed integer,
    class_weight integer DEFAULT 2 NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 20228)
-- Name: model_plane_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE model_plane_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2402 (class 0 OID 0)
-- Dependencies: 217
-- Name: model_plane_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE model_plane_id_seq OWNED BY model_plane.id;


--
-- TOC entry 218 (class 1259 OID 20230)
-- Name: plane; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE plane (
    id integer NOT NULL,
    bort_number character varying(20),
    model_plane_id integer
);


--
-- TOC entry 219 (class 1259 OID 20233)
-- Name: plane_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE plane_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2403 (class 0 OID 0)
-- Dependencies: 219
-- Name: plane_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE plane_id_seq OWNED BY plane.id;


--
-- TOC entry 220 (class 1259 OID 20235)
-- Name: price; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE price (
    id integer NOT NULL,
    data_change timestamp without time zone,
    basic_price numeric
);


--
-- TOC entry 221 (class 1259 OID 20241)
-- Name: price_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE price_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2404 (class 0 OID 0)
-- Dependencies: 221
-- Name: price_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE price_id_seq OWNED BY price.id;


--
-- TOC entry 222 (class 1259 OID 20243)
-- Name: ticket; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE ticket (
    id integer NOT NULL,
    flight_id integer,
    user_profile_id integer,
    paid boolean DEFAULT false,
    number_seats integer,
    date_bought timestamp without time zone,
    baggage boolean DEFAULT false,
    weight_baggage numeric DEFAULT 0.0,
    ticket_tupe integer,
    ticket_class integer,
    priority_registration boolean DEFAULT false,
    priority_seats boolean DEFAULT false,
    costs numeric,
    for_baby boolean DEFAULT false
);


--
-- TOC entry 223 (class 1259 OID 20255)
-- Name: ticket_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE ticket_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2405 (class 0 OID 0)
-- Dependencies: 223
-- Name: ticket_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE ticket_id_seq OWNED BY ticket.id;


--
-- TOC entry 224 (class 1259 OID 20257)
-- Name: user_profile; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_profile (
    id integer NOT NULL,
    login character varying(50),
    password character varying(50),
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(100),
    passport_number character varying(20),
    phone_number character varying(20),
    count_oder integer DEFAULT 0,
    vip boolean,
    date_registr timestamp without time zone,
    role integer,
    acept_registr boolean DEFAULT false
);


--
-- TOC entry 225 (class 1259 OID 20262)
-- Name: user_profile_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2406 (class 0 OID 0)
-- Dependencies: 225
-- Name: user_profile_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE user_profile_id_seq OWNED BY user_profile.id;


SET search_path = test, pg_catalog;

--
-- TOC entry 182 (class 1259 OID 19848)
-- Name: airport; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE airport (
    id integer NOT NULL,
    name character varying(20) NOT NULL,
    code_iata character varying(3) NOT NULL,
    code_icao character varying(4),
    city_id integer,
    class_weight integer DEFAULT 0 NOT NULL,
    coordinates_x numeric,
    coordinates_y numeric
);


--
-- TOC entry 183 (class 1259 OID 19855)
-- Name: airport_id_seq; Type: SEQUENCE; Schema: test; Owner: -
--

CREATE SEQUENCE airport_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2407 (class 0 OID 0)
-- Dependencies: 183
-- Name: airport_id_seq; Type: SEQUENCE OWNED BY; Schema: test; Owner: -
--

ALTER SEQUENCE airport_id_seq OWNED BY airport.id;


--
-- TOC entry 184 (class 1259 OID 19857)
-- Name: city; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE city (
    id integer NOT NULL,
    name character varying(50),
    country_id integer,
    timezone numeric
);


--
-- TOC entry 185 (class 1259 OID 19863)
-- Name: city_id_seq; Type: SEQUENCE; Schema: test; Owner: -
--

CREATE SEQUENCE city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2408 (class 0 OID 0)
-- Dependencies: 185
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: test; Owner: -
--

ALTER SEQUENCE city_id_seq OWNED BY city.id;


--
-- TOC entry 186 (class 1259 OID 19865)
-- Name: country; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE country (
    id integer NOT NULL,
    name character varying(50)
);


--
-- TOC entry 187 (class 1259 OID 19868)
-- Name: country_id_seq; Type: SEQUENCE; Schema: test; Owner: -
--

CREATE SEQUENCE country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2409 (class 0 OID 0)
-- Dependencies: 187
-- Name: country_id_seq; Type: SEQUENCE OWNED BY; Schema: test; Owner: -
--

ALTER SEQUENCE country_id_seq OWNED BY country.id;


--
-- TOC entry 188 (class 1259 OID 19870)
-- Name: flight; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE flight (
    id integer NOT NULL,
    flight_catalog_id integer,
    registr_time timestamp without time zone,
    departure_time timestamp without time zone,
    arrival_time timestamp without time zone,
    plane_id integer,
    start_sale_ticket timestamp without time zone
);


--
-- TOC entry 189 (class 1259 OID 19873)
-- Name: flight_catalog; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE flight_catalog (
    id integer NOT NULL,
    airport_start_id integer,
    airport_finish_id integer,
    distance integer,
    international boolean
);


--
-- TOC entry 190 (class 1259 OID 19876)
-- Name: flight_catalog_id_seq; Type: SEQUENCE; Schema: test; Owner: -
--

CREATE SEQUENCE flight_catalog_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2410 (class 0 OID 0)
-- Dependencies: 190
-- Name: flight_catalog_id_seq; Type: SEQUENCE OWNED BY; Schema: test; Owner: -
--

ALTER SEQUENCE flight_catalog_id_seq OWNED BY flight_catalog.id;


--
-- TOC entry 191 (class 1259 OID 19878)
-- Name: flight_id_seq; Type: SEQUENCE; Schema: test; Owner: -
--

CREATE SEQUENCE flight_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2411 (class 0 OID 0)
-- Dependencies: 191
-- Name: flight_id_seq; Type: SEQUENCE OWNED BY; Schema: test; Owner: -
--

ALTER SEQUENCE flight_id_seq OWNED BY flight.id;


--
-- TOC entry 192 (class 1259 OID 19880)
-- Name: manufactured_plane; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE manufactured_plane (
    id integer NOT NULL,
    name character varying(50) NOT NULL
);


--
-- TOC entry 193 (class 1259 OID 19883)
-- Name: manufactured_plane_id_seq; Type: SEQUENCE; Schema: test; Owner: -
--

CREATE SEQUENCE manufactured_plane_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2412 (class 0 OID 0)
-- Dependencies: 193
-- Name: manufactured_plane_id_seq; Type: SEQUENCE OWNED BY; Schema: test; Owner: -
--

ALTER SEQUENCE manufactured_plane_id_seq OWNED BY manufactured_plane.id;


--
-- TOC entry 194 (class 1259 OID 19885)
-- Name: model_plane; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE model_plane (
    id integer NOT NULL,
    manufactured_plane_id integer,
    model character varying(50),
    col_passangers_buisnes integer DEFAULT 0 NOT NULL,
    col_passangers_firstclass integer DEFAULT 0 NOT NULL,
    col_passangers_economy integer DEFAULT 0 NOT NULL,
    weight_all_baggage integer NOT NULL,
    avg_speed integer,
    class_weight integer DEFAULT 2 NOT NULL
);


--
-- TOC entry 195 (class 1259 OID 19892)
-- Name: model_plane_id_seq; Type: SEQUENCE; Schema: test; Owner: -
--

CREATE SEQUENCE model_plane_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2413 (class 0 OID 0)
-- Dependencies: 195
-- Name: model_plane_id_seq; Type: SEQUENCE OWNED BY; Schema: test; Owner: -
--

ALTER SEQUENCE model_plane_id_seq OWNED BY model_plane.id;


--
-- TOC entry 196 (class 1259 OID 19894)
-- Name: plane; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE plane (
    id integer NOT NULL,
    bort_number character varying(20),
    model_plane_id integer
);


--
-- TOC entry 197 (class 1259 OID 19897)
-- Name: plane_id_seq; Type: SEQUENCE; Schema: test; Owner: -
--

CREATE SEQUENCE plane_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2414 (class 0 OID 0)
-- Dependencies: 197
-- Name: plane_id_seq; Type: SEQUENCE OWNED BY; Schema: test; Owner: -
--

ALTER SEQUENCE plane_id_seq OWNED BY plane.id;


--
-- TOC entry 198 (class 1259 OID 19899)
-- Name: price; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE price (
    id integer NOT NULL,
    data_change timestamp without time zone,
    basic_price numeric
);


--
-- TOC entry 199 (class 1259 OID 19905)
-- Name: price_id_seq; Type: SEQUENCE; Schema: test; Owner: -
--

CREATE SEQUENCE price_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2415 (class 0 OID 0)
-- Dependencies: 199
-- Name: price_id_seq; Type: SEQUENCE OWNED BY; Schema: test; Owner: -
--

ALTER SEQUENCE price_id_seq OWNED BY price.id;


--
-- TOC entry 200 (class 1259 OID 19907)
-- Name: ticket; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE ticket (
    id integer NOT NULL,
    flight_id integer,
    user_profile_id integer,
    paid boolean DEFAULT false,
    number_seats integer,
    date_bought timestamp without time zone,
    baggage boolean DEFAULT false,
    weight_baggage numeric DEFAULT 0.0 NOT NULL,
    ticket_tupe integer,
    ticket_class integer,
    priority_registration boolean DEFAULT false,
    priority_seats boolean DEFAULT false,
    costs numeric,
    for_baby boolean DEFAULT false
);


--
-- TOC entry 201 (class 1259 OID 19919)
-- Name: ticket_id_seq; Type: SEQUENCE; Schema: test; Owner: -
--

CREATE SEQUENCE ticket_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2416 (class 0 OID 0)
-- Dependencies: 201
-- Name: ticket_id_seq; Type: SEQUENCE OWNED BY; Schema: test; Owner: -
--

ALTER SEQUENCE ticket_id_seq OWNED BY ticket.id;


--
-- TOC entry 202 (class 1259 OID 19921)
-- Name: user_profile; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE user_profile (
    id integer NOT NULL,
    login character varying(50),
    password character varying(50),
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(100),
    passport_number character varying(20),
    phone_number character varying(20),
    count_oder integer DEFAULT 0,
    vip boolean,
    date_registr timestamp without time zone,
    role integer,
    acept_registr boolean DEFAULT false
);


--
-- TOC entry 203 (class 1259 OID 19926)
-- Name: user_profile_id_seq; Type: SEQUENCE; Schema: test; Owner: -
--

CREATE SEQUENCE user_profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2417 (class 0 OID 0)
-- Dependencies: 203
-- Name: user_profile_id_seq; Type: SEQUENCE OWNED BY; Schema: test; Owner: -
--

ALTER SEQUENCE user_profile_id_seq OWNED BY user_profile.id;


SET search_path = public, pg_catalog;

--
-- TOC entry 2141 (class 2604 OID 20264)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY airport ALTER COLUMN id SET DEFAULT nextval('airport_id_seq'::regclass);


--
-- TOC entry 2142 (class 2604 OID 20265)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY city ALTER COLUMN id SET DEFAULT nextval('city_id_seq'::regclass);


--
-- TOC entry 2143 (class 2604 OID 20266)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY country ALTER COLUMN id SET DEFAULT nextval('country_id_seq'::regclass);


--
-- TOC entry 2144 (class 2604 OID 20267)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight ALTER COLUMN id SET DEFAULT nextval('flight_id_seq'::regclass);


--
-- TOC entry 2145 (class 2604 OID 20268)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight_catalog ALTER COLUMN id SET DEFAULT nextval('flight_catalog_id_seq'::regclass);


--
-- TOC entry 2146 (class 2604 OID 20269)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY manufactured_plane ALTER COLUMN id SET DEFAULT nextval('manufactured_plane_id_seq'::regclass);


--
-- TOC entry 2151 (class 2604 OID 20270)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY model_plane ALTER COLUMN id SET DEFAULT nextval('model_plane_id_seq'::regclass);


--
-- TOC entry 2152 (class 2604 OID 20271)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY plane ALTER COLUMN id SET DEFAULT nextval('plane_id_seq'::regclass);


--
-- TOC entry 2153 (class 2604 OID 20272)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY price ALTER COLUMN id SET DEFAULT nextval('price_id_seq'::regclass);


--
-- TOC entry 2160 (class 2604 OID 20273)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY ticket ALTER COLUMN id SET DEFAULT nextval('ticket_id_seq'::regclass);


--
-- TOC entry 2163 (class 2604 OID 20274)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_profile ALTER COLUMN id SET DEFAULT nextval('user_profile_id_seq'::regclass);


SET search_path = test, pg_catalog;

--
-- TOC entry 2117 (class 2604 OID 19928)
-- Name: id; Type: DEFAULT; Schema: test; Owner: -
--

ALTER TABLE ONLY airport ALTER COLUMN id SET DEFAULT nextval('airport_id_seq'::regclass);


--
-- TOC entry 2118 (class 2604 OID 19929)
-- Name: id; Type: DEFAULT; Schema: test; Owner: -
--

ALTER TABLE ONLY city ALTER COLUMN id SET DEFAULT nextval('city_id_seq'::regclass);


--
-- TOC entry 2119 (class 2604 OID 19930)
-- Name: id; Type: DEFAULT; Schema: test; Owner: -
--

ALTER TABLE ONLY country ALTER COLUMN id SET DEFAULT nextval('country_id_seq'::regclass);


--
-- TOC entry 2120 (class 2604 OID 19931)
-- Name: id; Type: DEFAULT; Schema: test; Owner: -
--

ALTER TABLE ONLY flight ALTER COLUMN id SET DEFAULT nextval('flight_id_seq'::regclass);


--
-- TOC entry 2121 (class 2604 OID 19932)
-- Name: id; Type: DEFAULT; Schema: test; Owner: -
--

ALTER TABLE ONLY flight_catalog ALTER COLUMN id SET DEFAULT nextval('flight_catalog_id_seq'::regclass);


--
-- TOC entry 2122 (class 2604 OID 19933)
-- Name: id; Type: DEFAULT; Schema: test; Owner: -
--

ALTER TABLE ONLY manufactured_plane ALTER COLUMN id SET DEFAULT nextval('manufactured_plane_id_seq'::regclass);


--
-- TOC entry 2127 (class 2604 OID 19934)
-- Name: id; Type: DEFAULT; Schema: test; Owner: -
--

ALTER TABLE ONLY model_plane ALTER COLUMN id SET DEFAULT nextval('model_plane_id_seq'::regclass);


--
-- TOC entry 2128 (class 2604 OID 19935)
-- Name: id; Type: DEFAULT; Schema: test; Owner: -
--

ALTER TABLE ONLY plane ALTER COLUMN id SET DEFAULT nextval('plane_id_seq'::regclass);


--
-- TOC entry 2129 (class 2604 OID 19936)
-- Name: id; Type: DEFAULT; Schema: test; Owner: -
--

ALTER TABLE ONLY price ALTER COLUMN id SET DEFAULT nextval('price_id_seq'::regclass);


--
-- TOC entry 2136 (class 2604 OID 19937)
-- Name: id; Type: DEFAULT; Schema: test; Owner: -
--

ALTER TABLE ONLY ticket ALTER COLUMN id SET DEFAULT nextval('ticket_id_seq'::regclass);


--
-- TOC entry 2139 (class 2604 OID 19938)
-- Name: id; Type: DEFAULT; Schema: test; Owner: -
--

ALTER TABLE ONLY user_profile ALTER COLUMN id SET DEFAULT nextval('user_profile_id_seq'::regclass);


SET search_path = public, pg_catalog;

--
-- TOC entry 2368 (class 0 OID 20184)
-- Dependencies: 204
-- Data for Name: airport; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO airport (id, name, code_iata, code_icao, city_id, class_weight, coordinates_x, coordinates_y) VALUES (2, 'Grodno', 'GRN', NULL, 1, 2, NULL, NULL);
INSERT INTO airport (id, name, code_iata, code_icao, city_id, class_weight, coordinates_x, coordinates_y) VALUES (3, 'Gomel', 'GML', NULL, 3, 2, NULL, NULL);
INSERT INTO airport (id, name, code_iata, code_icao, city_id, class_weight, coordinates_x, coordinates_y) VALUES (1, 'MInsk', 'MSK', NULL, 2, 2, NULL, NULL);
INSERT INTO airport (id, name, code_iata, code_icao, city_id, class_weight, coordinates_x, coordinates_y) VALUES (5, 'Warszaw', 'WAR', NULL, 7, 1, NULL, NULL);
INSERT INTO airport (id, name, code_iata, code_icao, city_id, class_weight, coordinates_x, coordinates_y) VALUES (6, 'Domodedovo', 'MSK', NULL, 5, 2, NULL, NULL);
INSERT INTO airport (id, name, code_iata, code_icao, city_id, class_weight, coordinates_x, coordinates_y) VALUES (7, 'Krakow', 'KRK', NULL, 8, 2, NULL, NULL);


--
-- TOC entry 2418 (class 0 OID 0)
-- Dependencies: 205
-- Name: airport_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('airport_id_seq', 7, true);


--
-- TOC entry 2370 (class 0 OID 20193)
-- Dependencies: 206
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO city (id, name, country_id, timezone) VALUES (1, 'Grodno', 1, 3);
INSERT INTO city (id, name, country_id, timezone) VALUES (2, 'Minsk', 1, 3);
INSERT INTO city (id, name, country_id, timezone) VALUES (3, 'Gomel', 1, 3);
INSERT INTO city (id, name, country_id, timezone) VALUES (5, 'Moscow', 4, 3);
INSERT INTO city (id, name, country_id, timezone) VALUES (6, 'Kaliningrad', 4, 3);
INSERT INTO city (id, name, country_id, timezone) VALUES (7, 'Warsaw', 3, 2);
INSERT INTO city (id, name, country_id, timezone) VALUES (8, 'Krakow', 3, 2);


--
-- TOC entry 2419 (class 0 OID 0)
-- Dependencies: 207
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('city_id_seq', 8, true);


--
-- TOC entry 2372 (class 0 OID 20201)
-- Dependencies: 208
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO country (id, name) VALUES (1, 'Belarus');
INSERT INTO country (id, name) VALUES (3, 'Poland');
INSERT INTO country (id, name) VALUES (4, 'Russia');


--
-- TOC entry 2420 (class 0 OID 0)
-- Dependencies: 209
-- Name: country_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('country_id_seq', 4, true);


--
-- TOC entry 2374 (class 0 OID 20206)
-- Dependencies: 210
-- Data for Name: flight; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (3, 3, '2016-06-01 00:00:00', '2016-06-01 00:00:00', '2016-06-01 00:00:00', 1, '2016-06-01 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (4, 1, '2016-06-09 00:00:00', '2016-06-09 00:00:00', '2016-06-09 00:00:00', 1, '2016-06-01 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (1, 1, '2016-06-11 00:00:00', '2016-06-11 00:00:00', '2016-06-11 00:00:00', 1, '2016-05-30 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (2, 2, '2016-06-14 00:00:00', '2016-06-14 00:00:00', '2016-06-14 00:00:00', 1, '2016-06-01 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (5, 5, '2016-06-28 00:00:00', '2016-06-28 01:00:00', '2016-06-28 05:00:00', 1, '2016-06-01 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (6, 1, '2016-06-21 12:00:00', '2016-06-21 13:00:00', '2016-06-21 13:30:00', 1, '2016-06-16 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (8, 11, '2016-06-22 12:10:00', '2016-06-14 13:00:00', '2016-06-14 14:00:00', 1, '2016-06-01 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (9, 9, '2016-06-17 10:00:00', '2016-06-17 11:00:00', '2016-06-17 12:00:00', 4, '2016-06-01 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (10, 11, '2016-06-08 00:00:00', '2016-06-16 00:30:00', '2016-06-16 01:30:00', 1, '2016-06-02 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (11, 10, '2016-06-22 13:20:00', '2016-06-22 13:50:00', '2016-06-22 15:00:00', 4, '2016-06-06 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (12, 13, '2016-06-21 00:00:00', '2016-06-21 00:50:00', '2016-06-21 02:10:00', 1, '2016-06-03 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (13, 11, '2016-06-16 00:10:00', '2016-06-16 00:50:00', '2016-06-16 01:50:00', 4, '2016-06-05 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (14, 10, '2016-06-16 12:00:00', '2016-06-16 13:00:00', '2016-06-16 15:00:00', 1, '2016-06-06 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (15, 13, '2016-06-17 00:00:00', '2016-06-17 00:30:00', '2016-06-17 02:30:00', 4, '2016-06-02 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (16, 9, '2016-06-18 12:30:00', '2016-06-18 13:00:00', '2016-06-18 14:00:00', 1, '2016-06-01 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (17, 14, '2016-06-30 00:00:00', '2016-06-30 00:30:00', '2016-06-30 03:00:00', 4, '2016-06-02 00:00:00');
INSERT INTO flight (id, flight_catalog_id, registr_time, departure_time, arrival_time, plane_id, start_sale_ticket) VALUES (18, 15, '2016-06-29 15:30:00', '2016-06-29 16:10:00', '2016-06-29 17:00:00', 1, '2016-06-01 00:00:00');


--
-- TOC entry 2375 (class 0 OID 20209)
-- Dependencies: 211
-- Data for Name: flight_catalog; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO flight_catalog (id, airport_start_id, airport_finish_id, distance, international) VALUES (9, 2, 5, 300, true);
INSERT INTO flight_catalog (id, airport_start_id, airport_finish_id, distance, international) VALUES (10, 2, 6, 1000, true);
INSERT INTO flight_catalog (id, airport_start_id, airport_finish_id, distance, international) VALUES (11, 5, 2, 300, true);
INSERT INTO flight_catalog (id, airport_start_id, airport_finish_id, distance, international) VALUES (1, 2, 1, 300, false);
INSERT INTO flight_catalog (id, airport_start_id, airport_finish_id, distance, international) VALUES (2, 1, 2, 300, false);
INSERT INTO flight_catalog (id, airport_start_id, airport_finish_id, distance, international) VALUES (3, 2, 3, 700, false);
INSERT INTO flight_catalog (id, airport_start_id, airport_finish_id, distance, international) VALUES (5, 3, 1, 400, false);
INSERT INTO flight_catalog (id, airport_start_id, airport_finish_id, distance, international) VALUES (4, 3, 2, 700, false);
INSERT INTO flight_catalog (id, airport_start_id, airport_finish_id, distance, international) VALUES (6, 1, 3, 400, false);
INSERT INTO flight_catalog (id, airport_start_id, airport_finish_id, distance, international) VALUES (7, 2, 3, 300, false);
INSERT INTO flight_catalog (id, airport_start_id, airport_finish_id, distance, international) VALUES (13, 6, 2, 1000, true);
INSERT INTO flight_catalog (id, airport_start_id, airport_finish_id, distance, international) VALUES (14, 5, 6, 1300, true);
INSERT INTO flight_catalog (id, airport_start_id, airport_finish_id, distance, international) VALUES (15, 6, 5, 1300, true);


--
-- TOC entry 2421 (class 0 OID 0)
-- Dependencies: 212
-- Name: flight_catalog_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('flight_catalog_id_seq', 15, true);


--
-- TOC entry 2422 (class 0 OID 0)
-- Dependencies: 213
-- Name: flight_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('flight_id_seq', 18, true);


--
-- TOC entry 2378 (class 0 OID 20216)
-- Dependencies: 214
-- Data for Name: manufactured_plane; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO manufactured_plane (id, name) VALUES (1, 'Boeing');


--
-- TOC entry 2423 (class 0 OID 0)
-- Dependencies: 215
-- Name: manufactured_plane_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('manufactured_plane_id_seq', 2, true);


--
-- TOC entry 2380 (class 0 OID 20221)
-- Dependencies: 216
-- Data for Name: model_plane; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO model_plane (id, manufactured_plane_id, model, col_passangers_buisnes, col_passangers_firstclass, col_passangers_economy, weight_all_baggage, avg_speed, class_weight) VALUES (1, 1, 'Boeing 707', 12, 14, 10, 1000, 300, 2);
INSERT INTO model_plane (id, manufactured_plane_id, model, col_passangers_buisnes, col_passangers_firstclass, col_passangers_economy, weight_all_baggage, avg_speed, class_weight) VALUES (2, 1, 'Boeing 777', 10, 10, 10, 1000, 300, 0);
INSERT INTO model_plane (id, manufactured_plane_id, model, col_passangers_buisnes, col_passangers_firstclass, col_passangers_economy, weight_all_baggage, avg_speed, class_weight) VALUES (4, 1, 'Boeing 717', 50, 60, 40, 3000, 400, 2);


--
-- TOC entry 2424 (class 0 OID 0)
-- Dependencies: 217
-- Name: model_plane_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('model_plane_id_seq', 4, true);


--
-- TOC entry 2382 (class 0 OID 20230)
-- Dependencies: 218
-- Data for Name: plane; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO plane (id, bort_number, model_plane_id) VALUES (1, 'BX-11415', 1);
INSERT INTO plane (id, bort_number, model_plane_id) VALUES (3, 'BX-52145', 2);
INSERT INTO plane (id, bort_number, model_plane_id) VALUES (4, 'BX-114154', 4);


--
-- TOC entry 2425 (class 0 OID 0)
-- Dependencies: 219
-- Name: plane_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('plane_id_seq', 4, true);


--
-- TOC entry 2384 (class 0 OID 20235)
-- Dependencies: 220
-- Data for Name: price; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO price (id, data_change, basic_price) VALUES (1, '2016-04-01 00:00:00', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (2, '2016-06-22 00:00:00', 0.18);


--
-- TOC entry 2426 (class 0 OID 0)
-- Dependencies: 221
-- Name: price_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('price_id_seq', 2, true);


--
-- TOC entry 2386 (class 0 OID 20243)
-- Dependencies: 222
-- Data for Name: ticket; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (77, 2, 1, true, 2, '2016-06-13 10:42:54.758', false, NULL, NULL, 0, false, false, 80.6, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (47, 4, 1, true, 3, '2016-06-07 11:55:22.255', false, 3, 0, 1, false, false, 94.3714285714286, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (48, 4, 1, true, 4, '2016-06-07 11:55:43.542', false, 45, 0, 1, false, false, 95.4, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (66, 1, 3, true, 12, '2016-06-08 12:39:34.18', true, 232, 0, 0, false, false, 83.2, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (51, 4, 1, true, 5, '2016-06-07 12:46:14.339', true, 23, 0, 1, false, false, 183.96, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (40, 4, 1, true, 1, '2016-06-07 12:50:34.308', true, 3, 0, 2, false, false, 148.5, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (68, 1, 3, true, 3, '2016-06-08 12:42:37.546', true, 23, 0, 0, false, false, 181.74, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (50, 1, 1, true, 5, '2016-06-07 13:01:02.214', true, 2, 0, 2, false, false, 125.4, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (53, 1, 1, true, 4, '2016-06-07 13:04:57.547', true, 5, 0, 1, false, false, 154.285714285714, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (54, 1, 1, true, 2, '2016-06-07 13:18:38.835', true, 5, 0, 0, false, false, 87.1, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (55, 2, 1, true, 7, '2016-06-07 13:39:54.746', true, 8, 0, 1, false, false, 79.6747252747253, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (78, 5, 3, true, 2, '2016-06-14 10:46:49.501', false, NULL, NULL, 1, false, false, 105.24, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (69, 1, 3, true, 11, '2016-06-08 12:45:24.172', true, 55, 0, 0, false, false, 208, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (70, 1, 3, true, 9, '2016-06-08 12:46:03.044', true, 55, 0, 0, false, false, 209.3, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (23, 4, 1, true, 4, '2016-06-04 16:25:43.607', false, 0, 1, 0, false, false, 88.4, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (80, 18, 1, true, 1, '2016-06-14 11:53:46.405', false, NULL, NULL, 0, false, false, 374.21, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (26, 4, 1, true, 2, '2016-06-06 10:20:37.726', false, 0, 0, 1, false, false, 78.6857142857143, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (25, 4, 1, true, 8, '2016-06-06 10:20:46.466', false, 99, 0, 2, false, false, 67.8, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (81, 18, 1, true, 3, '2016-06-14 11:55:08.43', false, NULL, NULL, 0, true, true, 414.85, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (32, 4, 1, true, 11, '2016-06-06 10:58:43.145', false, 0, 0, 0, false, false, 89.7, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (88, 8, 3, false, 6, '2016-06-14 12:25:40.934', false, NULL, NULL, 1, false, false, 72, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (89, 8, 3, false, 1, '2016-06-14 12:26:07.138', false, NULL, NULL, 0, false, false, 78, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (33, 4, 1, true, 12, '2016-06-08 09:13:40.172', false, 0, 0, 1, false, false, 93.6, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (34, 4, 1, true, 22, '2016-06-08 09:13:50.791', false, 0, 0, 1, false, false, 93.6, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (35, 4, 1, true, 89, '2016-06-08 09:16:11.341', false, 0, 0, 1, false, false, 93.6, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (36, 4, 1, true, 45, '2016-06-08 09:19:07.606', false, 0, 0, 1, false, false, 93.6, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (38, 4, 1, true, 9, '2016-06-07 10:54:04.411', false, 0, 0, 2, false, false, 74.7, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (39, 4, 1, true, 7, '2016-06-07 10:54:27.481', false, 0, 0, 2, false, false, 75.9, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (90, 5, 1, true, 1, '2016-06-14 15:23:42.24', false, NULL, NULL, 0, false, false, 114.01, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (91, 5, 1, false, 1, '2016-06-14 15:24:04.663', false, NULL, NULL, 1, false, false, 106.62, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (94, 18, 1, true, 9, '2016-06-14 15:42:09.189', true, 550, NULL, 0, false, false, 2582.48, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (92, 5, 1, true, 2, '2016-06-14 15:28:39.778', false, NULL, NULL, 0, false, false, 115.75, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (65, 1, 3, true, 1, '2016-06-08 12:39:05.452', false, 0, 0, 0, false, false, 81.9, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (41, 4, 1, true, 2, '2016-06-07 11:42:58.226', false, 0, 0, 2, false, false, 78.3, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (42, 4, 1, true, 3, '2016-06-07 11:43:30.146', false, 0, 0, 2, false, false, 79.5, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (43, 4, 1, true, 4, '2016-06-07 11:43:42.214', false, 0, 0, 2, false, false, 80.7, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (44, 4, 1, true, 5, '2016-06-07 11:44:04.126', false, 0, 0, 2, false, false, 81.9, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (45, 4, 1, true, 6, '2016-06-07 11:44:26.039', false, 0, 0, 2, false, false, 83.1, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (46, 4, 1, true, 10, '2016-06-07 11:44:48.422', false, 0, 0, 2, false, false, 84.3, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (59, 2, 1, true, 4, '2016-06-07 15:32:33.171', false, 0, 0, 2, false, false, 69.1384615384615, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (60, 1, 1, true, 5, '2016-06-07 15:42:11.516', false, 0, 1, 1, false, false, 80.7428571428571, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (61, 1, 1, true, 8, '2016-06-08 09:32:32.586', false, 0, 1, 1, false, false, 84.6857142857143, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (62, 1, 1, true, 7, '2016-06-08 09:31:18.802', false, 0, 1, 1, false, false, 84.6857142857143, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (63, 1, 1, true, 9, '2016-06-08 09:20:13.013', false, 0, 1, 1, false, false, 82.2857142857143, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (64, 1, 1, true, 3, '2016-06-07 17:19:47.301', false, 0, 1, 1, false, false, 85.8857142857143, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (73, 1, 1, true, 1, '2016-06-08 13:21:11.859', false, NULL, 0, 1, false, false, 82.6285714285714, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (75, 2, 1, true, 8, '2016-06-13 10:26:18.76', true, 23, NULL, 0, false, false, 173.94, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (76, 2, 1, true, 9, '2016-06-13 10:34:46.644', true, 15.5, NULL, 0, false, false, 169.39, false);
INSERT INTO ticket (id, flight_id, user_profile_id, paid, number_seats, date_bought, baggage, weight_baggage, ticket_tupe, ticket_class, priority_registration, priority_seats, costs, for_baby) VALUES (74, 2, 1, true, 5, '2016-06-13 10:25:32.137', false, NULL, NULL, 1, false, false, 73.0285714285714, false);


--
-- TOC entry 2427 (class 0 OID 0)
-- Dependencies: 223
-- Name: ticket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('ticket_id_seq', 94, true);


--
-- TOC entry 2388 (class 0 OID 20257)
-- Dependencies: 224
-- Data for Name: user_profile; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO user_profile (id, login, password, first_name, last_name, email, passport_number, phone_number, count_oder, vip, date_registr, role, acept_registr) VALUES (9, 'User2', '1', 'User', 'User', 'user@user.ru', 'QWE1212121212', '375292222222', 0, false, '2016-06-14 10:09:54.147', 2, true);
INSERT INTO user_profile (id, login, password, first_name, last_name, email, passport_number, phone_number, count_oder, vip, date_registr, role, acept_registr) VALUES (10, 'operator', '1', 'User', 'User', 'vovik-mts@tut.by', 'REW12345', '+375293333333', 0, false, '2016-06-14 10:24:22.823', 1, true);
INSERT INTO user_profile (id, login, password, first_name, last_name, email, passport_number, phone_number, count_oder, vip, date_registr, role, acept_registr) VALUES (3, 'User', '1', 'User', 'User', 'user@user.com', '1232212BP44', '1223323232', 1, false, '2016-06-08 12:37:56.486', 2, false);
INSERT INTO user_profile (id, login, password, first_name, last_name, email, passport_number, phone_number, count_oder, vip, date_registr, role, acept_registr) VALUES (1, 'vovik', '1', 'Bogdevich', 'Vladimir', 'etst@mail.ru', 'QWE12121212121', '375291111111', 8, NULL, NULL, 0, false);


--
-- TOC entry 2428 (class 0 OID 0)
-- Dependencies: 225
-- Name: user_profile_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('user_profile_id_seq', 10, true);


SET search_path = test, pg_catalog;

--
-- TOC entry 2346 (class 0 OID 19848)
-- Dependencies: 182
-- Data for Name: airport; Type: TABLE DATA; Schema: test; Owner: -
--



--
-- TOC entry 2429 (class 0 OID 0)
-- Dependencies: 183
-- Name: airport_id_seq; Type: SEQUENCE SET; Schema: test; Owner: -
--

SELECT pg_catalog.setval('airport_id_seq', 547, true);


--
-- TOC entry 2348 (class 0 OID 19857)
-- Dependencies: 184
-- Data for Name: city; Type: TABLE DATA; Schema: test; Owner: -
--



--
-- TOC entry 2430 (class 0 OID 0)
-- Dependencies: 185
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: test; Owner: -
--

SELECT pg_catalog.setval('city_id_seq', 599, true);


--
-- TOC entry 2350 (class 0 OID 19865)
-- Dependencies: 186
-- Data for Name: country; Type: TABLE DATA; Schema: test; Owner: -
--

INSERT INTO country (id, name) VALUES (652, 'FR');
INSERT INTO country (id, name) VALUES (654, 'updatedName');


--
-- TOC entry 2431 (class 0 OID 0)
-- Dependencies: 187
-- Name: country_id_seq; Type: SEQUENCE SET; Schema: test; Owner: -
--

SELECT pg_catalog.setval('country_id_seq', 654, true);


--
-- TOC entry 2352 (class 0 OID 19870)
-- Dependencies: 188
-- Data for Name: flight; Type: TABLE DATA; Schema: test; Owner: -
--



--
-- TOC entry 2353 (class 0 OID 19873)
-- Dependencies: 189
-- Data for Name: flight_catalog; Type: TABLE DATA; Schema: test; Owner: -
--



--
-- TOC entry 2432 (class 0 OID 0)
-- Dependencies: 190
-- Name: flight_catalog_id_seq; Type: SEQUENCE SET; Schema: test; Owner: -
--

SELECT pg_catalog.setval('flight_catalog_id_seq', 249, true);


--
-- TOC entry 2433 (class 0 OID 0)
-- Dependencies: 191
-- Name: flight_id_seq; Type: SEQUENCE SET; Schema: test; Owner: -
--

SELECT pg_catalog.setval('flight_id_seq', 169, true);


--
-- TOC entry 2356 (class 0 OID 19880)
-- Dependencies: 192
-- Data for Name: manufactured_plane; Type: TABLE DATA; Schema: test; Owner: -
--



--
-- TOC entry 2434 (class 0 OID 0)
-- Dependencies: 193
-- Name: manufactured_plane_id_seq; Type: SEQUENCE SET; Schema: test; Owner: -
--

SELECT pg_catalog.setval('manufactured_plane_id_seq', 350, true);


--
-- TOC entry 2358 (class 0 OID 19885)
-- Dependencies: 194
-- Data for Name: model_plane; Type: TABLE DATA; Schema: test; Owner: -
--



--
-- TOC entry 2435 (class 0 OID 0)
-- Dependencies: 195
-- Name: model_plane_id_seq; Type: SEQUENCE SET; Schema: test; Owner: -
--

SELECT pg_catalog.setval('model_plane_id_seq', 301, true);


--
-- TOC entry 2360 (class 0 OID 19894)
-- Dependencies: 196
-- Data for Name: plane; Type: TABLE DATA; Schema: test; Owner: -
--



--
-- TOC entry 2436 (class 0 OID 0)
-- Dependencies: 197
-- Name: plane_id_seq; Type: SEQUENCE SET; Schema: test; Owner: -
--

SELECT pg_catalog.setval('plane_id_seq', 235, true);


--
-- TOC entry 2362 (class 0 OID 19899)
-- Dependencies: 198
-- Data for Name: price; Type: TABLE DATA; Schema: test; Owner: -
--

INSERT INTO price (id, data_change, basic_price) VALUES (1, '2016-05-20 11:08:28.497', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (2, '2016-05-20 11:08:28.628', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (4, '2016-05-20 11:08:31.497', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (5, '2016-05-20 11:08:31.503', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (7, '2016-06-01 16:09:13.452', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (8, '2016-06-01 16:09:13.468', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (10, '2016-06-03 19:12:14.125', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (11, '2016-06-03 19:12:17.913', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (12, '2016-06-03 19:14:48.199', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (13, '2016-06-03 19:14:48.21', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (15, '2016-06-03 20:20:35.35', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (16, '2016-06-03 20:20:39.439', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (17, '2016-06-03 20:22:37.189', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (18, '2016-06-03 20:22:37.201', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (20, '2016-06-10 10:08:38.66', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (21, '2016-06-10 10:08:39.032', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (23, '2016-06-10 12:55:53.231', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (24, '2016-06-10 12:55:53.24', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (26, '2016-06-10 13:13:03.678', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (27, '2016-06-10 13:13:03.694', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (29, '2016-06-10 14:27:08.727', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (30, '2016-06-10 14:27:08.742', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (32, '2016-06-10 14:31:26.238', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (33, '2016-06-10 14:31:26.259', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (35, '2016-06-10 14:41:48.954', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (36, '2016-06-10 14:41:48.969', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (38, '2016-06-10 14:45:49.546', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (39, '2016-06-10 14:45:49.546', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (41, '2016-06-13 10:36:45.145', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (42, '2016-06-13 10:36:45.155', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (44, '2016-06-14 12:51:18.537', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (45, '2016-06-14 12:51:18.583', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (47, '2016-06-14 15:53:45.194', 0.2);
INSERT INTO price (id, data_change, basic_price) VALUES (48, '2016-06-14 15:53:45.207', 0.2);


--
-- TOC entry 2437 (class 0 OID 0)
-- Dependencies: 199
-- Name: price_id_seq; Type: SEQUENCE SET; Schema: test; Owner: -
--

SELECT pg_catalog.setval('price_id_seq', 49, true);


--
-- TOC entry 2364 (class 0 OID 19907)
-- Dependencies: 200
-- Data for Name: ticket; Type: TABLE DATA; Schema: test; Owner: -
--



--
-- TOC entry 2438 (class 0 OID 0)
-- Dependencies: 201
-- Name: ticket_id_seq; Type: SEQUENCE SET; Schema: test; Owner: -
--

SELECT pg_catalog.setval('ticket_id_seq', 130, true);


--
-- TOC entry 2366 (class 0 OID 19921)
-- Dependencies: 202
-- Data for Name: user_profile; Type: TABLE DATA; Schema: test; Owner: -
--



--
-- TOC entry 2439 (class 0 OID 0)
-- Dependencies: 203
-- Name: user_profile_id_seq; Type: SEQUENCE SET; Schema: test; Owner: -
--

SELECT pg_catalog.setval('user_profile_id_seq', 324, true);


SET search_path = public, pg_catalog;

--
-- TOC entry 2189 (class 2606 OID 20276)
-- Name: airport_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY airport
    ADD CONSTRAINT airport_pk PRIMARY KEY (id);


--
-- TOC entry 2191 (class 2606 OID 20278)
-- Name: city_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY city
    ADD CONSTRAINT city_pk PRIMARY KEY (id);


--
-- TOC entry 2193 (class 2606 OID 20280)
-- Name: country_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_pk PRIMARY KEY (id);


--
-- TOC entry 2197 (class 2606 OID 20282)
-- Name: flight_catalog_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight_catalog
    ADD CONSTRAINT flight_catalog_pk PRIMARY KEY (id);


--
-- TOC entry 2195 (class 2606 OID 20284)
-- Name: flight_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_pk PRIMARY KEY (id);


--
-- TOC entry 2199 (class 2606 OID 20286)
-- Name: manufactured_plane_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY manufactured_plane
    ADD CONSTRAINT manufactured_plane_pk PRIMARY KEY (id);


--
-- TOC entry 2201 (class 2606 OID 20288)
-- Name: model_plane_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY model_plane
    ADD CONSTRAINT model_plane_pk PRIMARY KEY (id);


--
-- TOC entry 2203 (class 2606 OID 20290)
-- Name: plane_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY plane
    ADD CONSTRAINT plane_pk PRIMARY KEY (id);


--
-- TOC entry 2205 (class 2606 OID 20292)
-- Name: price_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY price
    ADD CONSTRAINT price_pk PRIMARY KEY (id);


--
-- TOC entry 2207 (class 2606 OID 20294)
-- Name: ticket_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_pk PRIMARY KEY (id);


--
-- TOC entry 2209 (class 2606 OID 20296)
-- Name: user_profile_login_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_login_key UNIQUE (login);


--
-- TOC entry 2211 (class 2606 OID 20298)
-- Name: user_profile_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_pk PRIMARY KEY (id);


SET search_path = test, pg_catalog;

--
-- TOC entry 2165 (class 2606 OID 19940)
-- Name: airport_pk; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY airport
    ADD CONSTRAINT airport_pk PRIMARY KEY (id);


--
-- TOC entry 2167 (class 2606 OID 19942)
-- Name: city_pk; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY city
    ADD CONSTRAINT city_pk PRIMARY KEY (id);


--
-- TOC entry 2169 (class 2606 OID 19944)
-- Name: country_pk; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_pk PRIMARY KEY (id);


--
-- TOC entry 2173 (class 2606 OID 19946)
-- Name: flight_catalog_pk; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY flight_catalog
    ADD CONSTRAINT flight_catalog_pk PRIMARY KEY (id);


--
-- TOC entry 2171 (class 2606 OID 19948)
-- Name: flight_pk; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_pk PRIMARY KEY (id);


--
-- TOC entry 2175 (class 2606 OID 19950)
-- Name: manufactured_plane_pk; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY manufactured_plane
    ADD CONSTRAINT manufactured_plane_pk PRIMARY KEY (id);


--
-- TOC entry 2177 (class 2606 OID 19952)
-- Name: model_plane_pk; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY model_plane
    ADD CONSTRAINT model_plane_pk PRIMARY KEY (id);


--
-- TOC entry 2179 (class 2606 OID 19954)
-- Name: plane_pk; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY plane
    ADD CONSTRAINT plane_pk PRIMARY KEY (id);


--
-- TOC entry 2181 (class 2606 OID 19956)
-- Name: price_pk; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY price
    ADD CONSTRAINT price_pk PRIMARY KEY (id);


--
-- TOC entry 2183 (class 2606 OID 19958)
-- Name: ticket_pk; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_pk PRIMARY KEY (id);


--
-- TOC entry 2185 (class 2606 OID 19960)
-- Name: user_profile_login_key; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_login_key UNIQUE (login);


--
-- TOC entry 2187 (class 2606 OID 19962)
-- Name: user_profile_pk; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_pk PRIMARY KEY (id);


SET search_path = public, pg_catalog;

--
-- TOC entry 2222 (class 2606 OID 20299)
-- Name: airport_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY airport
    ADD CONSTRAINT airport_fk0 FOREIGN KEY (city_id) REFERENCES city(id);


--
-- TOC entry 2223 (class 2606 OID 20304)
-- Name: city_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY city
    ADD CONSTRAINT city_fk0 FOREIGN KEY (country_id) REFERENCES country(id);


--
-- TOC entry 2226 (class 2606 OID 20309)
-- Name: flight_catalog_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight_catalog
    ADD CONSTRAINT flight_catalog_fk0 FOREIGN KEY (airport_start_id) REFERENCES airport(id);


--
-- TOC entry 2227 (class 2606 OID 20314)
-- Name: flight_catalog_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight_catalog
    ADD CONSTRAINT flight_catalog_fk1 FOREIGN KEY (airport_finish_id) REFERENCES airport(id);


--
-- TOC entry 2224 (class 2606 OID 20319)
-- Name: flight_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_fk0 FOREIGN KEY (flight_catalog_id) REFERENCES flight_catalog(id);


--
-- TOC entry 2225 (class 2606 OID 20324)
-- Name: flight_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_fk1 FOREIGN KEY (plane_id) REFERENCES plane(id);


--
-- TOC entry 2228 (class 2606 OID 20329)
-- Name: model_plane_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY model_plane
    ADD CONSTRAINT model_plane_fk0 FOREIGN KEY (manufactured_plane_id) REFERENCES manufactured_plane(id);


--
-- TOC entry 2229 (class 2606 OID 20334)
-- Name: plane_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY plane
    ADD CONSTRAINT plane_fk0 FOREIGN KEY (model_plane_id) REFERENCES model_plane(id);


--
-- TOC entry 2230 (class 2606 OID 20339)
-- Name: ticket_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_fk0 FOREIGN KEY (flight_id) REFERENCES flight(id);


--
-- TOC entry 2231 (class 2606 OID 20344)
-- Name: ticket_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_fk1 FOREIGN KEY (user_profile_id) REFERENCES user_profile(id);


SET search_path = test, pg_catalog;

--
-- TOC entry 2212 (class 2606 OID 19963)
-- Name: airport_fk0; Type: FK CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY airport
    ADD CONSTRAINT airport_fk0 FOREIGN KEY (city_id) REFERENCES city(id);


--
-- TOC entry 2213 (class 2606 OID 19968)
-- Name: city_fk0; Type: FK CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY city
    ADD CONSTRAINT city_fk0 FOREIGN KEY (country_id) REFERENCES country(id);


--
-- TOC entry 2216 (class 2606 OID 19973)
-- Name: flight_catalog_fk0; Type: FK CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY flight_catalog
    ADD CONSTRAINT flight_catalog_fk0 FOREIGN KEY (airport_start_id) REFERENCES airport(id);


--
-- TOC entry 2217 (class 2606 OID 19978)
-- Name: flight_catalog_fk1; Type: FK CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY flight_catalog
    ADD CONSTRAINT flight_catalog_fk1 FOREIGN KEY (airport_finish_id) REFERENCES airport(id);


--
-- TOC entry 2214 (class 2606 OID 19983)
-- Name: flight_fk0; Type: FK CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_fk0 FOREIGN KEY (flight_catalog_id) REFERENCES flight_catalog(id);


--
-- TOC entry 2215 (class 2606 OID 19988)
-- Name: flight_fk1; Type: FK CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_fk1 FOREIGN KEY (plane_id) REFERENCES plane(id);


--
-- TOC entry 2218 (class 2606 OID 19993)
-- Name: model_plane_fk0; Type: FK CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY model_plane
    ADD CONSTRAINT model_plane_fk0 FOREIGN KEY (manufactured_plane_id) REFERENCES manufactured_plane(id);


--
-- TOC entry 2219 (class 2606 OID 19998)
-- Name: plane_fk0; Type: FK CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY plane
    ADD CONSTRAINT plane_fk0 FOREIGN KEY (model_plane_id) REFERENCES model_plane(id);


--
-- TOC entry 2220 (class 2606 OID 20003)
-- Name: ticket_fk0; Type: FK CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_fk0 FOREIGN KEY (flight_id) REFERENCES flight(id);


--
-- TOC entry 2221 (class 2606 OID 20008)
-- Name: ticket_fk1; Type: FK CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_fk1 FOREIGN KEY (user_profile_id) REFERENCES user_profile(id);


-- Completed on 2016-06-14 15:55:54

--
-- PostgreSQL database dump complete
--

