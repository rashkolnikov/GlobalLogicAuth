drop table user_base;
drop table phone;

create table user_base(
    id SERIAL primary key ,
    uuid        varchar(64)  default '0' not null,
    name        varchar(255) default null,
    email       varchar(320) default '0' not null,
    password    varchar(255) default '0' not null,
    created     timestamp    default CURRENT_TIMESTAMP not null,
    last_login  timestamp    default null,
    is_active   boolean      default true not null,
    unique (uuid),
    unique (email)
);

create table phone(
    id SERIAL primary key ,
    uuid         varchar(64)  default '0' not null,
    number       BIGINT       default 0 not null,
    city_code    int          default 0 not null,
    country_code varchar(8)   default '0' not null,
    foreign key (uuid) references user_base (uuid)
);