create table account
(
    id           integer generated always as identity,
    username     varchar(255) not null,
    password     varchar(255) not null,
    account_role varchar(255) not null,
    constraint account_pk
        primary key (id),
    constraint account_pk_2
        unique (username)
);

create table admin_table
(
    id integer not null,
    constraint admin_table_pk
        primary key (id),
    constraint admin_table_account_id_fk
        foreign key (id) references account
);

create table app_user
(
    id       integer not null,
    premium  boolean default false,
    explicit boolean default false,
    constraint app_user_pk
        primary key (id),
    constraint app_user_account_id_fk
        foreign key (id) references account
);

create table artist
(
    id          integer not null,
    description varchar,
    constraint artist_pk
        primary key (id),
    constraint artist_account_id_fk
        foreign key (id) references account
);



create table playlist
(
    id          integer generated always as identity,
    title       varchar(255),
    description varchar,
    constraint playlist_pk
        primary key (id)
);


create table track
(
    id       integer generated always as identity,
    title    varchar,
    explicit boolean default true,
    premium  boolean default false,
    constraint track_pk
        primary key (id)
);

create table user2artist
(
    user_id   integer,
    artist_id integer,
    constraint user2artist_app_user_id_fk
        foreign key (user_id) references app_user,
    constraint user2artist_artist_id_fk
        foreign key (artist_id) references artist
);

create table artist2track
(
    track_id  integer,
    artist_id integer,
    constraint artist2track_track_id_fk
        foreign key (track_id) references track,
    constraint artist2track_artist_id_fk
        foreign key (artist_id) references artist
);

create table playlist2user
(
    user_id     integer,
    playlist_id integer,
    constraint playlist2user_app_user_id_fk
        foreign key (user_id) references app_user,
    constraint playlist2user_playlist_id_fk
        foreign key (playlist_id) references playlist
);


create table playlist2owner
(
    playlist_id integer,
    owner_id    integer,
    constraint playlist2owner_playlist_id_fk
        foreign key (playlist_id) references playlist,
    constraint playlist2owner_app_user_id_fk
        foreign key (owner_id) references app_user
);

create table playlist2track
(
    track_id    integer,
    playlist_id integer,
    constraint playlist2track_track_id_fk
        foreign key (track_id) references track,
    constraint playlist2track_playlist_id_fk
        foreign key (playlist_id) references playlist
);
