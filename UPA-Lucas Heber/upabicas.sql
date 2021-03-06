PGDMP     +    &            	    v            upabicas    10.5    10.4                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    25084    upabicas    DATABASE     �   CREATE DATABASE upabicas WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE upabicas;
             postgres    false                       0    0    DATABASE upabicas    COMMENT     F   COMMENT ON DATABASE upabicas IS 'Atividade Sala Virtual - UPA Bicas';
                  postgres    false    2827                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false                       0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    25085    atendimento    TABLE     �   CREATE TABLE public.atendimento (
    id bigint NOT NULL,
    data date,
    receita character varying(255),
    recomendacoes character varying(255)
);
    DROP TABLE public.atendimento;
       public         postgres    false    3            �            1259    25113    atendimento_seq    SEQUENCE     x   CREATE SEQUENCE public.atendimento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.atendimento_seq;
       public       postgres    false    3            �            1259    25093    medico    TABLE     �   CREATE TABLE public.medico (
    id bigint NOT NULL,
    area character varying(255),
    crm character varying(255),
    nome character varying(255),
    salario double precision,
    telefone character varying(255)
);
    DROP TABLE public.medico;
       public         postgres    false    3            �            1259    25115 
   medico_seq    SEQUENCE     s   CREATE SEQUENCE public.medico_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.medico_seq;
       public       postgres    false    3            �            1259    25101    paciente    TABLE     �   CREATE TABLE public.paciente (
    id bigint NOT NULL,
    cpf character varying(255),
    datanascimento date,
    endereco character varying(255),
    nome character varying(255),
    sexo character varying(255),
    telefone character varying(255)
);
    DROP TABLE public.paciente;
       public         postgres    false    3            �            1259    25117    paciente_seq    SEQUENCE     u   CREATE SEQUENCE public.paciente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.paciente_seq;
       public       postgres    false    3                       0    25085    atendimento 
   TABLE DATA               G   COPY public.atendimento (id, data, receita, recomendacoes) FROM stdin;
    public       postgres    false    196   �                 0    25093    medico 
   TABLE DATA               H   COPY public.medico (id, area, crm, nome, salario, telefone) FROM stdin;
    public       postgres    false    197   �                 0    25101    paciente 
   TABLE DATA               [   COPY public.paciente (id, cpf, datanascimento, endereco, nome, sexo, telefone) FROM stdin;
    public       postgres    false    198                     0    0    atendimento_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.atendimento_seq', 1, false);
            public       postgres    false    199                       0    0 
   medico_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('public.medico_seq', 4, true);
            public       postgres    false    200                       0    0    paciente_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.paciente_seq', 1, false);
            public       postgres    false    201            ~
           2606    25092    atendimento atendimento_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.atendimento
    ADD CONSTRAINT atendimento_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.atendimento DROP CONSTRAINT atendimento_pkey;
       public         postgres    false    196            �
           2606    25100    medico medico_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.medico
    ADD CONSTRAINT medico_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.medico DROP CONSTRAINT medico_pkey;
       public         postgres    false    197            �
           2606    25108    paciente paciente_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.paciente DROP CONSTRAINT paciente_pkey;
       public         postgres    false    198            �
           2606    25110 #   medico uk_4mxaf7akak8hrpgyr1u46yw1k 
   CONSTRAINT     ]   ALTER TABLE ONLY public.medico
    ADD CONSTRAINT uk_4mxaf7akak8hrpgyr1u46yw1k UNIQUE (crm);
 M   ALTER TABLE ONLY public.medico DROP CONSTRAINT uk_4mxaf7akak8hrpgyr1u46yw1k;
       public         postgres    false    197            �
           2606    25112 %   paciente uk_6r0tguf5bfegf5k0dbbg7jryl 
   CONSTRAINT     _   ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT uk_6r0tguf5bfegf5k0dbbg7jryl UNIQUE (cpf);
 O   ALTER TABLE ONLY public.paciente DROP CONSTRAINT uk_6r0tguf5bfegf5k0dbbg7jryl;
       public         postgres    false    198                   x������ � �         L   x�3�t�,*-J�L��442615��)MN,V�HMJ-�4700�46R0SS.��:�8�M,-Q���qqq �9�            x������ � �     