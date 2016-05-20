--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-05-20 10:57:37

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 19665)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 185 (class 1259 OID 19676)
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
-- TOC entry 184 (class 1259 OID 19674)
-- Name: airport_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE airport_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2222 (class 0 OID 0)
-- Dependencies: 184
-- Name: airport_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE airport_id_seq OWNED BY airport.id;


--
-- TOC entry 187 (class 1259 OID 19688)
-- Name: city; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE city (
    id integer NOT NULL,
    name character varying(50),
    country_id integer,
    timezone numeric
);


--
-- TOC entry 186 (class 1259 OID 19686)
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2223 (class 0 OID 0)
-- Dependencies: 186
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE city_id_seq OWNED BY city.id;


--
-- TOC entry 189 (class 1259 OID 19699)
-- Name: country; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE country (
    id integer NOT NULL,
    name character varying(50)
);


--
-- TOC entry 188 (class 1259 OID 19697)
-- Name: country_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2224 (class 0 OID 0)
-- Dependencies: 188
-- Name: country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE country_id_seq OWNED BY country.id;


--
-- TOC entry 191 (class 1259 OID 19707)
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
-- TOC entry 183 (class 1259 OID 19668)
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
-- TOC entry 182 (class 1259 OID 19666)
-- Name: flight_catalog_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE flight_catalog_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2225 (class 0 OID 0)
-- Dependencies: 182
-- Name: flight_catalog_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE flight_catalog_id_seq OWNED BY flight_catalog.id;


--
-- TOC entry 190 (class 1259 OID 19705)
-- Name: flight_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE flight_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2226 (class 0 OID 0)
-- Dependencies: 190
-- Name: flight_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE flight_id_seq OWNED BY flight.id;


--
-- TOC entry 197 (class 1259 OID 19735)
-- Name: manufactured_plane; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE manufactured_plane (
    id integer NOT NULL,
    name character varying(50) NOT NULL
);


--
-- TOC entry 196 (class 1259 OID 19733)
-- Name: manufactured_plane_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE manufactured_plane_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2227 (class 0 OID 0)
-- Dependencies: 196
-- Name: manufactured_plane_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE manufactured_plane_id_seq OWNED BY manufactured_plane.id;


--
-- TOC entry 195 (class 1259 OID 19723)
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
-- TOC entry 194 (class 1259 OID 19721)
-- Name: model_plane_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE model_plane_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2228 (class 0 OID 0)
-- Dependencies: 194
-- Name: model_plane_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE model_plane_id_seq OWNED BY model_plane.id;


--
-- TOC entry 193 (class 1259 OID 19715)
-- Name: plane; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE plane (
    id integer NOT NULL,
    bort_number character varying(20),
    model_plane_id integer
);


--
-- TOC entry 192 (class 1259 OID 19713)
-- Name: plane_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE plane_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2229 (class 0 OID 0)
-- Dependencies: 192
-- Name: plane_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE plane_id_seq OWNED BY plane.id;


--
-- TOC entry 203 (class 1259 OID 19772)
-- Name: price; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE price (
    id integer NOT NULL,
    data_change timestamp without time zone,
    basic_price numeric
);


--
-- TOC entry 202 (class 1259 OID 19770)
-- Name: price_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE price_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2230 (class 0 OID 0)
-- Dependencies: 202
-- Name: price_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE price_id_seq OWNED BY price.id;


--
-- TOC entry 199 (class 1259 OID 19743)
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
    weight_baggage numeric DEFAULT 0.0 NOT NULL,
    ticket_tupe integer,
    ticket_class integer,
    priority_registration boolean DEFAULT false,
    priority_seats boolean DEFAULT false,
    costs numeric,
    for_baby boolean DEFAULT false
);


--
-- TOC entry 198 (class 1259 OID 19741)
-- Name: ticket_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE ticket_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2231 (class 0 OID 0)
-- Dependencies: 198
-- Name: ticket_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE ticket_id_seq OWNED BY ticket.id;


--
-- TOC entry 201 (class 1259 OID 19760)
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
-- TOC entry 200 (class 1259 OID 19758)
-- Name: user_profile_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2232 (class 0 OID 0)
-- Dependencies: 200
-- Name: user_profile_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE user_profile_id_seq OWNED BY user_profile.id;


--
-- TOC entry 2047 (class 2604 OID 19679)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY airport ALTER COLUMN id SET DEFAULT nextval('airport_id_seq'::regclass);


--
-- TOC entry 2049 (class 2604 OID 19691)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY city ALTER COLUMN id SET DEFAULT nextval('city_id_seq'::regclass);


--
-- TOC entry 2050 (class 2604 OID 19702)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY country ALTER COLUMN id SET DEFAULT nextval('country_id_seq'::regclass);


--
-- TOC entry 2051 (class 2604 OID 19710)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight ALTER COLUMN id SET DEFAULT nextval('flight_id_seq'::regclass);


--
-- TOC entry 2046 (class 2604 OID 19671)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight_catalog ALTER COLUMN id SET DEFAULT nextval('flight_catalog_id_seq'::regclass);


--
-- TOC entry 2058 (class 2604 OID 19738)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY manufactured_plane ALTER COLUMN id SET DEFAULT nextval('manufactured_plane_id_seq'::regclass);


--
-- TOC entry 2053 (class 2604 OID 19726)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY model_plane ALTER COLUMN id SET DEFAULT nextval('model_plane_id_seq'::regclass);


--
-- TOC entry 2052 (class 2604 OID 19718)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY plane ALTER COLUMN id SET DEFAULT nextval('plane_id_seq'::regclass);


--
-- TOC entry 2069 (class 2604 OID 19775)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY price ALTER COLUMN id SET DEFAULT nextval('price_id_seq'::regclass);


--
-- TOC entry 2059 (class 2604 OID 19746)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY ticket ALTER COLUMN id SET DEFAULT nextval('ticket_id_seq'::regclass);


--
-- TOC entry 2066 (class 2604 OID 19763)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_profile ALTER COLUMN id SET DEFAULT nextval('user_profile_id_seq'::regclass);


--
-- TOC entry 2073 (class 2606 OID 19685)
-- Name: airport_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY airport
    ADD CONSTRAINT airport_pk PRIMARY KEY (id);


--
-- TOC entry 2075 (class 2606 OID 19696)
-- Name: city_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY city
    ADD CONSTRAINT city_pk PRIMARY KEY (id);


--
-- TOC entry 2077 (class 2606 OID 19704)
-- Name: country_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_pk PRIMARY KEY (id);


--
-- TOC entry 2071 (class 2606 OID 19673)
-- Name: flight_catalog_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight_catalog
    ADD CONSTRAINT flight_catalog_pk PRIMARY KEY (id);


--
-- TOC entry 2079 (class 2606 OID 19712)
-- Name: flight_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_pk PRIMARY KEY (id);


--
-- TOC entry 2085 (class 2606 OID 19740)
-- Name: manufactured_plane_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY manufactured_plane
    ADD CONSTRAINT manufactured_plane_pk PRIMARY KEY (id);


--
-- TOC entry 2083 (class 2606 OID 19732)
-- Name: model_plane_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY model_plane
    ADD CONSTRAINT model_plane_pk PRIMARY KEY (id);


--
-- TOC entry 2081 (class 2606 OID 19720)
-- Name: plane_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY plane
    ADD CONSTRAINT plane_pk PRIMARY KEY (id);


--
-- TOC entry 2093 (class 2606 OID 19780)
-- Name: price_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY price
    ADD CONSTRAINT price_pk PRIMARY KEY (id);


--
-- TOC entry 2087 (class 2606 OID 19757)
-- Name: ticket_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_pk PRIMARY KEY (id);


--
-- TOC entry 2089 (class 2606 OID 19769)
-- Name: user_profile_login_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_login_key UNIQUE (login);


--
-- TOC entry 2091 (class 2606 OID 19767)
-- Name: user_profile_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT user_profile_pk PRIMARY KEY (id);


--
-- TOC entry 2096 (class 2606 OID 19791)
-- Name: airport_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY airport
    ADD CONSTRAINT airport_fk0 FOREIGN KEY (city_id) REFERENCES city(id);


--
-- TOC entry 2097 (class 2606 OID 19796)
-- Name: city_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY city
    ADD CONSTRAINT city_fk0 FOREIGN KEY (country_id) REFERENCES country(id);


--
-- TOC entry 2094 (class 2606 OID 19781)
-- Name: flight_catalog_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight_catalog
    ADD CONSTRAINT flight_catalog_fk0 FOREIGN KEY (airport_start_id) REFERENCES airport(id);


--
-- TOC entry 2095 (class 2606 OID 19786)
-- Name: flight_catalog_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight_catalog
    ADD CONSTRAINT flight_catalog_fk1 FOREIGN KEY (airport_finish_id) REFERENCES airport(id);


--
-- TOC entry 2098 (class 2606 OID 19801)
-- Name: flight_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_fk0 FOREIGN KEY (flight_catalog_id) REFERENCES flight_catalog(id);


--
-- TOC entry 2099 (class 2606 OID 19806)
-- Name: flight_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_fk1 FOREIGN KEY (plane_id) REFERENCES plane(id);


--
-- TOC entry 2101 (class 2606 OID 19816)
-- Name: model_plane_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY model_plane
    ADD CONSTRAINT model_plane_fk0 FOREIGN KEY (manufactured_plane_id) REFERENCES manufactured_plane(id);


--
-- TOC entry 2100 (class 2606 OID 19811)
-- Name: plane_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY plane
    ADD CONSTRAINT plane_fk0 FOREIGN KEY (model_plane_id) REFERENCES model_plane(id);


--
-- TOC entry 2102 (class 2606 OID 19821)
-- Name: ticket_fk0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_fk0 FOREIGN KEY (flight_id) REFERENCES flight(id);


--
-- TOC entry 2103 (class 2606 OID 19826)
-- Name: ticket_fk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_fk1 FOREIGN KEY (user_profile_id) REFERENCES user_profile(id);


-- Completed on 2016-05-20 10:57:37

--
-- PostgreSQL database dump complete
--

