create table s_user
(
    user_id          varchar(100) not null,
    user_name        varchar(20)  null,
    password         varchar(100) null,
    create_date      datetime     not null,
    last_update_date datetime     null,
    constraint s_user_user_id_uindex
        unique (user_id)
);

alter table s_user
    add primary key (user_id);

INSERT INTO myAccount.s_user (user_id, user_name, password, create_date, last_update_date) VALUES ('1', 'maochd', '$2a$10$5zepxvK3sMQSscBasilAe.ZTWmrzmr9RQOy9.koSaOcM2hIPafqIy', '2020-12-27 15:04:56', '2020-12-27 15:04:59');
INSERT INTO myAccount.s_user (user_id, user_name, password, create_date, last_update_date) VALUES ('2', 'admin', '$2a$10$5zepxvK3sMQSscBasilAe.ZTWmrzmr9RQOy9.koSaOcM2hIPafqIy', '2020-12-27 15:05:24', '2020-12-27 15:05:29');