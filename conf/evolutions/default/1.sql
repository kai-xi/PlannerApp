# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table planner_display (
  id                        bigint not null,
  constraint pk_planner_display primary key (id))
;

create table task (
  id                        bigint not null,
  title                     varchar(255),
  task_type                 varchar(255),
  start_day                 timestamp,
  end_time                  timestamp,
  place                     varchar(255),
  notes                     varchar(255),
  constraint pk_task primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create sequence planner_display_seq;

create sequence task_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists planner_display;

drop table if exists task;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists planner_display_seq;

drop sequence if exists task_seq;

drop sequence if exists user_seq;

