CREATE TABLE users (
    id serial primary key,
    email varchar(255) not null unique,
    username varchar(255) not null unique,
    password varchar(255) not null,
    role varchar(20),
    state varchar(20)
);

create table posts (
      post_id serial primary key,
      author_id integer not null references users,
      tags text not null,
      theme text not null,
      body text not null,
      posted timestamp,
      rate numeric
);

create table images (
    image_id serial primary key,
    post_id  integer references posts,
    link text
);

create table comments (
    comment_id serial primary key,
    post_id integer not null references posts,
    author_id  integer not null references users,
    comment text not null,
    posted timestamp
);