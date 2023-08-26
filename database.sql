CREATE SCHEMA fithub;
ALTER SCHEMA fithub OWNER TO postgres;
SET default_transaction_read_only = off;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
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



--SECUENCIA TABLA ROL
CREATE SEQUENCE fithub.rol_idrol_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
--TABLA ROL
CREATE TABLE fithub.rol(
    idrol BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('fithub.rol_idrol_seq'::regclass),
    nombre text NOT NULL
);

--INSERTS ROL
INSERT INTO fithub.rol VALUES (nextval('fithub.rol_idrol_seq'),'ROLE_ENTRENADOR');
INSERT INTO fithub.rol VALUES (nextval('fithub.rol_idrol_seq'),'ROLE_CLIENTE');
INSERT INTO fithub.rol VALUES (nextval('fithub.rol_idrol_seq'),'ROLE_ADMIN');
ALTER TABLE fithub.rol OWNER TO postgres;
--SECUENCIA TABLA USUARIO
CREATE SEQUENCE fithub.usuario_idusuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--TABLA USUARIO
CREATE TABLE fithub.usuario(
    idusuario bigint NOT NULL PRIMARY KEY DEFAULT nextval('fithub.usuario_idusuario_seq'::regclass),
    contrasenya CHARACTER VARYING(120) NOT NULL,
    nombre CHARACTER VARYING(255) NOT NULL,
    apellidos CHARACTER VARYING(255) NOT NULL,
    email CHARACTER VARYING (255) UNIQUE NOT NULL ,
    idioma CHARACTER VARYING(6) NOT NULL ,
    fecha_registro DATE NOT NULL ,
    delete boolean NOT NULL
);

ALTER TABLE fithub.usuario OWNER TO postgres;
DROP TABLE fithub.usuario cascade;
select * from fithub.usuario;
-- TABLA ROLUSUARIO
CREATE TABLE fithub.rolusuario(
                                  idusuari bigint not null constraint fkgtancumn0d0ahbouy5msgele3 references fithub.usuario,
                                  idrol bigint not null constraint fk6tajr6jxbt6qeuu4blsmur8nd references fithub.rol,
                                  primary key (idusuari, idrol)
);

ALTER TABLE fithub.rolusuario OWNER TO postgres;
DROP TABLE fithub.usuario;
select * from fithub.rolusuario;

--SECUENCIA EMPRESA
CREATE SEQUENCE fithub.empresa_idempresa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
--TABLA EMPRESA
CREATE TABLE fithub.empresa(
       idempresa BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('fithub.empresa_idempresa_seq'::regclass),
       direccion character varying(255),
       codigo_postal character varying(6),
       logotipo character varying(255),
       nif character varying(255),
       nombre_comercial character varying(255),
       pais character varying(255),
       poblacion character varying(255),
       provincia character varying(255),
       razonsocial character varying(255),
       telefono character varying(25),
       web character varying(255),
       delete boolean
);
ALTER TABLE fithub.empresa OWNER TO postgres;
drop table fithub.empresa;
--SECUENCIA TABLA ENTRENADOR
CREATE SEQUENCE fithub.entrenador_identrenador_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
--TABLA ENTRENADOR
CREATE TABLE fithub.entrenador(
    identrenador BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('fithub.entrenador_identrenador_seq'::regclass),
    descripcion CHARACTER VARYING(500),
    especialidad CHARACTER VARYING(200),
    foto_perfil CHARACTER VARYING(255),
    delete boolean,
    servicios character varying(500),
    empresa_idempresa BIGINT,
    usuario_idusuario BIGINT NOT NULL,
    CONSTRAINT usuario_idusuario FOREIGN KEY (usuario_idusuario)  REFERENCES fithub.usuario (idusuario),
    CONSTRAINT empresa_idempresa FOREIGN KEY (empresa_idempresa)  REFERENCES fithub.empresa (idempresa)
);

ALTER TABLE fithub.entrenador OWNER TO postgres;
drop table fithub.entrenador;
--SECUENCIA TABLA CODIGOPROMOCIONAL
CREATE SEQUENCE fithub.codigopromocinal_idcodigopromocional_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
-- TABLA CODIGOPROMOCIONAL
CREATE TABLE fithub.codigopromocional(
    idcodigopromocional BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('fithub.codigopromocinal_idcodigopromocional_seq'),
    nombre CHARACTER VARYING(100),
    descripcion CHARACTER VARYING(200),
    caducidad DATE,
    estado CHARACTER VARYING(50)
);

ALTER TABLE fithub.codigopromocional OWNER TO postgres;
--SECUENCIA TABLA SUSCRIPCION
CREATE SEQUENCE fithub.suscripcion_idsuscripcion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
--TABLA SUSCRIPCION
CREATE TABLE fithub.suscripcion(
    idsuscripcion BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('fithub.suscripcion_idsuscripcion_seq'),
    nombre CHARACTER VARYING(100),
    descripcion CHARACTER VARYING(200),
    precio INT,
    visibilidad INT,
    automatizado BOOLEAN,
    periocidad INT,
    borrado BOOLEAN,
    codigopromocional_idcodigopromocional BIGINT,
    entrenador_identrenador BIGINT NOT NULL ,
    CONSTRAINT entrenador_identrenador FOREIGN KEY (entrenador_identrenador)  REFERENCES fithub.entrenador (identrenador),
    CONSTRAINT codigopromocional_idcodigopromocional FOREIGN KEY (codigopromocional_idcodigopromocional) REFERENCES  fithub.codigopromocional(idcodigopromocional)
);

select * from fithub.usuario;


