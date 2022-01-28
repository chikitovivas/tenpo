CREATE TABLE users (
   id bigint  DEFAULT nextval('users_id_seq'),
   username text,
   password text,
   PRIMARY KEY( id )
);

CREATE TABLE auth (
  id bigint DEFAULT nextval('auth_id_seq'),
  user_id bigint not null,
  token varchar not null,
  expiration_date timestamp not null,
  active boolean default true,
  PRIMARY KEY( id )
);

CREATE TABLE log (
 id bigint DEFAULT nextval('log_id_seq'),
 request varchar,
 response varchar,
 date timestamp,
 url varchar not null,
 PRIMARY KEY( id )
);