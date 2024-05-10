--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

-- Started on 2024-05-08 13:29:30

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 245 (class 1259 OID 49810)
-- Name: _user; Type: TABLE; Schema: public; Owner: postgres
--

INSERT INTO public._user (username,first_name,last_name,email,password,role,active)
VALUES ('admin', 'Adam', 'Nowak', 'admin@email.pl', '$2a$12$PBImPs5TcGF3.F0pfX/5jODmG06zToraKO3SDyofK.RptQ1OoS0di', 'ADMIN', true);
INSERT INTO public._user (username,first_name,last_name,email,password,role,active)
VALUES ('user', 'Tomek', 'Kowal', 'testowy@email.com', '$2y$10$Wh3AMMNT7LspWoNjvyhYPe6rO10Eh96m2PncBwMMi4wrH1IzfZJee', 'USER', true);









--
-- TOC entry 5002 (class 0 OID 49721)
-- Dependencies: 233
-- Data for Name: platform; Type: TABLE DATA; Schema: public; Owner: postgres
--

--
-- TOC entry 4994 (class 0 OID 49670)
-- Dependencies: 225
-- Data for Name: publisher; Type: TABLE DATA; Schema: public; Owner: postgres
--



